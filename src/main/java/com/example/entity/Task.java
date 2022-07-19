/*package com.example.entity;

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
@Table(name = "tasks")
@Data
public class Task {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private int userId;

	@Column
	private String name;

	@Column
	private String memo;

	@Column
	private Integer finished;

	@Column
	private Date deadline;

	@Column
	@CreationTimestamp
	private Date created_datatime;

	@Column
	@UpdateTimestamp
	private Date updated_datatime;
}
*/