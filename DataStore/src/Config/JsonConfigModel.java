/**    
 * 文件名：JsonConfigModel.java    
 *    
 * 版本信息：    
 * 日期：2017年3月17日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package Config;

/**    
 *     
 * 项目名称：DataStore    
 * 类名称：JsonConfigModel    
 * 类描述：    Json格式配置文件
 * 创建人：jinyu    
 * 创建时间：2017年3月17日 上午2:09:21    
 * 修改人：jinyu    
 * 修改时间：2017年3月17日 上午2:09:21    
 * 修改备注：    
 * @version     
 *     
 */
public class JsonConfigModel {
    // 连接池属性  
    public String driverName="org.postgresql.Driver";  
    public String url="jdbc:postgresql://127.0.0.1:5432/PeostgreDB";  
    public String userName="postgres";  
    public String password="1234";  
    // 连接池名字  
    public String poolName="postgresql";  
    public int poolType=0;//0读取，1只读，2只写
    public boolean isMemory=false;
}
