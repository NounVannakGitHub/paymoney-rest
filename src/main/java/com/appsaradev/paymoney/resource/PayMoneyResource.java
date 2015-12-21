package com.appsaradev.paymoney.resource;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class PayMoneyResource {

	private final String template;
	private final String defaultName;
	private final AtomicLong counter;

	public PayMoneyResource(String template, String defaultName) {
		this.defaultName = defaultName;
		this.template = template;
		this.counter = new AtomicLong();
	}

}
