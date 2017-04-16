/**    
 * 文件名：DataColumConvert.java    
 *    
 * 版本信息：    
 * 日期：2017年3月18日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package Table;

import DataTypeColumn.Column;

/**    
 *     
 * 项目名称：DataTable    
 * 类名称：DataColumConvert    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年3月18日 下午3:46:13    
 * 修改人：jinyu    
 * 修改时间：2017年3月18日 下午3:46:13    
 * 修改备注：    
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
