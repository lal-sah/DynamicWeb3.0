/**
 * SAIHttpRequestHandlerServlet.java
 * Feb 15, 2012
 */
package com.sourceallies.spring.noxml.demo.web.servlet.framework;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.FrameworkServlet;

import com.sourceallies.spring.noxml.demo.initializer.ApplicationContextInitializer;

/**
 * @author Lal
 * 
 */
@SuppressWarnings("serial")
public class SAIHttpRequestHandlerServlet extends HttpServlet {

	private static final Logger LOGGER = Logger
			.getLogger(SAIHttpRequestHandlerServlet.class.getName());

	private static final String DISPATCHER_CONTEXT_ATTRIBUTE_NAME = FrameworkServlet.SERVLET_CONTEXT_PREFIX
			+ ApplicationContextInitializer.DISPATCHER_SERVLET_NAME;
	private HttpRequestHandler target;

	@Override
	public void init() throws ServletException {
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		try {
			this.target = (HttpRequestHandler) wac.getBean(getServletName(),
					HttpRequestHandler.class);
		} catch (NoSuchBeanDefinitionException e) {
			LOGGER.severe("HTTP Request Handler bean was not found in Spring Root Context! Looking up in the Dispatcher Context...");
			WebApplicationContext context = WebApplicationContextUtils
					.getWebApplicationContext(getServletContext(),
							DISPATCHER_CONTEXT_ATTRIBUTE_NAME);
			this.target = (HttpRequestHandler) context
					.getBean(getServletName());
		}
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		LocaleContextHolder.setLocale(request.getLocale());
		try {
			this.target.handleRequest(request, response);
		} catch (HttpRequestMethodNotSupportedException ex) {
			String[] supportedMethods = ((HttpRequestMethodNotSupportedException) ex)
					.getSupportedMethods();
			if (supportedMethods != null) {
				response.setHeader("Allow", StringUtils.arrayToDelimitedString(
						supportedMethods, ", "));
			}
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,
					ex.getMessage());
		} finally {
			LocaleContextHolder.resetLocaleContext();
		}
	}

}
