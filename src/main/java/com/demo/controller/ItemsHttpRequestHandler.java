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
 * @date 2016��3��6�� ����11:55:34
 */
public class ItemsHttpRequestHandler implements HttpRequestHandler{
	/**
	 * ��������������ͨ��response�޸Ķ�����Ӧ������,����json����.
	 * 			response.setCharacterEncoding("UTF-8");
	 * 			response.setContentType("application/json,charset=utf-8");
	 * 			response.getWriter().write("json��");
	 */
	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * ҵ���߼�
		 */
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json,charset=utf-8");
		response.getWriter().write("json��");

		//������servlet�ı��.  
		request.setAttribute("result", "success");
		//ת���������ǵ��õ�ԭ����servlet api,���Զ�����ͼ���������õ�ǰ׺�ͺ�׺����Դ�handler��Ч.
		request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
		//�ض���
//		response.sendRedirect("/WEB-INF/jsp/success.jsp"); 
	}

}
