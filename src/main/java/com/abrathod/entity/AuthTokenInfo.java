package com.abrathod.entity;

public class AuthTokenInfo {
	
	private String token_type;
	private String refresh_token;
	private String access_token;
	private String scope;
	private int expire_in;
	
	
	
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public int getExpire_in() {
		return expire_in;
	}
	public void setExpire_in(int expire_in) {
		this.expire_in = expire_in;
	}
	
	@Override
	public String toString() {
		return "AuthTokenInfo [token_type=" + token_type + ", refresh_token=" + refresh_token + ", access_token="
				+ access_token + ", scope=" + scope + ", expire_in=" + expire_in + "]";
	}
	
	
	
	
}
