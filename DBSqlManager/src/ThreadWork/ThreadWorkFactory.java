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

import DBSqlManager.ProcessQuest;
import DDS_Transfer.IDDS_Protocol;
import DataStruct.SqlRequest;


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
    private static volatile ThreadWorkFactory instance;  

    //����һ�����еľ�̬���������ظ�����ʵ��
    public static ThreadWorkFactory getIstance() { 
        // ����ʵ����ʱ����жϣ���ʹ��ͬ������飬instance������nullʱ��ֱ�ӷ��ض����������Ч�ʣ�
        if (instance == null) {
            //ͬ������飨����δ��ʼ��ʱ��ʹ��ͬ������飬��֤���̷߳���ʱ�����ڵ�һ�δ����󣬲����ظ���������
            synchronized (ThreadWorkFactory.class) {
                //δ��ʼ�������ʼinstance����
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
