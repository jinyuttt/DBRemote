/**    
 * 文件名：TimeServer.java    
 *    
 * 版本信息：    
 * 日期：2017年4月12日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package JudtProtocol;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**    
 *     
 * 项目名称：ThirdPartyProtocol    
 * 类名称：TimeServer    
 * 类描述：    计时器
 * 创建人：jinyu    
 * 创建时间：2017年4月12日 下午11:56:13    
 * 修改人：jinyu    
 * 修改时间：2017年4月12日 下午11:56:13    
 * 修改备注：    
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
   
    // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
    service.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
    }
    
    /*
     * 获取当前计时器值
     */
    public int getCount()
    {
        return timerCount.get();
    }
    /*
     * 重置计时器
     */
    public  void resetCount(int timer)
    {
        timerCount.set(timer);
    }
}
