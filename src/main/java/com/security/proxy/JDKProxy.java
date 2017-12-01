package com.security.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理;
 */
public class JDKProxy implements InvocationHandler{
	
	private Object object;
	
	public JDKProxy(Object object) {
		this.object=object;
	}
	
	public void before(){
		System.out.println("----------------->before");
	}
	
	public void after(){
		System.out.println("<----------------after");
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object result=method.invoke(object, args);
		after();
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy(){
		/**
		 * ClassLoader loader:类加载器;
		 * Class<?>[] interfaces:获取目标类全部的接口;
		 * InvocationHander:得到InvocationHandler接口的子类实例;
		 */
		return (T) Proxy.newProxyInstance(
				object.getClass().getClassLoader(), 
				object.getClass().getInterfaces(), 
				this);
	}

}
