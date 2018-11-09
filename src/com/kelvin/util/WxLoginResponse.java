package com.kelvin.util;

public class WxLoginResponse {

	private String openId;
	private String sessionKey;

	public WxLoginResponse(String openId, String sessionKey) {
		super();
		this.openId = openId;
		this.sessionKey = sessionKey;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

}
