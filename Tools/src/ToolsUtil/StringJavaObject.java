package ToolsUtil;

import java.io.IOException;
import java.net.URI;

public class StringJavaObject extends javax.tools.SimpleJavaFileObject {

  //Դ����  
       private String content = "";  
        //��ѭJava�淶���������ļ�  
         public StringJavaObject(String _javaFileName,String _content){  
              super(_createStringJavaObjectUri(_javaFileName),Kind.SOURCE);  
               content = _content;  
         }  
        //����һ��URL��Դ·��  
       private static URI _createStringJavaObjectUri(String name){  
           //ע��˴�û�����ð���  
            return URI.create("String:///" + name + Kind.SOURCE.extension);  
         }  
        //�ı��ļ�����  
       @Override  
       public CharSequence getCharContent(boolean ignoreEncodingErrors)  
               throws IOException {  
           return content;  
        }  

    

}
