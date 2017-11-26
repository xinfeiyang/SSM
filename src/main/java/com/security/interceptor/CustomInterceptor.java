package com.security.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器
 */
public class CustomInterceptor implements HandlerInterceptor {

	/**
	 * Controller执行前调用此方法,这里可以加入登录校验、权限拦截等
	 * return:true代表放行;false代表拦截,终止执行;
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("-------preHandle");
		HttpSession session = request.getSession();
		String username=(String) session.getAttribute("username");
		if(username!=null){
			return true;
		}else{
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
	}

	/**
	 * controller执行后但未返回视图前调用此方法
	 * 这里可在返回用户前对模型数据进行加工处理，比如这里加入公用信息以便页面显示
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("----------->postHandle");
	}

	/**
	 * controller执行后且视图返回后调用此方法
	 * 这里可得到执行controller时的异常信息
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("----------->afterCompletion");
	}

}
