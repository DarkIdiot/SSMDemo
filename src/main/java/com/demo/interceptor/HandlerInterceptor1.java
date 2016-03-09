package com.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/** 
 *
 * @author DarkIdiot-PC
 * @date 2016��3��9�� ����10:13:18
 */
public class HandlerInterceptor1 implements HandlerInterceptor{
	
	//����handler ����֮ǰ
	//Ӧ�ó���:�����֤�������Ȩ
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		System.out.println("HandlerInterceptor1 ----> preHandle ");
		
		// return false; ��ʾ���ز����¼���ִ��
		// return true; ��ʶ����,��������ִ��
		return true;
	}
	
	//����handler����֮��,�ڷ���ModelAndView֮ǰ
	//Ӧ�ó���:���õ�ģ�����ݵ���д(������������д,�˵������ݵ���д),�����ݵ���ͼ.
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("HandlerInterceptor1 ----> postHandle ");
	}
	
	//ִ�����handler����֮��
	//Ӧ�ó���:ͳһ���쳣����,ͳһ����־����
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("HandlerInterceptor1 ----> afterCompletion ");
	}

}
