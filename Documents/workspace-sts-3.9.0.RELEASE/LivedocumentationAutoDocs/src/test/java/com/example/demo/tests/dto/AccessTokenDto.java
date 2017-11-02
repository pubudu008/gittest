package com.example.demo.tests.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccessTokenDto implements Serializable {
	
	private String access_token;
	private String token_type;
	private String refresh_token;
	private String expires_in;
	private String scope;

}
