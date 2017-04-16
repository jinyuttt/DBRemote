/**    
 * �ļ�����ObjectColumn.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��16��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package DataTypeColumn;


import java.nio.Buffer;
import java.util.Arrays;

/**    
 *     
 * ��Ŀ���ƣ�DataTable    
 * �����ƣ�ObjectColumn    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��16�� ����12:24:11    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��16�� ����12:24:11    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class ObjectColumn extends Column {
public Object[] listData=null;
public ObjectColumn()
{
    this.columnType=ColumnJsonType.ObjectColumn;
}
@Override
public void addData(Object obj) {
    if(listData==null)
    {
        listData=new Object[this.enlargeNum];
    }
    enlargeData();
    rowNum++;//��һ�е�0��
    listData[rowNum-1]=obj;
    
}

@Override
protected void enlargeData() {
    if(this.rowNum==this.listData.length)
    {
        //��չ
        Object[]tmp=new Object[listData.length+this.enlargeNum];
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
    if(data instanceof  ObjectColumn)
    {
        checkThis(data);
        Object[]d=(Object[]) data.getAllValues();
        if(d==null)
        {
            return 0;
        }
        if(this.listData==null)
        {
            this.listData=new Object[d.length];
        }
        if(listData.length-this.rowNum>=d.length)
        {
            System.arraycopy(d, 0, listData, this.rowNum, d.length);
            this.rowNum=this.rowNum+d.length;
            r=d.length;
        }
        else
        {
            Object[] all=new Object[this.rowNum+d.length];
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
    Object[] data=Arrays.copyOfRange(listData, 0, rowNum);
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
