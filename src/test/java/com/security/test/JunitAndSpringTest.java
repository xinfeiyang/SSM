package com.security.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.security.bean.Item;
import com.security.dao.ItemMapper;

public class JunitAndSpringTest{
	
	private ApplicationContext context;
	
	@Before
	public void getApplicationContext(){
		context = new ClassPathXmlApplicationContext("applicationcontext.xml","springmvc.xml");
	}
	
	@Test
	public void generateBeanAndMapper() throws Exception{
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File("D:/WorkSpace/Eclipse/SSM/target/classes/mybatis-generator.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
				callback, warnings);
		myBatisGenerator.generate(null);
	}
	
	@Test
	public void getMapper(){
		ItemMapper mapper=(ItemMapper) context.getBean("itemMapper");
		List<Item> list = mapper.selectByExample(null);
		for(Item item:list){
			System.out.println(item.getDetail());
		}
	}

	@Test
	public void getDataSource() {
		ComboPooledDataSource dataSource=(ComboPooledDataSource) context.getBean("dataSource");
		System.out.println(dataSource.getDriverClass());
	}

}
