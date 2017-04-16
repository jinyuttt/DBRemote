/**    
 * 文件名：ThreadFactory.java    
 *    
 * 版本信息：    
 * 日期：2017年3月15日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package ThreadWork;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import ThreadConfig.Config;

/**    
 *     
 * 项目名称：DBThreadWork    
 * 类名称：ThreadFactory    
 * 类描述：    创建线程记录
 * 创建人：jinyu    
 * 创建时间：2017年3月15日 上午1:46:26    
 * 修改人：jinyu    
 * 修改时间：2017年3月15日 上午1:46:26    
 * 修改备注：    
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
