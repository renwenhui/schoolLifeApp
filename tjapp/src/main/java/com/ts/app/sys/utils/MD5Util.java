package com.ts.app.sys.utils;

import java.security.MessageDigest;

import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Util {
    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++){
        	resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0){
        	n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname)){
            	resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            }else{
            	resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        System.out.println("MD5:" + resultString);
        return resultString;
    }

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public static void main(String[] args) {
		System.out.println(MD5Util.MD5Encode("123456", "utf-8"));
	}
	
	/**
	 * @Description: 对密码进行md5加密
	 * @version: v1.0.0
	 * @author: liuhuan
	 * @date: 2017-11-30 下午5:41:06
	 */
	public static String saltMd5(String pw ,String salt) {
		String hashAlgorithmName = ReadConfig.getProperty("hashAlgorithmName");//加密方式  
		String hashIterations = ReadConfig.getProperty("hashIterations"); //加密次数
		Object simpleHash = new SimpleHash(hashAlgorithmName, pw,  
               salt, Integer.parseInt(hashIterations));
		return simpleHash.toString();  
   }

}
