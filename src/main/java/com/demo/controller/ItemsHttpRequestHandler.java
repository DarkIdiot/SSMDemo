package com.demo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.ModelAndView;

/** 
 *
 * @author DarkIdiot-PC
 * @date 2016年3月6日 下午11:55:34
 */
public class ItemsHttpRequestHandler implements HttpRequestHandler{
	/**
	 * 此类适配器可以通过response修改定义响应的内容,比如json数据.
	 * 			response.setCharacterEncoding("UTF-8");
	 * 			response.setContentType("application/json,charset=utf-8");
	 * 			response.getWriter().write("json串");
	 */
	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 业务逻辑
		 */
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json,charset=utf-8");
		response.getWriter().write("json串");

		//类似于servlet的编程.  
		request.setAttribute("result", "success");
		//转发，由于是调用的原生的servlet api,所以对于视图解析器配置的前缀和后缀不会对此handler生效.
		request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
		//重定向
//		response.sendRedirect("/WEB-INF/jsp/success.jsp"); 
	}

}
