package com.security.filter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {

	public static void main(String[] args) {
		
		/**
		 * 代理对象;
		 */
		final Target target=new Target();
		
		/**
		 * 动态创建代理对象;
		 */
		TargetInterface proxy=(TargetInterface) Proxy.newProxyInstance(
				target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(),
				new InvocationHandler() {
					
					/*
					 * proxy:是代理对象(几乎用不上)
					 * method:代表的是目标方法;
					 * args:代表是调用目标方法时参数
					 */
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						String methodName=method.getName();
						System.out.println("---------------->"+methodName+"执行前");
						Object result = method.invoke(target,args);
						System.out.println(methodName+"的执行结果为--------->"+result);
						System.out.println("---------------->>>>"+methodName+"执行后----->");
						return result;
					}
				});
		
		/**
		 * 执行代理兑现的方法;
		 */
		proxy.method();//调用invoke---Method
		proxy.add(12,23);
		proxy.getName("冯朗");
		
	}

}
