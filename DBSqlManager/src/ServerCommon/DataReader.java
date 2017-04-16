/**    
 * 文件名：DataReader.java    
 *    
 * 版本信息：    
 * 日期：2017年3月7日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package ServerCommon;

import java.sql.ResultSet;

import Table.DataTableJson;


/**
 * 
 * 项目名称：DBSqlManager 类名称：DataReader 类描述： 创建人：jinyu 创建时间：2017年3月7日 下午11:37:40
 * 修改人：jinyu 修改时间：2017年3月7日 下午11:37:40 修改备注：
 * 
 * @version
 * 
 */
public class DataReader {
    public DataTableJson cache = null;
    public DataTableJson lastData=null;
    public ResultSet rs = null;
}
