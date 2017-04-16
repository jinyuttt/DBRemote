/**    
 * �ļ�����DoubleColumn.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��16��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package DataTypeColumn;

import java.nio.Buffer;
import java.nio.DoubleBuffer;
import java.util.Arrays;

/**    
 *     
 * ��Ŀ���ƣ�DataTable    
 * �����ƣ�DoubleColumn    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��16�� ����12:12:46    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��16�� ����12:12:46    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class DoubleColumn extends Column {
public double[] listData=null;
public DoubleColumn()
{
    this.columnType=ColumnJsonType.DoubleColumn;
}
@Override
public void addData(Object obj) {
    if(listData==null)
    {
        listData=new double[this.enlargeNum];
    }
   
    enlargeData();
    rowNum++;//��һ�е�0��
    if(obj==null)
    {
        listData[rowNum-1]=Double.NaN;
    }
    else
    {
    listData[rowNum-1]=(double)obj;
    }
    
}

@Override
protected void enlargeData() {
    if(this.rowNum==this.listData.length)
    {
        //��չ
        double[]tmp=new double[listData.length+this.enlargeNum];
        System.arraycopy(listData, 0, tmp, 0, listData.length);
        listData=tmp;
    }
    
}

@Override
public Object getData(int index) {
    if(rowNum>index)
    {
        if(listData[index]==Double.NaN)
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
    if(data instanceof  DoubleColumn)
    {
        checkThis(data);
        double[]d=(double[]) data.getAllValues();
        if(d==null)
        {
            return 0;
        }
        if(this.listData==null)
        {
            this.listData=new double[d.length];
        }
        if(listData.length-this.rowNum>=d.length)
        {
            System.arraycopy(d, 0, listData, this.rowNum, d.length);
            this.rowNum=this.rowNum+d.length;
            r=d.length;
        }
        else
        {
        double[] all=new double[this.rowNum+d.length];
        DoubleBuffer buffer=DoubleBuffer.wrap(all);
        buffer.put(listData,0,this.rowNum);
        buffer.put(d);
        listData=buffer.array();
        this.rowNum=this.rowNum+d.length;
        r=d.length;
        }
    }
    return   r;
}
private void checkThis(Column data)
{
    
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
    double[] data=new double[this.rowNum];
    DoubleBuffer buffer=DoubleBuffer.wrap(data);
    buffer.put(listData, 0, rowNum);
    buffer.flip();
    return buffer;
  
}

@Override
public Object getAllValues() {
  
    double[] data=Arrays.copyOfRange(listData, 0, rowNum);
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
