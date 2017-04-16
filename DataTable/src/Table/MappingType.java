/**    
 * 文件名：MappingType.java    
 *    
 * 版本信息：    
 * 日期：2017年3月18日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package Table;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.HashMap;

import java.util.Map;
import java.util.Scanner;



/**    
 *     
 * 项目名称：DataTable    
 * 类名称：MappingType    
 * 类描述：    列类型存储
 * 创建人：jinyu    
 * 创建时间：2017年3月18日 上午11:32:25    
 * 修改人：jinyu    
 * 修改时间：2017年3月18日 上午11:32:25    
 * 修改备注：    
 * @version     
 *     
 */
public class MappingType {
    
    public static String MapTypeConfig="DBMapType.json";
    public static boolean Is_Kinship=true;
    private static HashMap<String,String> hashType=new HashMap<String,String>();
    static
    {
       
        hashType.put("CHAR", "String");
        hashType.put("VARCHAR", "String");
        hashType.put("NUMERIC", "BigDecimal");
        hashType.put("DECIMAL", "BigDecimal");
        hashType.put("BIT", "boolean");
        hashType.put("TINYINT", "byte");
        hashType.put("SMALLINT", "short");
        hashType.put("INTEGER", "int");
        hashType.put("BIGINT", "long");
        hashType.put("REAL", "float");
        hashType.put("FLOAT", "double");
        hashType.put("DOUBLE", "double");
        hashType.put("BINARY", "byte[]");
        hashType.put("VARBINARY", "byte[]");
        hashType.put("LONGVARBINARY", "byte[]");
        hashType.put("DATE", "DATE");
        hashType.put("TIMESTAMP", "long");
        ReadConfigMapType();
    }
    private static void WriteMapType()
    {
        FastjsonObj objJson=new FastjsonObj();
        File f=new File(MapTypeConfig);
        FileOutputStream fout=null;
         try {
             fout=new FileOutputStream(f,false);
        } catch (FileNotFoundException e) {
          
            e.printStackTrace();
        }
       
         String json=   objJson.ConvertJsonString(hashType, HashMap.class);
         try {
            fout.write(json.getBytes("utf-8"));
            fout.close();
        } catch (UnsupportedEncodingException e) {
          
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void  ReadConfigMapType()
    {
        String  filePath=MapTypeConfig;
        File file = new File(filePath);
        if(!file.exists())
        {
            System.out.println("MAP配置文件不存在");
            WriteMapType();
            return ;
        }
        Scanner scanner = null;
        FastjsonObj objJson=new FastjsonObj();
        StringBuffer buffer=new StringBuffer();
        try {
            try {
                scanner = new Scanner(file, "utf-8");
            } catch (FileNotFoundException e) {
            
                e.printStackTrace();
                return;
            }
            while (scanner.hasNextLine()) {
            
               String json=scanner.nextLine();
                if(json!=null&&!json.isEmpty())
                {
                    buffer.append(json);
                   
                }
            }

        }  finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        String mapJson=buffer.toString();
        HashMap<String,String> hash=objJson.ConvertToObj(mapJson, Map.class);
        hashType.putAll(hash);
        
    }
   public String CheckKinship(String dataType)
   {
       String  ship="";
       String curType=dataType.toLowerCase();
       if(curType.indexOf("int")>-1)
       {
           ship="int";
       }
       else if(curType.indexOf("char")>-1)
       {
           ship="String";
       }
       else if(curType.indexOf("text")>-1)
       {
           ship="String";
       }
       else if(curType.indexOf("real")>-1)
       {
           ship="float";
       }
       return ship;
   }
public String GetTypeName(String dataType)
{
    dataType=dataType.toUpperCase().trim();
    String columnType=hashType.getOrDefault(dataType, null);
    if(columnType==null)
    {
        if(Is_Kinship)
        {
            columnType= CheckKinship(dataType);
            if(columnType.isEmpty())
            {
                columnType=null;
            }
        }
    }
    if(columnType==null)
     {
        columnType="String";
     }
  return columnType;
}
}
