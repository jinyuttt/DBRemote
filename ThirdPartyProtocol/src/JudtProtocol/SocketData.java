/**    
 * 文件名：SocketData.java    
 *    
 * 版本信息：    
 * 日期：2017年4月14日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package JudtProtocol;

import DDS_Transfer.IRecMsg;
import udt.UDTSocket;

/**    
 *     
 * 项目名称：ThirdPartyProtocol    
 * 类名称：SocketData    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年4月14日 上午12:36:28    
 * 修改人：jinyu    
 * 修改时间：2017年4月14日 上午12:36:28    
 * 修改备注：    
 * @version     
 *     
 */
public class SocketData {
    int id;
    int timer;
    UdtProtocol protol;
    UDTSocket socket;
    IRecMsg outRec;
}
