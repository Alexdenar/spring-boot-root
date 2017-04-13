package com.hotdog.springboot.common.util.encryptionAndDecryption;


import com.hotdog.springboot.common.util.StringUtils;
import com.hotdog.springboot.common.util.clz.Base64Helper;
import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.acegisecurity.providers.encoding.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;

public class RsaHelper {
	
	private static final Logger log = LoggerFactory.getLogger(RsaHelper.class);
	public static final String PUBLIC_KEY = "<RSAKeyValue><Modulus>xS+gGSI/Ynonxsai8ye0IXKFIDygC5vaMZNnabtGy21ln9vRtUabAbsA2ORbmuq+fjHJVSA3/QN3RIOXhljzlhCmZ8mvIZAv/kwX07uQ9u04zN4NVkgm6M0e0hHo9F/Q4QClX4d+FdC6mMxPvCBzxGZdf+SWR+pXThKRIaUcw7E=</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";
	public static final String PRIVATE_KEY = "<RSAKeyValue><Modulus>xS+gGSI/Ynonxsai8ye0IXKFIDygC5vaMZNnabtGy21ln9vRtUabAbsA2ORbmuq+fjHJVSA3/QN3RIOXhljzlhCmZ8mvIZAv/kwX07uQ9u04zN4NVkgm6M0e0hHo9F/Q4QClX4d+FdC6mMxPvCBzxGZdf+SWR+pXThKRIaUcw7E=</Modulus><Exponent>AQAB</Exponent><P>/zJekrH3MTvDXMdcWAWBnBO1mkCfGblyaphNy5aW+RCBOOPqSp3bllLXReO4M3QAk7jpjCScvMEK7ZQH5b6/fQ==</P><Q>xc6DM4hAffIxDVfHtO5tDw9yPGrbpvGGBgFkqIMny/YrV7/cL8jX80+a+TR4OsSiEMbWH95PNUxZ1ACTBsZzRQ==</Q><DP>9ptTHslRiccp0+dnSjwP8qZtMKHP3YkCkxWY3+sm+4pChZphlzeSsmJdW1FieH5Z7GadcesTd6fIiqspMo+w9Q==</DP><DQ>FcFo3LoXncKNcpI8/MFcbVOdlNeL4QifXPm6QG5sahphGf2YEglc9v3CZbZHou8NhBraYhbpT0teddq6UPxyoQ==</DQ><InverseQ>DRZ9ZZru4jnIQjZzNdzNks9tX2WCE2d5xQDZekektG75k5stFzyT2/n/3g9E03yt3xxgHhqINL6VS5cj6YNz1Q==</InverseQ><D>ewpyvfwuzQYoV9KOz2dQ8SYCh9EMlPHVPuYZu1IOpNVIem2x94ltI7FKoAwuVQZxg1ERrjqCdTB6dZgfLsfGhhyF1o/FnkK3AO+uPAGbHxINa4Klv1RVM/CnaMkSCQLjdcEGXjhbBQoTucqYhGTcnP3Znx4uZU8S37GXQYFhoiE=</D></RSAKeyValue>";
	
	public static final String PUK4YZSB = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCVKBLIcwJRjA++2S0vzjyxiJnih6gSovXSr9IBpc4OmXQ5dDfNn/FU7u6lrE6CImc90qUHEsZk63fIALvrTGqITHvHr1hh32D/+y3tj3RrCuFfzDwBg0JR99y8OyRCJBSFPpioSib0dV1V/rZltoh5SqbSh0GrI+I9he/tiFDTiQIDAQAB";
	public static final String PRK4YZSB = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJUoEshzAlGMD77ZLS/OPLGImeKHqBKi9dKv0gGlzg6ZdDl0N82f8VTu7qWsToIiZz3SpQcSxmTrd8gAu+tMaohMe8evWGHfYP/7Le2PdGsK4V/MPAGDQlH33Lw7JEIkFIU+mKhKJvR1XVX+tmW2iHlKptKHQasj4j2F7+2IUNOJAgMBAAECgYEAjxRw6C5DFBzKYeYNDaXF3PvPulktZitCqoeUaZY20xRrRE3y1eJO642EChL/AKCl7s0pF17wR5EFRytFGNMmAP/oGWPSmQ2DN1V2OMb1UsvcVknQ6/3zEcmFouBgkkTaprkdb1nBPGXn5Kx0aOWTH5DYACz0BBcfTNmqo1EM+U0CQQDPSbQPR47DOnB1EG/RmlqzO9HRNL6VOA3nWykF7o0myB6AKF31pIMSSFBuvLXfwDg8SJxsjYnTjmXhosnCYc7jAkEAuDU6LRDfv9CerWzFCwvF1770qF02OKxPyBL95Hdyvz6EagHepJjUpMSODabhJ/enFRriI1XvNMc6qr/hCXfTowJBAMG9u4PEeBTU3CMuN7UKtddsA9GroK3LdINW2uLmchtbU9ao5BkhqDhASp0V2E/HU0hU/Y8gQz/uih74jecWj80CQB+9LBTvv12xeGWxRBIvKRuPT2BjDyCcr/JtM0nWOvMwtmf9gnshyiHJrCA2DL1yMlu0kho8iEazvSwGe6PB/bkCQHjv0ECRinkSE3MDsCQNAiRAoDaPD5Z8xn0AafEyefqdOkhpNAUqaNwV3vELYPHd9aSUYwC+N3PoIlfueum7cEw=";

	/**
	 * 生成RSA密钥对(默认密钥长度为1024)
	 * 
	 * @return
	 */
	public static KeyPair generateRSAKeyPair() {
		return generateRSAKeyPair(1024);
	}

	/**
	 * 生成RSA密钥对
	 * 
	 * @param keyLength
	 *            密钥长度，范围：512～2048
	 * @return
	 */
	public static KeyPair generateRSAKeyPair(int keyLength) {
		try {
			//KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA/ECB/PKCS1Padding");
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(keyLength);
			return kpg.genKeyPair();
		} catch (NoSuchAlgorithmException e) {
			log.error("生成RSA密钥对", e);
			return null;
		}
	}

	/*
	 * java端公钥转换成C#公钥
	 * 
	 * */
	public static String encodePublicKeyToXml(PublicKey key) {
		if (!RSAPublicKey.class.isInstance(key)) {
			return null;
		}
		RSAPublicKey pubKey = (RSAPublicKey) key;
		StringBuilder sb = new StringBuilder();

		sb.append("<RSAKeyValue>");
		sb.append("<Modulus>").append(
				Base64Helper.encode(pubKey.getModulus().toByteArray())).append(
				"</Modulus>");
		sb.append("<Exponent>").append(
				Base64Helper.encode(pubKey.getPublicExponent().toByteArray()))
				.append("</Exponent>");
		sb.append("</RSAKeyValue>");
		return sb.toString();
	}

	/*
	 * C#端公钥转换成java公钥
	 * 
	 * */
	public static PublicKey decodePublicKeyFromXml(String xml) {
		xml = xml.replaceAll("\r", "").replaceAll("\n", "");
		BigInteger modulus = new BigInteger(1, Base64Helper.decode(StringUtils
				.getMiddleString(xml, "<Modulus>", "</Modulus>")));
		BigInteger publicExponent = new BigInteger(1, Base64Helper
				.decode(StringUtils.getMiddleString(xml, "<Exponent>",
						"</Exponent>")));

		RSAPublicKeySpec rsaPubKey = new RSAPublicKeySpec(modulus,
				publicExponent);

		KeyFactory keyf;
		try {
			keyf = KeyFactory.getInstance("RSA");
		
			return keyf.generatePublic(rsaPubKey);
		} catch (Exception e) {
			log.error("C#端公钥转换成java公钥", e);
			return null;
		}
	}

	/*
	 * C#端私钥转换成java私钥
	 * 
	 * */
	public static PrivateKey decodePrivateKeyFromXml(String xml) {
		xml = xml.replaceAll("\r", "").replaceAll("\n", "");
		BigInteger modulus = new BigInteger(1, Base64Helper.decode(StringUtils
				.getMiddleString(xml, "<Modulus>", "</Modulus>")));
		BigInteger publicExponent = new BigInteger(1, Base64Helper
				.decode(StringUtils.getMiddleString(xml, "<Exponent>",
						"</Exponent>")));
		BigInteger privateExponent = new BigInteger(1, Base64Helper
				.decode(StringUtils.getMiddleString(xml, "<D>", "</D>")));
		BigInteger primeP = new BigInteger(1, Base64Helper.decode(StringUtils
				.getMiddleString(xml, "<P>", "</P>")));
		BigInteger primeQ = new BigInteger(1, Base64Helper.decode(StringUtils
				.getMiddleString(xml, "<Q>", "</Q>")));
		BigInteger primeExponentP = new BigInteger(1, Base64Helper
				.decode(StringUtils.getMiddleString(xml, "<DP>", "</DP>")));
		BigInteger primeExponentQ = new BigInteger(1, Base64Helper
				.decode(StringUtils.getMiddleString(xml, "<DQ>", "</DQ>")));
		BigInteger crtCoefficient = new BigInteger(1, Base64Helper
				.decode(StringUtils.getMiddleString(xml, "<InverseQ>",
						"</InverseQ>")));

		RSAPrivateCrtKeySpec rsaPriKey = new RSAPrivateCrtKeySpec(modulus,
				publicExponent, privateExponent, primeP, primeQ,
				primeExponentP, primeExponentQ, crtCoefficient);

		KeyFactory keyf;
		try {
			keyf = KeyFactory.getInstance("RSA");
			return keyf.generatePrivate(rsaPriKey);
		} catch (Exception e) {
			log.error("C#端私钥转换成java私钥", e);
			return null;
		}
	}

	/*
	 * java端私钥转换成C#私钥
	 * 
	 * */
	public static String encodePrivateKeyToXml(PrivateKey key) {
		if (!RSAPrivateCrtKey.class.isInstance(key)) {
			return null;
		}
		RSAPrivateCrtKey priKey = (RSAPrivateCrtKey) key;
		StringBuilder sb = new StringBuilder();

		sb.append("<RSAKeyValue>");
		sb.append("<Modulus>").append(
				Base64Helper.encode(priKey.getModulus().toByteArray())).append(
				"</Modulus>");
		sb.append("<Exponent>").append(
				Base64Helper.encode(priKey.getPublicExponent().toByteArray()))
				.append("</Exponent>");
		sb.append("<P>").append(
				Base64Helper.encode(priKey.getPrimeP().toByteArray())).append(
				"</P>");
		sb.append("<Q>").append(
				Base64Helper.encode(priKey.getPrimeQ().toByteArray())).append(
				"</Q>");
		sb.append("<DP>").append(
				Base64Helper.encode(priKey.getPrimeExponentP().toByteArray()))
				.append("</DP>");
		sb.append("<DQ>").append(
				Base64Helper.encode(priKey.getPrimeExponentQ().toByteArray()))
				.append("</DQ>");
		sb.append("<InverseQ>").append(
				Base64Helper.encode(priKey.getCrtCoefficient().toByteArray()))
				.append("</InverseQ>");
		sb.append("<D>").append(
				Base64Helper.encode(priKey.getPrivateExponent().toByteArray()))
				.append("</D>");
		sb.append("</RSAKeyValue>");
		return sb.toString();
	}

	// 用公钥加密
	public static byte[] encryptData(byte[] data, PublicKey pubKey) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			return cipher.doFinal(data);
		} catch (Exception e) {
			log.error("用公钥加密", e);
			return null;
		}
	}

	// 用私钥加密
	public static byte[] encryptData(byte[] data, PrivateKey priKey) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, priKey);
			return cipher.doFinal(data);
		} catch (Exception e) {
			log.error("用公钥加密", e);
			return null;
		}
	}

	// 用私钥解密
	public static byte[] decryptData(byte[] encryptedData, PrivateKey priKey) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, priKey);
			return cipher.doFinal(encryptedData);
		} catch (Exception e) {
			log.error("用私钥解密", e);
			return null;
		}
	}

	// 用公钥解密
	public static byte[] decryptData(byte[] encryptedData, PublicKey pubKey) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, pubKey);
			return cipher.doFinal(encryptedData);
		} catch (Exception e) {
			log.error("用私钥解密", e);
			return null;
		}
	}
	
	/**
	 * 根据指定公钥进行明文加密
	 * 
	 * @param plainText
	 *            要加密的明文数据
	 * @param pubKey
	 *            公钥
	 * @return
	 */
	public static String encryptDataFromStr(String plainText, PublicKey pubKey) {
		
		try {
	        byte[] dataByteArray = plainText.getBytes("gb2312");
	        byte[] encryptedDataByteArray = RsaHelper.encryptData(
	                dataByteArray, pubKey);	        
			return Base64Helper.encode(encryptedDataByteArray);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("根据指定公钥进行明文加密", e);
			return "";
		}
	}

	/**
	 * 根据指定私钥对数据进行签名(默认签名算法为"SHA1withRSA")
	 * 
	 * @param data
	 *            要签名的数据
	 * @param priKey
	 *            私钥
	 * @return
	 */
	public static byte[] signData(byte[] data, PrivateKey priKey) {
		return signData(data, priKey, "SHA1withRSA");
	}

	/**
	 * 根据指定私钥和算法对数据进行签名
	 * 
	 * @param data
	 *            要签名的数据
	 * @param priKey
	 *            私钥
	 * @param algorithm
	 *            签名算法
	 * @return
	 */
	public static byte[] signData(byte[] data, PrivateKey priKey,
			String algorithm) {
		try {
			Signature signature = Signature.getInstance(algorithm);
			signature.initSign(priKey);
			signature.update(data);
			return signature.sign();
		} catch (Exception ex) {
			log.error("根据指定私钥和算法对数据进行签名", ex);
			return null;
		}
	}

	/**
	 * 用指定的公钥进行签名验证(默认签名算法为"SHA1withRSA")
	 * 
	 * @param data
	 *            数据
	 * @param sign
	 *            签名结果
	 * @param pubKey
	 *            公钥
	 * @return
	 */
	public static boolean verifySign(byte[] data, byte[] sign, PublicKey pubKey) {
		return verifySign(data, sign, pubKey, "SHA1withRSA");
	}

	/**
	 * 
	 * @param data
	 *            数据
	 * @param sign
	 *            签名结果
	 * @param pubKey
	 *            公钥
	 * @param algorithm
	 *            签名算法
	 * @return
	 */
	public static boolean verifySign(byte[] data, byte[] sign,
			PublicKey pubKey, String algorithm) {
		try {
			Signature signature = Signature.getInstance(algorithm);
			signature.initVerify(pubKey);
			signature.update(data);
			return signature.verify(sign);
		} catch (Exception ex) {
			log.error("校验签名", ex);
			return false;
		}
	}
	
	private final static String batch_spliter = "govnet";

	/**
	 * 
	 * @param dataStr
	 *            数据
	 * @param pubKeyXml
	 *            公钥
	 * @return
	 */
	public static String encrypt(String dataStr, String pubKeyXml)throws Exception{
		PublicKey pubKey = RsaHelper.decodePublicKeyFromXml(pubKeyXml);
        StringBuilder sb = new StringBuilder();

		String sOld = "";
		String sNew = "";
		for(int i=0;i<dataStr.length();i++){
			sNew += dataStr.substring(i, i+1);
			
			if(sNew.getBytes("utf-8").length>117){
		        byte[] dataByteArray = sOld.getBytes("utf-8");
	            byte[] encryptedDataByteArray = RsaHelper.encryptData(dataByteArray, pubKey);
	            String str = Base64Helper.encode(encryptedDataByteArray);
	            sb.append(str+batch_spliter);
		        
				sOld = "";
				sNew = "";
			}else{
				sOld = sNew;
			}
		}
		
		if(sOld.length()>0){
	        byte[] dataByteArray = sOld.getBytes("utf-8");
            byte[] encryptedDataByteArray = RsaHelper.encryptData(dataByteArray, pubKey);
            String str = Base64Helper.encode(encryptedDataByteArray);
            sb.append(str+batch_spliter);
		}
                
        return sb.toString();
	}

	/**
	 * 
	 * @param encryptedStr
	 *            加密数据
	 * @param priKeyXml
	 *            私钥
	 * @return 解密字串
	 */
	public static String decrypt(String encryptedStr, String priKeyXml)throws Exception{
		PrivateKey priKey = RsaHelper.decodePrivateKeyFromXml(priKeyXml);
        
        String strArry[]=encryptedStr.split(batch_spliter);
        
        StringBuilder sb = new StringBuilder();
        for(String sOne:strArry){
        	if(sOne==null||sOne.trim().equals("")) break; 
	        byte[] encryptedDataByteArray = Base64Helper.decode(sOne);
    		
            byte[] decryptedDataByteArray = RsaHelper.decryptData(encryptedDataByteArray, priKey);
            String str = new String(decryptedDataByteArray, "utf-8");            
            sb.append(str);
        }

        return sb.toString();
	}
	
	/**
	 * 数据加密（海鑫）
	 * @param publicKey 公钥
	 * @param primaryKey 私钥
	 * @param str 字符串
	 * @return
	 * @throws Exception
	 */
	public static String encrypt4h(String publicKey, String primaryKey, String str) throws Exception {
		StringBuffer s = new StringBuffer("");
		PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		// 勘验号 + 勘验ID + 案件ID (跟旧加密顺序有变化)，加密得到md5值
		String md5 = passwordEncoder.encodePassword(str, null);
		// 将md5进行RSA加密
		String v = toKeySury(md5, publicKey);
		// 拼接加密后的字符串
		s.append("<v>").append(v).append("</v>");
		// 拼接解密密钥
		s.append("<p>").append(primaryKey).append("</p>");
		return s.toString();
	}
	
	/**
	 * 数据加密（一长四必平台）
	 * @param publicKey 公钥
	 * @param primaryKey 私钥
	 * @param str 字符串
	 * @return
	 * @throws Exception
	 */
	public static String encrypt4yzsb(String publicKey, String str) throws Exception {
		return toKeySury(str, publicKey);
	}
	
	/**
	 * 数据加密（海鑫）
	 * @param publicKey 公钥
	 * @param primaryKey 私钥
	 * @param str 字符串
	 * @return
	 * @throws Exception
	 */
	public static String encrypt4md5(String publicKey, String primaryKey, String md5) throws Exception {
		StringBuffer s = new StringBuffer("");
		// 将md5进行RSA加密
		String v = toKeySury(md5, publicKey);
		// 拼接加密后的字符串
		s.append("<v>").append(v).append("</v>");
		// 拼接解密密钥
		s.append("<p>").append(primaryKey).append("</p>");
		return s.toString();
	}
	
	/**
	 * 数据解密（海鑫）
	 * @param encriptStr
	 * @return
	 */
	public static String decrypt4h(String encriptStr, String prks) {
		String v = null;
		try {
			v = StringUtils.getMiddleString(encriptStr, "<v>", "</v>");
			String p1 = StringUtils.getMiddleString(encriptStr, "<p1>", "</p1>");
			String p2 = StringUtils.getMiddleString(encriptStr, "<p2>", "</p2>");
			// 解密p2
			p2 = getKeySury(p2, prks);
			v = getKeySury(v, p1 + p2);
		} catch (Exception e) {
			e.printStackTrace();
			return "invalid";
		}
		return v;
	}
	
	/**
	 * 数据解密（一长四必）
	 * @param encriptStr
	 * @return
	 */
	public static String decrypt4yzsb(String primaryKey, String str) {
		String v = null;
		try {
			v = getKeySury(str, primaryKey);
		} catch (Exception e) {
			e.printStackTrace();
			return "invalid";
		}
		return v;
	}
	
	//	公钥转换
	public static PublicKey getPubKey(String pubKey) throws Exception{
		BASE64Decoder be=new BASE64Decoder ();
		KeyFactory keyFactory=KeyFactory.getInstance("RSA");
		EncodedKeySpec  pubKeySpec=new X509EncodedKeySpec(be.decodeBuffer(pubKey));
		return keyFactory.generatePublic(pubKeySpec);
	}
	
	//私钥转换
	public static PrivateKey getPriKey(String priKey) throws Exception{
		BASE64Decoder  be=new BASE64Decoder ();
		KeyFactory keyFactory=KeyFactory.getInstance("RSA");
		EncodedKeySpec  priKeySpec=new PKCS8EncodedKeySpec(be.decodeBuffer(priKey));
		return keyFactory.generatePrivate(priKeySpec);
	}
	
	//加密
	public static String toKeySury(String param,String pubKey) throws Exception{
		Key key=getPubKey(pubKey);
		Cipher c=Cipher.getInstance("RSA");
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] b=param.getBytes();
		byte[] b1=c.doFinal(b);
		BASE64Encoder en=new BASE64Encoder();
		return en.encode(b1 );
	}
	
	//解密
	public static String getKeySury(String param,String privateKey) throws Exception{
		Key key=getPriKey(privateKey);
		BASE64Decoder de=new BASE64Decoder();
		byte[] b=de.decodeBuffer(param);
		Cipher c=Cipher.getInstance("RSA");
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] b1=c.doFinal(b);
		return new String(b1);
	}
	
	public static void main(String[] args) throws Exception {
		/*String jstr = "{\"loginid\":\"350705\",\"NODEID\":\"13\",\"IDCARD\":\"350125198606153116\",\"sessionId\":\"d897fdf8d89fe87re7r7e9yye89r7e7re3454\"}";
		System.out.println("原字符串：" + jstr);
		jstr = RsaHelper.encrypt(jstr, RsaHelper.PUBLIC_KEY);
		System.out.println("加密后：" + jstr);
		System.out.println("encode：" + URLEncoder.encode(jstr, "UTF-8"));
		jstr = RsaHelper.decrypt(jstr, RsaHelper.PRIVATE_KEY);
		System.out.println("解密后：" + jstr);*/
		
		String jstr = "{\"username\":\"admin\", \"password\":\"21232f297a57a5a743894a0e4a801fc3\"}";
		jstr = RsaHelper.encrypt4yzsb(RsaHelper.PUK4YZSB, jstr);
		System.out.println(jstr);
		jstr = RsaHelper.decrypt4yzsb(RsaHelper.PRK4YZSB, jstr);
		System.out.println(jstr);
		
		String s ="YDiRSys+4LMgXER+fPUqjRc1CqhGTxH86hcHYobkPCg2RZO/JRUcd5ebeCYB96dATBiV57Rty5hyC/5PeDxsiE3JViudlOdr0lkKdIEuCBO39UMWYSA5SpI4b26AoJ9E43eKLrwh/5uzXMf6cJ0Easp1/Cpug5wiU0CHs6lVWlM=";
		System.out.println(URLEncoder.encode(s));
	}
	
}
