/**    
 * 文件名：GSONObj.java    
 *    
 * 版本信息：    
 * 日期：2017年3月11日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package Common;

import com.google.gson.Gson;

/**    
 *     
 * 项目名称：IDBStandardized    
 * 类名称：GSONObj    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年3月11日 下午8:43:12    
 * 修改人：jinyu    
 * 修改时间：2017年3月11日 下午8:43:12    
 * 修改备注：    
 * @version     
 *     
 */
public class GSONObj implements JSonObj {
    
    Gson gson = new Gson();
    /* 
     *  json字符串转成对象  
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T ConvertToObj(String json, Class<?> c) {
      
            T result = (T) gson.fromJson(json, c);
             return result;
    }

    /*
     * 字节数组转成对象    
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T ConvertToObj(byte[] jsonBytes, Class<?> c) {
         String json=new String(jsonBytes);
         T result = (T) gson.fromJson(json, c);
         return result;
    }

    /*    
     * 对象转成json字符串  
     */
    @Override
    public <T> String ConvertJsonString(T obj, Class<?> c) {
      return  gson.toJson(obj, c);
        
    }

    /*    
     * 对象转成字节数组 
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
