/**
 * DispatcherConfig.java
 * Feb 10, 2012
 */
package com.sourceallies.spring.noxml.demo.context.web;

import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author Lal
 * 
 */
@Configuration
@ComponentScan(basePackages = {
		"com.sourceallies.spring.noxml.demo.web.controller",
		"com.sourceallies.spring.noxml.demo.web.servlet.handler" })
public class DispatcherConfiguration {

	private static final Logger LOGGER = Logger
			.getLogger(DispatcherConfiguration.class.getName());

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		LOGGER.info(">>>>>>>> configuring Spring internal view resolver / Web Servlet ............");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

}
