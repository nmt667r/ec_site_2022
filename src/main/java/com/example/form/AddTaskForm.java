package com.example.form;

import lombok.Data;

@Data
public class AddTaskForm {
	private String taskName;

	private String memo;

	private String deadline;
}
