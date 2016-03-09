/**
 * 全局异常处理器
 * 	思路:系统遇到异常,在程序中手动抛出,dao抛给service,service抛给controller，controller抛给前端前端控制器,前端控制器调用全局异常处理器.
 * 全局处理器思路:	1.如果该异常类型是系统自定义异常,直接取出异常欣喜,并在错误页面展示.
 * 					2.如果该异常类型不是系统自定义异常,则需要构造一个自定义异常(信息为"未知错误").
 */
package com.demo.exception;