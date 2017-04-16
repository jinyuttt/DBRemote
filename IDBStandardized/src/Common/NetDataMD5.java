/**    
 * 文件名：NetDataMD5.java    
 *    
 * 版本信息：    
 * 日期：2017年3月19日    
 * Copyright 足下 Corporation 2017     
 * 版权所有    
 *    
 */
package Common;


import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**    
 *     
 * 项目名称：DBSqlManager    
 * 类名称：NetDataMD5    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2017年3月19日 上午12:20:53    
 * 修改人：jinyu    
 * 修改时间：2017年3月19日 上午12:20:53    
 * 修改备注：    
 * @version     
 *     
 */
public class NetDataMD5 {
public static byte[] encryptDataPackage(byte[]data)
{
  ;
    try {
        SecureRandom secureRandom=new SecureRandom();
        DESedeKeySpec sedeKeySpec=new DESedeKeySpec("chengdujason".getBytes());

        SecretKeyFactory secretKeyFactory=SecretKeyFactory.getInstance("DESede");
        SecretKey key=secretKeyFactory.generateSecret(sedeKeySpec);

        Cipher cipher=Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,key,secureRandom);

        byte[] bytesresult=cipher.doFinal(data);
        return  bytesresult;
       
    } catch (Exception e) {
        e.printStackTrace();
    }
    return  data;
}
public static byte[] decryptBy3DES(byte[]data)
{
   
    try {
        SecureRandom secureRandom=new SecureRandom();
        DESedeKeySpec sedeKeySpec=new DESedeKeySpec("chengdujason".getBytes());

        SecretKeyFactory secretKeyFactory=SecretKeyFactory.getInstance("DESede");
        SecretKey key = secretKeyFactory.generateSecret(sedeKeySpec);

        Cipher cipher=Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,key,secureRandom);

        byte[] bytesresult=cipher.doFinal(data);
       return bytesresult;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return data;
}
}
