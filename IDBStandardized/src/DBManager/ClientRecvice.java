/**    
 * 文件名：ClientRecvice.java    
 *    
 * 版本信息：    
 * 日期：2017年4月9日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package DBManager;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

import Common.JsonConvert;
import Common.NetDataMD5;

import DBError.ErrorCode;
import DDS_Transfer.IDDS_Protocol;
import DataStruct.DBResult;


import Table.DataTableJson;

/**    
 *     
 * 项目名称：IDBStandardized    
 * 类名称：ClientRecvice    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年4月9日 下午4:52:37    
 * 修改人：jinyu    
 * 修改时间：2017年4月9日 下午4:52:37    
 * 修改备注：    
 * @version     
 *     
 */
public class ClientRecvice {
    IDDS_Protocol protocol=null;
   public boolean is_MD5=false;
   public boolean isClose=false;//是否关闭
   public static int num=0;
   public ClientRecvice(IDDS_Protocol protocol)
   {
       this.protocol=protocol;
   }
    /*
     * 接收数据
     */
 public DBResult  recResult()
 {
     byte[] data=null;
     byte[] buffer=null;
     DBResult dbResult=null;
     while(true)
     {
       
        try
         {
           data = protocol.RecClientSocketData();
         }
         catch(Exception ex)
         {
             ex.printStackTrace();
             break;
         }
   
     if(is_MD5)
     {
        data= NetDataMD5.decryptBy3DES(data);
     }
     //
     ByteBuffer buf=null;
     if(data==null)
     {
         break;
     }
     if(buffer!=null)
     {
         buf=ByteBuffer.allocate(buffer.length+data.length);
         buf.put(buffer);
         buf.put(data);
         buf.flip();
         
     }
     else
     {
      
         buf=ByteBuffer.wrap(data);
         buf.limit();
     }
     //按照字节接受;循序读取
   
     List<byte[]> list=RecviceGroupData(buf);
    for(int i=0;i<list.size();i++)
     {
      
     DBResult result = JsonConvert.ConvertToObj(list.get(i), DBResult.class);
     //
     if(dbResult==null)
     {
         DataTableJson r=null;
         dbResult=result;
         r=DataTableJsonConvert.Convert(dbResult);
         if(r!=null)
         {
           dbResult.Result=r;
         }
     }
     else
     {
          DataTableJson r=null;
          DataTableJson tmp=null;
          r=(DataTableJson) dbResult.Result;
          tmp=DataTableJsonConvert.Convert(result);
        
          try
          {
              if(r==null)
              {
                  r=tmp;
              }
              else
              {
               r.loadDataTableJson(tmp);
              }
          }
          catch(Exception ex)
          {
              System.out.println("合并失败："+ex.getMessage());
          }
          dbResult.Code=result.Code;
          dbResult.id=result.id;
     }
     }
     //
    buffer=new byte[buf.limit()-buf.position()];
    if(buffer.length>0)
    {
        System.out.println("剩余字节："+buffer.length);
        buf.get(buffer);
    }
    else
    {
        buffer=null;
    }
     
     //
    if(dbResult!=null)
    {
    if(dbResult.Code==ErrorCode.DataAll||dbResult.Code==ErrorCode.ReaderGroup)
    {
         //数据查询完成则断开
        System.out.println("查询数据库完成！");
        if(isClose||dbResult.Code==ErrorCode.DataAll)
         {
            protocol.ClientSocketClose();
         }
        break;
    }
    }
     }
     try
     {
        // DataTableJson r=(DataTableJson) dbResult.Result;
        // System.out.println("返回行数:"+r.getRowNum());
     }
     catch(Exception ex)
     {
         ex.printStackTrace();
     }
     return  dbResult;
    }
 /*
  * 利益引用传递
  */
 private List<byte[]> RecviceGroupData(ByteBuffer data)
 {
     LinkedList<byte[]> list=new  LinkedList<byte[]>();
    while(data.position()<data.limit())
    {
      int len=(int)data.getLong();
      if(data.position()+len>data.limit())
      {
          //数据不足了；
          System.out.println("当前批次长度:"+len);
          System.out.println("当前批次不足:"+(data.limit()-data.position()));
          data.position(data.position()-8);//数据置回
          break;
      }
      byte[] bytedata=new byte[len];
      data.get(bytedata);
      list.add(bytedata);
    }
     return list;
     
 }
}
