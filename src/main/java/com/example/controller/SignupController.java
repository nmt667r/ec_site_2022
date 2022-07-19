package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.User;
import com.example.form.SignupForm;
import com.example.service.ErrMessage;
import com.example.service.UserService;
import com.example.service.Validation;
import com.example.util.CipherUtil;

@Controller
public class SignupController {
	@Autowired
	HttpSession session;

	@Autowired
	UserService userService;

	@Autowired
	CipherUtil cipherUtil;

	@GetMapping("/signup")
	public String displaySignup(Model model) {
		SignupForm signupForm = new SignupForm();
		model.addAttribute("signupForm", signupForm);
		return "/signup";
	}

	@PostMapping("/signup")
	public String executeSignup(@ModelAttribute SignupForm signupForm, Model model) {
		User user = new User();
		List<String> errList = new ArrayList<>();
		errList = SignUpValid(errList, signupForm);
		if (errList.size() > 0) {
			model.addAttribute("errList", errList);
			model.addAttribute("signupForm", signupForm);
			return "/signup";
		} else {
			BeanUtils.copyProperties(signupForm, user);
			user.setPassword(cipherUtil.encode(user.getPassword()));
			user.setIs_stopped(0);
			User createUser = userService.createUser(user);
			userService.setLoginSession(createUser);
			return "redirect:/";
		}
	}

	private List<String> SignUpValid(List<String> errMsg, SignupForm signupForm) {
		if (Validation.valueIsBlank(signupForm.getAccount().length())) {
			errMsg.add(ErrMessage.nullMessage("アカウント名"));
		} else {
			if (Validation.lengthOver(20, signupForm.getAccount().length())) {
				errMsg.add(ErrMessage.lengthOverMessage("アカウント名", 20));
			} else if (Validation.lengthUnder(6, signupForm.getAccount().length())) {
				errMsg.add(ErrMessage.lengthUnderMessage("アカウント名", 6));
			}
		}

		if (Validation.valueIsBlank(signupForm.getName().length())) {
			errMsg.add(ErrMessage.nullMessage("ユーザー名"));
		} else {
			if (userService.findUser(signupForm.getAccount()) != null) {
				errMsg.add(ErrMessage.alreadyAccount());
			} else if (Validation.lengthOver(20, signupForm.getName().length())) {
				errMsg.add(ErrMessage.lengthOverMessage("ユーザー名", 20));
			} else if (Validation.lengthUnder(2, signupForm.getName().length())) {
				errMsg.add(ErrMessage.lengthUnderMessage("ユーザー名", 2));
			}
		}

		if (Validation.valueIsBlank(signupForm.getPassword().length())) {
			errMsg.add(ErrMessage.nullMessage("パスワード"));
		} else {
			if (Validation.lengthOver(20, signupForm.getPassword().length())) {
				errMsg.add(ErrMessage.lengthOverMessage("パスワード", 20));
			} else if (Validation.lengthUnder(6, signupForm.getPassword().length())) {
				errMsg.add(ErrMessage.lengthUnderMessage("パスワード", 6));
			} else if (Validation.confirmValue(signupForm.getPassword(), signupForm.getPasswordConfirm())) {
				errMsg.add(ErrMessage.notConfirmMessage("パスワード"));
			}
		}

		return errMsg;
	}
}
