package com.yjy;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * RSA加、解密算法工具类
 */
public class RsaUtil {
	
	private static Encoder encoder = Base64.getEncoder();
	private static Decoder decoder = Base64.getDecoder();

	private static String PUBLIC_KEY = "publicKey";
	private static String PRIVATE_KEY = "privateKey";
	private static Map<String, String> keyMap = new HashMap<>(); // 用于封装随机产生的公钥与私钥

	/**
	 * 随机生成密钥对
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static void genKeyPair() throws NoSuchAlgorithmException {
		// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		// 初始化密钥对生成器，密钥大小为96-1024位
		keyPairGen.initialize(1024, new SecureRandom());
		// 生成一个密钥对，保存在keyPair中
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); // 得到私钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); // 得到公钥
		String publicKeyString = new String(encoder.encode(publicKey.getEncoded()));
		// 得到私钥字符串
		String privateKeyString = new String(encoder.encode((privateKey.getEncoded())));
		// 将公钥和私钥保存到Map
		keyMap.put(PUBLIC_KEY, publicKeyString);
		keyMap.put(PRIVATE_KEY, privateKeyString);
	}

	/**
	 * RSA公钥加密
	 * 
	 * @param str
	 *            加密字符串
	 * @param publicKey
	 *            公钥
	 * @return 密文
	 * @throws Exception
	 *             加密过程中的异常信息
	 */
	public static String encrypt(String str, String publicKey) throws Exception {
		// base64编码的公钥
		byte[] decoded = decoder.decode(publicKey);
		RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
				.generatePublic(new X509EncodedKeySpec(decoded));
		// RSA加密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		String outStr = encoder.encodeToString(cipher.doFinal(str.getBytes("UTF-8")));
		return outStr;
	}

	/**
	 * RSA私钥解密
	 * 
	 * @param str
	 *            加密字符串
	 * @param privateKey
	 *            私钥
	 * @return 铭文
	 * @throws Exception
	 *             解密过程中的异常信息
	 */
	public static String decrypt(String str, String privateKey) throws Exception {
		// 64位解码加密后的字符串
		byte[] inputByte = decoder.decode(str.getBytes("UTF-8"));
		// base64编码的私钥
		byte[] decoded = decoder.decode(privateKey);
		RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
				.generatePrivate(new PKCS8EncodedKeySpec(decoded));
		// RSA解密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		String outStr = new String(cipher.doFinal(inputByte));
		return outStr;
	}
	
	public static void main(String[] args) throws Exception {
		// 生成公钥和私钥
		RsaUtil.genKeyPair();
		System.out.println("随机生成的公钥为:" + keyMap.get(PUBLIC_KEY));
		System.out.println("随机生成的私钥为:" + keyMap.get(PRIVATE_KEY));
		
		// 加密字符串
		String message = "123";
		String messageEn = encrypt(message, keyMap.get(PUBLIC_KEY));
		System.out.println("加密:" + messageEn);
		String messageDe = decrypt(messageEn, keyMap.get(PRIVATE_KEY));
		System.out.println("解密:" + messageDe);
	}
}
