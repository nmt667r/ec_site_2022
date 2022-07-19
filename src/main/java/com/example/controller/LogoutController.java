package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
	@Autowired
	HttpSession session;

	@GetMapping("/logout")
	public String executeLogout(Model model) {
		session.invalidate();
		return "redirect:/login";
	}
}
