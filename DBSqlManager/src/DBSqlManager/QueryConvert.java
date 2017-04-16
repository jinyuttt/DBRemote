package DBSqlManager;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import DataTypeColumn.Column;
import Table.DataColumConvert;

import Table.DataTableJson;


/**
 * 
 * 
 * 项目名称：DBSqlManager 类名称：QueryConvert 类描述： 将查询结构转换成Json表结构 创建人：jinyu
 * 创建时间：2017年3月7日 上午2:15:58 修改人：jinyu 修改时间：2017年3月7日 上午2:15:58 修改备注：
 * 
 * @version
 *
 */
public class QueryConvert {

    /**
     * 
     * @param rs
     *            查询对象
     * @param rowNum
     *            查询行数
     * @param cursor
     *            当前游标
     * @return 表数据
     */
    public static DataTableJson ConvertDataTable(ResultSet rs, int rowNum, int cursor) {
        try {
            int curIndex = 0;
            if (cursor != 0 || rowNum != -1) {
                rs.absolute(cursor);
            }
            DataTableJson dt = new DataTableJson();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNum = rsmd.getColumnCount();
            for (int i = 1; i <= columnNum; i++) {
               // DataColumnJson column = new DataColumnJson();
                String  typeName=rsmd.getColumnTypeName(i);
                Column column=DataColumConvert.Convert(typeName);
                column.dataType = rsmd.getColumnType(i);
                column.name = rsmd.getColumnName(i);
                column.ordinal = i;
                column.caption=rsmd.getColumnLabel(i);
                column.columnTypeName=rsmd.getColumnTypeName(i);
                dt.tableName=rsmd.getColumnName(i);
                column.maxLength=rsmd.getColumnDisplaySize(i);
                //dt.Columns.add(column);
                 dt.columns.add(column);
                dt.columnsIndex.put(column.name,  column.ordinal);
            }
            //
            while (rs.next()) {
                {
                    curIndex++;
                    dt.Cursor = curIndex + cursor;
                    if (curIndex > rowNum && rowNum != -1) {
                        break;
                    }
                    for (int i = 0; i < columnNum; i++) {
                        try {
                            //DataColumnJson tmp = dt.Columns.get(i);
                            Column tmp= dt.columns.get(i);
                            tmp.addData(rs.getObject(i + 1));
                            //tmp.listData.add(rs.getObject(i + 1));
                        } catch (NullPointerException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                }
                return dt;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static DataTableJson ConvertDataTable(ResultSet rs, int rowNum) {
        try {
            int curIndex = 0;
            DataTableJson dt = new DataTableJson();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNum = rsmd.getColumnCount();
            for (int i = 1; i <= columnNum; i++) {
               // DataColumnJson column = new DataColumnJson();
                String  typeName=rsmd.getColumnTypeName(i);
                Column column=DataColumConvert.Convert(typeName);
                column.dataType = rsmd.getColumnType(i);
                column.name = rsmd.getColumnName(i);
                column.ordinal = i;
                column.caption=rsmd.getColumnLabel(i);
                column.columnTypeName=rsmd.getColumnTypeName(i);
                dt.tableName=rsmd.getColumnName(i);
                column.maxLength=rsmd.getColumnDisplaySize(i);
                dt.columns.add(column);
                dt.columnsIndex.put(column.name,  column.ordinal);

            }
            //
            while (rs.next()) 
                {
                    try
                    {
                    curIndex++;
                    dt.Cursor = curIndex;
                    dt.rowCount=curIndex;
                    for (int i = 0; i <columnNum; i++) {
                        try {
                            Column col=dt.columns.get(i);
                            col.addData(rs.getObject(i + 1));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    //
                    if (curIndex > rowNum && rowNum != -1) {
                        break;
                    }
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
                return dt;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
