/**    
 * �ļ�����JsonDefaultModel.java    
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
 * �����ƣ�JsonDefaultModel    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��17�� ����10:18:13    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��17�� ����10:18:13    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class JsonDefaultModel {
    public int minConnections = 1; // �̳߳أ���С������  
    public int maxConnections = 10; // �̳߳أ����������  
      
    public int initConnections = 5;// ��ʼ��������  
      
    public long connTimeOut = 1000;// �ظ�������ӵ�Ƶ��  
      
    public int maxActiveConnections = 100;// ���������������������ݿ��Ӧ  
      
    public long connectionTimeOut = 1000*60*20;// ���ӳ�ʱʱ�䣬Ĭ��20����  
      
    public boolean isCurrentConnection = true; // �Ƿ��õ�ǰ���ӣ�Ĭ��true  
      
    public boolean isCheakPool = true; // �Ƿ�ʱ������ӳ�  
    public long lazyCheck = 1000*60*60;// �ӳٶ���ʱ���ʼ ���  
    public long periodCheck = 1000*60*60;// ���Ƶ��  
}
