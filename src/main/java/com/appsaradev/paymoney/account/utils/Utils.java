package com.appsaradev.paymoney.account.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.appsaradev.paymoney.account.dao.User;
import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;
import com.sun.jersey.core.util.Base64;

public class Utils {
	private static final String API_KEY = "b_PGUCOWpE_JzDvGmyW-9A";

	private static String error;

	public static String getError() {
		return error;
	}

	public static void setError(String errors) {
		error = errors;
	}

	public static String encodedBase64(String uuid) {
		return new String(Base64.encode(uuid));
	}

	public static String decodedBase64(String token) {
		return new String(Base64.decode(token));
	}

	public static void sendEmail(String subject, User user) {
		String link = "http://localhost:8080/paymoney/createuser/verify?token='"+ Utils.encodedBase64(user.getEmail()) +"'";
		String email_form = "<!DOCTYPE html><html><head><title>Confirm Account</title></head><body><table width='100%' height='100%' style='min-width: 348px' border='0' cellspacing='0' cellpadding='0'><tbody><tr height='32px'></tr><tr align='center'><td width='32px'></td><td><table border='0' cellspacing='0' cellpadding='0' style='max-width: 600px'><tbody><tr><td></td></tr><tr height='16'></tr><tr><td><table bgcolor='#4184F3' width='100%' border='0' cellspacing='0' cellpadding='0' style='min-width: 332px; max-width: 600px; border: 1px solid #e0e0e0; border-bottom: 0; border-top-left-radius: 3px; border-top-right-radius: 3px'> <tbody><tr><td height='72px' colspan='3'></td></tr><tr><td width='32px'></td><td style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 24px; color: #ffffff; line-height: 1.25'>'"
				+ subject
				+ "'</td><td width='32px'></td></tr><tr><td height='18px' colspan='3'></td></tr></tbody></table></td></tr><tr><td><table bgcolor='#FAFAFA' width='100%' border='0' cellspacing='0' cellpadding='0' style='min-width: 332px; max-width: 600px; border: 1px solid #f0f0f0; border-bottom: 1px solid #c0c0c0; border-top: 0; border-bottom-left-radius: 3px; border-bottom-right-radius: 3px'><tbody><tr height='16px'><td width='32px' rowspan='3'></td><td></td><td width='32px' rowspan='3'></td></tr> <tr><td><table style='min-width: 300px' border='0' cellspacing='0' cellpadding='0'><tbody><tr><td style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 13px; color: #202020; line-height: 1.5'>Hi '"
				+ user.getLname()
				+ "',</td></tr><tr height='32px'><td>Thank you for use my service.<br/>please, verify your account <a href='"
				+ link
				+ "'>Click here!</a></td></tr><tr><td style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 13px; color: #202020; line-height: 1.5'>Best,Regards<br>The PayMoney Accounts team</td></tr> <tr height='16px'></tr><tr><td><table style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 12px; color: #b9b9b9; line-height: 1.5'><tbody><tr><td>*The location is approximate and determined by the IP address it was coming from.<br></td></tr><tr><td>This email can't receive replies.</td></tr><tr></tr></tbody></table></td></tr></tbody></table></td></tr><trheight='32px'></tr></tbody></table></td></tr><tr height='16'></tr><tr><td style='max-width: 600px; font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 10px; color: #bcbcbc; line-height: 1.5'></td></tr><tr><td><table style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 10px; color: #666666; line-height: 18px; padding-bottom: 10px'><tbody><tr><td>You received this mandatory email service announcement to update you about important changes to your PayMoney product or account.</td></tr><tr><td><div style='direction: ltr; text-align: left'>� 2015 Appsaradev, Street 371 SangKat Boeng Tumpun Khan Meanchey, Phnom Penh</div></td></tr></tbody></table></td></tr></tbody></table></td><td width='32px'></td></tr><tr height='32px'></tr></tbody></table></body></html>";

		MandrillApi api = new MandrillApi(API_KEY);
		MandrillMessage message = new MandrillMessage();
		message.setSubject(subject);
		message.setHtml(email_form);
		message.setAutoText(true);
		message.setFromEmail("nounvannakrootid@gmail.com");
		message.setFromName("Admin");

		ArrayList<MandrillMessage.Recipient> recipients = new ArrayList<MandrillMessage.Recipient>();
		Recipient recipient = new Recipient();
		recipient.setEmail(user.getEmail());
		recipient.setName(user.getFname());
		recipients.add(recipient);
		message.setTo(recipients);
		message.setPreserveRecipients(true);
		message.setTags("getnewpassword");

		try {
			MandrillMessageStatus[] mandrillMessageStatus = api.messages().send(message, false);
		} catch (MandrillApiError e) {
			System.out.println("sendEmail(MandrillApiError): " + e.getMessage());
		} catch (IOException e) {
			System.out.println("sendEmail(IOException): " + e.getMessage());
		}
	}

	public static String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		String strDate = format.format(date);
		return strDate;
	}

}
