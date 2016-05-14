package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.service.UserService;

@Controller
@RequestMapping("/restful")
public class RESTFulController {
	@Autowired
	private UserService us;
	/**
	 * {XXX}占位符,请求的URL可以是如何数字,可以通过在方法中使用@@PathVariable获取{XXX}中的XXX变量.
	 */
	@RequestMapping(value="/user/{id}",method={RequestMethod.GET})
	public ModelAndView getUser(@PathVariable("id") Integer id ){
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", us.getUser(id));
		mv.setViewName("success");
		return mv;
	}
}
/**
 * RESTFul风格的URL:1.使用占位符传递必要的参数;2.根据不同的CRUD操作,使用不同的请求方法参数指定请求的HTTP的方法
 * 		标准的get的RESTFul方式配置:@RequestMapping(value="/user/{id}",method={RequestMethod.GET})
 */
/**	配置前端控制器的url-partten使用 '/'，对于静态资源出现400错误.
 *	  解决方案: 
 * 对静态资源的解析:(由于使用RESTFul风格的URL,静态资源文件的请求URL无法对应一个handler.) 
 * 		如果没有任何尾缀的url则使用spring的DispatcherServlet进行过滤，若尾缀是jsp就直接访问，不经过SpringMVC的过滤.
 * 	<mvc:resources location="/js/" mapping="/js/**"/>  指定资源文件的对应映射关系(所有的页面引用到/js/**的资源都从/js/里面进行查找)
 */
