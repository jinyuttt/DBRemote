/**    
 * �ļ�����JSonObj.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��11��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package Common;

/**    
 *     
 * ��Ŀ���ƣ�IDBStandardized    
 * �����ƣ�JSonObj    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��11�� ����7:56:11    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��11�� ����7:56:11    
 * �޸ı�ע��    
 * @version     
 *     
 */
public interface JSonObj {
    /*
     * Json�ַ���ת����
     */
    public  <T> T ConvertToObj(String json,Class<?> c);
    
    /*
     * json�ֽ�����ת����
     */
    public  <T> T ConvertToObj(byte[] jsonBytes,Class<?> c);
    
    /*
     * ���� תJson
     */
    public  <T> String ConvertJsonString(T obj,Class<?> c);
    
     /*
      * ���� ת�ֽ�
      */
    public  <T> byte[] ConvertJsonByte(T obj);
    
    /*
     * ���� ת�ֽ�
     */
   public  <T> T JsonObjectToObject(Object obj,Class<?> c);
   
   /*
    * ����ת����JAVA
    */
   public <T>  T JSONObjectToJavaObject(Object obj,Class<?> c,Class<?> ... args);
     
}
