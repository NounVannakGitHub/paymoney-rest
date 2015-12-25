package com.appsaradev.paymoney.account.auth;

import com.appsaradev.paymoney.account.database.DatabaseProcess;

public class SignIn {

	private Verify verify;

	public Verify getVerify() {
		return verify;
	}

	public void setVerify(Verify verify) {
		this.verify = verify;
	}

	public static boolean isLogin(String email, String password) {
		return DatabaseProcess.isUser(email, password);

	}

}
