/**
 * AnnotatedHttpServletRequestHandler.java
 * Feb 15, 2012
 */
package com.sourceallies.spring.noxml.demo.web.servlet.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.sourceallies.spring.noxml.demo.service.HelloService;
import com.sourceallies.spring.noxml.demo.web.controller.GreetingController;
import com.sourceallies.spring.noxml.demo.web.controller.HelloController;

/**
 * @author Lal
 * 
 */
@Component("annotatedServletHandler")
public class AnnotatedHttpServletRequestHandler implements HttpRequestHandler {

	private static final Logger LOGGER = Logger
			.getLogger(AnnotatedHttpServletRequestHandler.class.getName());

	@Autowired
	private HelloController helloController;

	@Autowired
	private GreetingController greetingController;

	@Autowired
	private HelloService helloService;

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		LOGGER.info(">>>>>>>>> Service: " + helloService);
		LOGGER.info(">>>>>>>>> Controller: " + helloController);
		LOGGER.info(">>>>>>>>> Greeting Controller: " + greetingController);
		LOGGER.info(">>>>>>>>> Service: " + helloService);
		writer.write("<html><head></head><body><h1>Servlet using pure Java / No web.xml!</h1><h2>"
				/* + helloController.hello(null, null) */+ "</h2></body></html>");
	}

}
