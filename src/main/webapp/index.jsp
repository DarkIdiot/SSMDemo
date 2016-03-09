<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
<script type="text/javascript">
	function test1(){
		var name = $("#userId1").val();
		$.ajax({type:'post',
			url:'json/test1',
			contentType:'application/json;charset=utf-8',
			data:'{"username":"'+name+'","password":"123"}',
			success:function(data){
				$("#userId1").val(data);
				}
			})
		}
	function test2(){
		var id = $("#userId2").val();
		$.ajax({type:'post',
			url:'json/test2',
			contentType:'application/x-www-form-urlencoded;charset=utf-8',
			data:'id='+id,
			success:function(data){
				$("#userId2").val(data);
				}
			})
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
</head>
<body>
	<form action="user/login" method="post">
		<center><h1>登陆界面</h1></center>
		<center><h1>name:<input type="text" name="username"></h1></center>
		<center><h1>password:<input type="password" name="password"></h1></center> 
		<center><input type="submit" value="确认"></center>
	</form>
	<hr>
	<input type="button" value="SystemException" onclick="javascrtpt:window.location.href='user/customException'">
	<input type="button" value="RuntimeException" onclick="javascrtpt:window.location.href='user/runtimeException'">
	<input type="button" value="RESTFul_style1" onclick="javascrtpt:window.location.href='restful/user/1'">
	<hr>
	<form action="upload/img" method="post" enctype="multipart/form-data">
		<center><h1>上传图片</h1></center>
		<center><h1>请选择图片:<input type="file" name="pic"></h1></center>
		<center><input type="submit" value="确认"></center>
	</form>
	<hr>
		<center><h1>查询用户</h1></center>
		<center><h1>ID: <input type="text" name="json" id="userId1"><input type="submit" value="确认" onclick="test1()"></h1></center>
		<center><h1>ID: <input type="text" name="json" id="userId2"><input type="submit" value="确认" onclick="test2()"></h1></center>
</body>
</html>