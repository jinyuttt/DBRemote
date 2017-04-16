package Config;

public class ScriptConfig {
public static String  RemoteAddr=null;
public static int RemotePort=5555;//绑定端口
public static String LocalIP=null;//不绑定
public static int LocalPort=0;//本地
public static int  ThreadPoolMaxSize=30;
public static int FitTaskNum=3;//合理处理线程
public static String FileSavePath="d:/tmp";//接收文件保存位置
public static int MinFreeSpace=50;//M
public static boolean RunServer=true;//停止文件接收
public static String FilePath="d:/SqlFile";//文件处理位置
public static String TmpFile="tmp";//文件临时后者类型
public static String  RFile="dat";//文件类型
}
