/**    
 * 文件名：JsonDefaultModel.java    
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
 * 类名称：JsonDefaultModel    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年3月17日 下午10:18:13    
 * 修改人：jinyu    
 * 修改时间：2017年3月17日 下午10:18:13    
 * 修改备注：    
 * @version     
 *     
 */
public class JsonDefaultModel {
    public int minConnections = 1; // 线程池，最小连接数  
    public int maxConnections = 10; // 线程池，最大连接数  
      
    public int initConnections = 5;// 初始化连接数  
      
    public long connTimeOut = 1000;// 重复获得连接的频率  
      
    public int maxActiveConnections = 100;// 最大允许的连接数，和数据库对应  
      
    public long connectionTimeOut = 1000*60*20;// 连接超时时间，默认20分钟  
      
    public boolean isCurrentConnection = true; // 是否获得当前连接，默认true  
      
    public boolean isCheakPool = true; // 是否定时检查连接池  
    public long lazyCheck = 1000*60*60;// 延迟多少时间后开始 检查  
    public long periodCheck = 1000*60*60;// 检查频率  
}
