package com.appsaradev.paymoney.account.dao;

public class MandatCashDao {

	private int id;

	public int getId() {
		return id;
	}

	public String getDigits() {
		return digits;
	}

	public double getCash() {
		return cash;
	}

	public String getSerial() {
		return serial;
	}

	public String getEmail() {
		return email;
	}

	public int getUsed() {
		return used;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDigits(String digits) {
		this.digits = digits;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsed(int used) {
		this.used = used;
	}

	private String digits;
	private double cash;
	private String serial;
	private String email;
	private int used;

	public MandatCashDao(String digits, double cash, String serial, String email, int used) {
		this.cash = cash;
		this.digits = digits;
		this.serial = serial;
		this.email = email;
		this.used = used;
		// TODO Auto-generated constructor stub
	}

}
