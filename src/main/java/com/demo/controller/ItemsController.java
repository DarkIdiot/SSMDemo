package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ItemsController implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		/*
		 * ҵ���߼�
		 */

		// ����ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// �൱��request.setAttribute(Object,Object);
		modelAndView.addObject("result", "success");
		// ָ����ͼ
		modelAndView.setViewName("/index.jsp");
		return modelAndView;
	}

}
