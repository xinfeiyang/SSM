<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户信息</title>

</head>
<body>

	<form action="${pageContext.request.contextPath}/update" method="post">
		<input type="hidden" name="id" value="${user.id}" /> 修改用户信息:
		<table width="100%" border=1>
			<tr>
				<td>姓名</td>
				<td><input type="text" name="name" value="${user.name }" /></td>
			</tr>
			<tr>
				<td>性别</td>
				<td><input type="text" name="gender" value="${user.gender}" /></td>
			</tr>
			<tr>
				<td>年龄</td>
				<td><input type="text" name="age" value="${user.age}" /></td>
			</tr>
			<tr>
				<td>生日</td>
				<%-- <td><input type="text" name="birth" value="<fmt:formatDate value='${user.birth}' pattern='yyyy-MM-dd HH:mm:ss' />" /></td> --%>
				<td><input type="text" name="birth" value="${user.birth}" /></td>
			</tr>
			<tr>
				<td>部门</td>
				<td><select name="department.id">
						<option>选择部门</option>
						<c:forEach items="${departments}" var="dept">
							<c:if test="${dept.deptname==user.department.deptname}">
								<option value=${dept.id } selected="selected">${dept.deptname}</option>
							</c:if>
							<c:if test="${dept.deptname!=user.department.deptname}">
								<option value=${dept.id}>${dept.deptname}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><input type="submit" value="提交" />
				</td>
			</tr>
		</table>

	</form>
</body>

</html>