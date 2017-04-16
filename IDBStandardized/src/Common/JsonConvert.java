package Common;


public class JsonConvert {
  private static  JSonObj JObj=JDataConvertAdapter.GetObj();
	  
/**
 * 
 * @param obj 数据对象
 * @return  Json字符串
 */
public static <T> String ConvertJsonString(T obj,Class<?> c)
{  
  return   JObj.ConvertJsonString(obj, c);
}

/**
 * 
 * @param obj  对象
 * @return   byte[]
 */
public static <T> byte[] ConvertJsonByte(T obj)
{
 	return JObj.ConvertJsonByte(obj);
}

/**
 * 
 * @param json  json字符串
 * @param c  泛型对象类型
 * @return  对象
 */

public static <T> T ConvertToObj(String json,Class<?> c)
{
    return JObj.ConvertToObj(json, c);
	
}

/**
 * 
 * @param jsonBytes 字符串
 * @param c  泛型类型
 * @return  对象
 */

public static <T> T ConvertToObj(byte[] jsonBytes,Class<?> c)
{
    return JObj.ConvertToObj(jsonBytes, c);
	
}
public static Object JsonObjectToObj(Object obj,Class<?> c)
{
    return JObj.JsonObjectToObject(obj, c);
    
}
public static Object JsonObjectToJavaObj(Object obj,Class<?> c,Class<?> ...agrs)
{
    return JObj.JSONObjectToJavaObject(obj, c,agrs);
    
}
}
