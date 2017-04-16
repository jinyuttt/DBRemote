/**    
 * 文件名：DBPool.java    
 *    
 * 版本信息：    
 * 日期：2017年3月17日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package Com.DBHelper;

import java.util.HashMap;

/**    
 *     
 * 项目名称：DataStore    
 * 类名称：DBPool    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年3月17日 上午2:41:09    
 * 修改人：jinyu    
 * 修改时间：2017年3月17日 上午2:41:09    
 * 修改备注：    
 * @version     
 *     
 */
public class DBPool {
private  static HashMap<Integer,String> hashMap=new HashMap<Integer,String>();
public static String GetPools(int poolType)
{
  return  hashMap.getOrDefault(poolType, null);
}
public static void Put(int poolType,String poolName)
{
    hashMap.put(poolType, poolName);
}
}
