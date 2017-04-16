package DBProxy;

import LogInfo.LogInfo;
import LogRecord.LogLevel;
import LogRecord.LogRecordObj;
import ProcessMessage.IInerMessage;

public class ProcessInnerMsg  implements IInerMessage{
	FrmServer  frmLog=null;
	@Override
	public void notify(Object arg0, String arg1, Object arg2, String arg3) {
	
		if(frmLog!=null)
		{
			frmLog.AddLog(arg2);
		}
		  LogInfo info=(LogInfo)arg2;
		  if(info!=null)
		  {
		      LogRecordObj.GetInstance().recordMessage(LogLevel.INFO, info.logMsg);
		  }
	}
	public void SetObj(FrmServer frm)
	{
		frmLog=frm;
	}

}
