/**    
 * �ļ�����JsonConfigModel.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��17��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package Config;

/**    
 *     
 * ��Ŀ���ƣ�DataStore    
 * �����ƣ�JsonConfigModel    
 * ��������    Json��ʽ�����ļ�
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��17�� ����2:09:21    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��17�� ����2:09:21    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class JsonConfigModel {
    // ���ӳ�����  
    public String driverName="org.postgresql.Driver";  
    public String url="jdbc:postgresql://127.0.0.1:5432/PeostgreDB";  
    public String userName="postgres";  
    public String password="1234";  
    // ���ӳ�����  
    public String poolName="postgresql";  
    public int poolType=0;//0��ȡ��1ֻ����2ֻд
    public boolean isMemory=false;
}
