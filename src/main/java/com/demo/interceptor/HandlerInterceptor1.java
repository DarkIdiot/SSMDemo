package com.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/** 
 *
 * @author DarkIdiot-PC
 * @date 2016年3月9日 下午10:13:18
 */
public class HandlerInterceptor1 implements HandlerInterceptor{
	
	//进入handler 方法之前
	//应用场景:身份认证和身份授权
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		System.out.println("HandlerInterceptor1 ----> preHandle ");
		
		// return false; 表示拦截不向下继续执行
		// return true; 标识放行,继续向下执行
		return true;
	}
	
	//进入handler方法之后,在返回ModelAndView之前
	//应用场景:公用的模型数据的填写(导航栏数据填写,菜单栏数据的填写),并传递到视图.
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("HandlerInterceptor1 ----> postHandle ");
	}
	
	//执行完成handler方法之后
	//应用场景:统一的异常处理,统一的日志处理
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("HandlerInterceptor1 ----> afterCompletion ");
	}

}
