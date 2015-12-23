package com.appsaradev.paymoney.account.auth;

import com.appsaradev.paymoney.account.dao.User;
import com.appsaradev.paymoney.account.database.DatabaseProcess;
import com.appsaradev.paymoney.account.utils.Utils;

public class API {

	public static void doCreateAPI(User user) {
		DatabaseProcess.doInsert(DatabaseProcess.BUSSINESS, DatabaseProcess.F_BUSINESS, DatabaseProcess.D_BUSINESS,
				user);
	}

	public static String doGenerateToken(User user) {
		return Utils.encodedBase64(user.getEmail() + user.getPassword() + user.getUrl());
	}

}
