/*package com.example.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.Task;
import com.example.entity.User;
import com.example.form.AddTaskForm;
import com.example.form.UpdateTaskForm;
import com.example.service.ErrMessage;
import com.example.service.TaskService;
import com.example.service.Validation;

@Controller
public class TaskController {
	@Autowired
	HttpSession session;
	@Autowired
	TaskService taskService;

	@GetMapping("/addTask")
	public String displayAddTask(Model model) {
		AddTaskForm addTaskForm = new AddTaskForm();
		model.addAttribute("addTaskForm", addTaskForm);
		return "/addTask";
	}

	@PostMapping("/addTask")
	public String addTask(@ModelAttribute AddTaskForm addTaskForm, Model model) {
		Task task = new Task();
		List<String> errList = new ArrayList<>();
		errList = addTaskValid(errList, addTaskForm);
		if (errList.size() > 0) {
			model.addAttribute("errList", errList);
			model.addAttribute("addTaskForm", addTaskForm);
			return "/addTask";
		} else {
			User user = (User) session.getAttribute("loginUser");
			task.setUserId(user.getId());
			task.setName(addTaskForm.getTaskName());
			task.setMemo(addTaskForm.getMemo());
			task.setDeadline(Timestamp.valueOf(addTaskForm.getDeadline() + " 00:00:00"));
			task.setFinished(0);
			taskService.createTask(task);
			return "redirect:/";
		}
	}

	@GetMapping("/update={id}")
	public String displayUpdateTask(@PathVariable Integer id, Model model) {
		Task task = taskService.findTaskById(id);
		UpdateTaskForm updateTaskForm = new UpdateTaskForm();
		BeanUtils.copyProperties(task, updateTaskForm);
		model.addAttribute("updateTaskForm", updateTaskForm);
		model.addAttribute("task", task);
		return "/update";
	}

	@PostMapping("/update={id}")
	public String updateTask(@PathVariable Integer id, UpdateTaskForm updateTaskForm, Model model) {
		Task task = taskService.findTaskById(id);
		List<String> errList = new ArrayList<>();
		errList = updateTaskValid(errList, updateTaskForm);
		if (errList.size() > 0) {
			model.addAttribute("errList", errList);
			model.addAttribute("updateTaskForm", updateTaskForm);
			return "redirect:/update=" + id;
		} else {
			task.setId(updateTaskForm.getId());
			task.setName(updateTaskForm.getName());
			task.setMemo(updateTaskForm.getMemo());
			taskService.updateTask(task);
			return "redirect:/";
		}
	}

	private List<String> addTaskValid(List<String> errMsg, AddTaskForm addTaskForm) {
		if (Validation.valueIsBlank(addTaskForm.getTaskName().length())) {
			errMsg.add(ErrMessage.nullMessage("タスク名"));
		} else if (Validation.lengthOver(20, addTaskForm.getTaskName().length())) {
			errMsg.add(ErrMessage.lengthOverMessage("タスク名", 20));
		}

		Timestamp nowstamp = new Timestamp(System.currentTimeMillis());
		if (Validation.valueIsBlank(addTaskForm.getDeadline().length())) {
			errMsg.add(ErrMessage.nullMessage("期日"));
		} else if (!Timestamp.valueOf(addTaskForm.getDeadline() + " 00:00:00").after(nowstamp)) {
			errMsg.add(ErrMessage.setBeforeDeadline());
		}

		if (addTaskForm.getMemo() != null) {
			if (Validation.lengthOver(30, addTaskForm.getMemo().length())) {
				errMsg.add(ErrMessage.lengthOverMessage("メモ", 30));
			}
		}

		return errMsg;
	}

	private List<String> updateTaskValid(List<String> errMsg, UpdateTaskForm updateTaskForm) {
		if (Validation.valueIsBlank(updateTaskForm.getName().length())) {
			errMsg.add(ErrMessage.nullMessage("タスク名"));
		} else if (Validation.lengthOver(20, updateTaskForm.getName().length())) {
			errMsg.add(ErrMessage.lengthOverMessage("タスク名", 20));
		}

		if (updateTaskForm.getMemo() != null) {
			if (Validation.lengthOver(30, updateTaskForm.getMemo().length())) {
				errMsg.add(ErrMessage.lengthOverMessage("メモ", 30));
			}
		}

		return errMsg;
	}
}
*/