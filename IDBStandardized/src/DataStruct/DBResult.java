package DataStruct;

import DBError.ErrorCode;

/**
 * 返回结构
 * 
 * @author jinyu
 *
 */
public class DBResult {
    public ErrorCode Code = ErrorCode.Sucess;
    
    /**
     * 当前SqlDataReader回传ID
     */
    public int id = -1;

    public Object Result;
}
