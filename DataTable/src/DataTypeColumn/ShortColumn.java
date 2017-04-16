package DataTypeColumn;

import java.nio.Buffer;

import java.nio.ShortBuffer;
import java.util.Arrays;

public class ShortColumn extends Column {
    public  short[] listData=null;
    public ShortColumn()
    {
        this.columnType=ColumnJsonType.ShortColumn;
    }
    @Override
    public void addData(Object obj) {
        if(listData==null)
        {
            listData=new short[this.enlargeNum];
        }
        enlargeData();
        rowNum++;//第一行第0个
        if(obj==null)
        {
            obj=Short.MAX_VALUE;
        }
        listData[rowNum-1]=(short)obj;

    }

    @Override
    protected void enlargeData() {
        if(this.rowNum==this.listData.length)
        {
            //扩展
            short[]tmp=new short[listData.length+this.enlargeNum];
            System.arraycopy(listData, 0, tmp, 0, listData.length);
            listData=tmp;
        }

    }

    @Override
    public Object getData(int index) {
        if(rowNum>index)
        {
            if(listData[index]==Short.MAX_VALUE)
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
        if(data instanceof  ShortColumn)
        {
            checkThis(data);
            short[]d=(short[]) data.getAllValues();
            if(d==null)
            {
                return 0;
            }
            if(this.listData==null)
            {
                this.listData=new short[d.length];
            }
            if(listData.length-this.rowNum>=d.length)
            {
                System.arraycopy(d, 0, listData, this.rowNum, d.length);
                this.rowNum=this.rowNum+d.length;
                r=d.length;
            }
            else
            {
            short[] all=new short[this.rowNum+d.length];
            ShortBuffer buffer=ShortBuffer.wrap(all);
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public Buffer getAllData() {
        short[] data=new short[this.rowNum];
        ShortBuffer buffer=ShortBuffer.wrap(data);
        buffer.put(listData, 0, rowNum);
        buffer.flip();
        return buffer;
      
    }

    @Override
    public Object getAllValues() {
        short[] data=Arrays.copyOfRange(listData, 0, rowNum);
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
