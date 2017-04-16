/**    
 * �ļ�����JDataConvertAdapter.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��11��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package Common;

/**    
 *     
 * ��Ŀ���ƣ�IDBStandardized    
 * �����ƣ�JDataConvertAdapter    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��11�� ����7:52:11    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��11�� ����7:52:11    
 * �޸ı�ע��    
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
