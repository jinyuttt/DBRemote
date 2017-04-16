package DataStruct;

/**
 * 
 * 
 * 项目名称：IDBStandardized 类名称：SqlRequest 类描述： Sql请求 创建人：jinyu
 * 创建时间：2017年3月7日上午12:22:05 修改人：jinyu 修改时间：2017年3月7日 上午12:22:05 修改备注：
 * 
 * @version
 *
 */
public class SqlRequest {
    public String sql = "";

    public String CloseFlage = "false";

    public MethodType methodType = MethodType.ExecuteQuery;

    public BatchData Parameter = null;
    
    public String TestInfo="";
}
