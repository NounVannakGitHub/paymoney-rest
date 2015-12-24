package com.appsaradev.paymoney;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

import org.apache.commons.io.IOUtils;

import com.appsaradev.paymoney.account.auth.Verify;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}

	public void testConnection() {
		Connection connection = com.appsaradev.paymoney.singleton.Connection.getSingleConnection();
	}

	public void testRandom() {
		System.out.println(Verify.doGenerateSecurityCode());
	}

	public void testLogin() {
		// System.out.println(SignIn.isLogin("vann", "38dsiiw84"));
	}

	public void testCashToRecievier() {
		// Transfer.setCashToReciever("rete", "hjfklshfks", 258.6);
	}

	public void testReadContent() throws FileNotFoundException, IOException {
		// System.out.println(IOUtils.toString(new
		// FileReader("src/resources/assets/notice.html")));
	}
}
