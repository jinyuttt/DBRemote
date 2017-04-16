/**    
 * �ļ�����DBPool.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��17��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package Com.DBHelper;

import java.util.HashMap;

/**    
 *     
 * ��Ŀ���ƣ�DataStore    
 * �����ƣ�DBPool    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��17�� ����2:41:09    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��17�� ����2:41:09    
 * �޸ı�ע��    
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
