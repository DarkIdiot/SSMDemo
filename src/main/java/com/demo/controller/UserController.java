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
 * @date 2016��3��7�� ����2:45:48
 */
@Controller  //(�ز�����)��ʶ������һ��handler������
@RequestMapping("/user")   //խ������·��
public class UserController {
	@Autowired
	UserService userService;
	
	//	@ModelAttributeע���ʾ���������շ��ص�ֵ����request��,��ʹ��ָ����key
	/**
	 * �˷�����д��UserController��,��ôִ��UserController�ڴ���@RequestMapping�ķ���֮ǰ,
	 * ������ִ�д�getTypes����.����ִ�еĹ����лὫ�β��е�pojo�ͷ����ķ���ֵװ��request��,������keyΪĬ��ֵ��ָ����ֵ.
	 */
	@ModelAttribute("map")
	public Map<String,String> getTypes(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("001","��������Ա");
		map.put("002","��ͨ����Ա");
		map.put("003","��Ա");
		return map;
	}
	
	
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
	/** 
	 * 1.����ҪУ���pojoǰ�����@Validated�Һ������β�BindingResult,����У�������Ϣ.NOTE:1.@Validated��BindingResult�����ʹ�õ�,��˳��̶�.2.ÿһ����ҪУ���pojo����Ҫ��Ӧ������
	 * 2.����У��@Validated(value=ValidationGroup1.class),ָ��ʹ��ValidationGroup1.class�����У��.
	 */
	/**
	 * ���ݻ���:@ModelAttributeָ��pojo���Ե�ҳ��request���е�key;(��ʹ��ע���ʾ����Ĭ��:����������ĸСд)
	 */
	@RequestMapping(value="/register.do",method={RequestMethod.POST})
	public String regitser(@ModelAttribute("user") @Validated(value=ValidationGroup1.class) User user, BindingResult bindingResult,Model model) {
		//У������߼�����
		if (bindingResult.hasErrors()) {
			//�������еĴ�����Ϣ
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for (ObjectError error : allErrors) {
				error.getDefaultMessage();
			}
			model.addAttribute("allErrors", allErrors);
		}
		
		model.addAttribute("user", userService.register(user));
		//�ض���   return "redirect:index";
		//ҳ��ת��(����request) return "forward:index";
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
	 *	����List<POJO> ��Ҫ��װ��VO���棬����ת��Ϊjson��ʽ���ַ�����ʵ���ֶ���ת���� 
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
	 *	����Map<String,POJO> ��Ҫ��װ��VO���棬����ת��Ϊjson��ʽ���ַ�����ʵ���ֶ���ת���� 
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
	
	@RequestMapping("/customException")
	public ModelAndView customException() throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("showUser");
		if (true) {
			throw new SystemException("ϵͳ�ڲ��Զ����쳣");
		}
		return mv;
	}
	
	@RequestMapping("/runtimeException")
	public ModelAndView runtimeException() throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("showUser");
		if (true) {
			throw new RuntimeException("����ʱ�׳���δ֪�쳣");
		}
		return mv;
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
 * 		����ע��ķ�ʽ: 1)<mvc:annotation-driven conversion-service="conversionService"/>(����ע��RequestMappingHandlerAdapter,RequestMappingHandlerMapping,��������400���� syntactically incorrect)
 * 					   2)<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter" p:webBindingInitializer="customBinder"/> <bean id="customBinder" class="org.springframework.web.bind.support.ConfigurableWebBindingInitializer" p:conversionService="conversionService"/>
 */
/**	���ݻ���: SpringMVCĬ��֧�����ݻ���,pojo����Controller�����к�,SpringMVC�Զ���pojo���ݷŵ�request��,keyΪpojo������(����ĸСд);
 * 		1. pojo����:ʹ��@ModelAttribute("key")ע�����Ϊrequest���е�pojoָ��key.
 * 		2. �����͵����ݻ���:ʹ��model�����ݴ��뵽ҳ��.
 */
/** �쳣����������(ExceptionResolver)  SpringMVC�ṩȫ�ֵ��쳣������(һ��ϵͳֻ��һ���쳣������)����ͳһ���쳣����.(Ԥ���쳣��Runtime�쳣)
 */
