
package JudtProtocol;
import DDS_Transfer.IRecMsg;
import udt.UDTSocket;


/*
 * ������ʱʹ��
 * ��Ҫ����udt�����ģʽ
 * �̵߳���
 */
public class ProcessThread extends Thread{
    public  boolean serverRunning=true;
  
    //ͨѶִ��
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
             System.out.println("�����ִ���߳�����"+ThreadManager.counter_integer.get());
          // }
	      }
	  
	  }
