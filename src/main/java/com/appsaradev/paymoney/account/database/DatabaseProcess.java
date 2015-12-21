package com.appsaradev.paymoney.account.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.appsaradev.paymoney.account.dao.User;
import com.google.common.util.concurrent.Service.State;

public class DatabaseProcess {

	static Connection connection = com.appsaradev.paymoney.singleton.Connection.getSingleConnection();
	public static final String USER = "pm_user";
	public static final String ACCOUNT = "pm_account";
	public static final String BUSSINESS = "pm_business_account";
	public static final String F_BUSINESS = "ba_email,ba_password,ba_url,ba_token";
	public static final String F_USER = "user_email,user_password,user_usertype,user_active,user_newsletter,user_delete";
	public static final String F_ACCOUNT = "ac_email,ac_reemail,ac_password,ac_fname,ac_lname,ac_mobile,ac_usertype,ac_datesignup";

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public final String D_USER = "'" + this.getUser().getEmail() + "','" + this.getUser().getPassword() + "','"
			+ this.getUser().getUserType() + "','" + this.getUser().getActive() + "','"
			+ this.getUser().getNewsLetters() + "','" + this.getUser().getDelete() + "'";

	public final String D_ACCOUNT = "'" + this.getUser().getEmail() + "','" + this.getUser().getrEmail() + "','"
			+ this.getUser().getPassword() + "','" + this.getUser().getFname() + "','" + this.getUser().getLname()
			+ "','" + this.getUser().getMobile() + "','" + this.getUser().getUserType() + "','"
			+ this.getUser().getSignUpDate() + "'";

	public final String D_BUSINESS = "'" + this.getUser().getEmail() + "','" + this.getUser().getPassword() + "','"
			+ this.getUser().getUrl() + "','" + this.getUser().getToken() + "'";

	public static void doInsert(String table, String dbFields, String data) {

		String sql = "insert into '" + table + "' ('" + dbFields + "') values ('" + data + "')";
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.execute(sql);
			System.out.println("Data was inserted!");
			connection.commit();
		} catch (Exception e) {
			System.out.println("doInsert: " + e.getMessage());
		}
	}

	public static void insert(String sql) {
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.execute(sql);
			connection.commit();
			System.out.println("inserted");
		} catch (Exception e) {
			System.out.println("insert: " + e.getMessage());
		}

	}

	public static boolean isCheck(String sql) {
		boolean check = false;
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				check = true;
			}
			System.out.println("isCheck: " + check);
			connection.commit();
		} catch (Exception e) {
			System.out.println("isCheck error: " + e.getMessage());
		}
		return check;
	}

	public static double checkCash(String sql) {
		double cash = 0;
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				cash = resultSet.getDouble(1);
				System.out.println("User cash: " + cash);
				connection.commit();
			}
		} catch (Exception e) {
			System.out.println("checkCash: " + e.getMessage());
		}
		return cash;
	}

	public static void doUpdate(String sql) {
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.execute(sql);
			connection.commit();
			System.out.println("Data was updated!");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("doUpdate: " + e.getMessage());
		}

	}

	public static List<String> getListEmail(String sql) {
		List<String> listEmail = new ArrayList<String>();
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				listEmail.add(resultSet.getString(1));
			}
		} catch (Exception e) {
			System.out.println("getListEmail: " + e.getMessage());
		}

		return listEmail;
	}

	public static List<String> getDBValue(String sql, int index) {
		List<String> account = new ArrayList<String>();
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				for (int i = 0; i < index; i++) {
					account.add(i, resultSet.getObject(i + 1).toString());
				}
			}
		} catch (Exception e) {
			System.out.println("getDBValue: " + e.getMessage());
		}
		return account;
	}

	public List<Object> getAccountInfo(String sql) {
		List<Object> account = new ArrayList<Object>();
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				for (int i = 0; i < 9; i++) {
					account.add(i, resultSet.getObject(i + 1));
				}
			}
		} catch (Exception e) {
			System.out.println("getAccountInfo: " + e.getMessage());
		}

		return account;
	}

}
