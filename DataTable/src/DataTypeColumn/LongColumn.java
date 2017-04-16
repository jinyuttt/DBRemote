/**    
 * �ļ�����LongColumn.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��16��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package DataTypeColumn;


import java.nio.Buffer;
import java.nio.LongBuffer;
import java.util.Arrays;

/**    
 *     
 * ��Ŀ���ƣ�DataTable    
 * �����ƣ�LongColumn    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��16�� ����12:18:55    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��16�� ����12:18:55    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class LongColumn extends Column {
public long[] listData=null;
public LongColumn()
{
    this.columnType=ColumnJsonType.LongColumn;
}
@Override
public void addData(Object obj) {
    if(listData==null)
    {
        listData=new long[this.enlargeNum];
    }
    enlargeData();
    rowNum++;//��һ�е�0��
   if(obj==null)
   {
       obj=Long.MAX_VALUE;
   }
    listData[rowNum-1]=(long)obj;
    
}

@Override
protected void enlargeData() {
    if(this.rowNum==this.listData.length)
    {
        //��չ
        long[]tmp=new long[listData.length+this.enlargeNum];
        System.arraycopy(listData, 0, tmp, 0, listData.length);
        listData=tmp;
    }
    
}

@Override
public Object getData(int index) {
    if(rowNum>index)
    {
        if(listData[index]==Long.MAX_VALUE)
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
    if(data instanceof  LongColumn)
    {
        checkThis(data);
        long[]d=(long[]) data.getAllValues();
        if(d==null)
        {
            return 0;
        }
        if(this.listData==null)
        {
            this.listData=new long[d.length];
        }
        if(listData.length-this.rowNum>=d.length)
        {
            System.arraycopy(d, 0, listData, this.rowNum, d.length);
            this.rowNum=this.rowNum+d.length;
            r=d.length;
        }
        else
        {
        long[] all=new long[this.rowNum+d.length];
        //System.arraycopy(listData, 0, all, 0, this.rowNum);
        //System.arraycopy(d, 0, all, this.rowNum,d.length);
        LongBuffer buffer=LongBuffer.wrap(all);
        buffer.put(listData);
        buffer.put(d);
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
    long[] data=new long[this.rowNum];
    LongBuffer buffer=LongBuffer.wrap(data);
    buffer.put(listData, 0, rowNum);
    buffer.flip();
    return buffer;
}

@Override
public Object getAllValues() {
    long[] data=Arrays.copyOfRange(listData, 0, rowNum);
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
