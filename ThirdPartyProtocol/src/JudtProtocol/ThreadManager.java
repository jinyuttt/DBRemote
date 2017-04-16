/**    
 * �ļ�����ThreadManager.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��4��13��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package JudtProtocol;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**    
 *     
 * ��Ŀ���ƣ�ThirdPartyProtocol    
 * �����ƣ�ThreadManager    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��4��13�� ����3:55:45    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��4��13�� ����3:55:45    
 * �޸ı�ע��    
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
