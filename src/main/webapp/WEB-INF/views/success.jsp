<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询用户列表</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/queryByCondition" method="post">
		查询条件:
		<table width="100%" border=1>
			<tr>
				<td><input type="text" name="condition"/></td>
				<td><input type="submit" value="查询" /></td>
			</tr>
		</table>
		商品列表：
		<table width="100%" border=1>
			<tr>
				<td>名称</td>
				<td>性别</td>
				<td>年龄</td>
				<td>生日</td>
				<td>部门</td>
				<td>编辑</td>
			</tr>
			<c:forEach items="${list}" var="user">
				<tr>
					<td>${user.name}</td>
					<td>${user.gender}</td>
					<td>${user.age}</td>
					<%-- <td><fmt:formatDate value="${user.birth}" pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
					<td>${user.birth}</td>
					<td>${user.department.deptname}</td>
					<td><a href="${pageContext.request.contextPath}/useredit/${user.id}">修改</a></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<a href="/sendEmail">发送邮件</a><br/>
	
	<form action="/file/upload" method="post" enctype="multipart/form-data">
		<input type="file" name="image"/><br/>
		<input type="submit" value="上传文件"/>
	</form>
	
	<script type="text/javascript" src="/resource/js/jquery.js"></script>
	<script type="text/javascript" src="/resource/js/jquery.ocupload-1.1.2.js"></script>
	<script type="text/javascript">
		$(function(){
			
		});
	</script>
</body>

</html>