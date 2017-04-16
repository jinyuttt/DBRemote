/**    
 * 文件名：SessionCache.java    
 *    
 * 版本信息：    
 * 日期：2017年4月12日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package JudtProtocol;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**    
 *     
 * 项目名称：ThirdPartyProtocol    
 * 类名称：SessionCache    
 * 类描述：    保存数据块
 * 创建人：jinyu    
 * 创建时间：2017年4月12日 下午11:13:21    
 * 修改人：jinyu    
 * 修改时间：2017年4月12日 下午11:13:21    
 * 修改备注：    
 * @version     
 *     
 */
public class SessionCache {
    LinkedBlockingQueue <Object> socketQueue=new LinkedBlockingQueue <Object>();
   
    /*
     * 添加数据
     */
    public void add(Object obj)
    {
        try {
            socketQueue.offer(obj,1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * 是否有数据
     */
    public boolean hasData()
    {
        if(socketQueue.size()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /*
     * 取出数据
     */
    public Object get()
    {
       return socketQueue.poll();
    }
    
    /*
     * 取出所有数据
     */
    public Object[]  getData()
    {
        Object[] objs= socketQueue.toArray();
        socketQueue.clear();
        return objs;
    }
}
