package com.appsaradev.paymoney.resource;

import java.net.URI;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.appsaradev.paymoney.account.auth.SignUp;
import com.appsaradev.paymoney.account.dao.User;
import com.appsaradev.paymoney.account.utils.Utils;

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

	@GET
	@Path("/createuser")
	public Response createUser(@QueryParam("signup-fname") String fname, @QueryParam("signup-lname") String lname,
			@QueryParam("signup-email") String email, @QueryParam("signup-password") String password,
			@QueryParam("signup-mobile") String mobile, @QueryParam("BLOCK_CATEG_DHTML") int usertype,
			@QueryParam("accept-terms") String newsletters) {
		int newsLetters = 0;
		if (newsletters.equals("1")) {
			newsLetters = 1;
		}
		User user = new User(email, email, password, fname, lname, mobile, usertype, 0, newsLetters, 0,
				Utils.getCurrentTime());
		SignUp.doCreateUser(user);
		Utils.sendEmail("Verify New Pay Money Account", user);
		URI uri = UriBuilder.fromUri("").build();
		return Response.seeOther(uri).build();
	}

	@GET
	@Path("/createuser/verify")
	public Response verify(@QueryParam("token") String token) {

		String email = Utils.decodedBase64(token);

		System.out.println(email);

		URI uri = UriBuilder.fromUri("").build();
		return Response.seeOther(uri).build();
	}

	@GET
	@Path("/login")
	public Response login(@QueryParam("signin-email") String email, @QueryParam("signin-password") String password) {
		URI uri = UriBuilder.fromUri("/account" + email).build();
		return Response.seeOther(uri).build();
	}

	@GET
	@Path("/account/{user}")
	public void account(@Context HttpServletRequest request, @PathParam("user") String email) {

	}

	@GET
	@Path("/account/transfer")
	public Response transfer() {
		URI uri = UriBuilder.fromUri("/").build();
		return Response.seeOther(uri).build();
	}

	@GET
	@Path("/createuser/topup/scratchcard")
	public Response scratchcard() {
		URI uri = UriBuilder.fromUri("/").build();
		return Response.seeOther(uri).build();
	}

	@GET
	@Path("/createuser/topup/mandatcash")
	public Response mandatcash() {
		URI uri = UriBuilder.fromUri("/").build();
		return Response.seeOther(uri).build();
	}

	@GET
	@Path("/createuser/setting/upgrade")
	public Response upgrade() {
		URI uri = UriBuilder.fromUri("/").build();
		return Response.seeOther(uri).build();
	}

	@GET
	@Path("/createuser/setting/changepassword")
	public Response changePassword() {
		URI uri = UriBuilder.fromUri("/").build();
		return Response.seeOther(uri).build();
	}

	@GET
	@Path("/forgotpassword")
	public Response forgotPassword() {
		URI uri = UriBuilder.fromUri("/").build();
		return Response.seeOther(uri).build();
	}

	@GET
	@Path("/forgotpassword/newpassword")
	public Response newPassword() {
		URI uri = UriBuilder.fromUri("/").build();
		return Response.seeOther(uri).build();
	}

	@GET
	@Path("/integration/checkbusiness")
	public Response checkbusiness() {
		URI uri = UriBuilder.fromUri("/").build();
		return Response.seeOther(uri).build();
	}

	@GET
	@Path("/integration/createbussiness")
	public Response createBusiness() {
		URI uri = UriBuilder.fromUri("/").build();
		return Response.seeOther(uri).build();
	}
}
