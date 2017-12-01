package com.security.proxy;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * CGLib动态代理;
 */
public class CGLibProxy implements MethodInterceptor {
	
	public void before(){
		System.out.println("----------------->before");
	}
	
	public void after(){
		System.out.println("<----------------after");
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		before();
		Object result=proxy.invokeSuper(obj, args);
		after();
		return result;
	}
	
	/**
	 * 获取代理的实例;
	 * @return
	 */
	public Object createProxyInstance(Class<?> clazz){
		return Enhancer.create(clazz,this);
	}
	
}
