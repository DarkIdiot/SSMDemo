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
 * @date 2016年3月7日 上午9:28:40
 */
@Controller
public class ItemsAnnotationController {
	
	@RequestMapping("/ItemsAnnotationContriller.do")
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
		//modelAndView.setViewName("/WEB-INF/jsp/success.jsp");
			//为视图解析器配置前缀和后缀后
		modelAndView.setViewName("success");
		return modelAndView;
	}
}
