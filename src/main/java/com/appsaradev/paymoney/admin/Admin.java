package com.appsaradev.paymoney.admin;

import java.util.Random;

import com.appsaradev.paymoney.account.database.DatabaseProcess;
import com.appsaradev.paymoney.account.utils.Utils;

public class Admin {

	public static void makeScratchCard(int pcs) {
		for (int i = 0; i < pcs; i++) {
			insertScratchCard();
		}
	}

	private static String randomDigits() {
		String code = "";
		Random random = new Random();
		for (int i = 0; i < 14; i++) {
			code += random.nextInt(9);
		}
		return code;
	}

	private static String randomSerial() {
		String code = "200";
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			code += random.nextInt(9);
		}
		return code;
	}

	private static double randomCash() {
		double[] cashs = { 5.0, 10.0, 20.0, 50.0, 100.0 };
		Random random = new Random();
		return cashs[random.nextInt(cashs.length)];
	}

	private static void insertScratchCard() {
		DatabaseProcess.insertScratchCard(randomDigits(), randomCash(), randomSerial(), Utils.getCurrentTime());
	}

}
