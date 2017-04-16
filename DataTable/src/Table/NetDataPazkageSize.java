/**    
 * 文件名：NetDataPazkageSize.java    
 *    
 * 版本信息：    
 * 日期：2017年3月19日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package Table;

/**    
 *     
 * 项目名称：DBSqlManager    
 * 类名称：NetDataPazkageSize    
 * 类描述：    计算传输
 * 创建人：jinyu    
 * 创建时间：2017年3月19日 上午1:40:37    
 * 修改人：jinyu    
 * 修改时间：2017年3月19日 上午1:40:37    
 * 修改备注：    
 * @version     
 *     
 */
public class NetDataPazkageSize {
    FastjsonObj objJson=new FastjsonObj();
 public int  computeSize(DataRowJson row)
 {
    byte[] size=   objJson.ConvertJsonByte(row); 
    if(size!=null)
    {
        return size.length;
    }
    return 0;

 }
}
