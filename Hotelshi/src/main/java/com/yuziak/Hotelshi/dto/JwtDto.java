package com.yuziak.Hotelshi.dto;

import java.io.Serializable;

public class JwtDto implements Serializable {
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
