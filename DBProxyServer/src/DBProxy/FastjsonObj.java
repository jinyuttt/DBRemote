/**    
 * 文件名：FastjsonObj.java    
 *    
 * 版本信息：    
 * 日期：2017年3月11日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package DBProxy;
import com.alibaba.fastjson.JSON;

/**    
 *     
 * 项目名称：IDBStandardized    
 * 类名称：FastjsonObj    
 * 类描述：    Fastjson
 * 创建人：jinyu    
 * 创建时间：2017年3月11日 下午8:15:17    
 * 修改人：jinyu    
 * 修改时间：2017年3月11日 下午8:15:17    
 * 修改备注：    
 * @version     
 *     
 */
public class FastjsonObj  {

    /* 字符串转对象  
     *   
     */
   
    @SuppressWarnings("unchecked")
   
    public <T> T ConvertToObj(String json, Class<?> c) {
      return (T)JSON.parseObject(json,c);
      
    }

    /*    
     * 字节转成对象   
     */
    @SuppressWarnings("unchecked")
  
    public <T> T ConvertToObj(byte[] jsonBytes, Class<?> c) {
      return (T)JSON.parseObject(jsonBytes, c);
    }

    /* 
     *  对象转成字符串
     */
  
    public <T> String ConvertJsonString(T obj, Class<?> c) {
       return JSON.toJSONString(obj);
    }

    /*  
     *  对象转成byte[]
     */
   
    public <T> byte[] ConvertJsonByte(T obj) {
       
        return JSON.toJSONBytes(obj);
    }

}
