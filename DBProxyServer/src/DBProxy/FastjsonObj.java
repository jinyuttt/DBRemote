/**    
 * �ļ�����FastjsonObj.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��11��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package DBProxy;
import com.alibaba.fastjson.JSON;

/**    
 *     
 * ��Ŀ���ƣ�IDBStandardized    
 * �����ƣ�FastjsonObj    
 * ��������    Fastjson
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��11�� ����8:15:17    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��11�� ����8:15:17    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class FastjsonObj  {

    /* �ַ���ת����  
     *   
     */
   
    @SuppressWarnings("unchecked")
   
    public <T> T ConvertToObj(String json, Class<?> c) {
      return (T)JSON.parseObject(json,c);
      
    }

    /*    
     * �ֽ�ת�ɶ���   
     */
    @SuppressWarnings("unchecked")
  
    public <T> T ConvertToObj(byte[] jsonBytes, Class<?> c) {
      return (T)JSON.parseObject(jsonBytes, c);
    }

    /* 
     *  ����ת���ַ���
     */
  
    public <T> String ConvertJsonString(T obj, Class<?> c) {
       return JSON.toJSONString(obj);
    }

    /*  
     *  ����ת��byte[]
     */
   
    public <T> byte[] ConvertJsonByte(T obj) {
       
        return JSON.toJSONBytes(obj);
    }

}
