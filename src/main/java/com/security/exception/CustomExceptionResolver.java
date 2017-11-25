package com.security.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理
 * 方法二:
 */
@Component
public class CustomExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		String msg="";
		if(ex instanceof BusinessException){
			BusinessException exception=(BusinessException)ex;
			msg=exception.getMessage();
		}else{
			msg=ex.getMessage();
		}
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("msg",msg);
		modelAndView.setViewName("error");
		return modelAndView;
	}

}
