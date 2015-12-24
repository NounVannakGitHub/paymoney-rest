package com.appsaradev.paymoney.account.topup;

import com.appsaradev.paymoney.account.database.DatabaseProcess;
import com.appsaradev.paymoney.account.history.History;
import com.appsaradev.paymoney.account.utils.Utils;

public class ScratchCard {
	private static boolean isCheck14Digits(String digits) {
		return DatabaseProcess.isScratchCard(digits);
	}

	private static double getCash(String digits) {
		return DatabaseProcess.getScratchCard(digits);
	}

	private static void updateCashUser(String email, double cash) {
		DatabaseProcess.updateCashUser(email, cash);
		updateCash(email, cash);
	}

	private static double doCheckCash(String email) {
		return DatabaseProcess.checkCash(email);
	}

	private static void updateCash(String email, double cash) {
		double recieverCash = (doCheckCash(email) + cash);
		updateCashUser(email, recieverCash);
		History.setCashHistory(email, recieverCash, Utils.getCurrentTime());
	}

	private static void updateScratchCardDB(String digits) {
		DatabaseProcess.updateScratchCardDB(digits);
	}

	public static void topupScratchCard(String digits, String email) {
		double cash = getCash(digits);
		if (isCheck14Digits(digits) == true) {
			updateCashUser(email, cash);
			updateScratchCardDB(digits);
			DatabaseProcess.insertTopupHistory(email, cash, "Scratch Card", Utils.getCurrentTime());
			History.setScratchCardHistory(email, cash, digits, Utils.getCurrentTime());
		} else {
			Utils.setError("wrong digits number");
		}

	}

}
