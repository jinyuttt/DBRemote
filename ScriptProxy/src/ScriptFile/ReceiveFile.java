/**    
 * 文件名：ReceiveFile.java    
 *    
 * 版本信息：    
 * 日期：2017年3月12日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
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
 * 项目名称：ScriptProxy    
 * 类名称：ReceiveFile    
 * 类描述：    接收文件
 * 创建人：jinyu    
 * 创建时间：2017年3月12日 下午9:31:16    
 * 修改人：jinyu    
 * 修改时间：2017年3月12日 下午9:31:16    
 * 修改备注：    
 * @version     
 *     
 */
public class ReceiveFile extends Application{
    private final ExecutorService threadPool=Executors.newFixedThreadPool(ScriptConfig.FitTaskNum);
    private final ExecutorService cacheThread=Executors.newCachedThreadPool();
    private NumberFormat format;
    private static final AtomicInteger taskNum=new AtomicInteger(0);
    private int runTimes=0;//总次数,不需要精确
    private long sumTime=0;//总时间，不需要精确
    private final Date StartDate=new Date();
    private long fitTime=0;
    private volatile boolean countRun=true;
    private volatile int sumTaskNum=0;
    
    /*
     * 处理接收过程
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
        * 判断磁盘大小
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
        * 判断等待线程是否过量
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
                 System.out.println("接收完成文件信息");
                 //how many bytes to read for the file name
                 byte[]nameLen=new byte[4];
                 bb.get(nameLen);
                 long length=decode(nameLen, 0);
                 byte[]fileName=new byte[(int)length];
                 bb.get(fileName);
                 byte[] fileLen=new byte[8];//long 8字节
                 bb.get(fileLen);
                 long fsize=decode(fileLen,0);
                 localFile=new String(fileName);
                 System.out.println("文件："+localFile);
               // Boolean devNull=Boolean.getBoolean("udt.dev.null");
//                if(devNull){
//                    while(true)Thread.sleep(10000);
//                }
                 //
                 byte[] call=new byte[2];//1正常接收，2延迟，3拒绝
                
                 if(!CheckDisk(fsize))
                 {
                     //不能接收
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
                    //只是线程问题就延迟
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
                 System.out.println("文件准备写入");
               //处理一下名称
                 SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMddHHmmss"); //格式化当前系统日期
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
     * 日期相差天数
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
                System.out.println("准备接收");
                UDTSocket socket=server.accept();
                Thread.sleep(1000);
                if(countRun)
                {
                    //做时间统计
                 if(betweenDays(StartDate,new Date())>7&&runTimes>7*3)
                 {
                    countRun=false;
                    fitTime=sumTime/runTimes;
                 }
                }
               //放在线程池中执行,任务服务端有能力处理提交文件
                 //预备高峰
                System.out.println("添加到任务");
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
            System.out.println("停止接收文件");
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
        
    }

}
