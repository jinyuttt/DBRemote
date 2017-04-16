/**    
 * 文件名：ThreadManager.java    
 *    
 * 版本信息：    
 * 日期：2017年4月13日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package JudtProtocol;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**    
 *     
 * 项目名称：ThirdPartyProtocol    
 * 类名称：ThreadManager    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年4月13日 上午3:55:45    
 * 修改人：jinyu    
 * 修改时间：2017年4月13日 上午3:55:45    
 * 修改备注：    
 * @version     
 *     
 */
public class ThreadManager {
    public  static AtomicInteger counter_integer=new  AtomicInteger(0);
    public  static volatile int ThreadName=0;
    public  static  final int MaxThreadNum=10;
    public static ExecutorService executorService = Executors.newFixedThreadPool(5);
    public static  ExecutorService cachedThreadPool = Executors.newCachedThreadPool();  
}
