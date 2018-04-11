package com.pj.core.util;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * 
 * @ClassName: RSAUtils
 * @Description: RSA加解密工具
 *
 */
public class RSAUtils {
	
	private static String UTF8 = "UTF-8";
	
	private static Logger log = Logger.getLogger(RSAUtils.class);
	
	/**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";
     
    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";
 
    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";
     
    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";
     
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
     
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
	
	/** 
     * 生成密钥对(公钥和私钥)
     * 
     * @return 
     */
    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }
    
    /** 
     * 获取私钥
     * 
     * @param keyMap 密钥对
     * @return
     */
    public static String getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /** 
     * 获取公钥
     * 
     * @param keyMap 密钥对
     * @return
     */
    public static String getPublicKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

	/**
	 * 使用公钥验证签名
	 * @param publicKey-公钥
	 * @param data-签名字符串
	 * @param sign-签名
	 * @return
	 */
	public static boolean verifyByPublicKey(byte[] publicKey, byte[] data, byte[] sign) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey key = keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(publicKey)));
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(key);
            signature.update(data);
            return signature.verify(Base64.decodeBase64(sign));
        } catch (Exception e) {
            log.error("验签失败：", e);
            return false;
        }
    }
    
	/**
	 * 使用私钥加签
	 * @param privateKey-私钥
	 * @param data-签名字符串
	 * @return
	 */
    public static byte[] signByPrivateKey(byte[] privateKey, byte[] data) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey key = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey)));

            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(key);
            signature.update(data);

            return signature.sign();
        } catch (Exception e) {
            log.error("rsa签名失败：", e);
        }
        return null;
    }
    
	/**
	 * 公钥解密
	 * 
	 * @param encryptedStr
	 *            已加密数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 */
	public static String decryptByPublicKey(String encryptedStr, String publicKey) {
		try {
			byte[] encryptedData = Base64.decodeBase64(encryptedStr);
			byte[] keyBytes = Base64.decodeBase64(publicKey);
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key publicK = keyFactory.generatePublic(x509KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, publicK);
			int inputLen = encryptedData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher
							.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher
							.doFinal(encryptedData, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return new String(decryptedData, UTF8);
		} catch (Exception e) {
			log.error("rsa解密失败：", e);
		}
		return null;
	}

	/**
	 * 私钥加密
	 * 
	 * @param data
	 *            源数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 */
	public static String encryptByPrivateKey(byte[] data, String privateKey) {
		try {
			byte[] keyBytes = Base64.decodeBase64(privateKey);
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, privateK);
			int inputLen = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段加密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(data, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] encryptedData = out.toByteArray();
			out.close();
			return Base64.encodeBase64String(encryptedData);
		} catch (Exception e) {
			log.error("rsa加密失败：", e);
		}
		return null;
	}
    
}
