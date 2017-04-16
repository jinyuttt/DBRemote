/**    
 * 文件名：ServerDataReader.java    
 *    
 * 版本信息：    
 * 日期：2017年3月7日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package DBSqlManager;

import java.util.concurrent.ConcurrentHashMap;

import ServerCommon.DataReader;

/**
 * 
 * 项目名称：DBSqlManager 类名称：ServerDataReader 类描述： 创建人：jinyu 创建时间：2017年3月7日
 * 下午10:11:42 修改人：jinyu 修改时间：2017年3月7日 下午10:11:42 修改备注：
 * 
 * @version
 * 
 */
public class ServerDataReader {
    /*
     * 保存查询器
     */
    public static ConcurrentHashMap<Integer, DataReader> setID = new ConcurrentHashMap<Integer, DataReader>();

    public static ConcurrentHashMap<Integer, DataReader> unAction = new ConcurrentHashMap<Integer, DataReader>();
}
