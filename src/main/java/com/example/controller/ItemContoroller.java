package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


public class ItemContoroller {
	@Autowired
	HttpSession session;

	@GetMapping("/itemlist")
	public ModelAndView displayItems(Model model)  {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/itemlist");
		return mav;
	}
}
