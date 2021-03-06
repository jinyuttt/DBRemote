package DBSqlManager;

import Common.JsonConvert;
import DDS_Transfer.IDDS_Protocol;
import DDS_Transfer.IRecMsg;
import DataStruct.SqlRequest;
import LogInfo.LogInfo;
import LogInfo.LogType;
import ProcessMessage.InnerMessage;

public class RecviceQuest implements IRecMsg {
    IDDS_Protocol obj = null;
    ProcessQuest questProcess=null;
    @Override
    public void RecData(String address, byte[] data) {
    	//
     LogInfo log=new LogInfo();
   	 if(log.isAllowTest())
   	 {
   		 log.logType=LogType.Test;
   		 if(data!=null)
   		 {
   		   log.logMsg="接收到字节:"+data.length;
   		   if(data.length==0)
   		   {
   		    obj.ClientSocketClose();
   		   }
   		 }
   		 else
   		 {
   		     if(obj!=null)
   		     {
   		         obj.ClientSocketClose();
   		     }
   			 log.logMsg="接收到空字节";
   			  return;
   		 }
   	   InnerMessage.getInstance().PostMessage(this, "UIMsg", log);
   	 }
        SqlRequest requet = JsonConvert.ConvertToObj(data, SqlRequest.class);
        if(questProcess==null)
        {
        	questProcess=new ProcessQuest();
        }
        questProcess.Process(requet, obj);
    }

    @Override
    public void SaveInstance(Object call) {
        obj = (IDDS_Protocol) call;

    }

}
