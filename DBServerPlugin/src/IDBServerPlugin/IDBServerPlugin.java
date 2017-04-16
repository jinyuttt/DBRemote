/**    
 * �ļ�����IDBServerPlugin.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��4��10��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package IDBServerPlugin;

import java.util.HashMap;

/**    
 *     
 * ��Ŀ���ƣ�DBServerPlugin    
 * �����ƣ�IDBServerPlugin    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��4��10�� ����12:31:02    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��4��10�� ����12:31:02    
 * �޸ı�ע��    
 * @version     
 *     
 */
public interface IDBServerPlugin {
public boolean start(HashMap<String, Object> args);
public boolean stop(long timeOut);
public boolean addData(Object data);
public void setName(String name);
public String getName();

}
