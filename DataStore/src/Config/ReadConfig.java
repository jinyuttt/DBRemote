/**    
 * 文件名：ReadConfig.java    
 *    
 * 版本信息：    
 * 日期：2017年3月17日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**    
 *     
 * 项目名称：DataStore    
 * 类名称：ReadConfig    
 * 类描述：    读取配置文件，Json文件
 * 创建人：jinyu    
 * 创建时间：2017年3月17日 上午1:06:44    
 * 修改人：jinyu    
 * 修改时间：2017年3月17日 上午1:06:44    
 * 修改备注：    
 * @version     
 *     
 */
public class ReadConfig {
    public static String ConfigFile="DBConfig.json";
    public static String ConfigDefaultFile="DBDefaultConfig.json";
    public static  boolean IsRead=false;//是否已经读取配置，一个文件一次
    public static  Object obj_lock=new Object();
    /*
     * 读取配置文件
     */
 public List<JsonConfigModel> ReadJson()
 {
     String  filePath=ConfigFile;
     File file = new File(filePath);
     if(!file.exists())
     {
         System.out.println("DB配置文件不存在");
         return null;
     }
     Scanner scanner = null;
     FastjsonObj objJson=new FastjsonObj();
     List<JsonConfigModel> list=new ArrayList<JsonConfigModel>();
     try {
         scanner = new Scanner(file, "utf-8");
         while (scanner.hasNextLine()) {
            // buffer.append(scanner.nextLine());
             String json=scanner.nextLine();
             if(json!=null&&!json.isEmpty())
             {
                 try
                 {
                 JsonConfigModel model=   objJson.ConvertToObj(json, JsonConfigModel.class);
                 if(model!=null)
                 {
                     list.add(model);
                 }
                 }
                 catch(Exception ex)
                 {
                     
                 }
             }
         }

     } catch (FileNotFoundException e) {
         System.out.println("DB配置文件不存在");
         return null;

     } finally {
         if (scanner != null) {
             scanner.close();
         }
     }
       return   list;
 }
 
 /*
  * 写入文件配置model
  */
 public void WriteConfigModel()
 {
     String  filePath="DBConfig_templet.json";
    // JsonConfigModel[] list=new JsonConfigModel[]{new JsonConfigModel(),new JsonConfigModel()};
     FastjsonObj objJson=new FastjsonObj();
     String  json=  objJson.ConvertJsonString(new JsonConfigModel(), JsonConfigModel.class);
      File f=new File(filePath);
      try
      {
        
      FileOutputStream out=new FileOutputStream(f,false); //如果追加方式用true        
    out.write(json.getBytes("utf-8"));//注意需要转换对应的字符集
      out.close();
      }
      catch(Exception ex)
      {
          System.out.print(ex.getMessage());
      }

     
 }

 /*
  * 读取线程池配置
  */
public JsonDefaultModel ReadDefaultConfig()
{
    String  filePath=ConfigDefaultFile;
    File file = new File(filePath);
    if(!file.exists())
    {
        System.out.println("DB默认配置文件不存在");
        return null;
    }
    Scanner scanner = null;
    FastjsonObj objJson=new FastjsonObj();
    JsonDefaultModel list=new JsonDefaultModel();
    try {
        scanner = new Scanner(file, "utf-8");
        while (scanner.hasNextLine()) {
           // buffer.append(scanner.nextLine());
            String json=scanner.nextLine();
            if(json!=null&&!json.isEmpty())
            {
                try
                {
                    JsonDefaultModel model=   objJson.ConvertToObj(json, JsonDefaultModel.class);
                if(model!=null)
                {
                    list=model;
                }
                }
                catch(Exception ex)
                {
                    
                }
            }
        }

    } catch (FileNotFoundException e) {
        System.out.println("DB配置文件不存在");
        return null;

    } finally {
        if (scanner != null) {
            scanner.close();
        }
    }
      return   list;
}

/*
 * 写入文件配置model
 */
public void WriteDefaultConfigModel()
{
    String  filePath="DBDefaultConfig_templet.json";
    FastjsonObj objJson=new FastjsonObj();
    String  json=  objJson.ConvertJsonString(new JsonDefaultModel(), JsonDefaultModel.class);
    File f=new File(filePath);
     try
     {
         FileOutputStream out=new FileOutputStream(f,false); //如果追加方式用true        
         out.write(json.getBytes("utf-8"));//注意需要转换对应的字符集
         out.close();
     }
     catch(Exception ex)
     {
         System.out.print(ex.getMessage());
     }

    
}
}
