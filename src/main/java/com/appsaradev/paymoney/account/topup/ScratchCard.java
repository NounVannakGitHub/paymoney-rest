package com.appsaradev.paymoney.account.topup;

import com.appsaradev.paymoney.account.database.DatabaseProcess;
import com.appsaradev.paymoney.account.history.History;
import com.appsaradev.paymoney.account.utils.Utils;

public class ScratchCard {
	private static String emials;
	private static double cashs;

	public static boolean isCheck14Digits(String digits) {
		return DatabaseProcess.isCheck("select * from pm_scratch_card where sc_digits='" + digits + "'");
	}

	public static double getCash(String digits) {
		return DatabaseProcess.getScratchCard(digits);
	}

	private static void updateCashUser(String email, double cash) {
		DatabaseProcess.doUpdate("update pm_cash set cs_cash='" + cash + "' where cs_email='" + email + "'");
	}

	private static double doCheckCash(String email) {
		return DatabaseProcess.checkCash("select cs_cash from pm_cash where cs_email='" + email + "'");
	}

	public static void updateCash(String email, double cash) {
		emials = email;
		cashs = cash;
		double recieverCash = (doCheckCash(email) + cash);
		updateCashUser(email, recieverCash);
		History.setCashHistory(email, recieverCash, Utils.getCurrentTime());
	}

	public static void updateScratchCardDB(String digits) {
		DatabaseProcess.doUpdate("update pm_scratch_card set sc_used=1 where cs_digits='" + digits + "'");
		History.setScratchCardHistory(emials, cashs, digits, Utils.getCurrentTime());
	}

}
