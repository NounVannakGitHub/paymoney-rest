package com.appsaradev.paymoney.account.transfer;

import com.appsaradev.paymoney.account.database.DatabaseProcess;
import com.appsaradev.paymoney.account.history.History;
import com.appsaradev.paymoney.account.notification.Notification;
import com.appsaradev.paymoney.account.utils.Utils;

public class Transfer {

	private static double doCheckCash(String email) {
		return DatabaseProcess.checkCash(email);
	}

	private static boolean isSender(String email) {
		return DatabaseProcess.isCheckSenderAndReciever(email);
	}

	private static boolean isReciever(String email) {
		return DatabaseProcess.isCheckSenderAndReciever(email);
	}

	private static void updateCashUser(String email, double cash) {
		DatabaseProcess.updateCashUser(email, cash);
	}

	public static void setCashToReciever(String sender, String reciever, double setCash) {
		double currentCash = doCheckCash(sender);
		double senderCash = (currentCash - setCash);
		double recieverCash = (doCheckCash(reciever) + setCash);
		if (isSender(sender) == true && isReciever(reciever) == true) {
			if (currentCash >= setCash) {
				updateCashUser(sender, senderCash);
				updateCashUser(reciever, recieverCash);
				History.setTransferHistory(sender, reciever, setCash, Utils.getCurrentTime());
				Notification.setTransferNotification(sender, reciever, setCash);
				History.setCashHistory(sender, senderCash, Utils.getCurrentTime());
				History.setCashHistory(reciever, recieverCash, Utils.getCurrentTime());
			} else {
				Utils.setError("Not enough money");
				System.out.println("setCashUser: " + Utils.getError());
			}
		} else {
			Utils.setError("Incorrect email and password !");
			System.out.println("setCashUser: " + Utils.getError());
		}
	}

}
