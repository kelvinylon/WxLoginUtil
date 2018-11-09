package com.kelvin.util;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class WxDecryptUtil {

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	public static byte[] decryptData(byte[] encryptedDataArray, byte[] sessionKeyArray, byte[] ivArray) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
			Key key = new SecretKeySpec(sessionKeyArray, "AES");
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivArray));
			return cipher.doFinal(encryptedDataArray);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
