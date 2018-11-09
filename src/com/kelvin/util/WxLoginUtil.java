package com.kelvin.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Base64;
import java.util.Base64.Decoder;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class WxLoginUtil {

	private static String URL = "https://api.weixin.qq.com/sns/jscode2session";

	@SuppressWarnings("deprecation")
	public static WxLoginResponse wxLogin(String appid, String secret, String code) {
		InputStream in = null;
		try {
			String url = URL + "?appid=" + appid + "&secret=" + secret + "&js_code=" + code
					+ "&grant_type=authorization_code";
			URL u = new URL(url);
			in = u.openStream();
			String content = IOUtils.toString(in, "utf-8");
			JSONObject json = new JSONObject(content);
			String openId = json.getString("openid");
			String sessionKey = json.getString("session_key");

			return new WxLoginResponse(openId, sessionKey);
		} catch (Exception e) {
			throw new RuntimeException("微信登录失败", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
	
	
	public static String decryptData(String encryptedData, String sessionKey, String iv) {
		Decoder decoder = Base64.getDecoder();
		byte[] encryptedDataArray = decoder.decode(encryptedData);
		byte[] sessionKeyArray =decoder.decode(sessionKey);
		byte[] ivArray = decoder.decode(iv);
		byte[] decryptedData = WxDecryptUtil.decryptData(encryptedDataArray,sessionKeyArray,ivArray);
		try {
			return new String(decryptedData, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}
