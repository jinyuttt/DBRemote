/**    
 * 文件名：DynamicTool.java    
 *    
 * 版本信息：    
 * 日期：2017年4月7日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package ToolsUtil;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**    
 *     
 * 项目名称：Tools    
 * 类名称：DynamicTool    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年4月7日 下午10:56:18    
 * 修改人：jinyu    
 * 修改时间：2017年4月7日 下午10:56:18    
 * 修改备注：    
 * @version     
 *     
 */
public class DynamicTool {
  String source="";
  String methodName="";
  String clsName="TmpClass";
  private Object newObj=null;
  private String filePath="";
  private String classDir="ClassTmp";
  public void setSource(String classContent)
  {
      source=classContent;
  }
  public void setMethod(String name)
  {
      methodName=name;
  }
  public void setFilePath(String file)
  {
      filePath=file;
  }
  public void setClassName(String clsName)
  {
      this.clsName=clsName;
  }
  public void setClassPath(String dir)
  {
      if(dir==null||dir.isEmpty())
      {
          System.out.println("ClassPath:"+classDir);
      }
      else
      {
          File f=new File(dir);
          if(!f.exists())
          {
              f.mkdir();
          }
          if(f.isDirectory())
          {
              classDir=f.getAbsolutePath();
          }
      }
  }
  public Object invokeMethod(Object[] args,Class<?>[] argsClass)
  {
      Class<? extends Object> cls = newObj.getClass();  
      //调用sayHello方法  
        Method m = null;
        try {
            m = cls.getMethod(methodName, argsClass);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
      Object r = null;
   
        try {
            r =m.invoke(newObj, args);
        } catch (IllegalAccessException e) {
         
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
          
            e.printStackTrace();
        } catch (InvocationTargetException e) {
          
            e.printStackTrace();
        }
        return r;
  }
  @SuppressWarnings("unchecked")
public Object CreateObj()
  {
      
     //当前编译器  
     JavaCompiler cmp = ToolProvider.getSystemJavaCompiler();  
    //Java标准文件管理器  
    StandardJavaFileManager fm = cmp.getStandardFileManager(null,null,null); 
    Iterable<JavaFileObject> it=null;
    if(filePath!=null&&!filePath.isEmpty())
    {
         it = (Iterable<JavaFileObject>) fm.getJavaFileObjects(new File(filePath));
    }
   //Java文件对象  
   JavaFileObject jfo = new StringJavaObject(clsName,source);  
   //编译参数，类似于javac <options>中的options  
    List<String> optionsList = new ArrayList<String>();  
   //编译文件的存放地方，注意：此处是为Eclipse工具特设的  
   optionsList.add(classDir);  
  //要编译的单元  
  List<JavaFileObject> jfos =new ArrayList<JavaFileObject>();
  if(it!=null)
  {
      jfos.addAll((Collection<? extends JavaFileObject>) it);
     
  }
  else
  {
      jfos=Arrays.asList(jfo);
  }
  
   //设置编译环境  
    JavaCompiler.CompilationTask task = cmp.getTask(null, fm, null, optionsList,null,jfos);  
    //编译成功  
    if(task.call()){  
         //生成对象  
         try {
            newObj = Class.forName(clsName).newInstance();
        } catch (InstantiationException e) {
        
            e.printStackTrace();
        } catch (IllegalAccessException e) {
           
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
         
            e.printStackTrace();
        }
    }
    return newObj;
      
  }

}
