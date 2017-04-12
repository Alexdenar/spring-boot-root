/**
 * 
 */
package com.hotdog.springboot.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * HTTP工具类
 * @author chailiangzhi
 * @date 2016-9-28
 * 
 */
public class HttpUtil {

	/**
	 * GET方法访问URL
	 * @param urlStr
	 * @return
	 * @throws Exception
	 */
	public static String simpleGet1(String urlStr) throws Exception {
		// 统一资源
		URL url = new URL(urlStr);
		// 连接类的父类，抽象类
		URLConnection urlConnection = url.openConnection();
		// http的连接类
		HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
		// 设定请求的方法，默认是GET
		//			httpURLConnection.setRequestMethod("POST");
		// 设置字符编码
		httpURLConnection.setRequestProperty("Charset", "UTF-8");
		// 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
		httpURLConnection.connect();
		InputStreamReader inr = new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8");
		BufferedReader br = new BufferedReader(inr);
		String line = br.readLine();
		StringBuilder sb = new StringBuilder();
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}
		br.close();
		return sb.toString();
	}

	/**
	 * 指定方法访问URL
	 * @param urlStr
	 * @param method
	 * @return
	 * @throws Exception
	 */
	public static String simpleHttp(String urlStr, String method) throws Exception {
		// 统一资源
		URL url = new URL(urlStr);
		// 连接类的父类，抽象类
		URLConnection urlConnection = url.openConnection();
		// http的连接类
		HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
		// 设定请求的方法，默认是GET
		httpURLConnection.setRequestMethod(method);
		// 设置字符编码
		httpURLConnection.setRequestProperty("Charset", "UTF-8");
		// 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
		httpURLConnection.connect();
		InputStream in = httpURLConnection.getInputStream();
		byte[] buf = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = in.read(buf);
		while (len != -1) {
			baos.write(buf, 0, len);
			len = in.read(buf);
		}
		in.close();
		return baos.toString("UTF-8");
	}
}
