/**
 * ServiceImpl.java
 * Feb 8, 2012
 */
package com.sourceallies.spring.noxml.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourceallies.spring.noxml.demo.dao.HelloDao;

/**
 * This will be scanned using @ComponentScan annotation.
 * 
 * @author Lal
 * 
 */
@Service(value = "helloService")
public class HelloServiceImpl implements HelloService {

	@Autowired
	private HelloDao helloDao;

	@Override
	public String sayHello(String name) {
		return helloDao.sayHello(name);
	}

}
