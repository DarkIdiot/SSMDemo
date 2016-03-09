package com.demo.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理器
 */
public class CustomerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		// handler就是处理器适配器要执行的Handler对象(只有一个method方法)
		SystemException se = null;
		if (ex instanceof SystemException) {
			se = (SystemException) ex;
		} else {
			se = new SystemException("未知错误");
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", se.getMessage());
		mv.setViewName("error");
		return mv;
	}

}
