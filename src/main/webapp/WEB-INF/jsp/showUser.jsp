<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>测试</title>
</head>
<body>
	<table width="400" border="1" align="center">
		<tr>
			<td colspan="2">用户窗口</td>
		</tr>
		<c:forEach items="${users }" var="user">
			<tr>
				<td>用户名</td>
				<td><input type="text" name="username"
					value="${user.username }" size="30"></td>
			</tr>

			<tr>
				<td>密码</td>
				<td><input type="text" name="password"
					value="${user.password }" size="30"></td>
			</tr>
			<tr>
				<td>出生日期</td>
				<td><input type="text" name="birthday"
					value="${user.birthday }" size="30"></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>