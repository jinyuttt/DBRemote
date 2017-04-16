/**    
 * �ļ�����ReadConfig.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��17��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
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
 * ��Ŀ���ƣ�DataStore    
 * �����ƣ�ReadConfig    
 * ��������    ��ȡ�����ļ���Json�ļ�
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��17�� ����1:06:44    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��17�� ����1:06:44    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class ReadConfig {
    public static String ConfigFile="DBConfig.json";
    public static String ConfigDefaultFile="DBDefaultConfig.json";
    public static  boolean IsRead=false;//�Ƿ��Ѿ���ȡ���ã�һ���ļ�һ��
    public static  Object obj_lock=new Object();
    /*
     * ��ȡ�����ļ�
     */
 public List<JsonConfigModel> ReadJson()
 {
     String  filePath=ConfigFile;
     File file = new File(filePath);
     if(!file.exists())
     {
         System.out.println("DB�����ļ�������");
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
         System.out.println("DB�����ļ�������");
         return null;

     } finally {
         if (scanner != null) {
             scanner.close();
         }
     }
       return   list;
 }
 
 /*
  * д���ļ�����model
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
        
      FileOutputStream out=new FileOutputStream(f,false); //���׷�ӷ�ʽ��true        
    out.write(json.getBytes("utf-8"));//ע����Ҫת����Ӧ���ַ���
      out.close();
      }
      catch(Exception ex)
      {
          System.out.print(ex.getMessage());
      }

     
 }

 /*
  * ��ȡ�̳߳�����
  */
public JsonDefaultModel ReadDefaultConfig()
{
    String  filePath=ConfigDefaultFile;
    File file = new File(filePath);
    if(!file.exists())
    {
        System.out.println("DBĬ�������ļ�������");
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
        System.out.println("DB�����ļ�������");
        return null;

    } finally {
        if (scanner != null) {
            scanner.close();
        }
    }
      return   list;
}

/*
 * д���ļ�����model
 */
public void WriteDefaultConfigModel()
{
    String  filePath="DBDefaultConfig_templet.json";
    FastjsonObj objJson=new FastjsonObj();
    String  json=  objJson.ConvertJsonString(new JsonDefaultModel(), JsonDefaultModel.class);
    File f=new File(filePath);
     try
     {
         FileOutputStream out=new FileOutputStream(f,false); //���׷�ӷ�ʽ��true        
         out.write(json.getBytes("utf-8"));//ע����Ҫת����Ӧ���ַ���
         out.close();
     }
     catch(Exception ex)
     {
         System.out.print(ex.getMessage());
     }

    
}
}
