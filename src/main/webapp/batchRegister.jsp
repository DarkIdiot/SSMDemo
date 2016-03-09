<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<body>
	<center>
		<h1>Hello World!我们都是好孩子</h1>
	</center>
	<form action="user/addListUser" method="post">
		<table width="400" border="1" align="center">

			<tr>
				<td colspan="2">注册窗口</td>
			</tr>
			<tr>
				<td>ID</td>
				<td><input type="text" name="users[0].id" size="30"></td>
			</tr>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="users[0].username" size="30"></td>
			</tr>

			<tr>
				<td>密码</td>
				<td><input type="password" name="users[0].password" size="30"></td>
			</tr>
			<tr>
				<td>出生日期</td>
				<td><input type="text" name="users[0].birthday" size="30"></td>
			</tr>
			<tr>
			<td colspan="2"><hr /></td>
			</tr>
			<tr>
				<td>ID</td>
				<td><input type="text" name="users[1].id" size="30"></td>
			</tr>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="users[1].username" size="30"></td>
			</tr>

			<tr>
				<td>密码</td>
				<td><input type="password" name="users[1].password" size="30"></td>
			</tr>
			<tr>
				<td>出生日期</td>
				<td><input type="text" name="users[1].birthday" size="30"></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit"
					name="submit" value="注册"></td>
			</tr>
		</table>
	</form>
	<hr />
		<form action="user/addMapUser" method="post">
		<table width="400" border="1" align="center">
			<tr>
				<td colspan="2">注册窗口</td>
			</tr>
			<tr>
				<td>ID</td>
				<td><input type="text" name="userMap[test].id" size="30"></td>
			</tr>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="userMap[test].username" size="30"></td>
			</tr>

			<tr>
				<td>密码</td>
				<td><input type="password" name="userMap[test].password" size="30"></td>
			</tr>
			<tr>
				<td>出生日期</td>
				<td><input type="text" name="userMap[test].birthday" size="30"></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit"
					name="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</body>
</html>
