package com.example.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String account;

	@Column
	private String name;

	@Column
	private String password;

	@Column
	private Integer is_stopped;

	@Column
	@CreationTimestamp
	private Date created_datatime;

	@Column
	@UpdateTimestamp
	private Date updated_datatime;
}
