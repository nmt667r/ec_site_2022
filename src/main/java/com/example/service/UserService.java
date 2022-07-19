package com.example.service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserService {
	@Autowired
	HttpSession session;

	@Autowired
	UserRepository userRepository;

	public User findUser(String account) {
		return userRepository.findByAccount(account);
	}

	public User findUser(String account, String password) {
		return userRepository.findByAccountAndPassword(account, password);
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public void setLoginSession(User user) {
		session.setAttribute("loginUser", user);
	}
}
