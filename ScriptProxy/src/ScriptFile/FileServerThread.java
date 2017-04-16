/**    
 * �ļ�����ClientSendFile.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��12��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package ScriptFile;

import java.io.File;

import Config.ScriptConfig;

/**    
 *     
 * ��Ŀ���ƣ�ScriptProxy    
 * �����ƣ�ClientSendFile    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��12�� ����7:53:17    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��12�� ����7:53:17    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class FileServerThread {

public void StartSend(String file)
{
    //�����Ҫ���Է����߳���
    SendFile send=new SendFile(file);
    send.run();
}
/*
 * ��������˽����ļ�
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
    //����һ������ֵ
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
