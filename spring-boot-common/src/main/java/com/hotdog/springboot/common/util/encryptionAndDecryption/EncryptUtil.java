/**
 * 
 */
package com.hotdog.springboot.common.util.encryptionAndDecryption;

import com.hisign.aes.AESCoder;
import com.hotdog.springboot.common.util.StringUtils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 加解密工具类
 * @author chailiangzhi
 * @date 2016-5-24
 * 
 */
public class EncryptUtil {

	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger("");

	/**
	 * 加密
	 * @param reqMsg
	 */
	public static String encryptMsg(JSONObject reqJson, String secretKey) throws IOException {
		// 加密
		String encryptField = "rspBody";
		logger.info("secretKey is:{}", secretKey);
		if (StringUtils.isNull(secretKey)) {
			return reqJson.toString();
		}
		String reqBodyPlain = reqJson.optString(encryptField);
		if (StringUtils.isNull(reqBodyPlain)) {
			return reqJson.toString();
		}
		String reqBodyEn = AESCoder.encrypt(reqBodyPlain, secretKey);
		logger.info("reqBodyEn is:{}", reqBodyEn);
		reqJson.put(encryptField, reqBodyEn);
		String reqMsg = reqJson.toString();
		return reqMsg;
	}
	/**
	 * 加密
	 * @param reqMsg
	 */
	public static String encryptMsgReq(String reqbody, String secretKey) throws IOException {
		// 加密
		logger.info("secretKey is:{}", secretKey);
		if (StringUtils.isNull(secretKey)) {
			return reqbody;
		}
		if (StringUtils.isNull(reqbody)) {
			return reqbody;
		}
		String reqBodyEn = AESCoder.encrypt(reqbody, secretKey);
		logger.info("reqBodyEn is:{}", reqBodyEn);
		return reqBodyEn;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
