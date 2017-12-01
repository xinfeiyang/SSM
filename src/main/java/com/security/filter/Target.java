package com.security.filter;

/**
 * 代理对象;
 */
public class Target implements TargetInterface{

	@Override
	public void method() {
		System.out.println("execute method...");
	}

	@Override
	public int add(int a, int b) {
		return a+b;
	}

	@Override
	public String getName(String name) {
		return name;
	}

}
