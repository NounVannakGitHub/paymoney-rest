package com.appsaradev.paymoney.account.notification;

import java.util.List;

import com.appsaradev.paymoney.account.database.DatabaseProcess;

public class Notification {

	public static List<String> getTransferNotification(String email) {
		return DatabaseProcess.getDBValue(
				"select * from pm_transfer_notification where tn_reciever='" + email + "' and tn_read=0", 6);

	}

	public static void setTransferNotification(String sender, String reciever, double cash, String date) {
		DatabaseProcess.doInsert("pm_transfer_notification", "tn_sender,tn_reciever,tn_cash,tn_date,tn_read",
				"'" + sender + "','" + reciever + "','" + cash + "','" + date + "','" + 0 + "'");
	}

	public static void readNotification(int notificationID) {
		DatabaseProcess.doUpdate(
				"update pm_transfer_notification set tn_read='" + 1 + "' where tn_id='" + notificationID + "'");
	}

}
