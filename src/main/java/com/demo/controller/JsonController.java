package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.model.User;
import com.demo.service.UserService;

@Controller
@RequestMapping("/json")
public class JsonController {
	@Autowired
	private UserService us;
	
	/**
	 * ����JSON,���JSON 
	 */
	//@RequestBody ��������Ϣjson��ת��Ϊ��Ӧ��pojo����.(������pojo���͵Ĳ���,���ڼ������޷��󶨳ɹ�)
	//@ResponseBody ��pojoת��Ϊjson��
	@RequestMapping("/test1")
	public @ResponseBody User jsonTest1(@RequestBody User user){
		return  user;
	}
	
	/**
	 * ����Key/Value,���JSON 
	 */
	//@ResponseBody ��pojoת��Ϊjson��
	@RequestMapping("/test2")
	public @ResponseBody User jsonTest2(int id){
		return us.getUser(id);
	}
}
/**
 * 1.SpringMVC��ʹ��jackson�İ�����jsonת��(@requestBody��@responseBodyʹ��jackson-mapper-asl,jackson-core-asl���jsonת��)
 * 2.��Ҫ����JSONת����:<mvc:annotation-driven /> Ĭ�ϰ���JSONת����.
 * ������������������messageConverters,��ע��org.springframework.http.converter.json.MappingJacksonHttpMessageConverter.
 * 	eg:
 * 		<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
 *		<property name="messageConverters">
 *			<list>
 *				<bean class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter"/>
 *			</list>
 *		</property>
 *	</bean>
 */
