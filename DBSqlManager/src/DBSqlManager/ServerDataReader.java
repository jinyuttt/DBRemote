/**    
 * �ļ�����ServerDataReader.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��7��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package DBSqlManager;

import java.util.concurrent.ConcurrentHashMap;

import ServerCommon.DataReader;

/**
 * 
 * ��Ŀ���ƣ�DBSqlManager �����ƣ�ServerDataReader �������� �����ˣ�jinyu ����ʱ�䣺2017��3��7��
 * ����10:11:42 �޸��ˣ�jinyu �޸�ʱ�䣺2017��3��7�� ����10:11:42 �޸ı�ע��
 * 
 * @version
 * 
 */
public class ServerDataReader {
    /*
     * �����ѯ��
     */
    public static ConcurrentHashMap<Integer, DataReader> setID = new ConcurrentHashMap<Integer, DataReader>();

    public static ConcurrentHashMap<Integer, DataReader> unAction = new ConcurrentHashMap<Integer, DataReader>();
}
