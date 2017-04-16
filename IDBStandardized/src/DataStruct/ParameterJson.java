/**    
 * 文件名：ParameterJson.java    
 *    
 * 版本信息：    
 * 日期：2017年3月6日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package DataStruct;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * 
 * 项目名称：IDBStandardized 类名称：ParameterJson 类描述： 参数 创建人：jinyu 创建时间：2017年3月6日
 * 下午11:43:26 修改人：jinyu 修改时间：2017年3月6日 下午11:43:26 修改备注：
 * 
 * @version
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public class ParameterJson {
    public SqlType ParameterType = SqlType.VARCHAR;

    public String ParameterName = "";
}
