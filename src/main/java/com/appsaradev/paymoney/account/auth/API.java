package com.appsaradev.paymoney.account.auth;

import com.appsaradev.paymoney.account.dao.User;
import com.appsaradev.paymoney.account.database.DatabaseProcess;
import com.appsaradev.paymoney.account.utils.Utils;

public class API {

	private static DatabaseProcess process = new DatabaseProcess();

	public static void doCreateAPI(User user) {
		process.setUser(user);
		process.doInsert(DatabaseProcess.BUSSINESS, DatabaseProcess.F_BUSINESS, process.D_BUSINESS);
	}

	public static String doGenerateToken(User user) {
		return Utils.encodedBase64(user.getEmail() + user.getPassword() + user.getUrl());
	}

}
