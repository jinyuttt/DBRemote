/**    
 * 文件名：BitColumn.java    
 *    
 * 版本信息：    
 * 日期：2017年3月16日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package DataTypeColumn;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**    
 *     
 * 项目名称：DataTable    
 * 类名称：BitColumn    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年3月16日 上午12:23:35    
 * 修改人：jinyu    
 * 修改时间：2017年3月16日 上午12:23:35    
 * 修改备注：    
 * @version     
 *     
 */
public class BitColumn extends Column {
public byte[] listData=null;
public BitColumn()
{
    this.columnType=ColumnJsonType.BitColumn;
}
@Override
public void addData(Object obj) {
    if(listData==null)
    {
        listData=new byte[this.enlargeNum];
    }
    enlargeData();
    rowNum++;//第一行第0个
    if(obj==null)
    {
        listData[rowNum-1]=Byte.MAX_VALUE;
    }
    listData[rowNum-1]=(byte)obj;
    
}

@Override
protected void enlargeData() {
    if(this.rowNum==this.listData.length)
    {
        //扩展
        byte[]tmp=new byte[listData.length+this.enlargeNum];
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
    if(data instanceof  BitColumn)
    {
        checkThis(data);
        byte[]d=(byte[]) data.getAllValues();
        if(d==null)
        {
            return 0;
        }
        if(this.listData==null)
        {
            this.listData=new byte[d.length];
        }
        if(listData.length-this.rowNum>=d.length)
        {
            System.arraycopy(d, 0, listData, this.rowNum, d.length);
            this.rowNum=this.rowNum+d.length;
            r=d.length;
        }
        else
        {
        byte[] all=new byte[this.rowNum+d.length];
        ByteBuffer buffer=ByteBuffer.wrap(all);
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
    
}

@Override
public Buffer getAllData() {
    byte[] data=new byte[this.rowNum];
    ByteBuffer buffer=ByteBuffer.wrap(data);
    buffer.put(listData, 0, rowNum);
    buffer.flip();
    return buffer;
}

@Override
public Object getAllValues() {
    byte[] data=Arrays.copyOfRange(listData, 0, rowNum);
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
