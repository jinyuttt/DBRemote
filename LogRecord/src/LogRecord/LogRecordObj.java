/**    
 * �ļ�����LogRecordObj.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��14��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package LogRecord;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

/**    
 *     
 * ��Ŀ���ƣ�LogRecord    
 * �����ƣ�LogRecordObj    
 * ��������    ��¼������־
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��14�� ����2:26:38    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��14�� ����2:26:38    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class LogRecordObj {
    private  static Logger logger= LogManager.getLogger(LogRecordObj.class);
    private static LogRecordObj instance=null;
    public static LogRecordObj GetInstance()
    {
        if(instance==null)
        {
            instance=new LogRecordObj();
        }
        return instance;
    }
   public  LogRecordObj()
    {
       ConfigurationSource source = null;
    try {
        File f=new File("log4j2.xml");
        if(!f.exists())
        {
            System.out.println("��־�����ļ�����");
            return;
        }
        FileInputStream fs=new FileInputStream(f);
       
        source = new ConfigurationSource(fs);
    } catch (FileNotFoundException e) {
       
        e.printStackTrace();
    } catch (IOException e) {
     
        e.printStackTrace();
    }     
       Configurator.initialize(null, source);    
       
    }
    public void  recordWarn(Object message)
    {
        logger.warn(message);
    }
    public void  recordFatal(Object message)
    {
        logger.fatal(message);
    }
    public void  recordInfo(Object message)
    {
        logger.info(message);
    }
    public void  recordDebug(Object message)
    {
        logger.debug(message);
    }
    public void  recordError(Object message)
    {
        logger.error(message);
    }
    /*
     * ��¼��־
     */
    public void  recordMessage(LogLevel level,  Object message)
    {
      
      switch(level)
      {
       case INFO:
          recordInfo(message);
          break;
       case ERROR:
           recordError(message);
           break;
       case FATAL:
           recordFatal(message);
           break;
       case DEBUG:
           recordDebug(message);
           break;
       case WARN:
           recordWarn(message);
           break;
       default:
           recordInfo(message);
           break;
          
      }
    }
}
