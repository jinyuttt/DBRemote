/**    
 * �ļ�����NetDataMD5.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2017��3��19��    
 * Copyright ���� Corporation 2017     
 * ��Ȩ����    
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
 * ��Ŀ���ƣ�DBSqlManager    
 * �����ƣ�NetDataMD5    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2017��3��19�� ����12:20:53    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2017��3��19�� ����12:20:53    
 * �޸ı�ע��    
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
