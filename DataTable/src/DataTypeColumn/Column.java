/**    
 * �ļ�����Column.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��15��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package DataTypeColumn;

import java.nio.Buffer;

import Table.DataTableJson;

/**    
 *     
 * ��Ŀ���ƣ�DataTable    
 * �����ƣ�Column    
 * ��������    �ж���
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��15�� ����11:30:43    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��15�� ����11:30:43    
 * �޸ı�ע��    byte,int,long,short�������͵����ֵ���������ݿ���ʹ��
 * �⼸�����ݵ����ֵ������̻�ʹ�ã�����Ϊ���ݿ��еĿ�ֵ
 * �����ݿ������ֵһ��Ҳ��ʹ�ò����ģ�����������Ϊ�Ǻ����
 * @version     
 *     
 */
public abstract class Column {
    public Column()
    {
        
    }
    /*
     * ����
     */
    public String caption;//����
    /*
     * ������
     */
    public String name;//������
    /*
     * ����
     */
    public int maxLength;//����
    /*
     * ��������
     */
    public int dataType;//����
    public int ordinal=0;//˳��
    public int capacity=0;//��ǰ����,��ʱ����
    public DataTableJson table;//�����
    protected int enlargeNum=15;//��չ����
    protected int rowNum=0;//��ǰ����ʵ����
    public ColumnJsonType columnType;
    public String columnTypeName;//����������
    public abstract void addData(Object obj);
    protected abstract void enlargeData();
    public abstract Object getData(int index);
    public abstract int mergeData(Column data);
    public  abstract  Buffer getAllData();
    public  abstract  Object getAllValues();
    public abstract int getRowNum();
    public abstract void setRowNum(int rowNum);
    
    
}
