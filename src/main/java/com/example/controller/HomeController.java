package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.User;

@Controller
public class HomeController {
	@Autowired
	HttpSession session;

	@GetMapping("/")
	public ModelAndView displayHome() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("session", session);
		User user = (User) session.getAttribute("loginUser");

		Integer id = user.getId();
		mav.setViewName("/home");
		return mav;
	}
/*
	@GetMapping("/itemList")
	public String displayLogin(Model model) {
		return "/itemList";
	}*/

}
