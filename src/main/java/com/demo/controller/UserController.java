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
 * @date 2016��3��7�� ����2:45:48
 */
@Controller
@RequestMapping("/user")   //խ������·��
public class UserController {
	@Autowired
	UserService userService;
	
	
	//����ʹ��method�����޶�http����ķ���.     //����http���󷽷�
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
		//�ض���   return "redirct:index";
		//ҳ��ת��(����request) return "forward:index";
		return "index";
	}
	
	
	@RequestMapping("/logout")
	public void logout(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		/*
		 * ҵ���߼�
		 */
		session.invalidate();
		
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
/** 1.����ModelAndView:
 * 	��Ҫ��������ʱ,����ModelAndView,��model��view�ֱ��������.
 * 	2.����String:
 * 	���controller��������string,���ʾ�����߼���ͼ��(֧���ض���,ת��).������ͼ(·��)=ǰ׺+�߼���ͼ��+��׺;ʹ���β��е�Model��model���ݷ��ظ���ͼ.
 * 	3.����void
 */
/**	SpringMVC���մӿͷ���(ҳ��)����key/value����,����������,��key/value���ݰ󶨵�controller�������β���.(������������struts2����controller���ж����Ա��������.)
 * 1.����������������SpringMVC�ṩ���������,��key/value����ת��controller�������β�.
 * 2.���������:��SpingMVC���ڰ汾ʹ��PropertyEditor(ֻ�ܽ��ַ�������java����);����ʹ��converter(�����������͵�ת��)
 * 		SpringMVC�ṩ�˺ܶ�converter(ת����),���������(�������ݵȵ�)����Ҫ�Զ���converter.
 * 3.������Ĭ��֧�ֵ�����:ֱ����controller�����β��϶�������Ͷ���,�Ϳ���ֱ��ʹ����Щ����,�ڲ����󶨵Ĺ�����,�������Ĭ��֧�ֵ����;�ֱ�ӽ��а�.
 *    HttpServletRequest(ͨ��request�����ȡ������Ϣ),HttpServletResponse(ͨ��response������Ӧ��Ϣ),HttpSession(ͨ��session����õ�session�д�ŵ�����),Model/ModelMap(ModelMap��Model�ӿڵ�ʵ����,ͨ��Model��ModelMap��ҳ�洫������)
 * 4.@RequestParam�Լ����͵Ĳ��ް�.�����ʹ��@RequestParam,Ҫ��request����Ĳ������ƺ�controller�������β�����һ��,���ܰ󶨳ɹ�.ʹ��@RequestParam��������request����������ƺ�controller�������β�����һ��.
 *    ��ע�������required=trueָ���������봫��,���򷵻�400����;����defaultValue����Ĭ��ֵ,û�д��������ʹ��Ĭ��ֵ���βΰ�.
 * 5.��pojo:�ᰴ����������� POJO �����������Զ�ƥ�䣬�Զ�Ϊ�ö����������ֵ��֧�ּ������ԡ�
 * 	  post����:��web.xml����ӱ���Ĺ�����org.springframework.web.filter.CharacterEncodingFilter,��ָ��ΪUTF-8�ַ���
 *    get����:1)�޸�tomcat�������ļ�,ʹ�������͹���ʹ�õı����ַ���һ��;server.xml <Connector port="8090" protocol="HTTP/1.1" maxThreads="150" connectionTimeout="20000" redirectPort="8443" URIEncoding="utf-8"/>  
 *    		  2)new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8"),�ֶ�����ɱ����ַ�����ת��;
 * 6.�Զ��������:��Ҫʵ��org.springframework.core.convert.converter.Converter�ӿڲ�ע�ᵽorg.springframework.format.support.FormattingConversionServiceFactoryBean�У����Ұ��Զ���Ĳ��������ע�ᵽHandlerAdapter����.
 * 		����ע��ķ�ʽ: 1)<mvc:annotation-driven conversion-service="conversionService"/>
 * 					   2)<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter" p:webBindingInitializer="customBinder"/> <bean id="customBinder" class="org.springframework.web.bind.support.ConfigurableWebBindingInitializer" p:conversionService="conversionService"/>
 */
