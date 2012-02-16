/**
 * HelloDaoImpl.java
 * Feb 14, 2012
 */
package com.sourceallies.spring.noxml.demo.dao;

/**
 * For the demo purpose, this class will be injected using @Configuration
 * annotation as if this is a third party class. In normal scenarios this can be
 * simply scanned using @ComponentScan annotation.
 * 
 * @author Lal
 * 
 */
public class HelloDaoImpl implements HelloDao {

	@Override
	public String sayHello(String name) {
		return "Hello " + name + "!";
	}

}
