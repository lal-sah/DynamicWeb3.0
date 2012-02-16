/**
 * HelloController.java
 * Feb 8, 2012
 */
package com.sourceallies.spring.noxml.demo.web.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sourceallies.spring.noxml.demo.service.HelloService;

/**
 * @author Lal
 * 
 */
@Controller(value = "helloController")
public class HelloController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2698341234314914659L;

	private static final Logger LOGGER = Logger.getLogger(HelloController.class
			.getName());

	@Resource(name = "helloService")
	private HelloService service;

	@Autowired
	@Qualifier("greetingController")
	private GreetingController greetingController;

	public HelloController() {
		LOGGER.info("...............Hello Controller >> " + this);
	}

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String hello(Locale locale, Model model) {
		LOGGER.info("Greeting controller: " + greetingController);
		LOGGER.info("Hello service: " + service);
		LOGGER.info("Hello  >>>>>>>>>>>>>>>.");
		model.addAttribute("now", new Date());
		return "hello";
	}
}
