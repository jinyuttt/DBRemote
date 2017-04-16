
package JudtProtocol;
import DDS_Transfer.IRecMsg;
import udt.UDTSocket;


/*
 * 多连接时使用
 * 主要用于udt服务端模式
 * 线程调度
 */
public class ProcessThread extends Thread{
    public  boolean serverRunning=true;
  
    //通讯执行
    private UDTSocket socket=null;
    private UdtProtocol curProtol=null;
    private IRecMsg rec=null;
	public ProcessThread(UDTSocket socket,IRecMsg outRec,UdtProtocol protol)
	{
		this.socket=socket;
		this.rec=outRec;
		this.curProtol=protol;
		this.setName("udtSession+"+ThreadManager.ThreadName++);
	}
    
    @Override 
      public void run(){
	      
        StartThread process=new StartThread(socket,rec,curProtol);
        process.setDaemon(true);
//        if(ThreadManager.counter_integer.get()>ThreadManager.MaxThreadNum)
//          {
//             ThreadManager.executorService.execute(process);
//          }
//          else
//           {
             ThreadManager.cachedThreadPool.execute(process);
             System.out.println("服务端执行线程数："+ThreadManager.counter_integer.get());
          // }
	      }
	  
	  }
