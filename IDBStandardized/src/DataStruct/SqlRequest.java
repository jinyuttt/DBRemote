package DataStruct;

/**
 * 
 * 
 * ��Ŀ���ƣ�IDBStandardized �����ƣ�SqlRequest �������� Sql���� �����ˣ�jinyu
 * ����ʱ�䣺2017��3��7������12:22:05 �޸��ˣ�jinyu �޸�ʱ�䣺2017��3��7�� ����12:22:05 �޸ı�ע��
 * 
 * @version
 *
 */
public class SqlRequest {
    public String sql = "";

    public String CloseFlage = "false";

    public MethodType methodType = MethodType.ExecuteQuery;

    public BatchData Parameter = null;
    
    public String TestInfo="";
}
