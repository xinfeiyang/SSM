<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试请求转发</title>
</head>
<body>
	<h2>Forward:请求转发</h2>
	<h2>${username}</h2>
	<form action="/convert" method="post">
		<input type="text" name="username" /><br/>
		<input type="password" name="password" /><br/>
		<c:forEach items="${list}" var="item" varStatus="status">
			<input type="text" value="${item}" name="pics[${status.index}]" /><br/>
		</c:forEach>
		<input type="submit" name="Submit" /><br/>
	</form>
</body>
</html>