/**    
 * �ļ�����JsonConfig.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��20��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
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
 * ��Ŀ���ƣ�DBProxyServer    
 * �����ƣ�JsonConfig    
 * ��������    ��ȡ����·��
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��20�� ����12:53:29    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��20�� ����12:53:29    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class JsonConfig {
    
   /*
    * ��ȡ��ʽ����
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
 * ���ó�ʼ����ȡ
 */
public void  GlobalRead()
{
    ConfigBean bean=Read();
    GlobalConfig.LocalAddr=bean.addr;
}
}
