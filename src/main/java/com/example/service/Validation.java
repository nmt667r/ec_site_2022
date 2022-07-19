package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.User;

public class Validation extends ErrMessage {
	@Autowired
	UserService userService;

	public static boolean valueIsBlank(int value) {
		if (value == 0) {
			return true;
		}
		return false;
	}

	public static boolean lengthOver(int value, int length) {
		if (value < length) {
			return true;
		}
		return false;
	}

	public static boolean lengthUnder(int value, int length) {
		if (value > length) {
			return true;
		}
		return false;
	}

	public static boolean confirmValue(String firstTerget, String secondTerget) {
		if (!(firstTerget.equals(secondTerget))) {
			return true;
		}
		return false;
	}

	public static boolean accountCheck(User user) {
		if (user != null) {
			return true;
		}
		return false;
	}
}
