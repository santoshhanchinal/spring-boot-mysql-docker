package com.bham.sso.utils.encryptUtils;

/**
 * Base64-bit encryption, decryption, using apache Base64 algorithm
 * @author Rujia Li
 * @author Alessandro Pozzer
 * @author Federico Bacci
 *
 */
public final class Base64 { 

	/**
	 * Base64 encryption
	 * @param origin
	 * @return
	 */
	public static String encrypt(String origin) {
		if (origin == null) {
			return "";
		}		
		return new String(org.apache.commons.codec.binary.Base64.encodeBase64(origin.getBytes()));
	}

	/**
	 * base64 decryption
	 * @param dest
	 * @return
	 */
	public static String dencrypt(String dest) {
		if (dest == null) {
			return "";
		}
		return new String(org.apache.commons.codec.binary.Base64.decodeBase64(dest.getBytes()));
	}
}
