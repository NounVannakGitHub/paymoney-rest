package com.appsaradev.paymoney.account;

import com.appsaradev.paymoney.account.auth.SignIn;
import com.appsaradev.paymoney.account.auth.SignUp;
import com.appsaradev.paymoney.account.history.History;
import com.appsaradev.paymoney.account.notification.Notification;
import com.appsaradev.paymoney.account.payment.Payment;
import com.appsaradev.paymoney.account.topup.TopUp;
import com.appsaradev.paymoney.account.transfer.Transfer;

public class Account {

	private SignUp signUp;

	public SignUp getSignUp() {
		return signUp;
	}

	public SignIn getSignIn() {
		return signIn;
	}

	public TopUp getTopUp() {
		return topUp;
	}

	public Transfer getTransfer() {
		return transfer;
	}

	public Payment getPayment() {
		return payment;
	}

	public AccountSetting getSetting() {
		return setting;
	}

	public void setSignUp(SignUp signUp) {
		this.signUp = signUp;
	}

	public void setSignIn(SignIn signIn) {
		this.signIn = signIn;
	}

	public void setTopUp(TopUp topUp) {
		this.topUp = topUp;
	}

	public void setTransfer(Transfer transfer) {
		this.transfer = transfer;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public void setSetting(AccountSetting setting) {
		this.setting = setting;
	}

	private SignIn signIn;
	private TopUp topUp;
	private Transfer transfer;
	private Payment payment;
	private AccountSetting setting;
	private History history;
	public History getHistory() {
		return history;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setHistory(History history) {
		this.history = history;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	private Notification notification;

}
