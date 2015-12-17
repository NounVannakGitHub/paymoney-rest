package com.appsaradev.paymoney.account.dao;

public class User {

	private String email;

	public String getEmail() {
		return email;
	}

	public String getrEmail() {
		return rEmail;
	}

	public String getPassword() {
		return password;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setrEmail(String rEmail) {
		this.rEmail = rEmail;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	private String rEmail;
	private String password;
	private String fname;
	private String lname;
	private String mobile;
	private int userType;
	private String url;
	private int delete;

	public int getDelete() {
		return delete;
	}

	public void setDelete(int delete) {
		this.delete = delete;
	}

	public String getUrl() {
		return url;
	}

	public String getToken() {
		return token;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private String token;

	public int getUserType() {
		return userType;
	}

	private String signUpDate;
	private int active;

	public int getActive() {
		return active;
	}

	public int getNewsLetters() {
		return newsLetters;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public void setNewsLetters(int newsLetters) {
		this.newsLetters = newsLetters;
	}

	private int newsLetters;

	public String getSignUpDate() {
		return signUpDate;
	}

	public void setSignUpDate(String signUpDate) {
		this.signUpDate = signUpDate;
	}

	public User(String email, String rEmail, String password, String fname, String lname, String mobile, int userType,
			String signUpDate) {
		this.email = email;
		this.rEmail = rEmail;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.mobile = mobile;
		this.userType = userType;
		this.signUpDate = signUpDate;
	}

	public User(String email, String password, int userType, int active, int newsLetters, int delete) {
		this.email = email;
		this.password = password;
		this.userType = userType;
		this.active = active;
		this.newsLetters = newsLetters;
		this.delete = delete;
	}

	public User(String email, String password, String url, String token) {
		this.email = email;
		this.password = password;
		this.url = url;
		this.token = token;
	}

}
