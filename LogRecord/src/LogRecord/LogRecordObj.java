/**    
 * 文件名：LogRecordObj.java    
 *    
 * 版本信息：    
 * 日期：2017年3月14日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
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
 * 项目名称：LogRecord    
 * 类名称：LogRecordObj    
 * 类描述：    记录本地日志
 * 创建人：jinyu    
 * 创建时间：2017年3月14日 上午2:26:38    
 * 修改人：jinyu    
 * 修改时间：2017年3月14日 上午2:26:38    
 * 修改备注：    
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
            System.out.println("日志配置文件不在");
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
     * 记录日志
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
