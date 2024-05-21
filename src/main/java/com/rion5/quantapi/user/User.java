package com.rion5.quantapi.user;

import java.time.LocalDate;

import lombok.Data;

@Data
public class User {
	private int id;
	private String name;
	private String email;
	private String password;
	private LocalDate created_date;
	private boolean activation;
}

//public record User(int id, String name, String email, String password,LocalDate created_date, boolean activation) {}