package com.appsaradev.paymoney.account.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.appsaradev.paymoney.account.dao.MandatCashDao;
import com.appsaradev.paymoney.account.dao.User;
import com.appsaradev.paymoney.account.utils.Utils;
import com.sun.xml.ws.developer.StatefulWebServiceManager;

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

	public void setUser(User users) {
		this.user = users;
	}

	public static final String D_USER = "USER";

	public static final String D_ACCOUNT = "ACCOUNT";

	public static final String D_BUSINESS = "BUSSINESS";

	public static void doInsert(String table, String dbFields, String data, User user) {
		String myData = "";
		if (data.equals("USER")) {
			myData = "'" + user.getEmail() + "','" + user.getPassword() + "','" + user.getUserType() + "','"
					+ user.getActive() + "','" + user.getNewsLetters() + "','" + user.getDelete() + "'";
		} else if (data.equals("ACCOUNT")) {
			myData = "'" + user.getEmail() + "','" + user.getrEmail() + "','" + user.getPassword() + "','"
					+ user.getFname() + "','" + user.getLname() + "','" + user.getMobile() + "','" + user.getUserType()
					+ "','" + user.getSignUpDate() + "'";
		} else {
			myData = "'" + user.getEmail() + "','" + user.getPassword() + "','" + user.getUrl() + "','"
					+ user.getToken() + "'";
		}

		String sql = "insert into '" + table + "' ('" + dbFields + "') values ('" + myData + "')";
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

	public static void insertUser(User user) {
		String sql = "insert into pm_user (user_email,user_password,user_usertype,user_active,user_newsletter,user_delete) values ('"
				+ user.getEmail() + "','" + Utils.encodedBase64(user.getPassword()) + "','" + user.getUserType() + "','"
				+ user.getActive() + "','" + user.getNewsLetters() + "','" + user.getDelete() + "')";
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.execute(sql);
			System.out.println("Data was inserted!");
			connection.commit();
		} catch (Exception e) {
			System.out.println("insertUser: " + e.getMessage());
		}
	}

	public static void insertAccount(User user) {
		String sql = "insert into pm_account (ac_email,ac_reemail,ac_password,ac_fname,ac_lname,ac_mobile,ac_usertype,ac_datesignup) values ('"
				+ user.getEmail() + "','" + user.getrEmail() + "','" + Utils.encodedBase64(user.getPassword()) + "','"
				+ user.getFname() + "','" + user.getLname() + "','" + user.getMobile() + "','" + user.getUserType()
				+ "','" + user.getSignUpDate() + "')";
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.execute(sql);
			connection.commit();
			System.out.println("account inserted!");
		} catch (Exception e) {
			System.out.println("insertAccount: " + e.getMessage());
		}

	}

	public static void doInsert(String table, String dbFields, String data) {
		String sql = "insert into " + table + " ('" + dbFields + "') values ('" + data + "')";
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

	public static double checkCash(String email) {
		double cash = 0;
		String sql = "select cs_cash from pm_cash where cs_email='" + email + "'";
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

	public static double getScratchCard(String digits) {
		double cash = 0.0;
		String sql = "select sc_cash from pm_scractch_card where sc_digits='" + digits + "'";
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				cash = resultSet.getDouble(1);
			}
			System.out.println("cash: " + cash);
		} catch (Exception e) {
			System.out.println("getScratchCard: " + e.getMessage());
		}

		return cash;
	}

	public static boolean isMandatCash(String digits) {
		boolean cash = false;
		String sql = "select * from pm_mandat_cash where mc_digit='" + digits + "' and mc_used=0";
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				cash = true;
			}
			System.out.println("cash: " + cash);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("isMandatCash:" + e.getMessage());
		}
		return cash;
	}

	public static double getMandatCash(String digits) {
		double cash = 0.0;
		String sql = "select mc_cash from pm_mandat_cash where mc_digits='" + digits + "'";
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				cash = resultSet.getDouble(1);
				System.out.println("cash :" + cash);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getMandatCash: " + e.getMessage());
		}
		return cash;
	}

	public static void insertMandatCash(MandatCashDao dao) {
		String sql = "insert into pm_mandat_cash (mc_digits,mc_cash,mc_serial,mc_email,mc_used) values ('"
				+ dao.getDigits() + "','" + dao.getCash() + "','" + dao.getSerial() + "','" + dao.getEmail() + "','"
				+ dao.getUsed() + "')";
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.execute(sql);
			connection.commit();
			System.out.println("insertMandatCash success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("insertMandatCash: " + e.getMessage());
		}

	}

	public static void updateCashUser(String email, double cash) {
		String sql = "update pm_cash set cs_cash='" + cash + "' where cs_email='" + email + "'";
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.execute(sql);
			connection.commit();
			System.out.println("cash user updated");
		} catch (Exception e) {
			System.out.println("updateCashUser: " + e.getMessage());
			// TODO: handle exception
		}

	}

	public static void updateMandatCash(String digits) {
		String sql = "update pm_mandat_cash set mc_used=1 where mc_digits='" + digits + "'";
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.execute(sql);
			connection.commit();
			System.out.println("updateMandatCash Inserted!");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("updateMandatCash: " + e.getMessage());
		}

	}

	public static void insertMandatCashHistory(String email, String digits, double cash, String date, String action) {
		String sql = "insert into pm_mandat_cash_history (mh_email,mh_digits,mh_cash,mh_date,mh_action) values ('"
				+ email + "','" + digits + "','" + cash + "','" + date + "','" + action + "')";
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.execute(sql);
			connection.commit();
			System.out.println("insertMandatCashHistory inserted!");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("insertMandatCashHistory: " + e.getMessage());
		}
	}

	public static boolean isScratchCard(String digits) {
		boolean cash = false;
		String sql = "select * from pm_scratch_card where sc_digits='" + digits + "' and sc_used=0";
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				cash = true;
				System.out.println("isScratchCard: " + cash);
			}
			connection.commit();
		} catch (Exception e) {
			System.out.println("isScratchCard: " + e.getMessage());
			// TODO: handle exception
		}
		return cash;

	}

	public static void updateScratchCardDB(String digits) {
		String sql = "update pm_scratch_card set sc_used=1 where sc_digits='" + digits + "'";
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.execute(sql);
			connection.commit();
			System.out.println("updateScratchCardDB inserted");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("upddateScratchCardDB: " + e.getMessage());
		}

	}

	public static void insertTopupHistory(String email, double cash, String topuptype, String date) {
		String sql = "insert into pm_topup_history (th_email,th_cash,th_topup_type,th_date) values ('" + email + "','"
				+ cash + "','" + topuptype + "','" + date + "')";
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.execute(sql);
			connection.commit();
			System.out.println("insertTopupHistory inserted");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("insertTopupHistory: " + e.getMessage());
		}
	}

	public static List<String> getTopupHistory(String email) {
		List<String> topup = new ArrayList<String>();
		String sql = "select * from pm_topup_history where th_email='" + email + "'";
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				for (int i = 0; i < 4; i++) {
					topup.add(i, resultSet.getObject(i + 1).toString());
				}
			}
			connection.commit();
			System.out.println("getTopupHistory completed");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getTopupHistory: " + e.getMessage());
		}
		return topup;
	}

	public static boolean isCheckSenderAndReciever(String email) {
		boolean user = false;
		String sql = "select * from pm_user where user_email='" + email + "' and user_active=1";
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				user = true;
				System.out.println("isCheckSenderAndReciever: " + user);
			}
			connection.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("isCheckSenderAndReciever: " + e.getMessage());
		}
		return user;
	}

	public static void insertTransferNotification(String sender, String reciever, double cash, String date) {
		String sql = "insert into pm_transfer_notification (tn_sender,tn_reciever,tn_cash,tn_date,tn_read) values ('"
				+ sender + "','" + reciever + "','" + cash + "','" + date + "',0)";
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.execute(sql);
			connection.commit();
			System.out.println("insertTransferNotification inserted");
		} catch (Exception e) {
			System.out.println("insertTransferNotification: " + e.getMessage());
			// TODO: handle exception
		}
	}

	public static void updateReadNotification(int id) {
		String sql = "update pm_transfer_notification set tn_read=1 where tn_id='" + id + "'";
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.execute(sql);
			connection.commit();
			System.out.println("updateReadNotification updated");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("updateReadNotification: " + e.getMessage());
		}

	}

	public static List<String> selectTransferNotification(String email) {
		List<String> notification = new ArrayList<String>();
		String sql = "select * from pm_transfer_notification where tn_email='" + email + "' and tn_read=0";
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				for (int i = 0; i < 6; i++) {
					notification.add(i, resultSet.getObject(i + 1).toString());
				}
			}
			System.out.println("selectTransferNotification success");
		} catch (Exception e) {
			System.out.println("selectTransferNotification: " + e.getMessage());
		}

		return notification;
	}

}
