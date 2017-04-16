package ServerProxy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import Common.JsonConvert;
import DBError.ErrorCode;
import DBManager.ClientRecvice;
import DBManager.SqlDataReader;
import DDS_Transfer.IDDS_Protocol;
import DataStruct.DBResult;
import DataStruct.MethodType;
import DataStruct.SqlRequest;
import ProtocolsManager.ProtocolManager;


public class Proxy {
 private   IDDS_Protocol protocol = null;
 private  String addr="";
 private  int port=0;
 public boolean is_MD5=false;//加密
 public boolean isClose=false;//是否关闭
 private int remoteID=-1;
 private String flage="";
 private SqlDataReader reader=null;
 private ExecutorService service=Executors.newCachedThreadPool();
 private AtomicInteger syquery=new AtomicInteger(0);
    public Proxy(IDDS_Protocol protos)
    {
        //设置连接端口
        protocol=protos;
    }
    public Proxy(IDDS_Protocol protos,String remoteaddr,int port)
    {
        addr=remoteaddr;
        this.port=port;
        //设置连接端口
        protocol=protos;
    }
    public Proxy(String remoteaddr,int port) {
        
        addr=remoteaddr;
       this.port=port;
        //创建连接端口
        if (protocol == null) {
            try {
                protocol = (IDDS_Protocol) ProtocolManager.getInstance().CreateObject("udt");
            } catch (InstantiationException e) {

                e.printStackTrace();
            } catch (IllegalAccessException e) {

                e.printStackTrace();
            }
        }
    }
    
    /*
     * 获取下一组数据
     */
    public void getNextReader(int id, String flage,SqlDataReader reader)
    {
        syquery.incrementAndGet();
        this.remoteID=id;
        this.flage=flage;
        this.reader=reader;
        QueryData();
        reader.isQuery=true;
    }
    
    /*
     * 发送信息
     */
    private DBResult SendQuest(int id, String flage) {

        if (protocol != null) {
          //protocol.ClientSocketClose();
          //protocol.Connect(addr, port);
            SqlRequest obj = new SqlRequest();
            obj.CloseFlage = flage;
            obj.sql = String.valueOf(id);
            obj.methodType = MethodType.ExecuteNonQuery;
            byte[] data = JsonConvert.ConvertJsonByte(obj);
            protocol.ClientSocketSendData(data);
            if(flage.equals("close"))
            {
                protocol.ClientSocketClose();
                System.out.println("关闭");
                try
                {
                service.shutdown();
                }
                catch(Exception ex)
                {
                    //关闭不需要管
                }
                
            }
            else
            {
              ClientRecvice recvice=new ClientRecvice(protocol);
              DBResult result= recvice.recResult();
              return result;
            }
        }
        return null;

    }
    private void QueryData()
    {
        Thread query=new Thread(new Runnable()
                {

                    @Override
                    public void run() {
                        int num=syquery.get();
                      DBResult r=SendQuest(remoteID,flage);
                      System.out.println("SqlDataReader启动查询");
                      if(r==null)
                      {
                          reader.dataState="reset";
                          reader.cacheQuery=true;
                          return;
                      }
                      else
                      {
                          reader.dataState="sucess";
                      }
                      reader.cache=r;
                      if (r.Code == ErrorCode.DataAll) {
                          reader.eof = 1;
                          reader.cacheQuery=false;
                      }
                      reader.isQuery=false;
                      if(num==syquery.get())
                      {
                          //不同则说明有了新的查询
                         try {
                        reader.syqueue.put(r);
                         } catch (InterruptedException e) {
                        e.printStackTrace();
                        } 
                      }
                     
                    }
            
                });
        service.execute(query);
    }

}
