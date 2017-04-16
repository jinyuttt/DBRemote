/**    
 * �ļ�����DataTable.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��15��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package Table;

import java.util.HashMap;
import java.util.List;

/**    
 *     
 * ��Ŀ���ƣ�IDBStandardized    
 * �����ƣ�DataTable    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��15�� ����8:46:46    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��15�� ����8:46:46    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class DataTable {
    public String tableName;
    public List<DataColumnJson> Columns;
    public List<DataRowJson> Rows;
    public HashMap<String,Integer> columnsIndex;
}
