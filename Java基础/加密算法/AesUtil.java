package com.yjy;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/*
 * AES对称加密和解密
 */
public class AesUtil {

	private static Encoder encoder = Base64.getEncoder();
	private static Decoder decoder = Base64.getDecoder();

	/*
	 * 加密
	 */
	public static String encode(String encodeRules, String content) {
		try {
			// 1.构造密钥生成器，指定为AES算法,不区分大小写
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			// 2.根据ecnodeRules规则初始化密钥生成器
			// 生成一个128位的随机源,根据传入的字节数组
			keygen.init(128, new SecureRandom(encodeRules.getBytes()));
			// 3.产生原始对称密钥
			SecretKey original_key = keygen.generateKey();
			// 4.获得原始对称密钥的字节数组
			byte[] raw = original_key.getEncoded();
			// 5.根据字节数组生成AES密钥
			SecretKey key = new SecretKeySpec(raw, "AES");
			// 6.根据指定算法AES自成密码器
			Cipher cipher = Cipher.getInstance("AES");
			// 7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
			cipher.init(Cipher.ENCRYPT_MODE, key);
			// 8.将加密后的数据转换为字符串(这里要设置为utf-8，不然内容中如果有中文和英文混合中文就会解密为乱码)
			String AES_encode = new String(encoder.encode(cipher.doFinal(content.getBytes("utf-8"))));
			return AES_encode;
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 如果有错就返加nulll
		return null;
	}

	/*
	 * 解密
	 */
	public static String decode(String encodeRules, String content) {
		try {
			// 1.构造密钥生成器，指定为AES算法,不区分大小写
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			// 2.根据ecnodeRules规则初始化密钥生成器
			// 生成一个128位的随机源,根据传入的字节数组
			keygen.init(128, new SecureRandom(encodeRules.getBytes()));
			// 3.产生原始对称密钥
			SecretKey original_key = keygen.generateKey();
			// 4.获得原始对称密钥的字节数组
			byte[] raw = original_key.getEncoded();
			// 5.根据字节数组生成AES密钥
			SecretKey key = new SecretKeySpec(raw, "AES");
			// 6.根据指定算法AES自成密码器
			Cipher cipher = Cipher.getInstance("AES");
			// 7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
			cipher.init(Cipher.DECRYPT_MODE, key);
			// 8.将加密并编码后的内容解码成字节数组
			String AES_decode = new String(cipher.doFinal(decoder.decode(content)), "utf-8");
			return AES_decode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String encodeRules = "my_rule"; // 加密规则
		String content = "123"; // 要加密的内容
		String aesEncode = AesUtil.encode(encodeRules, content); // 加密
		String aesDncode = AesUtil.decode(encodeRules, aesEncode); // 解密

		System.out.println("加密后的密文是:" + aesEncode);
		System.out.println("解密后的明文是:" + aesDncode);
	}

}