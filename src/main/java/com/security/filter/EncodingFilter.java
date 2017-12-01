package com.security.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *编码过滤器;
 */
public class EncodingFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//方法一:
		/*request.setCharacterEncoding("UTF-8");
		//被增强的对象;
		HttpServletRequest req=(HttpServletRequest) request;
		//增强的对象;
		EnhanceRequest enhanceRequest=new EnhanceRequest(req);
		//经过UTF-8编码,解决乱码问题;
		chain.doFilter(enhanceRequest,response);*/
		
		//方法二:
		/**
		 * 代理对象;
		 * 将ServletRequest转化为HttpServletRequest;
		 */
		final HttpServletRequest req=(HttpServletRequest) request;
		
		/**
		 * 动态代理;
		 * 动态创建代理对象;
		 */
		HttpServletRequest enhanceRequest=(HttpServletRequest) Proxy.newProxyInstance(
				req.getClass().getClassLoader(), 
				req.getClass().getClasses(),
				new InvocationHandler() {
					/*
					 * proxy:是代理对象(几乎用不上);
					 * method:代表的是目标方法;
					 * args:代表是调用目标方法时参数;
					 */
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						//获取执行方法的方法名;
						String methodName=method.getName();
						if(methodName.equals("getParameter")){
							//执行getParameter()方法的执行结果;
							String result=(String) method.invoke(req,args);
							result=new String(result.getBytes("ISO-8859-1"),"UTF-8");
							return result;
						}
						return method.invoke(req, args);
					}
				});
		//放行
		chain.doFilter(enhanceRequest,response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void destroy() {
		
	}
	
	/**
	 * 加强版的HttpServletRequest;
	 */
	@SuppressWarnings("unused")
	private class EnhanceRequest extends HttpServletRequestWrapper{

		private HttpServletRequest request;
		
		public EnhanceRequest(HttpServletRequest request) {
			super(request);
			this.request=request;
		}
		
		/**
		 * 重写HttpServeltRequest的getParameter()方法;
		 * 作用:将编码格式转化为UTF-8;
		 */
		@Override
		public String getParameter(String name) {
			String parameter=request.getParameter(name);//可能会乱码;
			try {
				parameter=new String(parameter.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return parameter;
		}
		
	}

}
