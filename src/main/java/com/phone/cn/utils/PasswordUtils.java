package com.phone.cn.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtils {

	private static MessageDigest md;
	private static final String EncodeType = "SHA-256";

	private static final char[] hexDigits	= { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
			'A', 'B', 'C', 'D', 'E', 'F' };
	
	private static final char[] randomChars	= { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	static {
		try {
			md = MessageDigest.getInstance(EncodeType);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	private static String encode(String str) {
		md.update(str.getBytes());
		byte[] bytes = md.digest();
		StringBuffer sb = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; ++i) {
			char c0 = hexDigits[(bytes[i] & 0xF0) >> 4];
			char c1 = hexDigits[bytes[i] & 0x0F];
			sb.append(c0);
			sb.append(c1);

		}
		return sb.toString();
	}

	/**
	 * 密码加密  key + （（key +pwd）加密）
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String password) throws Exception {
		if(StringUtils.isBlank(password))
			throw new Exception("password can not be null or empty");
		
		String tempKey = RandomStringUtils.random(10, randomChars);
		password = tempKey + password;
		return tempKey + encode(password);
	}

	/**
	 * 
	 * @param passwordSys 加密密码
	 * @param passwordUsr  原文密码
	 * @return
	 */
	public static boolean validPassword(String passwordSys, String passwordUsr) {
		try{
			String tempKey = passwordSys.substring(0, 10);
			if (passwordSys.equals(tempKey + encode(tempKey + passwordUsr)))
				return true;
			else
				return false;
		}catch(Exception e) {
			return false;
		}
	}
	
	public static void main(String[] args) {
		try {
		//	System.out.println(encode(encrypt("123456")));
		//	System.out.println(encrypt(encode("123456")));
			
			System.out.println(encrypt("123456"));
			System.out.println(validPassword(encrypt("123456"),"123456"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
