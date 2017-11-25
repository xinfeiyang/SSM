package com.security.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理
 * 方法一:
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value={BusinessException.class,Exception.class})
	public ModelAndView handkeException(Exception ex) {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("msg",ex.getMessage());
		modelAndView.setViewName("error");
		return modelAndView;
	}

}
