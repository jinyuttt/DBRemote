/**    
 * 文件名：DBConnectException.java    
 *    
 * 版本信息：    
 * 日期：2017年4月16日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package DBException;

/**    
 *     
 * 项目名称：IDBStandardized    
 * 类名称：DBConnectException    
 * 类描述：   数据库通信异常 
 * 创建人：jinyu    
 * 创建时间：2017年4月16日 下午8:37:11    
 * 修改人：jinyu    
 * 修改时间：2017年4月16日 下午8:37:11    
 * 修改备注：    
 * @version     
 *     
 */
public class DBConnectException extends Exception {

    /**    
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
     *    
     * @since Ver 1.1    
     */    
    public DBConnectException(String msg)
    {
        super(msg);
    }
    private static final long serialVersionUID = 1L;

}
