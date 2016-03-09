package com.demo.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * ȫ���쳣������
 */
public class CustomerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		// handler���Ǵ�����������Ҫִ�е�Handler����(ֻ��һ��method����)
		SystemException se = null;
		if (ex instanceof SystemException) {
			se = (SystemException) ex;
		} else {
			se = new SystemException("δ֪����");
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", se.getMessage());
		mv.setViewName("error");
		return mv;
	}

}
