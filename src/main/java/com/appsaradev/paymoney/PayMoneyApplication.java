package com.appsaradev.paymoney;

import org.eclipse.jetty.server.session.SessionHandler;

import com.appsaradev.paymoney.health.TemplateHealthCheck;
import com.appsaradev.paymoney.resource.PayMoneyResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jersey.sessions.HttpSessionProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PayMoneyApplication extends Application<PayMoneyConfiguration> {

	public static void main(String[] args) throws Exception {
		new PayMoneyApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<PayMoneyConfiguration> bootstrap) {
		// TODO Auto-generated method stub

		bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));

	}

	@Override
	public void run(PayMoneyConfiguration configuration, Environment environment) throws Exception {
		final PayMoneyResource resource = new PayMoneyResource(configuration.getTemplate(),
				configuration.getDefaultName());
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);
		environment.jersey().register(resource);
		environment.jersey().register(HttpSessionProvider.class);
		environment.servlets().setSessionHandler(new SessionHandler());

		// run assets resource
		environment.jersey().setUrlPattern("/api/*");
	}

}
