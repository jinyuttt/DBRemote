package TestClient;

import java.io.File;


import ScriptFile.FileServerThread;

public class FileTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
       // private final ExecutorService cacheThread=Executors.newCachedThreadPool();
        FileServerThread send=new FileServerThread();
        while(true)
      {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                           e.printStackTrace();
            }
          File f=new File("d:/Pre");
          File[] tempList = f.listFiles();
          System.out.println("��Ŀ¼�¶��������"+tempList.length);
          for (int i = 0; i < tempList.length; i++) {
           if (tempList[i].isFile()) {
            System.out.println("��     ����"+tempList[i]);
            send.StartSend(tempList[i].getAbsolutePath());
            tempList[i].deleteOnExit();
           }
           if (tempList[i].isDirectory()) {
            System.out.println("�ļ��У�"+tempList[i]);
           }
      }
    }}

}
