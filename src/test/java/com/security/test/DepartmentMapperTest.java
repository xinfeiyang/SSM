package com.security.test;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.security.bean.Department;
import com.security.dao.DepartmentMapper;

public class DepartmentMapperTest{
	
	private ApplicationContext context;
	private DepartmentMapper mapper;
	
	@Before
	public void getApplicationContext(){
		context = new ClassPathXmlApplicationContext("applicationcontext.xml","springmvc.xml");
		mapper=context.getBean(DepartmentMapper.class);
	}
	
	@Test
	public void getDepartment(){
		//Department department=mapper.getDepartmentById(2);
		Department department=mapper.getDepartmentWithUsersById(2);
		System.out.println(Arrays.asList(department.getUsers()));
	}
	
	
}
