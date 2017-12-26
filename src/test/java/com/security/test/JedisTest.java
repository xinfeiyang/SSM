package com.security.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.security.util.JedisClientPool;

public class JedisTest {

	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-redis.xml");
		JedisClientPool pool = context.getBean(JedisClientPool.class);
		System.out.println(pool);
	}
}
