package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.User;
import com.example.form.LoginForm;
import com.example.service.ErrMessage;
import com.example.service.UserService;
import com.example.service.Validation;
import com.example.util.CipherUtil;

@Controller
public class LoginController {
	@Autowired
	HttpSession session;

	@Autowired
	CipherUtil cipherUtil;

	@Autowired
	UserService userService;

	@GetMapping("/login")
	public String displayLogin(Model model) {
		LoginForm loginForm = new LoginForm();
		model.addAttribute("loginForm", loginForm);
		return "/login";
	}

	@PostMapping("/login")
	public String executeLogin(@ModelAttribute LoginForm loginForm, Model model) {
		List<String> errList = new ArrayList<>();
		errList = LoginValid(errList, loginForm);
		if (errList.size() > 0) {
			model.addAttribute("errList", errList);
			model.addAttribute("loginForm", loginForm);
			return "/login";
		} else {
			User user = userService.findUser(loginForm.getAccount());
			session.setAttribute("loginUser", user);
			userService.setLoginSession(user);
			return "redirect:/";
		}
	}

	private List<String> LoginValid(List<String> errMsg, LoginForm loginForm) {
		if (Validation.valueIsBlank(loginForm.getAccount().length())) {
			errMsg.add(ErrMessage.nullMessage("アカウント名"));
		}
		if (Validation.valueIsBlank(loginForm.getPassword().length())) {
			errMsg.add(ErrMessage.nullMessage("パスワード"));
		} else if (Validation.valueIsBlank(loginForm.getPasswordConfirm().length())) {
			errMsg.add(ErrMessage.nullMessage("確認用パスワード"));
		}
		if(errMsg.size() == 0){
			loginForm.setPassword(cipherUtil.encode(loginForm.getPassword()));
			loginForm.setPasswordConfirm(cipherUtil.encode(loginForm.getPasswordConfirm()));
			if (Validation.confirmValue(loginForm.getPassword(), loginForm.getPasswordConfirm())) {
				errMsg.add(ErrMessage.notConfirmMessage("パスワード"));
			} else {
				if (userService.findUser(loginForm.getAccount(), loginForm.getPassword()) == null) {
					errMsg.add(ErrMessage.notConfirmMessage("アカウントかパスワード"));
				} else if(userService.findUser(loginForm.getAccount(), loginForm.getPassword()).getIs_stopped() == 1) {
					errMsg.add(ErrMessage.notConfirmMessage("アカウントかパスワード"));
				}
			}
		}
		return errMsg;
	}
}
