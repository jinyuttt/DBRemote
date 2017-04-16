package Com.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Com.DAL.IPoolDAL;

import Config.JsonConfigModel;
import Config.JsonDefaultModel;
import Config.ReadConfig;
import ConnectPoolManager.ConnectionPoolManager;
import ConnectPoolManager.DBInitInfo;
import ConnectPoolManager.DBbean;

public class DBManager implements IPoolDAL {
    public String poolName = "";
    public DBManager()
    {
        ReadJsonConfig();
    }
    /*
     * 读取配置信息
     */
    private void ReadJsonConfig()
    {
        if(ReadConfig.IsRead)
        {
           return;
        }
        synchronized(ReadConfig.obj_lock)
        {
            if(ReadConfig.IsRead)
            {
               return;
            }
        ReadConfig.IsRead=true;
        ReadConfig rd=new ReadConfig();
        List<JsonConfigModel>   list=rd.ReadJson();
        boolean dbpool=true;
        boolean dbDefault=true;
        if(list==null||list.isEmpty())
        {
            dbpool=false;
             rd.WriteConfigModel();
        }
        JsonDefaultModel model=rd.ReadDefaultConfig();
        if(model==null)
        {
            dbDefault=false;
            rd.WriteDefaultConfigModel();
        }
        if(dbpool&&dbDefault)
        {
            InitConfig(list,model);
        }

        }
    }
    
    /*
     * 初始化配置
     */
  private void  InitConfig(List<JsonConfigModel>list,JsonDefaultModel listmodel)
  {
      DBInitInfo.beans.clear();
    //  DBInitInfo.beans.addAll(list);
      if(list!=null)
      {
          for(int i=0;i<list.size();i++)
          {
              DBbean tmp=new DBbean(); 
              JsonConfigModel model=list.get(i);
              {
                  tmp.setDriverName(model.driverName);
                tmp.setUrl(model.url);
                 tmp.setUserName(model.userName);
                 tmp.setPassword(model.password);  
                  // 连接池名字  
                 tmp.setPoolName(model.poolName); 
                 tmp.setMinConnections(listmodel.minConnections); 
                 tmp.setMaxConnections(listmodel.maxConnections); 
                    
                 tmp.setInitConnections(listmodel.initConnections);
                    
                 tmp.setConnTimeOut(listmodel.connTimeOut);  
                    
                 tmp.setMaxActiveConnections(listmodel.maxActiveConnections); 
                    
                 tmp.setConnectionTimeOut(listmodel.connectionTimeOut);
                    
                 //tmp.seti =model.isCurrentConnection;  
                    
                 tmp.setCheakPool(listmodel.isCheakPool);
                 tmp.setLazyCheck(listmodel.lazyCheck);
                 tmp.setPeriodCheck(listmodel.periodCheck); 
                 tmp.poolType=model.poolType;
                 tmp.setMemory(model.isMemory);
                 DBInitInfo.beans.add(tmp);
                 if(model.poolType==0)
                 {
                     DBPool.Put(1, model.poolName);
                     DBPool.Put(2, model.poolName);
                 }
                 else
                 {
                 DBPool.Put(model.poolType, model.poolName);
                 }
              }
          }
      }
      
  }
    @Override
     public Statement GetStatement() {
        try {
            Connection conn = ConnectionPoolManager.getInstance().getConnection(poolName);
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            return st;
        } catch (Exception ex) {
            return null;
        }
    }
   /*
    *  
    *  准备查询
    */
    @Override
    public PreparedStatement GetPreparedStatement(String Sql) {

        try {
            Connection conn = ConnectionPoolManager.getInstance().getConnection(poolName);
            PreparedStatement prest = conn.prepareStatement(Sql);
            return prest;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ResultSet executeQuery(String sql) {

        try {
            poolName=DBPool.GetPools(1);
            Statement st = this.GetStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public int executeUpdate(String sql) {

        try {
            poolName=DBPool.GetPools(2);
            Statement st = this.GetStatement();
            int rs = st.executeUpdate(sql);
             st.close();
            return rs;
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public void closeAll(PreparedStatement prsts, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (prsts != null) {
            try {
                prsts.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ConnectionPoolManager.getInstance().close(poolName);

    }

    @Override
    public int executeUpdate(String sql, Object[] param, int[] type) {
        int rows = 0;

        PreparedStatement prsts = null;
        try {
            poolName=DBPool.GetPools(2);
            prsts = this.GetPreparedStatement(sql);
            for (int i = 1; i <= param.length; i++) {
                prsts.setObject(i, param[i - 1], type[i - 1]);
            }
            rows = prsts.executeUpdate();
        } catch (SQLException e) {

        } finally {
            try {
                prsts.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
        return rows;

    }

    @Override
    public List<Map<String, Object>> executeQuery(String sql, Object[] param, int[] type) {

        ResultSet rs = null;
        List<Map<String, Object>> list = null;

        PreparedStatement prsts = null;
        try {
            poolName=DBPool.GetPools(1);
            prsts = this.GetPreparedStatement(sql);
            for (int i = 1; i <= param.length; i++) {
                prsts.setObject(i, param[i - 1], type[i - 1]);
            }
            rs = prsts.executeQuery();

            list = new ArrayList<Map<String, Object>>();
            ResultSetMetaData rsm = rs.getMetaData();
            Map<String, Object> map = null;
            while (rs.next()) {
                map = new HashMap<String, Object>();
                for (int i = 1; i <= rsm.getColumnCount(); i++) {
                    map.put(rsm.getColumnName(i), rs.getObject(rsm.getColumnName(i)));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll(prsts, rs);
        }
        return list;

    }

    @Override
    public List<?> executeQueryList(String sql) {
        ResultSet rs = null;
        List<Map<String, Object>> list = null;

        try {
            poolName=DBPool.GetPools(1);
            rs = this.executeQuery(sql);
            list = new ArrayList<Map<String, Object>>();
            ResultSetMetaData rsm = rs.getMetaData();
            Map<String, Object> map = null;
            while (rs.next()) {
                map = new HashMap<String, Object>();
                for (int i = 1; i <= rsm.getColumnCount(); i++) {
                    map.put(rsm.getColumnName(i), rs.getObject(rsm.getColumnName(i)));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll(null, rs);
        }
        return list;
    }

    @Override
    public int executeInsertBatch(String sql, Object[] param, int[] type, int num) {

        // 从池中获取连接
        if (num == 0) {
            num = 500;
        }
        int index = 0;
        try {
            Connection conn = ConnectionPoolManager.getInstance().getConnection(poolName);

            PreparedStatement pstmt = this.GetPreparedStatement(sql);
            conn.setAutoCommit(false);
            for (int i = 1; i <= param.length; i++) {
                pstmt.setObject(i, param[i - 1], type[i - 1]);
                index++;
                if (index == num) {
                    // 加入批处理
                    pstmt.addBatch();
                    pstmt.executeBatch(); // 执行批处理
                    index = 0;
                }
            }
            if (index > 0) {
                // 加入批处理
                pstmt.addBatch();
                pstmt.executeBatch(); // 执行批处理
            }
            conn.setAutoCommit(true);
            pstmt.close();
            return 1;
        } catch (Exception ex) {
            return 0;
        }

    }

    @Override
    public boolean executeSql(String sql) {
        try {
            Statement st = this.GetStatement();
            boolean r = st.execute(sql);
            return r;
        } catch (Exception ex) {
            return false;
        }

    }

    @Override
    public boolean executeSql(String sql, Object[] param, int[] type) {
        boolean r = false;
        PreparedStatement prsts = null;
        try {
            prsts = this.GetPreparedStatement(sql);
            for (int i = 1; i <= param.length; i++) {
                prsts.setObject(i, param[i - 1], type[i - 1]);
            }
            r = prsts.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return r;
    }

    @Override
    public ResultSet executeQueryParam(String sql, String[] paramName, Object[] param, int[] type) {
        ResultSet rs = null;
        PreparedStatement prsts = null;
        try {
            prsts = this.GetPreparedStatement(sql);
            for (int i = 1; i <= param.length; i++) {
                prsts.setObject(i, param[i - 1], type[i - 1]);
            }
            rs = prsts.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll(prsts, rs);
        }
        return rs;
    }

}
