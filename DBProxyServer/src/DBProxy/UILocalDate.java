/**    
 * 文件名：UILocalDate.java    
 *    
 * 版本信息：    
 * 日期：2017年3月19日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package DBProxy;

import java.text.SimpleDateFormat;
import java.util.Date;

/**    
 *     
 * 项目名称：DBProxyServer    
 * 类名称：UILocalDate    
 * 类描述：    日期格式输出
 * 创建人：jinyu    
 * 创建时间：2017年3月19日 下午7:21:50    
 * 修改人：jinyu    
 * 修改时间：2017年3月19日 下午7:21:50    
 * 修改备注：    
 * @version     
 *     
 */
public class UILocalDate {
    Date theDate;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  public  UILocalDate(Date now)
  {
      theDate=now;
      
  }
  /*
   * 格式化输出
   * 默认 yyyy/MM/dd HH:mm:ss
   */
  public   String  toString(String fomatString)
  {
      if(fomatString!=null&&!fomatString.isEmpty())
      {
         sdf.applyPattern(fomatString);
      }

       return  sdf.format(theDate);
   
      
  }
  /*
   * 格式化输出
   * 默认 yyyy/MM/dd HH:mm:ss
   */
  public   String  toString()
  {
       return  sdf.format(theDate);
   
      
  }
}
