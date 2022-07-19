package com.example.service;

public class ErrMessage {
	public static String nullMessage(String terget) {
		return terget + "を入力してください。";
	}

	public static String lengthOverMessage(String terget, int length) {
		return terget + "は" + length + "文字以内で入力してください。";
	}

	public static String lengthUnderMessage(String terget, int length) {
		return terget + "は" + length + "文字以上で入力してください。";
	}

	public static String duplicateMessage(String terget) {
		return terget + "が重複しています。";
	}

	public static String combinationIllegalMessage(String terget) {
		return terget + "の組み合わせが不正です。";
	}

	public static String notConfirmMessage(String terget) {
		return terget + "が一致しません。";
	}

	public static String alreadyAccount() {
		return "すでに登録されているアカウント名です。";
	}

	public static String setBeforeDeadline() {
		return "期日は明日以降に設定してください。";
	}
}
