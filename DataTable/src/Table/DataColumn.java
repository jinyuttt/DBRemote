/**    
 * �ļ�����DataColumn.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��15��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package Table;

import java.util.List;

/**    
 *     
 * ��Ŀ���ƣ�IDBStandardized    
 * �����ƣ�DataColumn    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��15�� ����8:46:19    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��15�� ����8:46:19    
 * �޸ı�ע��    
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
