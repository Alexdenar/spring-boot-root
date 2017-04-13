package com.hotdog.springboot.common.util.ftp;

import com.hotdog.springboot.common.util.yxl.FileUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FTPUtils {
	public static FTPClient ftp;
	/**
	 * 
	 */
	private final static Logger logger = LoggerFactory.getLogger(FTPClientUtils.class);

	/**
	 * @param path 上传到ftp服务器哪个路径下
	 * @param addr 地址
	 * @param port 端口号
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 * @return
	 * @throws Exception
	 */
	public static boolean connect(String path, String addr, int port, String username, String password)
			throws Exception {
		boolean result = false;
		ftp = new FTPClient();
		ftp.connect(addr);
		ftp.setConnectTimeout(3000);// 超时时间为3秒
		boolean loginFlag = ftp.login(username, password);
		if (loginFlag) {
			logger.debug("服务器连接成功！");
			ftp.enterLocalPassiveMode();
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftp.changeWorkingDirectory("/");
			result = true;
			return result;
		} else {
			ftp.disconnect();
			logger.debug("服务器连接失败！");
			return result;
		}
	}

	/**
	 * @param file 上传的文件或文件夹
	 * @throws IOException
	 * @throws Exception
	 */
	public static void upload(File category) throws IOException {
		logger.info("开始上传FTP文件...");
		if (category.isDirectory()) {
			logger.debug("如果是文件夹！！！！！！");
			// ftp.makeDirectory(file.getName());
			ftp.changeWorkingDirectory(category.getName());

			String[] files = category.list();
			logger.info("待上传文件数：" + files.length);
			
			// 创建备份文件夹
			File bakDir = new File(category.getAbsolutePath() + File.separator + "bakDir");
			if (!bakDir.exists()) {
				bakDir.mkdirs();
			}

			for (String fileName : files) {
				File file = new File(category.getAbsolutePath() + File.separator + fileName);
				if (file.isDirectory()) {
					continue;
				}
				logger.debug("上传文件:" + fileName);

				FileInputStream input = null;
				try {
					input = new FileInputStream(file);
					if (ftp.storeFile(file.getName(), input)) {
						input.close();
						// 移除到备份文件夹
						FileUtil.copyFile(file.getAbsolutePath(), bakDir.getAbsolutePath() + File.separator + fileName);
						// 上传成功后删除文件
						file.delete();
					}
					
					logger.info("上传成功:" + fileName);
				} catch (IOException e) {
					throw e;
				} finally {
					if (null != input) {
						try {
							input.close();
						} catch (IOException e) {
						}
					}
				}

			}
		}

		logger.info("上传FTP文件结束!");
		ftp.logout();
	}

	public static void main(String[] args) throws Exception {
		FTPUtils t = new FTPUtils();
		t.connect("", "172.16.0.3", 21, "sp", "sp");
		File file = new File("D:\\zipUrl");
		t.upload(file);
	}
}
