package com.security.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.security.bean.Department;
import com.security.bean.User;
import com.security.dao.UserMapper;

public class UserMapperTest{
	
	private ApplicationContext context;
	private UserMapper mapper;
	
	@Before
	public void getApplicationContext(){
		context = new ClassPathXmlApplicationContext("applicationcontext.xml","springmvc.xml");
		mapper=context.getBean(UserMapper.class);
	}
	
	@Test
	public void insertUser(){
		mapper.addUser(new User("李晓丹","女",22,new Department(2)));
		mapper.addUser(new User("李佩丹","女",25,new Department(3)));
		mapper.addUser(new User("朱蕊","女",28,new Department(1)));
		mapper.addUser(new User("郑艳玲","女",27,new Department(4)));
		mapper.addUser(new User("郑玲玲","女",26,new Department(1)));
		mapper.addUser(new User("杨幂","女",35,new Department(3)));
		mapper.addUser(new User("唐嫣","女",36,new Department(4)));
		mapper.addUser(new User("胡歌","男",38,new Department(2)));
	}
	
	@Test
	public void getUser(){
		User user = mapper.getUserWithDeptById(2);
		System.out.println(user);
	}
	
}
