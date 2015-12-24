package com.appsaradev.paymoney.account.history;

import java.util.List;

import com.appsaradev.paymoney.account.database.DatabaseProcess;

public class History {

	public static void setTransferHistory(String sender, String reciever, double cash, String date) {
		DatabaseProcess.doInsert("pm_transfer_history", "th_sender,th_reciever,th_cash,th_date",
				"'" + sender + "','" + reciever + "','" + cash + "','" + date + "'");
	}

	public static void setCashHistory(String email, double cash, String date) {
		DatabaseProcess.doInsert("pm_cash_history", "ch_email,ch_cash,ch_date",
				"'" + email + "','" + cash + "','" + date + "'");
	}

	public static void setUserHistory(String email, String date, String action) {

		DatabaseProcess.doInsert("pm_user_history", "uh_email,uh_date,uh_action",
				"'" + email + "','" + date + "','" + action + "'");

	}

	public static void setScratchCardHistory(String email, double cash, String digits, String date) {
		DatabaseProcess.doInsert("pm_scratch_card_history", "sh_email,sh_digits,sh_cash,sh_date",
				"'" + email + "','" + digits + "','" + cash + "','" + date + "'");
	}

	public static List<String> getTopupHistory(String email) {
		return DatabaseProcess.getTopupHistory(email);
	}

}
