package com.example.form;

import javax.persistence.Table;

import lombok.Data;

@Table(name = "users")
@Data
public class LoginForm {
	private String account;

	private String password;

	private String passwordConfirm;
}
