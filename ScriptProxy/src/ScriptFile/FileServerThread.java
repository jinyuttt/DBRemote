/**    
 * 文件名：ClientSendFile.java    
 *    
 * 版本信息：    
 * 日期：2017年3月12日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package ScriptFile;

import java.io.File;

import Config.ScriptConfig;

/**    
 *     
 * 项目名称：ScriptProxy    
 * 类名称：ClientSendFile    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年3月12日 下午7:53:17    
 * 修改人：jinyu    
 * 修改时间：2017年3月12日 下午7:53:17    
 * 修改备注：    
 * @version     
 *     
 */
public class FileServerThread {

public void StartSend(String file)
{
    //如果需要可以放在线程中
    SendFile send=new SendFile(file);
    send.run();
}
/*
 * 启动服务端接收文件
 */
public void runServer(){
   
    File dir=new File(ScriptConfig.FileSavePath);
    if(!dir.exists())
    {
        dir.mkdirs();
    }
     dir=new File(ScriptConfig.FilePath);
    if(!dir.exists())
    {
        dir.mkdirs();
    }
    //计算一个合理值
    ScriptConfig.FitTaskNum=(int) (Runtime.getRuntime().availableProcessors()*1.5);
    Thread rec=new Thread(new ReceiveFile());
    rec.setDaemon(true);
    rec.setName("filerecvice");
    rec.start();
          
   
}
public static void StopServer()
{
    ScriptConfig.RunServer=false;
}
}
