/**
 * HelloDaoImpl.java
 * Feb 14, 2012
 */
package com.sourceallies.spring.noxml.demo.dao;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * For the demo purpose, this class will be injected using @Configuration
 * annotation as if this is a third party class. In normal scenarios this can be
 * simply scanned using @ComponentScan annotation.
 * 
 * @author Lal
 * 
 */
public class HelloDaoImpl implements HelloDao {

	private static final Logger LOGGER = Logger.getLogger(HelloDaoImpl.class
			.getName());

	@Resource(mappedName = "jdbc/SourceAlliesDemoDS")
	private DataSource souceAlliesDS;

	@Override
	public String sayHello(String name) {
		LOGGER.info("Source Allies DS: " + souceAlliesDS);
		return "Hello " + name + "!!!";
	}

}
