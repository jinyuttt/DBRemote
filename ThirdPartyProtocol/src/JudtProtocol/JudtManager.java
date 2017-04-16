/**    
 * 文件名：JudtManager.java    
 *    
 * 版本信息：    
 * 日期：2017年4月12日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package JudtProtocol;


import java.io.IOException;

import DDS_Transfer.IRecMsg;
import udt.UDPEndPoint;
import udt.UDTClient;
import udt.UDTSocket;

/**    
 *     
 * 项目名称：ThirdPartyProtocol    
 * 类名称：JudtManager    
 * 类描述：    管理走过数据的socket，可以直接用来做长连接
 * 
 * 创建人：jinyu    
 * 创建时间：2017年4月12日 上午12:41:20    
 * 修改人：jinyu    
 * 修改时间：2017年4月12日 上午12:41:20    
 * 修改备注：    
 * @version     
 *     
 */
public class JudtManager {
    int groupSize=1000;//分组，按照分钟存储，可以存储3分钟
    int timelapse=20;//秒
    TimeServer server=new TimeServer();
    SessionCache[] seesionCache=new SessionCache[groupSize];
    SessionCache[] uncation=new SessionCache[groupSize];
    private static JudtManager instance;  
      private JudtManager (){initCheckSocket();}   
      public static JudtManager getInstance() {  
      if (instance == null) {  
         instance = new JudtManager();  
      }  
       return instance;  
      }
      
      /*
       * 初始化
       * 
       */
      private void initCheckSocket()
      {
          //
          for(int i=0;i<groupSize;i++)
          {
              SessionCache cache1=new SessionCache();
              SessionCache cache2=new SessionCache();
              seesionCache[i]=cache1;
              uncation[i]=cache2;
          }
          Thread serverThread=new Thread(new Runnable()
                  {

                    @Override
                    public void run() {
                      
                        checkSocket();
                    }
              
                  });
          serverThread.setDaemon(true);
          serverThread.setName("checkSocket");
          serverThread.start();
          Thread clientThread=new Thread(new Runnable()
          {

            @Override
            public void run() {
              
                checkSession();
            }
      
          });
          clientThread.setDaemon(true);
          clientThread.setName("checkClient");
          clientThread.start();
      }
      
   /*
    * 使用过后的socket放入
    * 没有关闭的再次传递，获取数据
    * 可以保证长连接
    * 如果已经关闭则放入unaction，延时关闭
    * 
    */
public void addSocket(UDTSocket socket, UdtProtocol protol,IRecMsg rec)
{
        SocketData data=new SocketData();
        data.timer=server.getCount();
        data.id=server.getCount();
        data.protol=protol;
        data.socket=socket;
        data.outRec=rec;
        int index=data.id%groupSize;
        System.out.println("buffer:"+index);
        if(socket.getSession().isShutdown())
        {
            setBuffer(uncation[index],data);
        }
        else
        {
            setBuffer(seesionCache[index],data);
        }
}

/*
 * 放入缓存
 */
private void setBuffer(SessionCache cache,Object obj)
{
    cache.add(obj);
}

/*
 * 加入客户端
 */
public void addClient(UDTClient client)
{
    int index=server.getCount()%groupSize;
    SessionData data=new SessionData();
    data.client=client;
    data.id=client.getSession().getSocketID();
    data.timer=server.getCount();
    System.out.println("uncation:"+index);
    setBuffer(uncation[index],data);
}

/*
 * 检查服务端socket
 * 没有关闭再次放入服务
 * 关闭的间隔一定时间删除session,保证已经发送的数据能够准确到达
 * 不能及时关闭，丢包无法检查发送，因此延时
 */
public void checkSocket()
{
    try {
        int index=0;
        while(true)
        {
            Thread.sleep(500);
            //比当前时间小的要全部处理
            //如果处理性能不足，则要分组开启线程
            //将符合的组分批放入不同线程执行
            //TODO
            index=index%groupSize;
            //几乎前一秒的数据要处理完
            for(int i=index;i<server.getCount()%groupSize;i++)
            {
                index++;
                SessionCache cache= seesionCache[i];
              
                if(cache.hasData())
                { 
                    Object[] sessions=cache.getData();//取出的同时将清除数据
                   if(sessions!=null&&sessions.length>0)
                   {
                       for(int j=0;j<sessions.length;j++)
                       {
                           SocketData socket=(SocketData) sessions[j];
                           if(socket.socket.isActive())
                           {
                               //说明还在用，再次等待使用
                               ProcessThread pThread=new ProcessThread(socket.socket, socket.outRec,socket.protol);
                               pThread.setDaemon(true);
                               pThread.start();
                             }
                           else
                           {
                               addSocket(socket.socket, socket.protol,socket.outRec);
                               //关闭所有数据
                             //  socket.socket.getEndpoint().Remove(socket.socket.getSession().getSocketID());
                             //  System.out.println("消除socket");
                           }
                       }
                   }
                }
            }
            
        }
        
        
    } catch (InterruptedException e) {
      
        e.printStackTrace();
    }
    
}

/*
 * 延时关闭socket
 * 取出不用的session
 */
public void checkSession()
{
    try {
        int index=0;
        while(true)
        {
            Thread.sleep(500);
            index=index%groupSize;//重置，上次的位置;
            //比起当前计时器早延时时间的全部处理
            
            for(int i=index;i<server.getCount()%groupSize-timelapse;i++)
            {
                index++;
                SessionCache cache= uncation[i];
                if(cache.hasData())
                {
                    Object[] sessions=cache.getData();
                   if(sessions!=null&&sessions.length>0)
                   {
                       for(int j=0;j<sessions.length;j++)
                       {
                           Object  mysession=sessions[j];
                           if(mysession instanceof  SocketData)
                           {
                               //服务端session清除
                               SocketData socket=(SocketData)mysession;
                               socket.socket.getEndpoint().Remove(socket.socket.getSession().getSocketID());
                           }
                           else if(mysession instanceof SessionData)
                           {
                             //客户端关闭
                              SessionData client=(SessionData)mysession;
                               try {
                                    UDPEndPoint point=client.client.getEndpoint();
                                   if(point!=null)
                                   {
                                       point.Remove(client.id);//取出客户端session;
                                       System.out.println("关闭客户端 session: "+client.id);
                                       if(point.getSessions().isEmpty())
                                       {
                                           //如果已经为空，则说明没有再次使用客户端对象
                                           //整个对象应该被关闭;
                                           //
                                           client.client.Close();
                                           System.out.println("关闭客户端");
                                           //
                                           
                                        }
                                   }
                                
                            } catch (IOException e) {
                                
                                e.printStackTrace();
                            }
                             
                           }
                          
                           else
                           {
                               //关闭所有数据
                             
                               System.out.println("消除socket失败");
                           }
                       }
                   }
                }
            }
            
        } 
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

}
