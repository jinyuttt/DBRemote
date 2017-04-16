package DBManager;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import DBError.ErrorCode;
import DBException.DBConnectException;
import DDS_Transfer.IDDS_Protocol;
import DataStruct.DBResult;
import ServerProxy.Proxy;
import Table.DataRowJson;
import Table.DataTableJson;

/**
 * 
 * 
 * 项目名称：IDBStandardized 类名称：SqlDataReader 类描述： 创建人：jinyu 创建时间：2017年3月6日
 * 下午8:43:59 修改人：jinyu 修改时间：2017年3月6日 下午8:43:59 修改备注：
 * 
 * @version
 *
 */
public class SqlDataReader {
    DataTableJson result = null;
    public DBResult  cache=null;

    /**
     * 服务端分配的缓存ID
     */
    private int id = -1;

    /**
     * 数据是否结束
     * 主要是客户端抽取数据使用
     */
    public volatile int eof = 0;

    private  Proxy proxy = null;//通信接口
    public boolean is_MD5=false;//加密
    public String dataState="sucess";//sucess正常返回，reset重置获取,close关闭
    private float requestRate=0.4f;//小于该比例时查询数据
    public volatile boolean cacheQuery=true;
    public volatile boolean isQuery=false;
    public SynchronousQueue<DBResult> syqueue=new SynchronousQueue<DBResult>();
  
  /*
   * 设置连接代理,使用已经存在的
   */
  public void SetProxy(Proxy obj)
  {
      proxy=obj;
  }
  /*
   * 重新创建
   */
  public void SetProxy(String addr,int port)
  {
      proxy=new Proxy(addr,port);
  }
  /*
   * 设置连接，需要重新连接
   */
  public void SetProtocol(IDDS_Protocol protocol,String addr,int port)
  {
      proxy=new Proxy(protocol,addr,port);
  }
    public SqlDataReader(DBResult r) {
        result = (DataTableJson) r.Result;
        id = r.id;
        if (r.Code == ErrorCode.DataAll) {
            eof = 1;
        }
    }

    public DataRowJson next() throws DBConnectException {
        if (eof == 0 && cacheQuery&&result.getReadRate()<this.requestRate) {
            cacheQuery=false;
            proxy.getNextReader(id, dataState,this);
        }
        if(eof == 0&&!result.HasData())
        {
            if(this.isQuery&&this.cache==null)
            {
                //查询还没有返回
//                try {
//                    this.cache.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                try {
                    int count=0;
                    while(true)
                    {
                    cache =this.syqueue.poll(3000, TimeUnit.SECONDS);
                    if(cache==null)
                    {
                        //重新查询
                        cacheQuery=true;
                        dataState="reset";
                        proxy.getNextReader(id, dataState,this);
                        count++;
                        if(count>3)
                        {
                            DBConnectException e=new DBConnectException("服务端通信异常");
                      
                                throw e;
                        }
                    }
                    else
                    {
                        break;
                    }
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
            //没有数据时置换
            result=(DataTableJson) cache.Result;
            id = cache.id;
            cache=null;//已经使用的cache清除
            cacheQuery=true;
        }
        return result.next();
    }

    public void Close() {
        proxy.getNextReader(id, "close",this);
    }

    // public Object Get
    public boolean HasNext() {
        return result.HasData() || eof == 0;

    }
}
