package com.sourceallies.spring.noxml.demo.web.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.FrameworkServlet;

import com.sourceallies.spring.noxml.demo.initializer.ApplicationContextInitializer;
import com.sourceallies.spring.noxml.demo.web.servlet.framework.SAHttpRequestHandlerServlet;

/**
 * Servlet implementation class AnnotatedHttpServlet
 */
@WebServlet(description = "Http Servlet using pure java / annotations", urlPatterns = { "/annotatedServlet" }, name = "annotatedServletHandler")
public class AnnotatedHttpServlet extends SAHttpRequestHandlerServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger
			.getLogger(AnnotatedHttpServlet.class.getName());

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		WebApplicationContext rootContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());

		WebApplicationContext webContext = WebApplicationContextUtils
				.getWebApplicationContext(
						getServletContext(),
						FrameworkServlet.SERVLET_CONTEXT_PREFIX
								+ ApplicationContextInitializer.DISPATCHER_SERVLET_NAME);
		LOGGER.info("---------------- Root Context: " + rootContext);
		LOGGER.info("---------------- Web Context: " + webContext);

		super.service(request, response);
	}
}
