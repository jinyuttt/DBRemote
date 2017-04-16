/**    
 * 文件名：IDBServerPlugin.java    
 *    
 * 版本信息：    
 * 日期：2017年4月10日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package IDBServerPlugin;

import java.util.HashMap;

/**    
 *     
 * 项目名称：DBServerPlugin    
 * 类名称：IDBServerPlugin    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年4月10日 上午12:31:02    
 * 修改人：jinyu    
 * 修改时间：2017年4月10日 上午12:31:02    
 * 修改备注：    
 * @version     
 *     
 */
public interface IDBServerPlugin {
public boolean start(HashMap<String, Object> args);
public boolean stop(long timeOut);
public boolean addData(Object data);
public void setName(String name);
public String getName();

}
