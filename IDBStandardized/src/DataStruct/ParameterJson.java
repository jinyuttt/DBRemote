/**    
 * �ļ�����ParameterJson.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��6��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
 *    
 */
package DataStruct;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * 
 * ��Ŀ���ƣ�IDBStandardized �����ƣ�ParameterJson �������� ���� �����ˣ�jinyu ����ʱ�䣺2017��3��6��
 * ����11:43:26 �޸��ˣ�jinyu �޸�ʱ�䣺2017��3��6�� ����11:43:26 �޸ı�ע��
 * 
 * @version
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public class ParameterJson {
    public SqlType ParameterType = SqlType.VARCHAR;

    public String ParameterName = "";
}
