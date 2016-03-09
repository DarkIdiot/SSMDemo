package com.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.User;
/**
 * 登陆认证拦截器的实现
 */
public class HandlerInterceptor2 implements HandlerInterceptor{
	/**
	 * 拦截器方法执行顺序:
	 * preHandle 按照拦截器顺序,顺序执行.
	 * postHandle,afterCompletion 按照拦截器顺序,逆序执行.
	 */
	/**
	 * 拦截器拦截规律:	拦截器1放行,拦截器2的preHandle方法才会执行.
	 * 					拦截器preHandle不放行,其拦截器的postHandle,afterCompletion方法都不会执行.
	 * 					只要有一个拦截器不放行,拦截器链中的所有的拦截器的postHandle方法都不会被执行.
	 * 
	 * 	理解应用: eg.统一的日志处理拦截器:需要改拦截器的preHandle必须放行,且该拦截器必须要在拦截器链的第一位,才能保证该拦截器的afterCompletion必定执行.
	 * 			  eg.登陆认证的拦截器(放在日志拦截器之后),权限校验的拦截器(放在登陆拦截器之后)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("HandlerInterceptor2 ----> preHandle ");
		
		//获得请求的url
		StringBuffer url = request.getRequestURL();
		//判断是否属于公开地址,放行
		
		//如果是进入登陆见面,放行
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
