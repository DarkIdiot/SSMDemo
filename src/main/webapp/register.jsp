<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<body>
	<center>
		<h1>Hello World!我们都是好孩子</h1>
	</center>
	
	<form  action="user/register.do" method="post">
		<table width="400" border="1" align="center">

			<tr>
				<td colspan="2">注册窗口</td>
			</tr>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="username" size="30"></td>
			</tr>

			<tr>
				<td>密码</td>
				<td><input type="password" name="password" size="30"></td>
			</tr>
			<tr>
				<td>出生日期</td>
				<td><input type="text" name="birthday" size="30"></td>
			</tr>

			<tr>
				<td align="center" colspan="2"><input type="submit" name="submit" value="注册"></td>
			</tr>

		</table>

	</form>
</body>
</html>
