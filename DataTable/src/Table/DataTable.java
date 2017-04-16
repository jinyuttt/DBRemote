/**    
 * 文件名：DataTable.java    
 *    
 * 版本信息：    
 * 日期：2017年3月15日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package Table;

import java.util.HashMap;
import java.util.List;

/**    
 *     
 * 项目名称：IDBStandardized    
 * 类名称：DataTable    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年3月15日 下午8:46:46    
 * 修改人：jinyu    
 * 修改时间：2017年3月15日 下午8:46:46    
 * 修改备注：    
 * @version     
 *     
 */
public class DataTable {
    public String tableName;
    public List<DataColumnJson> Columns;
    public List<DataRowJson> Rows;
    public HashMap<String,Integer> columnsIndex;
}
