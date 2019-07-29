package com.hp.cc.security.jwt;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JwtAuthenticationResponse(String token) {
		super();
		this.token = token;
	}
	
}
