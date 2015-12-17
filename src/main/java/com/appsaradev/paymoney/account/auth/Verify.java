package com.appsaradev.paymoney.account.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Verify {

	public static String doGenerateSecurityCode() {
		String code = "";
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			code += random.nextInt(9);
		}
		return code;
	}

	public static void doSetSecurityCodeToCookie(HttpServletResponse response, String code) {
		Cookie cookie = new Cookie("app_code", code);
		cookie.setMaxAge(24 * 60 * 30);
		cookie.setSecure(true);
		response.addCookie(cookie);
	}

	public static String getCookie(HttpServletRequest request) {
		String cookie = "";
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie1 : cookies) {
				if (cookie1.getName().equals("app_code")) {
					cookie = cookie1.getValue();
				}
			}
		}
		return cookie;
	}

	public static List<String> getUserCookie(HttpServletRequest request) {
		List<String> cookieList = new ArrayList<String>();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookieList.add(cookie.getValue());
			}
		}
		return cookieList;
	}

	public static boolean isConfirmSecurityCode(String cookie, String code) {
		boolean isCookie = false;
		if (cookie.equals(code)) {
			isCookie = true;
		}
		return isCookie;
	}

	public static void setUserCookie(HttpServletResponse response, String email, String password) {
		Cookie cookie = new Cookie("appsaradev_us_email", email);
		Cookie cookie2 = new Cookie("appsaradev_us_password", password);
		cookie.setMaxAge(3600 * 24 * 30);
		cookie2.setMaxAge(3600 * 24 * 30);
		response.addCookie(cookie);
		response.addCookie(cookie2);
	}

}
