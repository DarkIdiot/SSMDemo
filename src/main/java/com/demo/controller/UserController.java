package com.demo.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.exception.SystemException;
import com.demo.model.User;
import com.demo.model.UserVo;
import com.demo.service.UserService;
import com.demo.validation.ValidationGroup1;

/**
 *
 * @author DarkIdiot-PC
 * @date 2016年3月7日 下午2:45:48
 */
@Controller  //(必不可少)标识此类是一个handler处理类
@RequestMapping("/user")   //窄化请求路径
public class UserController {
	@Autowired
	UserService userService;
	
	//	@ModelAttribute注解表示将方法最终返回的值放入request中,并使用指定的key
	/**
	 * 此方法是写在UserController内,那么执行UserController内带有@RequestMapping的方法之前,
	 * 都会先执行此getTypes方法.并且执行的过程中会将形参中的pojo和方法的返回值装入request域,并设置key为默认值或指定的值.
	 */
	@ModelAttribute("map")
	public Map<String,String> getTypes(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("001","超级管理员");
		map.put("002","普通管理员");
		map.put("003","会员");
		return map;
	}
	
	
	//可以使用method属性限定http请求的方法.     //限制http请求方法
	@RequestMapping(value="/login",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView login(User user, HttpSession session) {
		String name = user.getUsername();
		String password = user.getPassword();

		ModelAndView mv = new ModelAndView();
		if (userService.login(name, password)) {
			mv.addObject("result", "success");
			session.setAttribute("user", user);
			mv.setViewName("success");
		} else {
			mv.addObject("result", "failure");
			mv.setViewName("failure");
		}
		return mv;
	}
	/** 
	 * 1.在需要校验的pojo前边添加@Validated且后边添加形参BindingResult,接受校验出错信息.NOTE:1.@Validated与BindingResult是配对使用的,且顺序固定.2.每一个需要校验的pojo都需要响应的配置
	 * 2.分组校验@Validated(value=ValidationGroup1.class),指定使用ValidationGroup1.class分组的校验.
	 */
	/**
	 * 数据回显:@ModelAttribute指定pojo回显到页面request域中的key;(不使用注解表示采用默认:类型名首字母小写)
	 */
	@RequestMapping(value="/register.do",method={RequestMethod.POST})
	public String regitser(@ModelAttribute("user") @Validated(value=ValidationGroup1.class) User user, BindingResult bindingResult,Model model) {
		//校验出错逻辑代码
		if (bindingResult.hasErrors()) {
			//遍历所有的错误信息
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for (ObjectError error : allErrors) {
				error.getDefaultMessage();
			}
			model.addAttribute("allErrors", allErrors);
		}
		
		model.addAttribute("user", userService.register(user));
		//重定向   return "redirect:index";
		//页面转发(共享request) return "forward:index";
		return "forward:login";
	}
	
	@RequestMapping("/showUsers")
	public ModelAndView showUsers(Integer[] ids){
		List<User> users = userService.showUser(ids);
		System.out.println(users);
		ModelAndView mv = new ModelAndView();
		mv.addObject("users", users);
		mv.setViewName("showUser");
		return mv;
	}
	
	/**
	 *	传入List<POJO> 需要封装在VO里面，或者转换为json格式的字符串，实现手动的转化。 
	 */
	@RequestMapping("/addListUser")
	public ModelAndView addListUser(UserVo uv){
		ModelAndView mv = new ModelAndView();
		int count = userService.addUsers(uv.getUsers());
		if (count == uv.getUsers().size()) {
			mv.addObject("result", "success");
			mv.setViewName("success");
		}else{
			mv.addObject("result", "failure");
			mv.setViewName("failure");
		}
		return mv;
	}
	
	/**
	 *	传入Map<String,POJO> 需要封装在VO里面，或者转换为json格式的字符串，实现手动的转化。 
	 */
	@RequestMapping("/addMapUser")
	public ModelAndView addMapUser(UserVo uv){
		ModelAndView mv = new ModelAndView();
		int count = userService.insertUser(uv.getUserMap().get("test"));
		if (count == 1) {
			mv.addObject("result", "success");
			mv.setViewName("success");
		}else{
			mv.addObject("result", "failure");
			mv.setViewName("failure");
		}
		return mv;
	}
	
	@RequestMapping("/logout")
	public void logout(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		/*
		 * 业务逻辑
		 */
		session.invalidate();
		
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
	
	@RequestMapping("/customException")
	public ModelAndView customException() throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("showUser");
		if (true) {
			throw new SystemException("系统内部自定义异常");
		}
		return mv;
	}
	
	@RequestMapping("/runtimeException")
	public ModelAndView runtimeException() throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("showUser");
		if (true) {
			throw new RuntimeException("运行时抛出的未知异常");
		}
		return mv;
	}
}
/** 1.返回ModelAndView:
 * 	需要方法结束时,定义ModelAndView,将model和view分别进行设置.
 * 	2.返回String:
 * 	如果controller方法返回string,则表示返回逻辑视图名(支持重定向,转发).真正视图(路径)=前缀+逻辑视图名+后缀;使用形参中的Model将model数据返回给视图.
 * 	3.返回void
 */
/**	SpringMVC接收从客服端(页面)请求key/value数据,经过参数绑定,将key/value数据绑定到controller方法的形参上.(并不是类似于struts2的在controller类中定义成员变量接受.)
 * 1.处理器适配器调用SpringMVC提供参数绑定组件,将key/value数据转成controller方法的形参.
 * 2.参数绑定组件:在SpingMVC早期版本使用PropertyEditor(只能将字符串传成java对象);后期使用converter(进行任意类型的转换)
 * 		SpringMVC提供了很多converter(转换器),在特殊情况(日期数据等等)下需要自定义converter.
 * 3.参数绑定默认支持的类型:直接在controller方法形参上定义的类型对象,就可以直接使用这些对象,在参数绑定的过程中,如果遇到默认支持的类型就直接进行绑定.
 *    HttpServletRequest(通过request对象获取请求信息),HttpServletResponse(通过response处理响应信息),HttpSession(通过session对象得到session中存放的数据),Model/ModelMap(ModelMap是Model接口的实现类,通过Model或ModelMap向页面传递数据)
 * 4.@RequestParam对简单类型的餐宿绑定.如果不使用@RequestParam,要求request传入的参数名称和controller方法的形参名称一致,才能绑定成功.使用@RequestParam不用限制request传入参数名称和controller方法的形参名称一致.
 *    该注解的属性required=true指定参数必须传入,否则返回400错误;属性defaultValue设置默认值,没有传入参数则使用默认值与形参绑定.
 * 5.绑定pojo:会按请求参数名和 POJO 属性名进行自动匹配，自动为该对象填充属性值。支持级联属性。
 * 	  post乱码:在web.xml中添加编码的过滤器org.springframework.web.filter.CharacterEncodingFilter,并指定为UTF-8字符集
 *    get乱码:1)修改tomcat的配置文件,使得其编码和工程使用的编码字符集一致;server.xml <Connector port="8090" protocol="HTTP/1.1" maxThreads="150" connectionTimeout="20000" redirectPort="8443" URIEncoding="utf-8"/>  
 *    		  2)new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8"),手动的完成编码字符集的转换;
 * 6.自定义参数绑定:需要实现org.springframework.core.convert.converter.Converter接口并注册到org.springframework.format.support.FormattingConversionServiceFactoryBean中，并且把自定义的参数绑定组件注册到HandlerAdapter里面.
 * 		两种注入的方式: 1)<mvc:annotation-driven conversion-service="conversionService"/>(必须注释RequestMappingHandlerAdapter,RequestMappingHandlerMapping,否则会出现400错误 syntactically incorrect)
 * 					   2)<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter" p:webBindingInitializer="customBinder"/> <bean id="customBinder" class="org.springframework.web.bind.support.ConfigurableWebBindingInitializer" p:conversionService="conversionService"/>
 */
/**	数据回显: SpringMVC默认支持数据回显,pojo传入Controller方法中后,SpringMVC自动将pojo数据放到request域,key为pojo的类型(首字母小写);
 * 		1. pojo回显:使用@ModelAttribute("key")注解可以为request域中的pojo指定key.
 * 		2. 简单类型的数据回显:使用model把数据传入到页面.
 */
/** 异常解析处理器(ExceptionResolver)  SpringMVC提供全局的异常处理器(一个系统只有一个异常处理器)进行统一的异常处理.(预期异常和Runtime异常)
 */
