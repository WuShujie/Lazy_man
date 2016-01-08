package com.example.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密   使用MD5对原文进行加密
 * @author Dontouch
 *
 */
public class MD5 {
	
	/***
	 * 
	 * @param value
	 * @return
	 */
	public static String digest(String value) {
		StringBuilder sb = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] result = digest.digest(value.getBytes());
			sb = new StringBuilder();
			for (byte b : result) {
				String hexString = Integer.toHexString(b & 0xFF);
				if (hexString.length() == 1) {
					sb.append("0" + hexString);
				} else {
					sb.append(hexString);
				}
			}
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}
	
}
