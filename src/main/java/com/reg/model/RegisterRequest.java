package com.reg.model;

import lombok.Data;

@Data
public class RegisterRequest {
	private String Name;
	private String password;
	private String email;
}
