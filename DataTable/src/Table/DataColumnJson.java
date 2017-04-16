/**    
 * 文件名：DataColumnJson.java    
 *    
 * 版本信息：    
 * 日期：2017年3月15日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package Table;

import java.util.ArrayList;
import java.util.List;

/**    
 *     
 * 项目名称：IDBStandardized    
 * 类名称：DataColumnJson    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年3月15日 下午8:45:27    
 * 修改人：jinyu    
 * 修改时间：2017年3月15日 下午8:45:27    
 * 修改备注：    
 * @version     
 *     
 */
public class DataColumnJson {
 
public String caption;
 public String name;
 public int maxLength;
 public int dataType;
 public String columnTypeName;
 public int ordinal=0;
 public String comment;
 public List<Object> listData=new ArrayList<Object>();
 public DataTableJson table;
 
}
