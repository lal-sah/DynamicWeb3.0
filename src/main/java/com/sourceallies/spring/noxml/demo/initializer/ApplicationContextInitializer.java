/**
 * ApplicationContextInitializer.java
 * Feb 10, 2012
 */
package com.sourceallies.spring.noxml.demo.initializer;

import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.sourceallies.spring.noxml.demo.context.business.BusinessConfiguration;
import com.sourceallies.spring.noxml.demo.context.web.DispatcherConfiguration;

/**
 * @author Lal
 * 
 */
public class ApplicationContextInitializer implements WebApplicationInitializer {

	private static final Logger LOGGER = Logger
			.getLogger(ApplicationContextInitializer.class.getName());

	public static final String DISPATCHER_SERVLET_NAME = "dispatcher";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * 
	 * org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet
	 * .ServletContext)
	 */
	@Override
	public void onStartup(ServletContext container) throws ServletException {
		LOGGER.info("Initializing Spring Context configurations............");

		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(BusinessConfiguration.class);

		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(rootContext));

		// Create the dispatcher servlet's Spring application context
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(DispatcherConfiguration.class);

		// register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = container.addServlet(
				DISPATCHER_SERVLET_NAME, new DispatcherServlet(
						dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/app/*");

		LOGGER.info(".............Spring container started up!");
	}
}
