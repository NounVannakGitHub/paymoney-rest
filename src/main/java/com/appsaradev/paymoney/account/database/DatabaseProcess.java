package com.appsaradev.paymoney.account.database;

import java.sql.Connection;
import java.sql.Statement;

import com.appsaradev.paymoney.account.dao.User;

public class DatabaseProcess {

	Connection connection = com.appsaradev.paymoney.singleton.Connection.getSingleConnection();
	public static final String USER = "pm_user";
	public static final String ACCOUNT = "pm_account";
	public static final String F_USER = "user_email,user_password,user_usertype,user_active,user_newsletter";
	public static final String F_ACCOUNT = "ac_email,ac_reemail,ac_password,ac_fname,ac_lname,ac_mobile,ac_usertype,ac_datesignup";
	public final String D_USER = "'" + this.getUser().getEmail() + "','" + this.getUser().getPassword() + "','"
			+ this.getUser().getUserType() + "','" + this.getUser().getActive() + "','"
			+ this.getUser().getNewsLetters() + "'";

	public final String D_ACCOUNT = "'" + this.getUser().getEmail() + "','" + this.getUser().getrEmail() + "','"
			+ this.getUser().getPassword() + "','" + this.getUser().getFname() + "','" + this.getUser().getLname()
			+ "','" + this.getUser().getMobile() + "','" + this.getUser().getUserType() + "','"
			+ this.getUser().getSignUpDate() + "'";

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void doInsert(String table, String dbFields, String data) {

		String sql = "insert into '" + table + "' ('" + dbFields + "') values ('" + data + "')";
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.execute(sql);
			System.out.println();
			connection.commit();
		} catch (Exception e) {
			System.out.println();
		}
	}

}
