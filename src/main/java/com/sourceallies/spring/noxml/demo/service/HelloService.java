/**
 * Service.java
 * Feb 8, 2012
 */
package com.sourceallies.spring.noxml.demo.service;

/**
 * @author Lal
 * 
 */
public interface HelloService {

	/**
	 * Invokes data access layer to communicate.
	 * 
	 * @param name
	 * @return
	 */
	public String sayHello(String name);

}
