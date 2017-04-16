/**    
 * 文件名：DataColumn.java    
 *    
 * 版本信息：    
 * 日期：2017年3月15日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package Table;

import java.util.List;

/**    
 *     
 * 项目名称：IDBStandardized    
 * 类名称：DataColumn    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年3月15日 下午8:46:19    
 * 修改人：jinyu    
 * 修改时间：2017年3月15日 下午8:46:19    
 * 修改备注：    
 * @version     
 *     
 */
public class DataColumn {
    public String caption;
    public String name;
    public int maxLength;
    public String dataType;
    public int ordinal=0;
    public DataTableJson table;
    List<Object> data=null;
}
