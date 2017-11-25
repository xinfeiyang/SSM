package com.security.listener;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import com.security.bean.User;
import com.security.service.UserService;

/**
 * WEB容器初始化时调动;
 */
@Component
public class ContextRefreshListener implements ApplicationListener<ContextRefreshedEvent>,ServletContextAware{

	@Autowired
	private UserService service;
	
	private ServletContext servletContext;
	
	/**
	 * 上下文刷新事件
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent()==null){
			System.out.println("---------->web容器初始化");
			User user=service.getUserById(1);
			servletContext.setAttribute("user",user);
		}
	}


	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext=servletContext;
	}

}
