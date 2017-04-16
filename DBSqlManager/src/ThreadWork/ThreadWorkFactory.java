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

import DBSqlManager.ProcessQuest;
import DDS_Transfer.IDDS_Protocol;
import DataStruct.SqlRequest;


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
    private static volatile ThreadWorkFactory instance;  

    //定义一个共有的静态方法，返回该类型实例
    public static ThreadWorkFactory getIstance() { 
        // 对象实例化时与否判断（不使用同步代码块，instance不等于null时，直接返回对象，提高运行效率）
        if (instance == null) {
            //同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建）
            synchronized (ThreadWorkFactory.class) {
                //未初始化，则初始instance变量
                if (instance == null) {
                    instance = new ThreadWorkFactory();   
                }   
            }   
        }   
        return instance;   
    }   

    ExecutorService flexibleThread = Executors.newCachedThreadPool(); 
    ExecutorService fixThread=Executors.newFixedThreadPool(Config.ThreadWorkFitSize);
     AtomicInteger c=new AtomicInteger(0);
     private  class WorkRunner implements Runnable
     {
         SqlRequest request;
         IDDS_Protocol  communication;
       public WorkRunner(SqlRequest requet, IDDS_Protocol obj)
       {
           request=requet;
           communication=obj;
       }
        @Override
        public void run() {
            ProcessQuest prequest=new ProcessQuest();
            prequest.Process(request, communication);
            
        }
         
     }
     public void AddWork(SqlRequest request, IDDS_Protocol obj)
     {
         if(c.get()>Config.ThreadWorkMaxSize)
         {
             fixThread.execute(new WorkRunner(request,obj));
         }
         else
         {
             flexibleThread.execute(new WorkRunner(request,obj));
         }
     }
}
