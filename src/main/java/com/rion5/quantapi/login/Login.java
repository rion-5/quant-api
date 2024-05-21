package com.rion5.quantapi.login;

import com.rion5.quantapi.user.User;

import lombok.Data;

@Data
public class Login {
	private Boolean success;
	private User user;
	private String message;
	private String token;

}
