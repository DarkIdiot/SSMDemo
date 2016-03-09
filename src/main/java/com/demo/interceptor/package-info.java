/**
 * SpringMVC�еĴ�����������������Servlet�����еĹ�����Filter,���ڶԴ���������Ԥ����ͺ���.
 * 
 * �Զ���������:(ʵ��org.springframework.web.servlet.HandlerInterceptor�ӿ�)
 * 		�������Ļ����ǻ���AOP��ʵ�ַ�ʽ.
 */
package com.demo.interceptor;
/** 
 * 1.SpringMVC���������HandlerMapping������������
 * �����ĳ��HandlerMapping����������,������HandlerMappingӳ��ɹ���handler����ʹ�ø�������.(�������ļ��п���ͬʱ���ö��HandlerMapping)
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
 * 2.SpringMVC���Ա�������������ڵ�ȫ�ֵ���������SrpingMVC��ܽ����õ�����ȫ�ֵ�������ע�뵽ÿ��HandlerMapping��.
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
