package com.security.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.security.service.RightsService;

public class RightsMapperTest{
	
	private ApplicationContext context;
	private RightsService service;
	
	@Before
	public void getApplicationContext(){
		context = new ClassPathXmlApplicationContext("applicationcontext.xml");
		service=context.getBean(RightsService.class);
	}
	
	@Test
	public void insertUser(){
		service.addRight("/log","查看日志");
	}
	
}
