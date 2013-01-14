/**
 * 
 */
package org.tmail.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * @author hongliuliao
 * 
 *         createTime:2013-1-14 下午4:08:36
 */
public class MD5Util {
	/**
	 * 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符,apache校验下载的文件的正确性用的就是默认的这个组合
	 */
	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String crypt(String source) {
		return crypt(source.getBytes(Charset.forName("UTF-8")));
	}
	
	/**
     * encode bytes
     * 
     * @param source source bytes
     * @return the encode String
     */
    public static String crypt(byte[] source) {
        String s = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest();
            char str[] = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];

                str[k++] = hexDigits[byte0 & 0xf];
            }
            s = new String(str);

        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return s;
    }


}
