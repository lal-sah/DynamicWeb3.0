/**
 * MasterConfiguration.java
 * Feb 8, 2012
 */
package com.sourceallies.spring.noxml.demo.context.business;

import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.sourceallies.spring.noxml.demo.dao.HelloDao;
import com.sourceallies.spring.noxml.demo.dao.HelloDaoImpl;

/**
 * 
 * Business context configuration.
 * 
 * @author Lal
 * 
 */
@Configuration
@ComponentScan(basePackages = { "com.sourceallies.spring.noxml.demo.service"
// ,"com.sourceallies.spring.noxml.demo.web.servlet.handler",
// "com.sourceallies.spring.noxml.demo.web.controller"
// ,"com.sourceallies.spring.noxml.demo.web"
// , "com.sourceallies.spring.noxml.demo.dao"
})
public class BusinessConfiguration {

	private static final Logger LOGGER = Logger
			.getLogger(BusinessConfiguration.class.getName());

	@Bean
	public HelloDao helloDao() {
		LOGGER.info(">>>>>> Creating hello dao....");
		return new HelloDaoImpl();
	}
}