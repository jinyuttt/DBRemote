/**    
 * 文件名：StringColumn.java    
 *    
 * 版本信息：    
 * 日期：2017年3月16日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package DataTypeColumn;
import java.nio.Buffer;
import java.util.Arrays;

/**    
 *     
 * 项目名称：DataTable    
 * 类名称：StringColumn    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年3月16日 上午12:19:36    
 * 修改人：jinyu    
 * 修改时间：2017年3月16日 上午12:19:36    
 * 修改备注：    
 * @version     
 *     
 */
public class StringColumn extends Column {
public String[] listData=null;
public StringColumn()
{
    this.columnType=ColumnJsonType.StringColumn;
}
@Override
public void addData(Object obj) {
    if(listData==null)
    {
        listData=new String[this.enlargeNum];
    }
    enlargeData();
    rowNum++;//第一行第0个
    listData[rowNum-1]=(String)obj;
    
}

@Override
protected void enlargeData() {
    if(this.rowNum==this.listData.length)
    {
        //扩展
        String[]tmp=new String[listData.length+this.enlargeNum];
        System.arraycopy(listData, 0, tmp, 0, listData.length);
        listData=tmp;
    }
    
}

@Override
public Object getData(int index) {
    if(rowNum>index)
    {
        return listData[index];
    }
    else
    {
        return null;
    }
}



@Override
public int mergeData(Column data) {
    int r=0;
    if(data instanceof  StringColumn)
    {
        checkThis(data);
        String[]d=(String[]) data.getAllValues();
        if(d==null)
        {
            return 0;
        }
        if(this.listData==null)
        {
            this.listData=new String[d.length];
        }
        if(listData.length-this.rowNum>=d.length)
        {
            System.arraycopy(d, 0, listData, this.rowNum, d.length);
            this.rowNum=this.rowNum+d.length;
            r=d.length;
        }
        else
        {
        String[] all=new String[this.rowNum+d.length];
        System.arraycopy(listData, 0, all, 0, this.rowNum);
        System.arraycopy(d, 0, all, this.rowNum,d.length);
        this.rowNum=this.rowNum+d.length;
        r=d.length;
        this.listData=all;
        }
    }
    return   r;
}

private void checkThis(Column data) {
    if(this.caption==null||this.caption.isEmpty())
    {
        this.caption=data.caption;
    }
    if(this.columnTypeName==null||this.columnTypeName.isEmpty())
    {
        this.columnTypeName=data.columnTypeName;
    }
    
}

@Override
public Buffer getAllData() {

    return null;
}

@Override
public Object getAllValues() {
    String[] data=Arrays.copyOfRange(listData, 0, rowNum);
    return data;
}
@Override
public int getRowNum() {
    return this.rowNum;
}
@Override
public void setRowNum(int rowNum) {
    this.rowNum=rowNum;
    
}
}
