package com.security.proxy;

public class JDKTest {

	public static void main(String[] args) {

		Hello hello=new HelloImp();
		JDKProxy jdkProxy=new JDKProxy(hello);
		Hello proxy=jdkProxy.getProxy();
		proxy.say("冯朗");
	}

}
