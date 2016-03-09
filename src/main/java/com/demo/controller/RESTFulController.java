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
	 * {XXX}ռλ��,�����URL�������������,����ͨ���ڷ�����ʹ��@@PathVariable��ȡ{XXX}�е�XXX����.
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
 * RESTFul����URL:1.ʹ��ռλ�����ݱ�Ҫ�Ĳ���;2.���ݲ�ͬ��CRUD����,ʹ�ò�ͬ�����󷽷�����ָ�������HTTP�ķ���
 * 		��׼��get��RESTFul��ʽ����:@RequestMapping(value="/user/{id}",method={RequestMethod.GET})
 */
/**	����ǰ�˿�������url-parttenʹ�� '/'�����ھ�̬��Դ����400����.
 *	  �������: 
 * �Ծ�̬��Դ�Ľ���:(����ʹ��RESTFul����URL,��̬��Դ�ļ�������URL�޷���Ӧһ��handler.) 
 * 		���û���κ�β׺��url��ʹ��spring��DispatcherServlet���й��ˣ���β׺��jsp��ֱ�ӷ��ʣ�������SpringMVC�Ĺ���.
 * 	<mvc:resources location="/js/" mapping="/js/**"/>  ָ����Դ�ļ��Ķ�Ӧӳ���ϵ(���е�ҳ�����õ�/js/**����Դ����/js/������в���)
 */
