package ServerCommon;

public class ServerConfig {
    /**
     * 返回客户端时的行数
     */
    public static int BackClient = 500;

    public static int GlobalID = 0;

    // SqlDataReader缓存使用
    public static int SqlReaderNum = 500;
    
    public static String ConnectionString="postgresql";
    
    /*
     * 加密验证
     */
    public static boolean Is_MD5=false;
    
    /*
     * 网络优化设置
     */
    public static boolean Is_NetOption=false;
    
    /*
     * 每次发送的数据大小（M)
     */
    public static int   RowComputer=30;
}
