/**    
 * �ļ�����DataReader.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��7��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package ServerCommon;

import java.sql.ResultSet;

import Table.DataTableJson;


/**
 * 
 * ��Ŀ���ƣ�DBSqlManager �����ƣ�DataReader �������� �����ˣ�jinyu ����ʱ�䣺2017��3��7�� ����11:37:40
 * �޸��ˣ�jinyu �޸�ʱ�䣺2017��3��7�� ����11:37:40 �޸ı�ע��
 * 
 * @version
 * 
 */
public class DataReader {
    public DataTableJson cache = null;
    public DataTableJson lastData=null;
    public ResultSet rs = null;
}
