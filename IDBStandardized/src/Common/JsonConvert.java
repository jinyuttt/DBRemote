package Common;


public class JsonConvert {
  private static  JSonObj JObj=JDataConvertAdapter.GetObj();
	  
/**
 * 
 * @param obj ���ݶ���
 * @return  Json�ַ���
 */
public static <T> String ConvertJsonString(T obj,Class<?> c)
{  
  return   JObj.ConvertJsonString(obj, c);
}

/**
 * 
 * @param obj  ����
 * @return   byte[]
 */
public static <T> byte[] ConvertJsonByte(T obj)
{
 	return JObj.ConvertJsonByte(obj);
}

/**
 * 
 * @param json  json�ַ���
 * @param c  ���Ͷ�������
 * @return  ����
 */

public static <T> T ConvertToObj(String json,Class<?> c)
{
    return JObj.ConvertToObj(json, c);
	
}

/**
 * 
 * @param jsonBytes �ַ���
 * @param c  ��������
 * @return  ����
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
