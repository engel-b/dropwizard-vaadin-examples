package io.dropwizard;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import com.vaadin.tutorial.addressbook.AddressbookUI;

import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class VaadinApplication extends Application<VaadinApplication.Configuration> {

	public static class Configuration extends io.dropwizard.Configuration {
		public String name;
	}

	@VaadinServletConfiguration(ui = AddressbookUI.class, productionMode = false)
	public static class Servlet extends VaadinServlet {
		// empty
	}

	public static void main(String... args) throws Exception {
		new VaadinApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<Configuration> bootstrap) {
		bootstrap.addBundle(new VaadinBundle(Servlet.class, "/vaadin/*"));
	}

	@Override
	public void run(Configuration configuration, Environment environment) throws Exception {
		// empty
	}
}
