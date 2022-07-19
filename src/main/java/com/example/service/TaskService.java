/*package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Task;
import com.example.repository.TaskRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class TaskService {
	@Autowired
	TaskRepository taskRepository;

	public Task createTask(Task task) {
		return taskRepository.save(task);
	}

	public List<Task> findAllTask(Integer id) {
		return taskRepository.findAllByUserIdOrderByDeadline(id);
	}

	public Task findTaskById(Integer id) {
		return taskRepository.findTaskById(id);
	}

	public Task updateTask(Task task) {
		return taskRepository.save(task);
	}

	public void deleteTask(Integer id) {
		taskRepository.deleteById(id);
	}
}
*/