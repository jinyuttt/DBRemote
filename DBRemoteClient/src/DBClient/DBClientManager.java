package DBClient;



import Common.JsonConvert;

import DBManager.ClientRecvice;
import DBManager.Parameter;
import DBManager.SqlDataReader;
import DDS_Transfer.IDDS_Protocol;
import DataStruct.BatchData;
import DataStruct.DBResult;
import DataStruct.MethodType;
import DataStruct.ParameterJson;
import DataStruct.SqlRequest;

import ProtocolsManager.ProtocolManager;

public class DBClientManager {
    IDDS_Protocol protocol = null;

    public DBClientManager() {
        if (protocol == null) {
            try {
                protocol = (IDDS_Protocol) ProtocolManager.getInstance().CreateObject(ClientConfig.protocol);
                protocol.CreateClient();
                protocol.Connect(ClientConfig.remoteAddr, ClientConfig.remotePort);
            } catch (InstantiationException e) {

                e.printStackTrace();
            } catch (IllegalAccessException e) {

                e.printStackTrace();
            }
        }
    }

    public DBResult ExecuteNonQuery(String sql) {
        SqlRequest request = new SqlRequest();
        request.sql = sql;
        request.methodType = MethodType.ExecuteNonQuery;
        return CreateRequest(request);
    }

    public DBResult ExecuteSclar(String sql) {
        SqlRequest request = new SqlRequest();
        request.sql = sql;
        request.methodType = MethodType.ExecuteSclar;
        return CreateRequest(request);
    }

    public SqlDataReader ExecuteReader(String sql) {
        SqlRequest request = new SqlRequest();
        request.sql = sql;
        request.methodType = MethodType.ExecuteReader;
        return CreateReqReader(request);
    }

    public DBResult ExecuteQuery(String sql) {
        SqlRequest request = new SqlRequest();
        request.sql = sql;
        request.methodType = MethodType.ExecuteQuery;
        return CreateRequest(request);
    }

    public DBResult ExecuteNonQuery(String sql, Parameter... param) {
        SqlRequest request = new SqlRequest();
        request.sql = sql;
        request.methodType = MethodType.ExecuteNonQueryParam;
        BatchData batch = new BatchData();
        for (int i = 0; i < param.length; i++) {
            Parameter cp = param[i];
            ParameterJson p = new ParameterJson();
            p.ParameterType = cp.ParameterType;
            p.ParameterName = cp.ParameterName;
            batch.ParameterInfo.add(p);
            batch.ParameterData.add(cp.ParamValue);
        }
        request.Parameter = batch;
        return CreateRequest(request);

    }

    public DBResult ExecuteSclar(String sql, Parameter... param) {
        SqlRequest request = new SqlRequest();
        request.sql = sql;
        request.methodType = MethodType.ExecuteSclar;
        BatchData batch = new BatchData();
        for (int i = 0; i < param.length; i++) {
            Parameter cp = param[i];
            ParameterJson p = new ParameterJson();
            p.ParameterType = cp.ParameterType;
            p.ParameterName = cp.ParameterName;
            batch.ParameterInfo.add(p);
            batch.ParameterData.add(cp.ParamValue);
        }
        request.Parameter = batch;
        return CreateRequest(request);
    }

    public SqlDataReader ExecuteReader(String sql, Parameter... param) {
        SqlRequest request = new SqlRequest();
        request.sql = sql;
        request.methodType = MethodType.ExecuteSclar;
        BatchData batch = new BatchData();
        for (int i = 0; i < param.length; i++) {
            Parameter cp = param[i];
            ParameterJson p = new ParameterJson();
            p.ParameterType = cp.ParameterType;
            p.ParameterName = cp.ParameterName;
            batch.ParameterInfo.add(p);
            batch.ParameterData.add(cp.ParamValue);
        }
        request.Parameter = batch;
        return CreateReqReader(request);
    }

    public DBResult ExecuteQuery(String sql, Parameter... param) {
        SqlRequest request = new SqlRequest();
        request.sql = sql;
        request.methodType = MethodType.ExecuteQueryParam;
        BatchData batch = new BatchData();
        for (int i = 0; i < param.length; i++) {
            Parameter cp = param[i];
            ParameterJson p = new ParameterJson();
            p.ParameterType = cp.ParameterType;
            p.ParameterName = cp.ParameterName;
            batch.ParameterInfo.add(p);
            batch.ParameterData.add(cp.ParamValue);
        }
        request.Parameter = batch;
        return CreateRequest(request);
    }

    private DBResult CreateRequest(SqlRequest obj) {
        if (protocol != null) {
           try
           {
            //long sumBytes=0;
            byte[] data = JsonConvert.ConvertJsonByte(obj);
            protocol.ClientSocketSendData(data);
            DBResult dbResult;
            ClientRecvice rec=new ClientRecvice(protocol);
            rec.isClose=true;
            if(ClientConfig.Is_MD5)
             {
                rec.is_MD5=true;
           }
            dbResult=rec.recResult();
            return  dbResult;
           }
           catch(Exception ex)
           {
               System.out.println("½ÓÊÕ×Ö½Ú£º"+ex.getMessage());
               protocol.ClientSocketClose();
               return null;
           }
        }
        return null;
    }
    private SqlDataReader CreateReqReader(SqlRequest obj) {
        SqlDataReader reader = null;
        if (protocol != null) {

            byte[] data = JsonConvert.ConvertJsonByte(obj);
            protocol.ClientSocketSendData(data);
         //   data = protocol.RecClientSocket();
            //DBResult result = JsonConvert.ConvertToObj(data, DBResult.class);
            ClientRecvice rec=new ClientRecvice(protocol);
            if(ClientConfig.Is_MD5)
             {
                rec.is_MD5=true;
           }
            DBResult result =rec.recResult();
            reader = new SqlDataReader(result);
            if(ClientConfig.Is_MD5)
            {
                reader.is_MD5=true;
            }
            reader.SetProtocol(protocol,ClientConfig.remoteAddr, ClientConfig.remotePort);
        }
        return reader;
    }
}
