/**    
 * 文件名：DataTableJson.java    
 *    
 * 版本信息：    
 * 日期：2017年3月15日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import DataTypeColumn.Column;

/**    
 *     
 * 项目名称：IDBStandardized    
 * 类名称：DataTableJson    
 * 类描述：    表结构 面向列存储
 * 创建人：jinyu    
 * 创建时间：2017年3月15日 下午8:44:53    
 * 修改人：jinyu    
 * 修改时间：2017年3月15日 下午8:44:53    
 * 修改备注：    
 * @version     
 *     
 */
public class DataTableJson {
//当前表作为一部分指示的索引
public  int Cursor = 0;
//表名称
public String tableName;
//列数据
public List<Column> columns=new ArrayList<Column>();
public DataRowJson[] Rows;//行数据
public HashMap<String,Integer> columnsIndex=new HashMap<String,Integer>();
public int rowCount=0;//当前有的行数
private int index=-1;
private int nextadd=1;

/*
 * 获取行数
 */
public int getRowNum()
{
    if(Rows==null&&index==-1&&rowCount==0)
    {
        //初始化
        rowCount=columns.get(0).getRowNum();
    }
    return rowCount;
}
/*
 * 
 */
public void setColumns(List<Column> Columns)
{
    this.columns=Columns;
}
/*
 * 
 */
public List<Column> sgetColumns()
{
    return columns;
}
public void setColumnsIndex(HashMap<String,Integer> columnsIndex)
{
    this.columnsIndex=columnsIndex;
}
public HashMap<String,Integer> getColumnsIndex()
{
    return columnsIndex;
}
public void setTableName(String tableName)
{
    this.tableName=tableName;
}
public String getTableName()
{
    return tableName;
}
/*
 * 如果当前行还没有转换则转换
 * 否则获取
 */
private DataRowJson ConvertRow(int index)
{ 
   //
    if(Rows==null&&index==-1)
    {
        //初始化
        rowCount=columns.get(0).getRowNum();
       //说明还没有转换行
        Rows=new DataRowJson[rowCount];
        index=0;
    }
    if(index>=rowCount)
    {
        return null;
    }
    
   DataRowJson row=Rows[index];//只有通过添加行方法
   if(row==null)
   {
       //没有转换该行
       row=new DataRowJson();
   for(int i=0;i<columns.size();i++)
   {
      // row.items.add(Columns.get(i).listData.get(index));
       rowCount=columns.get(0).getRowNum();
       row.items.add(columns.get(i).getData(index));
   }
   }
   return row;
}

/*
 * 完全新添加行
 */
public DataRowJson AddRow()
{

    if(Rows==null&&index==-1)
    {
        //初始化
        //rowCount=Columns.get(0).listData.size();
       //说明还没有转换行
        Rows=new DataRowJson[rowCount];
    }
    else
    {
        if(nextadd==Rows.length)
          {
                //扩展行
                //每次添加一行很费事
              DataRowJson[] tmp=new DataRowJson[Rows.length+15];
              System.arraycopy(Rows, 0, tmp, 0, Rows.length);
              Rows=tmp;
          }
    }
    nextadd++;//指示真实添加的位置
    rowCount=nextadd;
    DataRowJson row=new DataRowJson();
    Rows[nextadd]=row;
    row.index=nextadd;
    row.table=this;
    return row;
}

/*
 * 获取其中某行数据
 * 没有改行则返回空
 */
public DataRowJson GetRow(int index)
{      DataRowJson row=ConvertRow(index);
        return row;  
}

/*
 * 判断是否还存在数据
 */
public boolean HasData() {
    if(index<rowCount)
    {
        return true;
    }
    else
    {
    return false;
    }
}


/*
 * 顺序读取行数据
 */
public DataRowJson next() {
    DataRowJson row=  ConvertRow(index);
    index++;
    return row;
}

/*
 * 添加行数据
 */
public void loadDataRowJson(DataRowJson row)
{
    for(int i=0;i<columns.size();i++)
    {
        columns.get(i).addData(row.Get(columns.get(i).columnTypeName));
    }
   
}

/*
 * 合并列表
 */
public void loadDataTableJson(DataTableJson table)
{
    if(table==null)
    {
        return;
    }
    int  num=0;
    for(int i=0;i<columns.size();i++)
    {
        String name=columns.get(i).name;
        int id=  table.columnsIndex.get(name);
        Column col= table.columns.get(id-1);
        columns.get(i).mergeData(col);
        num=col.getRowNum();
    }
   this.rowCount+=num;
}

/*
 * 获取取出的数据与行数比例
 * 该方法仅仅为sqlreader准备
 */
public float getReadRate()
{
    if(rowCount==0)
    {
        return 0;
    }
    else
    {
    return (float)(rowCount-index)/(rowCount);
    }
   
    
}
}
