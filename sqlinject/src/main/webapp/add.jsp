<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<form action="add" method="post">
		<input name="username" placeholder="用户名" />
		<input type="password" name="password" placeholder="密码" />
		<button>登录</button>
		<button type="button" onclick="javascript:window.location.href='index.jsp';">返回</button>
	</form>
<ul>
<%
java.util.List<com.noisyle.demo.sqlinject.entity.User> users = (java.util.List<com.noisyle.demo.sqlinject.entity.User>) request.getAttribute("users");
for(com.noisyle.demo.sqlinject.entity.User user : users){
	out.println("<li>" + user.getUsername() + "</li>");
}
%>
</ul>
</body>
</html>