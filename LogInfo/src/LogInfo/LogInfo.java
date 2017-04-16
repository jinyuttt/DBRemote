package LogInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * ��־����
 */
public class LogInfo {
	
/**
 * ��־���
 */
 public	LogType logType;
 
 /**
  * ��־��Ϣ������ʹ�ã�
  */
 public String logMsg;
 
 /**
  * ��־����
  */
 public Object logObj;
 
 /**
  * ��־ʱ��
  */
 public Date logTime=new Date();
 
 /**
  * ����Ϊ��Ϣ����Ψһʶ����Ϣ����������
  * ������Ҫ�ж�ʱ�Լ�����
  */
 public String logFlage="";
 
 /**
  * ���в�����Ϣ
  * @return
  */
 public boolean isAllowTest()
 {
	 return LogConfig.Is_Test;
 }
 /**
  * �ж��ǲ����ַ�����Ϣ
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
        String printMsg= String.format("��־ʱ�䣺%s ��־���%s ��־���ݣ�%s", dateString, logType,logMsg);
	    return printMsg;
	 
 }
}
