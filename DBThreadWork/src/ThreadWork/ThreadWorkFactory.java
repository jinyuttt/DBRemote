/**    
 * �ļ�����ThreadFactory.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��15��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package ThreadWork;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import ThreadConfig.Config;

/**    
 *     
 * ��Ŀ���ƣ�DBThreadWork    
 * �����ƣ�ThreadFactory    
 * ��������    �����̼߳�¼
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��15�� ����1:46:26    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��15�� ����1:46:26    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class ThreadWorkFactory {
    ExecutorService flexibleThread = Executors.newCachedThreadPool(); 
    ExecutorService fixThread=Executors.newFixedThreadPool(Config.ThreadWorkFitSize);
     AtomicInteger c=new AtomicInteger(0);
     private  class WorkRunner implements Runnable
     {
         IWorkProcess task=null;
       public WorkRunner(IWorkProcess work)
       {
            task=work;
       }
        @Override
        public void run() {
            if(task!=null)
            {
                c.incrementAndGet();
                task.Process();
                c.decrementAndGet();
            }
            
        }
         
     }
     public void AddWork(IWorkProcess obj)
     {
         if(c.get()>Config.ThreadWorkMaxSize)
         {
             fixThread.execute(new WorkRunner(obj));
         }
         else
         {
             flexibleThread.execute(new WorkRunner(obj));
         }
     }
}
