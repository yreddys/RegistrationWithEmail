package com.reg.model;

import lombok.Data;

@Data
public class LoginResponse {
	public LoginResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }
	private String message;
	private String token;
}
