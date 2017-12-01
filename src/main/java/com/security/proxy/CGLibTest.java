package com.security.proxy;

public class CGLibTest {

	public static void main(String[] args) {
		
		/*Hello hello=(Hello) new CGLibProxy(new HelloImp()).createProxyInstance();
		hello.say("冯朗");*/
		HelloImp hello=(HelloImp) new CGLibProxy().createProxyInstance(HelloImp.class);
		hello.say("冯朗");
	}

}
