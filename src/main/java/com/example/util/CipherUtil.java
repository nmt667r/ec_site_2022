package com.example.util;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.stereotype.Component;

/**
* SHA256の暗号化ユーティリティー
*/
@Component
public class CipherUtil {

	public String encode(String input) {

		String toReturn = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(input.getBytes("utf8"));
			toReturn = String.format("%064x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return toReturn;
	}
}