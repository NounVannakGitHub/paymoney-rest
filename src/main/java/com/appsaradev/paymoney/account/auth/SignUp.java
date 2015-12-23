package com.appsaradev.paymoney.account.auth;

import com.appsaradev.paymoney.account.dao.User;
import com.appsaradev.paymoney.account.database.DatabaseProcess;

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
	}

}
