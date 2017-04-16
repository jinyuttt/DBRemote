/**    
 * �ļ�����ReceiveFile.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��12��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package ScriptFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import Config.ScriptConfig;
import udt.UDTInputStream;
import udt.UDTOutputStream;
import udt.UDTReceiver;
import udt.UDTServerSocket;
import udt.UDTSocket;
import udt.util.Util;

/**    
 *     
 * ��Ŀ���ƣ�ScriptProxy    
 * �����ƣ�ReceiveFile    
 * ��������    �����ļ�
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��12�� ����9:31:16    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��12�� ����9:31:16    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class ReceiveFile extends Application{
    private final ExecutorService threadPool=Executors.newFixedThreadPool(ScriptConfig.FitTaskNum);
    private final ExecutorService cacheThread=Executors.newCachedThreadPool();
    private NumberFormat format;
    private static final AtomicInteger taskNum=new AtomicInteger(0);
    private int runTimes=0;//�ܴ���,����Ҫ��ȷ
    private long sumTime=0;//��ʱ�䣬����Ҫ��ȷ
    private final Date StartDate=new Date();
    private long fitTime=0;
    private volatile boolean countRun=true;
    private volatile int sumTaskNum=0;
    
    /*
     * ������չ���
     */
  public  class Receive implements Runnable
    {
      UDTSocket socket=null;
      private String localFile;
       public Receive(UDTSocket client)
       {
           socket=client;
           format=NumberFormat.getNumberInstance();
           format.setMaximumFractionDigits(3);
       }
       /*
        * �жϴ��̴�С
        */
       private boolean CheckDisk(long cursize)
       {
           File froot=new File(ScriptConfig.FileSavePath);
           long  freePartitionSpace= froot.getFreeSpace();
           long fM=    freePartitionSpace/(1024 *1024);
         if(fM<ScriptConfig.MinFreeSpace+cursize/1024)
         {
             return false;
         }
        return true;
           
       }
       /*
        * �жϵȴ��߳��Ƿ����
        */
       private boolean CheckThread()
       {
           int cur=taskNum.get();
           if(sumTaskNum-cur>0)
           {
               return false;
           }
          return true;
       }
        @Override
        public void run() {
            taskNum.incrementAndGet();
            configure();
            verbose=true;
            long Start=0;
            if(countRun)
            {
            runTimes++;
            Start=System.currentTimeMillis();
            }
            try{
                
                 //UDTReceiver.connectionExpiryDisabled=true;
                 UDTInputStream in=socket.getInputStream();
                 UDTOutputStream out=socket.getOutputStream();
                 byte[]readBuf=new byte[32768];
                 ByteBuffer bb=ByteBuffer.wrap(readBuf);

                 //read file name info 
                 while(in.read(readBuf)==0)Thread.sleep(100);
                 System.out.println("��������ļ���Ϣ");
                 //how many bytes to read for the file name
                 byte[]nameLen=new byte[4];
                 bb.get(nameLen);
                 long length=decode(nameLen, 0);
                 byte[]fileName=new byte[(int)length];
                 bb.get(fileName);
                 byte[] fileLen=new byte[8];//long 8�ֽ�
                 bb.get(fileLen);
                 long fsize=decode(fileLen,0);
                 localFile=new String(fileName);
                 System.out.println("�ļ���"+localFile);
               // Boolean devNull=Boolean.getBoolean("udt.dev.null");
//                if(devNull){
//                    while(true)Thread.sleep(10000);
//                }
                 //
                 byte[] call=new byte[2];//1�������գ�2�ӳ٣�3�ܾ�
                
                 if(!CheckDisk(fsize))
                 {
                     //���ܽ���
                      call[0]=3;
                      out.write(call);
                      out.flush();
                      try{
                       socket.close();
                      }
                      finally
                      {
                          
                      }
                       return;
                 }
                 if(CheckThread())
                 {
                    //ֻ���߳�������ӳ�
                     call[0]=2;
                     if(fitTime>0)
                     {
                         long r=(sumTaskNum-taskNum.get())*fitTime/10;
                         if(r<255)
                         {
                             call[1]=(byte) r;
                         }
                         else
                         {
                             call[1]=(byte) 200;
                         }
                     }
                     else
                     {
                         call[1]=1;
                     }
                     out.write(call);
                     out.flush();
                 }
                 else
                 {
                 call[1]=2;
                 out.write(call);
                 out.flush();
                 }
                 System.out.println("�ļ�׼��д��");
               //����һ������
                 SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMddHHmmss"); //��ʽ����ǰϵͳ����
                 String dateTime = dateFm.format(new java.util.Date())+"_";
                 int index=localFile.lastIndexOf(".");
                 String fileReal=localFile.substring(0, index-1);
                 File file=new File(ScriptConfig.FileSavePath+"/"+fileReal+"."+ScriptConfig.TmpFile);
                 String dest="";
                 if(ScriptConfig.RFile==null||ScriptConfig.RFile.isEmpty())
                 {
                     dest=ScriptConfig.FilePath+"/"+dateTime+localFile;
                 }
                 else
                     {
                      dest=ScriptConfig.FilePath+"/"+dateTime+fileReal+"."+ScriptConfig.RFile;
                     }
                FileOutputStream fos=new FileOutputStream(file);
                OutputStream os=new BufferedOutputStream(fos,1024*1024);
                try{
                   
                    long start = System.currentTimeMillis();
                    Util.copy(in, os, fsize, true);
                    System.out.println("Fineshed Recvie File");
                    System.out.println(" File Size:"+fsize);
                    long end = System.currentTimeMillis();
                    double rate=1000.0*fsize/1024/1024/(end-start);
                    System.out.println("[ReceiveFile] Rate: "+format.format(rate)+" MBytes/sec. "
                            +format.format(8*rate)+" MBit/sec.");
                    
                    socket.close();
                    fos.close();
                    file.renameTo(new File(dest));
                }finally{
                    fos.close();
                }  
                
            }catch(Exception ex){
                throw new RuntimeException(ex);
            }
            taskNum.decrementAndGet();
            sumTaskNum--;
            if(countRun)
            {
               sumTime+=(System.currentTimeMillis()-Start)/1000;
            }
        }
        
    }
    /*
     * �����������
     */
public long  betweenDays(Date beginDate,Date endDate)
{
    long beginTime = beginDate.getTime(); 
    long endTime= endDate.getTime(); 
    long betweenDays = (long)((endTime - beginTime) / (1000 * 60 * 60 *24) + 0.5); 
    return betweenDays;
}

    @Override
    public void run() {
        configure();
        try{
            UDTReceiver.connectionExpiryDisabled=true;
            localIP=ScriptConfig.RemoteAddr;
            InetAddress myHost=localIP!=null?InetAddress.getByName(localIP):InetAddress.getLocalHost();
            int serverPort=ScriptConfig.RemotePort;
            UDTServerSocket server=new UDTServerSocket(myHost,serverPort);
            boolean isRun=ScriptConfig.RunServer;
            while(isRun){
                isRun=ScriptConfig.RunServer;
                System.out.println("׼������");
                UDTSocket socket=server.accept();
                Thread.sleep(1000);
                if(countRun)
                {
                    //��ʱ��ͳ��
                 if(betweenDays(StartDate,new Date())>7&&runTimes>7*3)
                 {
                    countRun=false;
                    fitTime=sumTime/runTimes;
                 }
                }
               //�����̳߳���ִ��,�������������������ύ�ļ�
                 //Ԥ���߷�
                System.out.println("��ӵ�����");
                if(taskNum.get()<ScriptConfig.ThreadPoolMaxSize)
                {
                  cacheThread.execute(new Receive(socket));
                }
                else
                {
                   threadPool.execute(new Receive(socket));
                }
                sumTaskNum++;
               
            }
            //
            threadPool.shutdown();
            cacheThread.shutdown();
            System.out.println("ֹͣ�����ļ�");
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
        
    }

}
