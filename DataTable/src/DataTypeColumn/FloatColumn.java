/**    
 * �ļ�����FloatColumn.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��16��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package DataTypeColumn;

import java.nio.Buffer;

import java.nio.FloatBuffer;
import java.util.Arrays;

/**    
 *     
 * ��Ŀ���ƣ�DataTable    
 * �����ƣ�FloatColumn    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��16�� ����12:15:37    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��16�� ����12:15:37    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class FloatColumn extends Column {
public  float[] listData=null;
public FloatColumn()
{
    this.columnType=ColumnJsonType.FloatColumn;
}
@Override
public void addData(Object obj) {
    if(listData==null)
    {
        listData=new float[this.enlargeNum];
    }
    enlargeData();
    rowNum++;//��һ�е�0��
    if(obj==null)
    {
        listData[rowNum-1]=Float.NaN;
    }
    else
    {
    listData[rowNum-1]=(float)obj;
    }
    
}

@Override
protected void enlargeData() {
    if(this.rowNum==this.listData.length)
    {
        //��չ
        float[]tmp=new float[listData.length+this.enlargeNum];
        System.arraycopy(listData, 0, tmp, 0, listData.length);
        listData=tmp;
    }
    
}

@Override
public Object getData(int index) {
    if(rowNum>index)
    {
        if(listData[index]==Float.NaN)
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
    if(data instanceof  FloatColumn)
    {
        checkThis(data);
        float[]d=(float[]) data.getAllValues();
        if(d==null)
        {
            return 0;
        }
        if(this.listData==null)
        {
            this.listData=new float[d.length];
        }
        if(listData.length-this.rowNum>=d.length)
        {
            System.arraycopy(d, 0, listData, this.rowNum, d.length);
            this.rowNum=this.rowNum+d.length;
            r=d.length;
        }
        else
        {
        float[] all=new float[this.rowNum+d.length];
        FloatBuffer buffer=FloatBuffer.wrap(all);
        buffer.put(listData,0,this.rowNum);
        buffer.put(d);
        listData=buffer.array();
        this.rowNum=this.rowNum+d.length;
        r=d.length;
        }
    }
    return   r;
}

private void checkThis(Column data) {
    // TODO Auto-generated method stub
    
}

@Override
public Buffer getAllData() {
    float[] data=new float[this.rowNum];
    FloatBuffer buffer=FloatBuffer.wrap(data);
    buffer.put(listData, 0, rowNum);
    buffer.flip();
    return buffer;
}

@Override
public Object getAllValues() {
    float[] data=Arrays.copyOfRange(listData, 0, rowNum);
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
