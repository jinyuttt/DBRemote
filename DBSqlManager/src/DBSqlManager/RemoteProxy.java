package DBSqlManager;

import DDS_Transfer.IDDS_Protocol;
import DDS_Transfer.IRecMsg;
import ProtocolsManager.ProtocolManager;

public class RemoteProxy {
    IDDS_Protocol protocol = null;

    public void init(String addr) {
        try {
            IRecMsg rec = new RecviceQuest();
            protocol = (IDDS_Protocol) ProtocolManager.getInstance().CreateObject("udt");
            protocol.RecData(addr, rec);
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
