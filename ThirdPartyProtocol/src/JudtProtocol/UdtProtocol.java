package JudtProtocol;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import DDS_Transfer.IDDS_Protocol;
import DDS_Transfer.IRecMsg;
import DDS_Transfer.ProtocolType;
import udt.UDPEndPoint;
import udt.UDTClient;

import udt.UDTOutputStream;
import udt.UDTServerSocket;
import udt.UDTSocket;

@ProtocolType(Name = "udt")
 public class UdtProtocol implements IDDS_Protocol {
 private	UDTServerSocket serverSocket = null;
 private	UDTClient socketClient = null;
 private UDPEndPoint endpoint = null;
 private UDTSocket clientcall=null;
 private boolean isAction=true;
 //private  int socketBuffersize = 32;
private  int recBuffersize = 16348;
 public final Map<Long,String>sessions=new ConcurrentHashMap<Long,String>();
 public boolean serverRunning=true;
   public UdtProtocol()
   {
       isAction=true;
   }
  public void removeID(Long id)
  {
      sessions.remove(id);
  }
   /*
    * 重新置换通讯客户端
    */
   public  void resetClientSocket(UDTSocket socket)
   {
       clientcall=socket;
   }
    @Override
    public boolean SendData(String adress, byte[] data) {
      
        return false;
    }
    
    /**
     * 合并数据
     * @param src1
     * @param src2
     * @return
     */
    private byte[] CatBytes(byte[]src1,byte[]src2)
	{
		byte[] result=null;
		 if(src1==null&&src2==null)
		 {
			 return null;
		 }
		int len=0;
		int index=0;
		if(src1!=null)
		{
			len+=src1.length;
		}
		if(src2!=null)
		{
			len+=src2.length;
		}
	    result=new byte[len];
	    if(src1!=null)
	    {
	      System.arraycopy(src1, 0, result, 0, src1.length);
	      index=src1.length;
	    }
	    if(src2!=null)
	    {
	    	 System.arraycopy(src2, 0, result,index, src2.length);
	    }
		return result;
	}
   
    
    @Override
   public void DisConnect() {
        if (socketClient != null) {
            try {
                socketClient.shutdown();
            } catch (IOException e) {
             
                e.printStackTrace();
            }
        }

    }
	
    @Override
    public void RecData(String Address, IRecMsg rec) {
        String[] addr = Address.split(":");
        InetAddress local = null;
        try {
            local = InetAddress.getByName(addr[0]);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        int port = Integer.valueOf(addr[1]);
		try {
			serverSocket = new UDTServerSocket(local,port);
		} catch (SocketException e1) {
		
			e1.printStackTrace();
		} catch (UnknownHostException e1) {
		
			e1.printStackTrace();
		}
		UdtProtocol curProtol=this;
		Thread serverProcess=new Thread(new Runnable(){
		   
			 @Override
		public void run(){
				while(serverRunning)
				{
					UDTSocket s = null;
                    try {
                        s = serverSocket.accept();
                    } catch (InterruptedException e) {
            
                        e.printStackTrace();
                    }
					if(s==null)
					{
						continue;
					}
					clientcall=s;
					System.out.println("接收到客户端");
					//
					Long id=s.getSession().getSocketID();
					if(sessions.containsKey(id))
					{
					    continue;//已经在执行
					}
					sessions.put(id, "");
					ProcessThread pThread=new ProcessThread(s, rec,curProtol);
					pThread.setDaemon(true);
					pThread.start();
			    }
				//
				
			 }

			
		});
		serverProcess.setDaemon(true);
		serverProcess.setName("udtServer");
		serverProcess.start();
        }
       

    @Override
    public void Close() {
    	serverRunning=false;
        endpoint.stop();

    }

    @Override
    public void ClientSocketClose() {
        if (socketClient != null) {
            try {
                //socketClient.shutdown();
               // socketClient.getEndpoint().Dispose();
                isAction=false;
                socketClient.shutdown();
                System.out.println("udtProtocol关闭！");
                JudtManager.getInstance().addClient(socketClient);
            } catch (Exception e) {
            
                e.printStackTrace();
            }
        }
        if(clientcall!=null)
        {
        	try {
				clientcall.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
        }

    }

    @Override
    public void CreateClient(String ip, int port) {
        if (socketClient == null) {
            InetAddress local = null;
            try {
                local = InetAddress.getByName(ip);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            try {
                endpoint = new UDPEndPoint(local, port);
            } catch (SocketException e) {
              
                e.printStackTrace();
            } catch (UnknownHostException e) {
              
                e.printStackTrace();
            }
            try {
                socketClient = new UDTClient(endpoint);
                
            } catch (SocketException e) {
               
                e.printStackTrace();
            } catch (UnknownHostException e) {
             
                e.printStackTrace();
            }
        }

    }

    @Override
    public void CreateClient() {
        if (socketClient == null) {

            try {
                socketClient = new UDTClient(null, 0);
                
            } catch (SocketException e) {
               
                e.printStackTrace();
            } catch (UnknownHostException e) {
               
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean BindLocal(String locahost, int port) {
        InetAddress local = null;
        try {
            local = InetAddress.getByName(locahost);
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }
        try {
            endpoint = new UDPEndPoint(local, port);
        } catch (SocketException e) {
            
            e.printStackTrace();
        } catch (UnknownHostException e) {
         
            e.printStackTrace();
        }
        if (socketClient != null) {
            try {
                socketClient = new UDTClient(endpoint);
               
            } catch (SocketException e) {
               
                e.printStackTrace();
            } catch (UnknownHostException e) {
              
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean Connect(String remoteaddr, int port) {
        if (socketClient != null) {
             
				try {
                    socketClient.connect(remoteaddr, port);
                    this.isAction=true;//再次激活
                } catch (UnknownHostException e) {
                   
                    e.printStackTrace();
                } catch (InterruptedException e) {
                   
                    e.printStackTrace();
                } catch (IOException e) {
                 
                    e.printStackTrace();
                }
			    try {
                    socketClient.getInputStream().setBlocking(true);
                } catch (IOException e) {
                   
                    e.printStackTrace();
                }
        	return true;
        }
        return false;
    }

    @Override
    public void ClientSocketSend(byte[] data) {
        if(!isAction)
        {
            //throw new Exception("已经关闭");
            return ;
        }
        if (socketClient != null) {
					try {
					    String info="*******方向："+socketClient.getSession().getSocketID()+"---->"
					    +socketClient.getSession().getDestination().getSocketID()+"  *******大小："+data.length;
					            
					    System.out.println(info);
						socketClient.sendBlocking(data);
					} catch (IOException e1) {
						
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						
						e1.printStackTrace();
					}
				
            try {
                //socketClient.shutdown();
               // socketClient.getEndpoint().Dispose();
                socketClient.Close();
                JudtManager.getInstance().addClient(socketClient);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void ClientSocketSendData(byte[] data) {
        if(!isAction)
        {
            //throw new Exception("已经关闭");
            return ;
        }
        if (socketClient != null) {
           
            	 try {
            	     String info="******方向："+socketClient.getSession().getSocketID()+"---->"
                         +socketClient.getSession().getDestination().getSocketID()+"  *******大小："+data.length;
                       System.out.println(info);
					 socketClient.sendBlocking(data);
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
            

        }
    }

    @Override
    public void ServerSocketSend(byte[] data) {
        	if(clientcall!=null)
            	{
            		try {
            		
            			UDTOutputStream stream=clientcall.getOutputStream();
            			stream.write(data);
            			stream.flush();
            			stream.close();
            			clientcall.close();
            			String info="******方向："+clientcall.getSession().getSocketID()+"---->"
                                +clientcall.getSession().getDestination().getSocketID()+"  *******大小："+data.length;
            			System.out.println(info);
            			JudtManager.getInstance().addSocket(clientcall, this, null);
            			//clientcall.getSession().Dispose();
					} catch (IOException e) {
						e.printStackTrace();
					}
            	}
    }

    @Override
    public void ServerSocketSendData(byte[] data) {
    	if(clientcall!=null)
    	{
    		try {
    		    UDTOutputStream stream=clientcall.getOutputStream();
    			stream.write(data);
    			stream.flush();
    			String info=" *******方向："+clientcall.getSession().getSocketID()+"---->"
                        +clientcall.getSession().getDestination().getSocketID()+"  *******大小："+data.length;
                System.out.println(info);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
    	}
        }
    
    
    @Override
    public byte[] RecClientSocket() {
       
        byte[] realData = null;
       if (socketClient != null) {
           try {
               socketClient.getInputStream().setBlocking(true);
           } catch (IOException e) {
                e.printStackTrace();
           }
          byte[] recData = new byte[recBuffersize];
            while (true) {
                int r = 0;
                try {
					r = socketClient.read(recData);
					JudtManager.getInstance().addClient(socketClient);
				} catch (IOException e) {
				    e.printStackTrace();
				} catch (InterruptedException e) {
				    e.printStackTrace();
				}
                if(r<0)
                {
                    System.out.println("客户端接收c<0");
                	break;
                }
                if(r==0)
                {
                	try {
						Thread.sleep(1000);//等待1秒
						continue;
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
                }
                if (r < recData.length&&r>0) {
                    System.out.println("客户端跳出接收c="+r);
                    byte[]cur=new byte[r];
                    System.arraycopy(recData, 0, cur, 0, r);
                    realData=  CatBytes(realData,cur);
                    break;
                } else {
                	
                     realData= CatBytes(realData,recData);
                }
            }

        }
        try {
			socketClient.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return realData;
    }

    @Override
    public byte[] RecClientSocketData() {
    	 byte[] realData = null;
    	 int num=0;
        if (socketClient != null) {
            try {
                socketClient.getInputStream().setBlocking(true);
            } catch (IOException e) {
                
                e.printStackTrace();
            }
             byte[] recData = new byte[recBuffersize];
             while (true) {
                 int r = 0;
                 try {
 					r = socketClient.read(recData);
 				} catch (IOException e) {
 				    e.printStackTrace();
 				} catch (InterruptedException e) {
 				    e.printStackTrace();
 				}
                 if(r<0)
                 {
                     System.out.println("客户端接收c<0");
                     break;
                 }
                 if(r==0)
                 {
                     try {
                         Thread.sleep(1000);//等待1秒
                         continue;
                     } catch (InterruptedException e) {
                         
                         e.printStackTrace();
                     }
                 }
                 if (r < recData.length) {
                    byte[]cur=new byte[r];
                    System.arraycopy(recData, 0, cur, 0, r);
                    realData=  CatBytes(realData,cur);
                    num+=r;
                    System.out.println("客户端跳出接收c="+r);
                    break;
                 } else {
                     num+=r;
                      realData= CatBytes(realData,recData);
                 }
             }

         }
          System.out.println("当前接受网络数据："+num);
         return realData;
    }

    @Override
    public String GetBindAddress() {
      
        return null;
    }

    @Override
    public void SetBindAddress(String addr) {
       

    }

    @Override
    public String GetLocalAddress() {
       
        return null;
    }

    @Override
    public void SetSocketBufferSize(int size) {
       // socketBuffersize = size;

    }

    @Override
    public void SetRecBufferSize(int size) {
        recBuffersize = size;

    }

    @Override
    public String GetClientAddress() {
     
        return null;
    }
	

}
