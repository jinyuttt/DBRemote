/**    
 * �ļ�����TimeServer.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��4��12��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package JudtProtocol;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**    
 *     
 * ��Ŀ���ƣ�ThirdPartyProtocol    
 * �����ƣ�TimeServer    
 * ��������    ��ʱ��
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��4��12�� ����11:56:13    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��4��12�� ����11:56:13    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class TimeServer {
    ScheduledExecutorService service = Executors  
            .newSingleThreadScheduledExecutor();  
    AtomicInteger timerCount=new AtomicInteger();
   
    public TimeServer()
    {
       Runnable runnable = new Runnable() {  
        public void run() {  
            timerCount.incrementAndGet();
        }  
    };  
   
    // �ڶ�������Ϊ�״�ִ�е���ʱʱ�䣬����������Ϊ��ʱִ�еļ��ʱ��  
    service.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
    }
    
    /*
     * ��ȡ��ǰ��ʱ��ֵ
     */
    public int getCount()
    {
        return timerCount.get();
    }
    /*
     * ���ü�ʱ��
     */
    public  void resetCount(int timer)
    {
        timerCount.set(timer);
    }
}
