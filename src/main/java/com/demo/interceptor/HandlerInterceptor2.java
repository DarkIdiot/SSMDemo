package com.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.User;
/**
 * ��½��֤��������ʵ��
 */
public class HandlerInterceptor2 implements HandlerInterceptor{
	/**
	 * ����������ִ��˳��:
	 * preHandle ����������˳��,˳��ִ��.
	 * postHandle,afterCompletion ����������˳��,����ִ��.
	 */
	/**
	 * ���������ع���:	������1����,������2��preHandle�����Ż�ִ��.
	 * 					������preHandle������,����������postHandle,afterCompletion����������ִ��.
	 * 					ֻҪ��һ��������������,���������е����е���������postHandle���������ᱻִ��.
	 * 
	 * 	���Ӧ��: eg.ͳһ����־����������:��Ҫ����������preHandle�������,�Ҹ�����������Ҫ�����������ĵ�һλ,���ܱ�֤����������afterCompletion�ض�ִ��.
	 * 			  eg.��½��֤��������(������־������֮��),Ȩ��У���������(���ڵ�½������֮��)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("HandlerInterceptor2 ----> preHandle ");
		
		//��������url
		StringBuffer url = request.getRequestURL();
		//�ж��Ƿ����ڹ�����ַ,����
		
		//����ǽ����½����,����
		if (url.indexOf("login") >= 0) {
			return true;
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("/SSMDemo/index.jsp");
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("HandlerInterceptor2 ----> postHandle ");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("HandlerInterceptor2 ----> afterCompletion ");
	}

}
