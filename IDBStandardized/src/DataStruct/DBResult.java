package DataStruct;

import DBError.ErrorCode;

/**
 * ���ؽṹ
 * 
 * @author jinyu
 *
 */
public class DBResult {
    public ErrorCode Code = ErrorCode.Sucess;
    
    /**
     * ��ǰSqlDataReader�ش�ID
     */
    public int id = -1;

    public Object Result;
}
