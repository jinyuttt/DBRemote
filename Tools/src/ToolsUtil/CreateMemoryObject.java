/**    
 * 文件名：CreateMemoryObject.java    
 *    
 * 版本信息：    
 * 日期：2017年4月8日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package ToolsUtil;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtField.Initializer;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;

/**    
 *     
 * 项目名称：Tools    
 * 类名称：CreateMemoryObject    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年4月8日 上午12:33:52    
 * 修改人：jinyu    
 * 修改时间：2017年4月8日 上午12:33:52    
 * 修改备注：    
 * @version     
 *     
 */
public class CreateMemoryObject {
  public static CtClass CreateNewCls(String clsName)
  {
      //创建类    
      ClassPool pool = ClassPool.getDefault();    
      CtClass cls = pool.makeClass(clsName); 
      return cls;
  }
  public static void AddField(CtClass cls,String name,String argsType)
  {
      if(cls==null)
      {
          return;
      }
      ClassPool pool = ClassPool.getDefault(); 
      CtField param = null;
    try {
        param = new CtField(pool.get(argsType), name, cls);
    } catch (CannotCompileException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (NotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } //相当于private String name  
      try {
        cls.addField(param, Initializer.constant(""));
    } catch (CannotCompileException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } //写入class文件   
  }
  public static void AddProty(CtClass cls,String name,String argsType)
  {
      if(cls==null)
      {
          return;
      }
      ClassPool pool = ClassPool.getDefault(); 
        // 添加私有成员name及其getter、setter方法    
 CtField param = null;
try {
    param = new CtField(pool.get(argsType), name, cls);
} catch (CannotCompileException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
} catch (NotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
} //相当于private String name  
 param.setModifiers(Modifier.PRIVATE);  //私有修饰  
try {
    cls.addMethod(CtNewMethod.setter("setName", param));
} catch (CannotCompileException e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
}//增加set方法，名字为"setName"  
  try {
    cls.addMethod(CtNewMethod.getter("getName", param));
} catch (CannotCompileException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}//增加get方法，名字为getname  
 try {
    cls.addField(param, Initializer.constant(""));
} catch (CannotCompileException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
} //写入class文件  
  }
  public static void AddMethod(CtClass cls,int modifiers, String returnType, String mname, String[] parameters, String[] exceptions, java.lang.String body, String declaring)
  {
      if(cls==null)
      {
          return;
      }
      CtMethod method = null;
    try {
        //
        
        ClassPool pool = ClassPool.getDefault(); 
      
        CtClass rType = null;
        try {
            rType = pool.get(returnType);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        CtClass[]args=new CtClass[parameters.length];
        CtClass[]exceptioncls=new CtClass[exceptions.length];
        CtClass declaringcls = null;
        try {
            declaringcls = pool.get(declaring);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for(int i=0;i<parameters.length;i++)
        {
            try {
                args[i]=pool.get(parameters[i]);
            } catch (NotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for(int i=0;i<exceptioncls.length;i++)
        {
            try {
                exceptioncls[i]=pool.get(exceptions[i]);
            } catch (NotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        method = CtNewMethod.make(rType, mname, args, exceptioncls, body, declaringcls);
    } catch (CannotCompileException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
      try {
        cls.addMethod(method);
    } catch (CannotCompileException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
  }
public static CtClass Create(String clsName)
{
 // 创建类    
    ClassPool pool = ClassPool.getDefault();    
    CtClass cls = pool.makeClass(clsName);    
    try {   
        
    // 添加无参的构造体    
    CtConstructor cons = new CtConstructor(new CtClass[] {}, cls);  //相当于public Sclass(){this.name = "brant";}  
   // cons.setBody("{name = \"Brant\";}");    
    cls.addConstructor(cons);    
        
    // 添加有参的构造体    
//    cons = new CtConstructor(new CtClass[] {pool.get("java.lang.String")}, cls);//把参数列表写在本行    
//    cons.setBody("{$0.name = $1;}");  //第一个传入的形参$1,$2第二个传入的形参，相当于public Sclass(String s){this.name = s;}  
//    cls.addConstructor(cons);    

        //把生成的class文件写入文件,也可以不写入  
//        byte[] byteArr = ctClass.toBytecode();    
//        FileOutputStream fos = new FileOutputStream(new File("D://Emp.class"));    
//        fos.write(byteArr);    
//        fos.close();    

    } catch (CannotCompileException e) {  
        // TODO Auto-generated catch block  
        e.printStackTrace();  
    }
    return cls; 
      
      
}
}
