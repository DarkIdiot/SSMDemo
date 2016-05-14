/**
 * SpringMVC中的处理器拦截器类似于Servlet开发中的过滤器Filter,用于对处理器进行预处理和后处理.
 * 
 * 自定义拦截器:(实现org.springframework.web.servlet.HandlerInterceptor接口)
 * 		拦截器的机制是基于AOP的实现方式.
 */
package com.demo.interceptor;
/** 
 * 1.SpringMVC拦截器针对HandlerMapping进行拦截设置
 * 如果在某个HandlerMapping中配置拦截,经过该HandlerMapping映射成功的handler最终使用该拦截器.(在配置文件中可以同时配置多个HandlerMapping)
 * <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping" >
 *		<property name="interceptors">
 *			<list>
 *				<ref bean="handlerInterceptor1"/>
 * 				<ref bean="handlerInterceptor2"/>
 *			</list>
 *		</property>
 *	</bean>
 *	<bean id="handlerInterceptor1" class="com.demo.interceptor.HandlerInterceptor1"/>
 *	<bean id="handlerInterceptor2" class="com.demo.interceptor.HandlerInterceptor2"/>
 * 
 * 2.SpringMVC可以变相的配置类似于的全局的拦截器，SrpingMVC框架将配置的类似全局的拦截器注入到每个HandlerMapping中.
 * 	<mvc:interceptors>
 *		<mvc:interceptor>
 *			<mvc:mapping path="/**" />
 *			<bean class="com.demo.interceptor.HandlerInterceptor1" />
 *		</mvc:interceptor>
 *		<mvc:interceptor>
 *			<mvc:mapping path="/**" />
 *			<bean class="com.demo.interceptor.HandlerInterceptor2" />
 * 		</mvc:interceptor>
 *	</mvc:interceptors>
 */
