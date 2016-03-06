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
		 * 业务逻辑
		 */

		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// 相当于request.setAttribute(Object,Object);
		modelAndView.addObject("result", "success");
		// 指定视图
		modelAndView.setViewName("/index.jsp");
		return modelAndView;
	}

}
