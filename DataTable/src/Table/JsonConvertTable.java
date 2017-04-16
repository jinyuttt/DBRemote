/**    
 * 文件名：JsonConvertTable.java    
 *    
 * 版本信息：    
 * 日期：2017年4月8日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package Table;

import DataTypeColumn.Column;
import DataTypeColumn.ColumnJsonType;

/**    
 *     
 * 项目名称：IDBStandardized    
 * 类名称：JsonConvertTable    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年4月8日 下午10:14:06    
 * 修改人：jinyu    
 * 修改时间：2017年4月8日 下午10:14:06    
 * 修改备注：    
 * @version     
 *     
 */
public class JsonConvertTable {
public static Column  getColumn(String name)
{
    return getDataColumn(name);
    
}
public static Column  getColumn(int index)
{
    ColumnJsonType b= ColumnJsonType.values()[index];
    
    return getColumn(b);
      
    
}
public static Column  getColumn(ColumnJsonType type)
{
    
    return getDataColumn(type.name());
    
}
private static Column  getDataColumn(String typeName)
{
      String org="DataTypeColumn."+typeName;
      Class<?> cls = null;
    try {
        cls = Class.forName(org);
    } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
       Column col = null;
    try {
        col = (Column) cls.newInstance();
    } catch (InstantiationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
       return col;
   
}
}
