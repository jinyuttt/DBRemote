/**    
 * 文件名：LoadClassFile.java    
 *    
 * 版本信息：    
 * 日期：2017年4月7日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package ToolsUtil;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**    
 *     
 * 项目名称：Tools    
 * 类名称：LoadClassFile    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年4月7日 下午11:57:27    
 * 修改人：jinyu    
 * 修改时间：2017年4月7日 下午11:57:27    
 * 修改备注：    
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
