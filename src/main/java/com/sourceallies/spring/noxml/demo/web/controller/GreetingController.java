/**
 * GreetingController.java
 * Feb 14, 2012
 */
package com.sourceallies.spring.noxml.demo.web.controller;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Lal
 * 
 */
@Controller(value = "greetingController")
public class GreetingController {

	private static final Logger LOGGER = Logger
			.getLogger(GreetingController.class.getName());

	@Inject
	@Named(value = "helloController")
	private HelloController helloController;

	@RequestMapping(value = "greet", method = RequestMethod.GET)
	public String greet() {
		LOGGER.info("Hello Controller: " + helloController);
		return "greet";
	}

}
