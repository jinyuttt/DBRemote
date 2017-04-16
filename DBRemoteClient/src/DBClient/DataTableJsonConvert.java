/**    
 * 文件名：DataTableJsonConvert.java    
 *    
 * 版本信息：    
 * 日期：2017年4月9日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package DBClient;

import java.util.ArrayList;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import Common.JsonConvert;
import DataStruct.DBResult;
import DataTypeColumn.Column;

import Table.DataTableJson;
import Table.JsonConvertTable;

/**    
 *     
 * 项目名称：DBRemoteClient    
 * 类名称：DataTableJsonConvert    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年4月9日 上午12:45:53    
 * 修改人：jinyu    
 * 修改时间：2017年4月9日 上午12:45:53    
 * 修改备注：    
 * @version     
 *     
 */
public class DataTableJsonConvert {
public static DataTableJson Convert(DBResult result)
{
    DataTableJson r;
    if(result.Result instanceof Character)
    {
        r=JsonConvert.ConvertToObj(result.Result.toString(), DataTableJson.class);
        result.Result=r;
    }
    else  if(result.Result instanceof Integer||result.Result instanceof Byte||result.Result instanceof Short
            ||result.Result instanceof Long||result.Result instanceof Float||result.Result instanceof Double)
    {
        r=null;
    }
    else if(result.Result instanceof JSONObject)
    {
         String json=JsonConvert.ConvertJsonString(result.Result,DataTableJson.class);
         r=JsonConvert.ConvertToObj(json, DataTableJson.class);
        // r=(DataTableJson) JsonConvert.JsonObjectToObj(result.Result,DataTableJson.class);
         r.columns=getColumns(result.Result);
         result.Result=r;
    }
    else
    {
        r=(DataTableJson) JsonConvert.JsonObjectToObj(result.Result, DataTableJson.class);
        result.Result=r;
    }
    return r;
    }
private static List<Column> getColumns(Object objData)
{
      ArrayList<Column> list=new ArrayList<Column>();
     JSONObject  table=(JSONObject)objData;
     Object allColumn=  table.get("columns");
     JSONArray cols=(JSONArray) allColumn;
     int colNum=cols.size();
     for(int i=0;i<colNum;i++)
     {
       JSONObject  colObj= cols.getJSONObject(i);
       Object colType=colObj.get("columnType");
       Column  pCol=JsonConvertTable.getColumn(colType.toString());
       pCol=(Column) JsonConvert.JsonObjectToObj(colObj, pCol.getClass());
       pCol.getRowNum();
       list.add(pCol);
       
     }
     return list;
}
}
