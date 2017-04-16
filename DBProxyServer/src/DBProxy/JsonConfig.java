/**    
 * 文件名：JsonConfig.java    
 *    
 * 版本信息：    
 * 日期：2017年3月20日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package DBProxy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;



/**    
 *     
 * 项目名称：DBProxyServer    
 * 类名称：JsonConfig    
 * 类描述：    读取配置路径
 * 创建人：jinyu    
 * 创建时间：2017年3月20日 上午12:53:29    
 * 修改人：jinyu    
 * 修改时间：2017年3月20日 上午12:53:29    
 * 修改备注：    
 * @version     
 *     
 */
public class JsonConfig {
    
   /*
    * 读取格式配置
    */
public ConfigBean Read()
{
    File f=new File("Server.json");
    if(!f.exists())
    {
        Write();
    }
    Scanner scanner = null;
    FastjsonObj objJson=new FastjsonObj();
    StringBuffer buffer=new StringBuffer();
    try {
        try {
            scanner = new Scanner(f, "utf-8");
        } catch (FileNotFoundException e) {
        
            e.printStackTrace();
            return null;
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
    ConfigBean bean =objJson.ConvertToObj(mapJson, ConfigBean.class);
    return bean;
   
}
private void Write()
{
    FastjsonObj objJson=new FastjsonObj();
    File f=new File("Server.json");
    FileOutputStream fout=null;
     try {
         fout=new FileOutputStream(f,false);
    } catch (FileNotFoundException e) {
      
        e.printStackTrace();
    }
     ConfigBean bean=new ConfigBean();
     String json=   objJson.ConvertJsonString(bean, ConfigBean.class);
     try {
        fout.write(json.getBytes("utf-8"));
        fout.close();
    } catch (UnsupportedEncodingException e) {
      
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
/*
 * 配置初始化读取
 */
public void  GlobalRead()
{
    ConfigBean bean=Read();
    GlobalConfig.LocalAddr=bean.addr;
}
}
