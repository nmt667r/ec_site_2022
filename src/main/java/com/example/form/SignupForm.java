package com.example.form;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;

import lombok.Data;

@Table(name = "users")
@Data
public class SignupForm implements Serializable {
	private String account;

	private String name;

	private String password;

	private String passwordConfirm;

	private Integer is_stopped;

	private Date created_datatime;

	private Date updated_datatime;
}
