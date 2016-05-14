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
	 * 请求JSON,输出JSON 
	 */
	//@RequestBody 将请求信息json串转换为对应的pojo对象.(适用于pojo类型的参数,对于简单类型无法绑定成功)
	//@ResponseBody 将pojo转换为json串
	@RequestMapping("/test1")
	public @ResponseBody User jsonTest1(@RequestBody User user){
		return  user;
	}
	
	/**
	 * 请求Key/Value,输出JSON 
	 */
	//@ResponseBody 将pojo转换为json串
	@RequestMapping("/test2")
	public @ResponseBody User jsonTest2(int id){
		return us.getUser(id);
	}
}
/**
 * 1.SpringMVC中使用jackson的包进行json转换(@requestBody和@responseBody使用jackson-mapper-asl,jackson-core-asl完成json转换)
 * 2.需要配置JSON转换器:<mvc:annotation-driven /> 默认包含JSON转换器.
 * 或者在适配器中配置messageConverters,并注入org.springframework.http.converter.json.MappingJacksonHttpMessageConverter.
 * 	eg:
 * 		<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
 *		<property name="messageConverters">
 *			<list>
 *				<bean class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter"/>
 *			</list>
 *		</property>
 *	</bean>
 */
