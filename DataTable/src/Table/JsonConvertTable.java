/**    
 * �ļ�����JsonConvertTable.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��4��8��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package Table;

import DataTypeColumn.Column;
import DataTypeColumn.ColumnJsonType;

/**    
 *     
 * ��Ŀ���ƣ�IDBStandardized    
 * �����ƣ�JsonConvertTable    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��4��8�� ����10:14:06    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��4��8�� ����10:14:06    
 * �޸ı�ע��    
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
