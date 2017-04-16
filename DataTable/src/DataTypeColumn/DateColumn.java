/**    
 * �ļ�����DateColumn.java    
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
import java.util.Date;

/**    
 *     
 * ��Ŀ���ƣ�DataTable    
 * �����ƣ�DateColumn    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��16�� ����12:09:08    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��16�� ����12:09:08    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class DateColumn extends Column {
 public Date[] listData=null;

@Override
public void addData(Object obj) {
    if(listData==null)
    {
        listData=new Date[this.enlargeNum];
    }
    enlargeData();
    rowNum++;//��һ�е�0��
    listData[rowNum-1]=(Date)obj;
    
}

@Override
protected void enlargeData() {
    if(this.rowNum==this.listData.length)
    {
        //��չ
        Date[]tmp=new Date[listData.length+this.enlargeNum];
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
public Buffer getAllData() {
   
    return null;
}

@Override
public Object getAllValues() {
    Date[] data=Arrays.copyOfRange(listData, 0, rowNum);
    return data;
}

@Override
public int mergeData(Column data) {
    int r=0;
    if(data instanceof  DateColumn)
    {
        checkThis(data);
        Date[]d=(Date[]) data.getAllValues();
        if(d==null)
        {
            return 0;
        }
        if(this.listData==null)
        {
            this.listData=new Date[d.length];
        }
        if(listData.length-this.rowNum>=d.length)
        {
            System.arraycopy(d, 0, listData, this.rowNum, d.length);
            this.rowNum=this.rowNum+d.length;
            r=d.length;
        }
        else
        {
        Date[] all=new Date[this.rowNum+d.length];
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
