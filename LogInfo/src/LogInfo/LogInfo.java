package LogInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 日志对象
 */
public class LogInfo {
	
/**
 * 日志类别
 */
 public	LogType logType;
 
 /**
  * 日志信息（优先使用）
  */
 public String logMsg;
 
 /**
  * 日志对象
  */
 public Object logObj;
 
 /**
  * 日志时间
  */
 public Date logTime=new Date();
 
 /**
  * 可以为信息打上唯一识别信息，比如名称
  * 否则需要判断时自己处理
  */
 public String logFlage="";
 
 /**
  * 运行测试信息
  * @return
  */
 public boolean isAllowTest()
 {
	 return LogConfig.Is_Test;
 }
 /**
  * 判断是不是字符串信息
  * @return
  */
 public boolean isMsgString()
 {
	 if(logMsg==null||logMsg.isEmpty())
	 {
		 return false;
	 }
	 else
	 {
		 return true;
	 }
 }
 public String toString()
 {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(logTime);
        String printMsg= String.format("日志时间：%s 日志类别：%s 日志内容：%s", dateString, logType,logMsg);
	    return printMsg;
	 
 }
}
