package com.appsaradev.paymoney.account.utils;

import java.io.IOException;
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

	public static String encodedBase64(String uuid) {
		return new String(Base64.encode(uuid));
	}

	public static String decodedBase64(String token) {
		return new String(Base64.decode(token));
	}

	public static void sendEmail(String subject, String comment, User user) {
		MandrillApi api = new MandrillApi(API_KEY);
		MandrillMessage message = new MandrillMessage();
		message.setSubject(subject);
		message.setHtml("<h2>Hi! '" + user.getFname() + "',</h2></br><p>'" + comment + "'</p>");
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

}
