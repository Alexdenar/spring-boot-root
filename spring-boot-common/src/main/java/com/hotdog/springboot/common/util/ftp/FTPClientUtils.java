package com.hotdog.springboot.common.util.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**  
 * @类功能说明：   FTP Client 工具类
 * @作者：hotdog  
 * @创建时间：2016-8-8 下午3:55:59  
 */ 
public class FTPClientUtils {
	
	/**
	 * 
	 */
	private final static Logger logger = LoggerFactory.getLogger(FTPClientUtils.class);
	
	/** 
	 * Description: 向FTP服务器上传文件 
	 * @param url FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param path FTP服务器保存目录 
	 * @param filename 上传到FTP服务器上的文件名 
	 * @param input 输入流 
	 * @return 成功返回true，否则返回false 
	 */  
	public static boolean uploadFile(String url,int port,
			String username, String password, 
			String path, String filename, 
			InputStream input) {  
		 boolean success = false;  
	        FTPClient ftp = new FTPClient();  
	        ftp.setControlEncoding("GBK");  
	        try {  
	            int reply; 
	            ftp.connect(url, port);// 连接FTP服务器  
	            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
	            ftp.login(username, password);// 登录  
	            reply = ftp.getReplyCode();  
	            if (!FTPReply.isPositiveCompletion(reply)) {  
	                ftp.disconnect();  
	                logger.debug("服务器连接失败");
	                return false; 
	            }  
	            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);  
	            ftp.makeDirectory(path);  
	            ftp.changeWorkingDirectory(path);  
	            ftp.storeFile(filename, input);  
	            input.close();  
	            ftp.logout();  
	            success = true;  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (ftp.isConnected()) {  
	                try {  
	                    ftp.disconnect();  
	                } catch (IOException ioe) {  
	                }  
	            }  
	        }  
	        return success;  
	}
	
	public static void main(String[] args) {
		try {  
	        FileInputStream in=new FileInputStream(new File("D:\\测试_20160804_200623.zip"));  
	        boolean flag = uploadFile("192.168.40.234", 21, "hotdog", "hotdog", "", "test.zip", in);  
	        System.out.println(flag);  
	    } catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	    }  
	}
}
