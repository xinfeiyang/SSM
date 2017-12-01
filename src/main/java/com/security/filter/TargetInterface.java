package com.security.filter;

/**
 * 代理对象的接口
 */
public interface TargetInterface {

	public void method();
	
	public int add(int a,int b);
	
	public String getName(String name);
}
