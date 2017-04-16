/**    
 * �ļ�����CreateMemoryObject.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��4��8��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
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
 * ��Ŀ���ƣ�Tools    
 * �����ƣ�CreateMemoryObject    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��4��8�� ����12:33:52    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��4��8�� ����12:33:52    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class CreateMemoryObject {
  public static CtClass CreateNewCls(String clsName)
  {
      //������    
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
    } //�൱��private String name  
      try {
        cls.addField(param, Initializer.constant(""));
    } catch (CannotCompileException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } //д��class�ļ�   
  }
  public static void AddProty(CtClass cls,String name,String argsType)
  {
      if(cls==null)
      {
          return;
      }
      ClassPool pool = ClassPool.getDefault(); 
        // ���˽�г�Աname����getter��setter����    
 CtField param = null;
try {
    param = new CtField(pool.get(argsType), name, cls);
} catch (CannotCompileException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
} catch (NotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
} //�൱��private String name  
 param.setModifiers(Modifier.PRIVATE);  //˽������  
try {
    cls.addMethod(CtNewMethod.setter("setName", param));
} catch (CannotCompileException e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
}//����set����������Ϊ"setName"  
  try {
    cls.addMethod(CtNewMethod.getter("getName", param));
} catch (CannotCompileException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}//����get����������Ϊgetname  
 try {
    cls.addField(param, Initializer.constant(""));
} catch (CannotCompileException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
} //д��class�ļ�  
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
 // ������    
    ClassPool pool = ClassPool.getDefault();    
    CtClass cls = pool.makeClass(clsName);    
    try {   
        
    // ����޲εĹ�����    
    CtConstructor cons = new CtConstructor(new CtClass[] {}, cls);  //�൱��public Sclass(){this.name = "brant";}  
   // cons.setBody("{name = \"Brant\";}");    
    cls.addConstructor(cons);    
        
    // ����вεĹ�����    
//    cons = new CtConstructor(new CtClass[] {pool.get("java.lang.String")}, cls);//�Ѳ����б�д�ڱ���    
//    cons.setBody("{$0.name = $1;}");  //��һ��������β�$1,$2�ڶ���������βΣ��൱��public Sclass(String s){this.name = s;}  
//    cls.addConstructor(cons);    

        //�����ɵ�class�ļ�д���ļ�,Ҳ���Բ�д��  
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
