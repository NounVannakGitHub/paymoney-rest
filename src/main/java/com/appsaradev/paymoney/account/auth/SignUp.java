package com.appsaradev.paymoney.account.auth;

import com.appsaradev.paymoney.account.dao.User;
import com.appsaradev.paymoney.account.database.DatabaseProcess;

public class SignUp {

	private DatabaseProcess process = new DatabaseProcess();

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

	public void doCreateUser(User user) {
		process.setUser(user);
		if (user.getUserType() == 1) {
			process.doInsert(DatabaseProcess.USER, DatabaseProcess.F_USER, process.D_USER);
			process.doInsert(DatabaseProcess.ACCOUNT, DatabaseProcess.F_ACCOUNT, process.D_ACCOUNT);
		} else {
			process.doInsert(DatabaseProcess.USER, DatabaseProcess.F_USER, process.D_USER);
			process.doInsert(DatabaseProcess.ACCOUNT, DatabaseProcess.F_ACCOUNT, process.D_ACCOUNT);
		}
	}

}
