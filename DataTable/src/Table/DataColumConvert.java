/**    
 * �ļ�����DataColumConvert.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��18��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package Table;

import DataTypeColumn.Column;

/**    
 *     
 * ��Ŀ���ƣ�DataTable    
 * �����ƣ�DataColumConvert    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��18�� ����3:46:13    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��18�� ����3:46:13    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class DataColumConvert {
    static MappingType columnMap=new MappingType();
  public static Column Convert(String typeName)
  {
      try {
         // String className="DataTypeColumn."+
          String name=columnMap.GetTypeName(typeName);
          if(name==null)
          {
              name="Object";
          }
          char[] cs=name.toCharArray();
          if(cs[0]>96&&cs[0]<123)
          {
             cs[0]-=32;
          }
           name=  String.valueOf(cs);
           String className="DataTypeColumn."+name+"Column";
           Class<?> clazz = Class.forName(className);
           try {
            Column col=(Column)clazz.newInstance();
            return col;
        } catch (InstantiationException e) {
         
            e.printStackTrace();
        } catch (IllegalAccessException e) {
           
            e.printStackTrace();
        }
    } catch (ClassNotFoundException e) {
       
        e.printStackTrace();
    }
      return null;
  }
}
