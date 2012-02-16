/**
 * SpringDependencyInjectionServlet.java
 * Feb 14, 2012
 */
package com.sourceallies.spring.noxml.demo.web.springutils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

@SuppressWarnings("serial")
public class SpringDependencyInjectionServlet extends HttpServlet {

	private static final Logger LOGGER = Logger
			.getLogger(SpringDependencyInjectionServlet.class.getName());

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> attributeNames = getServletContext()
				.getAttributeNames();

		while (attributeNames.hasMoreElements()) {

			String name = (String) attributeNames.nextElement();
			LOGGER.log(Level.INFO, "attempting to autowire " + name);

			autowire(name);
		}

		super.service(request, response);
	}

	protected boolean autowireByType() {
		return true;
	}

	private void autowire(String name) {
		if (name != null) {
			Object attribute = getServletContext().getAttribute(name);
			Class<?> c = getClass();
			while (c != null && c != c.getSuperclass()) {
				try {
					if (autowireByType()) {
						if (byType(c, attribute)) {
							break;
						} else {
							c = c.getSuperclass();
						}
					} else {
						if (byName(c, name, attribute)) {
							break;
						} else {
							c = c.getSuperclass();
						}
					}
				} catch (NoSuchMethodException e) {
					c = c.getSuperclass();
				}
			}
		}
	}

	private boolean byName(Class<?> c, String name, Object attribute)
			throws NoSuchMethodException {
		boolean success = false;

		if (attribute != null) {

			Method[] methods = c.getDeclaredMethods();
			for (Method method : methods) {
				if (method.getName().equals(getMethodName(name))) {
					Class<?>[] paramTypes = method.getParameterTypes();

					if (paramTypes.length == 1) {
						success = invokeSpringBeanSetter(method, attribute);
					}
				}
			}
		}
		return success;
	}

	private boolean byType(Class<?> c, Object attribute) {
		boolean success = false;

		if (attribute != null) {
			Method[] methods = c.getDeclaredMethods();

			for (Method method : methods) {
				Class<?>[] paramTypes = method.getParameterTypes();

				Class<?> attributeClass = attribute.getClass();
				if (paramTypes.length == 1
						&& paramTypes[0].equals(attributeClass)) {
					boolean succeeded = invokeSpringBeanSetter(method,
							attribute);
					if (!success && succeeded) {
						success = succeeded;
					}
				}
			}
		}
		return success;
	}

	private boolean invokeSpringBeanSetter(Method method, Object attribute) {
		boolean success = false;
		try {
			method.invoke(this, attribute);
			success = true;
		} catch (Exception e) {
			// TODO do we care?
		}
		return success;
	}

	private String getMethodName(String contextName) {
		return "set" + StringUtils.capitalize(contextName);
	}
}