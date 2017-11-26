<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>页面登录</title>
</head>
<body>
	<form action="/login" method="post">
		<input type="text" name="username" id="username"/><br/>
		<input type="checkbox" name="remember" id="rememberme"/>记住我<br/>
		<input type="submit" value="LOGIN"/>
	</form>
	<br/>
	<h3>${applicationScope.user.name}:${user.age}</h3>
	
	<script type="text/javascript" src="/resource/js/jquery.js"></script>
	<script type="text/javascript" src="/resource/js/jquery.cookie.js"></script>
	<script type="text/javascript">
		$(function(){
			var COOLIE_NAME="username";
			if($.cookie(COOLIE_NAME)){
				$("#username").val($.cookie(COOLIE_NAME));
				$("#rememberme").attr("checked","checked");
			}
			
			$("#rememberme").click(function(){
				if(this.checked){
					$.cookie(COOLIE_NAME,$("#username").val(),{path:'/',expires:10});
				}else{
					$.cookie(COOLIE_NAME,null,{path:'/'});
				}
			});
		});
	</script>
</body>
</html>