package Common;

import java.io.IOException;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonObj implements JSonObj {
  //private static JsonGenerator jsonGenerator = null;
    //private static ObjectMapper objectMapper = null;
    private    final ObjectMapper objectMapper = new ObjectMapper();
    public  JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
              return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        //objectMapper.getTypeFactory().constructParametricType(collectionClass,elementClasses);
       }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T ConvertToObj(String json, Class<?> c) {
      //jsonGenerator.writeObject(obj);  
        //JavaType javaType = getCollectionType(ArrayList.class, c);
        try {
            return  (T) objectMapper.readValue(json,c);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T ConvertToObj(byte[] jsonBytes, Class<?> c) {
       // JavaType javaType = getCollectionType(ArrayList.class, c);
        //jsonGenerator.writeObject(obj);    
        try {
            
        return  (T) objectMapper.readValue(jsonBytes, c);
        } catch (JsonGenerationException e) {
        
            e.printStackTrace();
        } catch (JsonMappingException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> String ConvertJsonString(T obj, Class<?> c) {
      //jsonGenerator.writeObject(obj); 
        //JavaType javaType = getCollectionType(ArrayList.class, c); 
  try {
  return  objectMapper.writeValueAsString(obj);
  } catch (JsonGenerationException e) {
      e.printStackTrace();
  } catch (JsonMappingException e) {
      e.printStackTrace();
  } catch (IOException e) {
      e.printStackTrace();
  }
  return null;
    }

    @Override
    public <T> byte[] ConvertJsonByte(T obj) {
      //jsonGenerator.writeObject(obj);    
        try {
        return  objectMapper.writeValueAsBytes(obj);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
