/*
 * MD5Encrypt.java Created on 2007-4-6
 * Copyright 2007@BroadText Inc.
 * All right reserved. 
 */
package com.hotdog.springboot.common.util.md5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * MD5的计算类
 * 
 * @Time 12:31:14
 * @author zhangwei
 */
public class MD5Encrypt {
	public MD5Encrypt() {
	}

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */
	public static String byteArrayToString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			// resultSb.append(byteToHexString(b[i]));//若使用本函数转换则可得到加密结果的16进制表示，即数字字母混合的形式
			resultSb.append(byteToNumString(b[i]));// 使用本函数则返回加密结果的10进制数字字串，即全数字形式
		}
		return resultSb.toString();
	}

	private static String byteToNumString(byte b) {

		int _b = b;
		if (_b < 0) {
			_b = 256 + _b;
		}

		return String.valueOf(_b);
	}

	public static String MD5Encode(String origin) {
		String resultString = null;

		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {

		}
		return resultString;
	}
	
	public static String MD5Encode(File file){
		FileInputStream is = null;
		byte[] data = new byte[(int) file.length()];
		try {
			is = new FileInputStream(file);
			is.read(data);
			return MD5Encrypt.MD5Encode(data);
		} catch (IOException e) {
			System.err.println("[错误]无法读取文件\"" + file + "\".");
			return  "";
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
				}
		}
	}
	
	public static String MD5Encode(byte[] origin) {
		String resultString = null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToString(md.digest(origin));
		} catch (Exception ex) {

		}
		return resultString;
	}

	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Documents and Settings\\Administrator\\My Documents\\My Pictures\\pageTitle.jpg");
		InputStream inputStream = new FileInputStream(file);
		byte[] byteData = org.apache.commons.io.IOUtils.toByteArray(inputStream);

		System.out.println(MD5Encrypt.MD5Encode(byteData));
	}

}
