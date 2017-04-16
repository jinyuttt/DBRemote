/**    
 * 文件名：SendFile.java    
 *    
 * 版本信息：    
 * 日期：2017年3月12日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package ScriptFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.text.NumberFormat;
import java.util.logging.Logger;

import Config.ScriptConfig;
import udt.UDTClient;
import udt.UDTInputStream;
import udt.UDTOutputStream;
import udt.util.Util;

/**    
 *     
 * 项目名称：ScriptProxy    
 * 类名称：SendFile    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年3月12日 下午9:21:00    
 * 修改人：jinyu    
 * 修改时间：2017年3月12日 下午9:21:00    
 * 修改备注：    
 * @version     
 *     
 */
public class SendFile  extends Application{
     
    String fileName;
    public SendFile(String file)
    {
        fileName=file;
    }
    public  class  SendRunner implements Runnable{

        private Logger logger=Logger.getLogger(SendRunner.class.getName());

        private UDTClient socket;

        private final NumberFormat format=NumberFormat.getNumberInstance();

        private final boolean memMapped;
        public SendRunner(UDTClient socket){
            this.socket=socket;
            format.setMaximumFractionDigits(3);
            memMapped=false;//true;
        }
       
        /*
         * 发送文件
         * 一般磁盘 30M-60M/s
         * 网线30M
         * 按照30M/s传送
         *         
         */
        private void UtilCopyFile(InputStream source, OutputStream target)
        {
            byte[]buf=new byte[40*65536];//10M左右
            int c = 0;
            long read=0;
            while(true){
                try {
                    c=source.read(buf);
                } catch (IOException e) {
            
                    e.printStackTrace();
                }
                if(c<0)break;
                read+=c;
                try {
                    target.write(buf, 0, c);
                    target.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                 
            }
            System.out.println("send file size:"+read);
            
        }
        @Override
        public void run(){
            try{
                
                UDTInputStream in=socket.getInputStream();
                UDTOutputStream out=socket.getOutputStream();
                //send file name info
                File file=new File(new String(fileName));
                 long flen=file.length();
                 byte[]fsize=encode(flen);
                 byte[]fName=file.getName().getBytes();
                 byte[]nameinfo=new byte[fName.length+fsize.length+4];
                 //文件名称长度
                 System.arraycopy(encode(fName.length), 0, nameinfo, 0, 4);
                 //文件名称
                 System.arraycopy(fName, 0, nameinfo, 4, fName.length);
                  //文件大小
                 System.arraycopy(fsize, 0, nameinfo, fName.length+4,fsize.length);
              
                 //pause the sender to save some CPU time
                 //out.pauseOutput();
                // 接收服务端状态
                 socket.send(nameinfo);
                 socket.flush();
                 byte[]sizeInfo=new byte[2];
                 byte total=0;
                 
                  while(true){
                      int r=in.read(sizeInfo);
                   if(r<0)break;
                   else if(r==0)
                   {
                       Thread.sleep(300);
                       total++;
                   }
                   else
                   {
                      
                       break;
                   }
                   if(total>4)
                   {
                    //超过1秒则失败；
                       logger.info("文件传输失败，服务端无法接收");
                       return;
                   }
               }
                 if(sizeInfo[0]==0)
                 {
                     //说明没有接收数据
                     return;
                 }
                 else
                 {
                     if(sizeInfo[0]==2)//正常执行
                     {
                          //读取后一字节
                         Thread.sleep(sizeInfo[1]*1000*10);//需要暂停10秒的整数倍
                     }
                     else if(sizeInfo[0]==3)
                     {
                        //返回不发送
                         System.out.println("不能发送文件："+fileName);
                         return;
                     }
                 }
                 //
                 FileInputStream fis=null;
                try{
                    long size=file.length();
                    long start=System.currentTimeMillis();
                    //and send the file
                    System.out.println("发送文件："+fileName);
                    if(memMapped){
                        copyFile(file,out);
                    }else{
                        fis=new FileInputStream(file);
                        if(size>30*1024*1024)
                        {
                            //大于30M
                            System.out.println("大于30M文件，另外传送");
                            UtilCopyFile(fis,out);
                        }
                        else
                        {
                            Util.copy(fis, out, size, false);
                        }
                      
                    }
                    System.out.println("[SendFile] Finished sending data.");
                    System.out.println("[SendFile]  File size:"+size);
                    long end=System.currentTimeMillis();
                    System.out.println(socket.getStatistics().toString());
                    double rate=1000.0*size/1024/1024/(end-start);
                    System.out.println("[SendFile] Rate: "+format.format(rate)+" MBytes/sec. "+format.format(8*rate)+" MBit/sec.");
                    if(Boolean.getBoolean("udt.sender.storeStatistics")){
                        socket.getStatistics().writeParameterHistory(new File("udtstats-"+System.currentTimeMillis()+".csv"));
                    }
                    if(fis!=null)fis.close();
                    socket.shutdown();
                }
                catch(Exception ex)
                {
                    if(fis!=null)fis.close();
                    socket.shutdown();
                }
                finally{
                  
                    socket=null;
                }
               logger.info("结束文件传输："+fileName);
            }catch(Exception ex){
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    
    }

    @Override
    public void run() {
          int serverPort=ScriptConfig.RemotePort;
          String serverHost=ScriptConfig.RemoteAddr;
        InetAddress myHost=null;
     
            try {
                myHost = localIP!=null?InetAddress.getByName(localIP):InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
            
                e.printStackTrace();
            }
        
            UDTClient client=null;
           
            try {
                client = localPort!=-1?new UDTClient(myHost,localPort):new UDTClient(myHost);
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
              
                e.printStackTrace();
            }
       
            try {
                client.connect(serverHost, serverPort);
                //隔离一层
                Thread sendRun=new Thread(new SendRunner(client));
                sendRun.setDaemon(true);
                sendRun.setName("sendFile_"+fileName);
                sendRun.start();
            } catch (UnknownHostException e) {
               
                e.printStackTrace();
            } catch (InterruptedException e) {
             
                e.printStackTrace();
            } catch (IOException e) {
             
                e.printStackTrace();
            }
       
        
    }
    private static void copyFile(File file, OutputStream os)throws Exception{
        @SuppressWarnings("resource")
        FileChannel c=new RandomAccessFile(file,"r").getChannel();
        MappedByteBuffer b=c.map(MapMode.READ_ONLY, 0, file.length());
        b.load();
        byte[]buf=new byte[1024*1024];
        int len=0;
        while(true){
            len=Math.min(buf.length, b.remaining());
            b.get(buf, 0, len);
            os.write(buf, 0, len);
            if(b.remaining()==0)break;
        }
        os.flush();
    }   

}
