/**    
 * 文件名：FastjsonObj.java    
 *    
 * 版本信息：    
 * 日期：2017年3月11日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package Common;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

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
public class FastjsonObj implements JSonObj {

    /* 字符串转对象  
     *   
     */
   
    @SuppressWarnings("unchecked")
    @Override
    public <T> T ConvertToObj(String json, Class<?> c) {
      return (T)JSON.parseObject(json,c);
      
    }

    /*    
     * 字节转成对象   
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T ConvertToObj(byte[] jsonBytes, Class<?> c) {
      return (T)JSON.parseObject(jsonBytes, c);
    }

    /* 
     *  对象转成字符串
     */
    @Override
    public <T> String ConvertJsonString(T obj, Class<?> c) {
       return JSON.toJSONString(obj);
    }

    /*  
     *  对象转成byte[]
     */
    @Override
    public <T> byte[] ConvertJsonByte(T obj) {
       
        return JSON.toJSONBytes(obj);
    }
    
  
    @SuppressWarnings("unchecked")
    @Override
    public  <T> T JsonObjectToObject(Object json, Class<?> c) {
             JSONObject tmp=(JSONObject) json;
            return (T)JSON.toJavaObject(tmp, c);
    }
   
    @SuppressWarnings("unchecked")
    @Override
    public <T> T JSONObjectToJavaObject(Object objData, Class<?> pojo,Class<?>... args) {
        JSONObject json =(JSONObject) objData;
        Field [] fields = pojo.getDeclaredFields();  
        // 根据传入的Class动态生成pojo对象  
        Object obj = null;
        try {
            obj = pojo.newInstance();
        } catch (InstantiationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }  
        for(Field field: fields){  
            // 设置字段可访问（必须，否则报错）  
        field.setAccessible(true);  
            // 得到字段的属性名  
         String name = field.getName();  
            // 这一段的作用是如果字段在JSONObject中不存在会抛出异常，如果出异常，则跳过。  
    try{  
        json.get(name);  
    }catch(Exception ex){  
        continue;  
    }  
    if(json.get(name) != null && !"".equals(json.getString(name))){  
      // 根据字段的类型将值转化为相应的类型，并设置到生成的对象中。  
        if(field.getType().equals(Long.class) || field.getType().equals(long.class)){  
            try {
                field.set(obj, Long.parseLong(json.getString(name)));
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  
        }else if(field.getType().equals(String.class)){  
            try {
                field.set(obj, json.getString(name));
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  
        } else if(field.getType().equals(Double.class) || field.getType().equals(double.class)){  
            try {
                field.set(obj, Double.parseDouble(json.getString(name)));
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  
        } else if(field.getType().equals(Integer.class) || field.getType().equals(int.class)){  
            try {
                field.set(obj, Integer.parseInt(json.getString(name)));
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  
        } else if(field.getType().equals(java.util.Date.class)){  
            try {
               
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                try {
                    Date date = sdf.parse(json.getString(name));
                    field.set(obj,date);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }  
                
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  
        }
      
        else
        {
            continue;
        }
} }
        
    return (T) obj;  
    }

  

}
