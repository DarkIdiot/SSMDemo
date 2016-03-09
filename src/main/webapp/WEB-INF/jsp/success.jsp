<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>success</title>
</head>
<body>
  	<center><h1>success</h1></center>
    <center>返回值:${result}</center>
    <c:forEach items="${ map }" var="item">
    	<center><c:out value="${item.key }"/> : <c:out value="${ item.value }"></c:out></center><br/>
    </c:forEach>
    <center><c:out value="${user.id }"/> : <c:out value="${ user.username }"></c:out>:<c:out value="${ user.password }"></c:out></center><br/>
</body>
</html>