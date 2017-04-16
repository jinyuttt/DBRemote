/**    
 * �ļ�����GSONObj.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��11��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package Common;

import com.google.gson.Gson;

/**    
 *     
 * ��Ŀ���ƣ�IDBStandardized    
 * �����ƣ�GSONObj    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��11�� ����8:43:12    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��11�� ����8:43:12    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class GSONObj implements JSonObj {
    
    Gson gson = new Gson();
    /* 
     *  json�ַ���ת�ɶ���  
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T ConvertToObj(String json, Class<?> c) {
      
            T result = (T) gson.fromJson(json, c);
             return result;
    }

    /*
     * �ֽ�����ת�ɶ���    
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T ConvertToObj(byte[] jsonBytes, Class<?> c) {
         String json=new String(jsonBytes);
         T result = (T) gson.fromJson(json, c);
         return result;
    }

    /*    
     * ����ת��json�ַ���  
     */
    @Override
    public <T> String ConvertJsonString(T obj, Class<?> c) {
      return  gson.toJson(obj, c);
        
    }

    /*    
     * ����ת���ֽ����� 
     */
    @Override
    public <T> byte[] ConvertJsonByte(T obj) {
        String json= gson.toJson(obj);
        
        return json.getBytes();
    }

   

    @Override
    public <T> T JsonObjectToObject(Object obj, Class<?> c) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T JSONObjectToJavaObject(Object obj, Class<?> c, Class<?>... args) {
        // TODO Auto-generated method stub
        return null;
    }

 

}
