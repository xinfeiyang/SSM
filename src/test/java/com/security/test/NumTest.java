package com.security.test;

import org.junit.Test;

import com.security.bean.Person;

public class NumTest {

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
