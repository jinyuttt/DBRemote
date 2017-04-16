/**    
 * 文件名：JSonObj.java    
 *    
 * 版本信息：    
 * 日期：2017年3月11日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package Common;

/**    
 *     
 * 项目名称：IDBStandardized    
 * 类名称：JSonObj    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年3月11日 下午7:56:11    
 * 修改人：jinyu    
 * 修改时间：2017年3月11日 下午7:56:11    
 * 修改备注：    
 * @version     
 *     
 */
public interface JSonObj {
    /*
     * Json字符串转对象
     */
    public  <T> T ConvertToObj(String json,Class<?> c);
    
    /*
     * json字节数组转对象
     */
    public  <T> T ConvertToObj(byte[] jsonBytes,Class<?> c);
    
    /*
     * 对象 转Json
     */
    public  <T> String ConvertJsonString(T obj,Class<?> c);
    
     /*
      * 对象 转字节
      */
    public  <T> byte[] ConvertJsonByte(T obj);
    
    /*
     * 对象 转字节
     */
   public  <T> T JsonObjectToObject(Object obj,Class<?> c);
   
   /*
    * 对象转换成JAVA
    */
   public <T>  T JSONObjectToJavaObject(Object obj,Class<?> c,Class<?> ... args);
     
}
