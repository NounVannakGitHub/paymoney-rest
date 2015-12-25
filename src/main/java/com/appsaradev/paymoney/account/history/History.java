package com.appsaradev.paymoney.account.history;

import java.util.List;

import com.appsaradev.paymoney.account.database.DatabaseProcess;

public class History {

	public static void setTransferHistory(String sender, String reciever, double cash, String date) {
		DatabaseProcess.insertTransferHistory(sender, reciever, cash, date);
	}

	public static void setCashHistory(String email, double cash, String date) {
		DatabaseProcess.insertCashHistory(email, cash, date);
	}

	public static void setUserHistory(String email, String date, String action) {

		DatabaseProcess.insertUserHistory(email, date, action);

	}

	public static void setScratchCardHistory(String email, double cash, String digits, String date) {
		DatabaseProcess.insertScratchCardHistory(email, digits, cash, date);
	}

	public static List<String> getTopupHistory(String email) {
		return DatabaseProcess.getTopupHistory(email);
	}

}
