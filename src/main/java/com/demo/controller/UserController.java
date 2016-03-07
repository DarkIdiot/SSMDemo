package com.demo.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.User;
import com.demo.service.UserService;

/**
 *
 * @author DarkIdiot-PC
 * @date 2016年3月7日 下午2:45:48
 */
@Controller
@RequestMapping("/user")   //窄化请求路径
public class UserController {
	@Autowired
	UserService userService;
	
	
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

	@RequestMapping("/register")
	public String regitser(User user, Model model) {
		
		model.addAttribute("user", userService.register(user));
		//重定向   return "redirct:index";
		//页面转发(共享request) return "forward:index";
		return "index";
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
 * 		两种注入的方式: 1)<mvc:annotation-driven conversion-service="conversionService"/>
 * 					   2)<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter" p:webBindingInitializer="customBinder"/> <bean id="customBinder" class="org.springframework.web.bind.support.ConfigurableWebBindingInitializer" p:conversionService="conversionService"/>
 */
