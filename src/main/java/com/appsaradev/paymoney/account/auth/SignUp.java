package com.appsaradev.paymoney.account.auth;

import com.appsaradev.paymoney.account.dao.User;
import com.appsaradev.paymoney.account.database.DatabaseProcess;
import com.appsaradev.paymoney.account.utils.Utils;

import ch.qos.logback.classic.pattern.Util;

public class SignUp {

	private Verify verify;

	public Verify getVerify() {
		return verify;
	}

	public API getApi() {
		return api;
	}

	public void setVerify(Verify verify) {
		this.verify = verify;
	}

	public void setApi(API api) {
		this.api = api;
	}

	private API api;

	public static void doCreateUser(User user) {
		DatabaseProcess.insertUser(user);
		DatabaseProcess.insertAccount(user);
		DatabaseProcess.insertCashUser(user.getEmail(), 0);
		DatabaseProcess.insertUserToken(user.getEmail(), Utils.encodedBase64(user.getEmail()));
	}

}
