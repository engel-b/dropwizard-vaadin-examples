package com.github.justcoke.example;

import org.bonn.se.gui.ui.MyUI;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class VaadinApplication extends Application<VaadinConfiguration> {

	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class Servlet extends VaadinServlet {
		// empty
	}

	public static void main(String... args) throws Exception {
		if (args.length == 0) {
			args = new String[] { "server", "config.yml" };
		}
		new VaadinApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<VaadinConfiguration> bootstrap) {
		bootstrap.addBundle(new VaadinBundle(Servlet.class, "/vaadin/*"));

		// set up folders for static content
		bootstrap.addBundle(new AssetsBundle("/VAADIN", "/VAADIN", null));

		// setup database migration with Liquibase. See
		// <code>migrations.xml</code> for
		// details.
		bootstrap.addBundle(new MigrationsBundle<VaadinConfiguration>() {
			@Override
			public DataSourceFactory getDataSourceFactory(VaadinConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
		});
	}

	@Override
	public void run(VaadinConfiguration arg0, Environment arg1) throws Exception {
		// TODO Auto-generated method stub

	}
}
