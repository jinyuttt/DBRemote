/**    
 * 文件名：IntColumn.java    
 *    
 * 版本信息：    
 * 日期：2017年3月16日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package DataTypeColumn;

import java.nio.Buffer;

import java.nio.IntBuffer;
import java.util.Arrays;

/**    
 *     
 * 项目名称：DataTable    
 * 类名称：IntColumn    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年3月16日 上午12:17:51    
 * 修改人：jinyu    
 * 修改时间：2017年3月16日 上午12:17:51    
 * 修改备注：    
 * @version     
 *     
 */
public class IntColumn extends Column {
public int[]listData=null;
public IntColumn()
{
    this.columnType=ColumnJsonType.IntColumn;
}
@Override
public void addData(Object obj) {
    if(listData==null)
    {
        listData=new int[this.enlargeNum];
    }
    enlargeData();
    rowNum++;//第一行第0个
    if(obj==null)
    {
        obj=Integer.MAX_VALUE;
    }
    listData[rowNum-1]=(int)obj;
    
}

@Override
protected void enlargeData() {
    if(this.rowNum==this.listData.length)
    {
        //扩展
        int[]tmp=new int[listData.length+this.enlargeNum];
        System.arraycopy(listData, 0, tmp, 0, listData.length);
        listData=tmp;
    }
    
}

@Override
public Object getData(int index) {
    if(rowNum>index)
    {
        if(listData[index]==Integer.MAX_VALUE)
        {
            return null;
        }
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
    if(data instanceof  IntColumn)
    {
        checkThis(data);
        int[]d=(int[]) data.getAllValues();
        if(d==null)
        {
            return 0;
        }
        if(this.listData==null)
        {
            this.listData=new int[d.length];
        }
        if(listData.length-this.rowNum>=d.length)
        {
            System.arraycopy(d, 0, listData, this.rowNum, d.length);
            this.rowNum=this.rowNum+d.length;
            r=d.length;
        }
        else
        {
        int[] all=new int[this.rowNum+d.length];
        IntBuffer buffer=IntBuffer.wrap(all);
        buffer.put(listData,0,this.rowNum);
        buffer.put(d);
        this.rowNum=this.rowNum+d.length;
        listData=buffer.array();
        r=d.length;
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
    int[] data=new int[this.rowNum];
    IntBuffer buffer=IntBuffer.wrap(data);
    buffer.put(listData, 0, rowNum);
    buffer.flip();
    return buffer;
}

@Override
public Object getAllValues() {
    int[] data=Arrays.copyOfRange(listData, 0, rowNum);
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
