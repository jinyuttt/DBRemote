/**    
 * �ļ�����DataColumnJson.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��15��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package Table;

import java.util.ArrayList;
import java.util.List;

/**    
 *     
 * ��Ŀ���ƣ�IDBStandardized    
 * �����ƣ�DataColumnJson    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��15�� ����8:45:27    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��15�� ����8:45:27    
 * �޸ı�ע��    
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
