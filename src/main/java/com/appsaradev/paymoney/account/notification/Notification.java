package com.appsaradev.paymoney.account.notification;

import java.util.List;

import com.appsaradev.paymoney.account.database.DatabaseProcess;
import com.appsaradev.paymoney.account.utils.Utils;

public class Notification {

	public static List<String> getTransferNotification(String email) {
		return DatabaseProcess.selectTransferNotification(email);
	}

	public static void setTransferNotification(String sender, String reciever, double cash) {
		DatabaseProcess.insertTransferNotification(sender, reciever, cash, Utils.getCurrentTime());
	}

	public static void readNotification(int notificationID) {
		DatabaseProcess.updateReadNotification(notificationID);
	}

}
