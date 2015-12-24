package com.appsaradev.paymoney.account.topup;

import com.appsaradev.paymoney.account.dao.MandatCashDao;
import com.appsaradev.paymoney.account.database.DatabaseProcess;
import com.appsaradev.paymoney.account.history.History;
import com.appsaradev.paymoney.account.utils.Utils;

public class MandatCash {

	public static void setMandatCash(MandatCashDao dao) {
		DatabaseProcess.insertMandatCash(dao);
		DatabaseProcess.insertMandatCashHistory(dao.getEmail(), dao.getDigits(), dao.getCash(), Utils.getCurrentTime(),
				"recieve topup");
	}

	private static double getCash(String digits) {
		return DatabaseProcess.getMandatCash(digits);
	}

	private static boolean isDigits(String digits) {
		return DatabaseProcess.isMandatCash(digits);

	}

	private static void updateCashUser(String email, double cash) {
		DatabaseProcess.updateCashUser(email, cash);
	}

	private static double doCheckCash(String email) {
		return DatabaseProcess.checkCash(email);
	}

	private static void updateCash(String email, double cash) {
		double recieverCash = (doCheckCash(email) + cash);
		updateCashUser(email, recieverCash);
		History.setCashHistory(email, recieverCash, Utils.getCurrentTime());
	}

	private static void updateMandatCashDB(String digits) {
		DatabaseProcess.updateMandatCash(digits);
	}

	public static void topupMandatCash(String digits, String email) {
		double cash = getCash(digits);
		if (isDigits(digits) == true) {
			updateCash(email, cash);
			updateMandatCashDB(digits);
			DatabaseProcess.insertTopupHistory(email, cash, "Mandat Cash", Utils.getCurrentTime());
			DatabaseProcess.insertMandatCashHistory(email, digits, cash, Utils.getCurrentTime(), "user topup");
		} else {
			Utils.setError("wrong digits number");
		}

	}

}
