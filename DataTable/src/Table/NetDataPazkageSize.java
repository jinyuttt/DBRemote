/**    
 * �ļ�����NetDataPazkageSize.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��19��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package Table;

/**    
 *     
 * ��Ŀ���ƣ�DBSqlManager    
 * �����ƣ�NetDataPazkageSize    
 * ��������    ���㴫��
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��19�� ����1:40:37    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��19�� ����1:40:37    
 * �޸ı�ע��    
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
