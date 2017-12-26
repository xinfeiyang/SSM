package com.security.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.junit.Test;

import com.security.bean.Person;

public class NumTest {
	
	@Test
	public void testProperty() throws IOException{
		String fileName3 = NumTest.class.getResource("/db.properties").getFile();
		System.out.println(fileName3);
		
		/*String url=ResourceBundle.getBundle("db").getString("jdbc.url");
		System.out.println(url);*/
		
		/*InputStream in = NumTest.class.getResourceAsStream("/db.properties");
		Properties properties = new Properties();
		properties.load(in);
		String value = properties.getProperty("jdbc.url"); // 获得name属性
		System.out.println(value);*/
	}

	@Test
	public void simpleTest() {
		long number =1;
		for(int i=0;i<=60;i++){
			System.out.println("1<<"+i+"="+(number<<i));
		}
		System.out.println(5<<1);
	}
	
	@Test
	public void stringTest(){
		String a="programming";
		String b=new String("programming");
		String c="program"+"ming";
		System.out.println(a==b);
		System.out.println(a==c);
		System.out.println(a.equals(b));
		System.out.println(a.equals(c));
	}
	
	@Test
	public void testFinal(){
		Person person=new Person();
		person.setName("冯朗");
		Person p2=person;
		System.out.println(person);
		System.out.println(p2);
		System.out.println("---------------");
		person.setName("冯跃");
		System.out.println(person);
		System.out.println(p2);
		
	}

}
