/**    
 * �ļ�����DynamicTool.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��4��7��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
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
 * ��Ŀ���ƣ�Tools    
 * �����ƣ�DynamicTool    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��4��7�� ����10:56:18    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��4��7�� ����10:56:18    
 * �޸ı�ע��    
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
      //����sayHello����  
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
      
     //��ǰ������  
     JavaCompiler cmp = ToolProvider.getSystemJavaCompiler();  
    //Java��׼�ļ�������  
    StandardJavaFileManager fm = cmp.getStandardFileManager(null,null,null); 
    Iterable<JavaFileObject> it=null;
    if(filePath!=null&&!filePath.isEmpty())
    {
         it = (Iterable<JavaFileObject>) fm.getJavaFileObjects(new File(filePath));
    }
   //Java�ļ�����  
   JavaFileObject jfo = new StringJavaObject(clsName,source);  
   //���������������javac <options>�е�options  
    List<String> optionsList = new ArrayList<String>();  
   //�����ļ��Ĵ�ŵط���ע�⣺�˴���ΪEclipse���������  
   optionsList.add(classDir);  
  //Ҫ����ĵ�Ԫ  
  List<JavaFileObject> jfos =new ArrayList<JavaFileObject>();
  if(it!=null)
  {
      jfos.addAll((Collection<? extends JavaFileObject>) it);
     
  }
  else
  {
      jfos=Arrays.asList(jfo);
  }
  
   //���ñ��뻷��  
    JavaCompiler.CompilationTask task = cmp.getTask(null, fm, null, optionsList,null,jfos);  
    //����ɹ�  
    if(task.call()){  
         //���ɶ���  
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
