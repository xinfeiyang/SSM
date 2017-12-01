package com.security.proxy;

/**
 * 自定义的接口的一个实现类
 */
public class HelloImp implements Hello {

	@Override
	public void say(String name) {
		System.out.println("hello-->"+name);
	}

}
