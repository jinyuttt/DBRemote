package TestClient;
import DBClient.DBClientManager;
import DBException.DBConnectException;
import DBManager.SqlDataReader;
import DataStruct.DBResult;

public class TestClient {

	public static void main(String[] args)  {
	
		while(true)
		{
		  String sql = "select * from test";
		 // sql="insert into test values(1,'ffsdgd','ss','dfghj','rtewert')";
		 long ss=System.currentTimeMillis();
		  DBClientManager client=new DBClientManager();
		 // long ss=System.currentTimeMillis();
	      // DBResult r=	client.ExecuteSclar(sql);
		// DBResult   r=client.ExecuteNonQuery(sql);
		  DBResult   r=null;
		  long num=0;
		//  r=client.ExecuteQuery(sql);
		 SqlDataReader rd=  client.ExecuteReader(sql);
		  while(rd.HasNext())
		  {
		      try {
                rd.next();
            } catch (DBConnectException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		      num++;
		  }
		  //
		  rd.Close();
		  long mm=System.currentTimeMillis();
          long ls=mm-ss;
          System.out.println("耗时："+ls/1000);
          System.out.println("总行数："+num);
	       if(r!=null)
	       {
	    	  // String strObj="";
//	    	   if(r.Result!=null)
//	    	   {
//	    		   strObj=r.Result.toString();
//	    		   System.out.println(strObj);
//	    	   }
	    	//  if(!strObj.isEmpty())
	    	  {
//	    		  DataTableJson dttmp = JsonConvert.ConvertToObj(strObj, DataTableJson.class);
//	    		  if(dttmp!=null)
//	    		  {
	    			  System.out.println("查询数据成功");
	    		 // }
	    	  }
            }
	       else
	       {
	           System.out.println("返回空");
	       }
				try {
					Thread.sleep(10000*6);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
         
         
	}

}
