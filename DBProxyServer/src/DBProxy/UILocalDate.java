/**    
 * �ļ�����UILocalDate.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��19��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package DBProxy;

import java.text.SimpleDateFormat;
import java.util.Date;

/**    
 *     
 * ��Ŀ���ƣ�DBProxyServer    
 * �����ƣ�UILocalDate    
 * ��������    ���ڸ�ʽ���
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��19�� ����7:21:50    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��19�� ����7:21:50    
 * �޸ı�ע��    
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
   * ��ʽ�����
   * Ĭ�� yyyy/MM/dd HH:mm:ss
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
   * ��ʽ�����
   * Ĭ�� yyyy/MM/dd HH:mm:ss
   */
  public   String  toString()
  {
       return  sdf.format(theDate);
   
      
  }
}
