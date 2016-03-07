package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demo.service.UserService;

/** 
 *
 * @author DarkIdiot-PC
 * @date 2016��3��7�� ����9:28:40
 */
@Controller
public class ItemsAnnotationController {
	
	@RequestMapping("/ItemsAnnotationContriller.do")
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
		//modelAndView.setViewName("/WEB-INF/jsp/success.jsp");
			//Ϊ��ͼ����������ǰ׺�ͺ�׺��
		modelAndView.setViewName("success");
		return modelAndView;
	}
}
