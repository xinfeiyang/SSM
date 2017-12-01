package com.security.proxy;

/**
 * 静态代理;
 */
public class StaticProxy implements Hello {
	private Hello hello;

	public StaticProxy() {
		hello = new HelloImp();
	}
	
	public void before(){
		System.out.println("----------------->before");
	}
	
	public void after(){
		System.out.println("<----------------after");
	}

	@Override
	public void say(String name) {
		before();
		hello.say(name);
		after();
	}
	
	public static void main(String[] args) {
		StaticProxy proxy=new StaticProxy();
		proxy.say("冯朗");
	}

}
