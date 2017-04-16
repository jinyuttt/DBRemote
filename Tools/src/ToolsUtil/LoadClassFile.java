/**    
 * �ļ�����LoadClassFile.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��4��7��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package ToolsUtil;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**    
 *     
 * ��Ŀ���ƣ�Tools    
 * �����ƣ�LoadClassFile    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��4��7�� ����11:57:27    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��4��7�� ����11:57:27    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class LoadClassFile {
public static Object Load(String classFile)
{
    File f=new File(classFile);
    if(!f.isFile())
    {
        return null;
    }
    String name=f.getName();
    String className=name.substring(0, name.length()-6);
    URLClassLoader loader = null;
    try {
        loader = new URLClassLoader(new URL[]{new URL("file:"+f.getParent())});
    } catch (MalformedURLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    Object model=null;
    try {
         model=loader.loadClass(className).newInstance();
    } catch (InstantiationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
   
    return model;
}
}
