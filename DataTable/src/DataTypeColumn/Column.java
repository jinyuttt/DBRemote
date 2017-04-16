/**    
 * 文件名：Column.java    
 *    
 * 版本信息：    
 * 日期：2017年3月15日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package DataTypeColumn;

import java.nio.Buffer;

import Table.DataTableJson;

/**    
 *     
 * 项目名称：DataTable    
 * 类名称：Column    
 * 类描述：    列定义
 * 创建人：jinyu    
 * 创建时间：2017年3月15日 下午11:30:43    
 * 修改人：jinyu    
 * 修改时间：2017年3月15日 下午11:30:43    
 * 修改备注：    byte,int,long,short几种类型的最大值不能在数据库中使用
 * 这几类数据的最大值被程序固化使用，定义为数据库中的空值
 * 在数据库中最大值一般也是使用不到的，这样做我认为是合理的
 * @version     
 *     
 */
public abstract class Column {
    public Column()
    {
        
    }
    /*
     * 标题
     */
    public String caption;//标题
    /*
     * 列名称
     */
    public String name;//列名称
    /*
     * 长度
     */
    public int maxLength;//长度
    /*
     * 数据类型
     */
    public int dataType;//类型
    public int ordinal=0;//顺序
    public int capacity=0;//当前容量,暂时保留
    public DataTableJson table;//表对象
    protected int enlargeNum=15;//扩展行数
    protected int rowNum=0;//当前列真实行数
    public ColumnJsonType columnType;
    public String columnTypeName;//列类型名称
    public abstract void addData(Object obj);
    protected abstract void enlargeData();
    public abstract Object getData(int index);
    public abstract int mergeData(Column data);
    public  abstract  Buffer getAllData();
    public  abstract  Object getAllValues();
    public abstract int getRowNum();
    public abstract void setRowNum(int rowNum);
    
    
}
