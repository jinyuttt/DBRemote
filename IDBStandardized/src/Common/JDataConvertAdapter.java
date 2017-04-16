/**    
 * 文件名：JDataConvertAdapter.java    
 *    
 * 版本信息：    
 * 日期：2017年3月11日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package Common;

/**    
 *     
 * 项目名称：IDBStandardized    
 * 类名称：JDataConvertAdapter    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年3月11日 下午7:52:11    
 * 修改人：jinyu    
 * 修改时间：2017年3月11日 下午7:52:11    
 * 修改备注：    
 * @version     
 *     
 */
public class JDataConvertAdapter {
 private static  JSonObj jobj=null;
public static JSonObj GetObj()
{
    if(jobj==null)
    {
        switch(DataConfig.JSonPack)
        {
        case "jackson":
            jobj=new JacksonObj();
            break;
        case "fastjson":
            jobj=new FastjsonObj();
            break;
        case "gson":
            jobj=new GSONObj();
            default:
                jobj=new GSONObj();
                break;
        }
    }
    return jobj;
}
}
