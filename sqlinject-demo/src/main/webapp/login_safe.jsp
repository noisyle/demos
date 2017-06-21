<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<form action="login_safe" method="post">
		<input name="username" placeholder="用户名" />
		<input type="password" name="password" placeholder="密码" />
		<button>登录</button>
		<button type="button" onclick="javascript:window.location.href='/sqlinjectdemo';">返回</button>
	</form>
	<c:choose>
		<c:when test="${user != null}">
			<h3>登陆成功</h3>
			<dl>
				<dt>ID:</dt>
				<dd>${user.id}</dd>
				<dt>用户名:</dt>
				<dd>${user.username}</dd>
				<dt>邮箱:</dt>
				<dd>${user.email}</dd>
			</dl>
		</c:when>
		<c:otherwise>
			<h3>请输入正确的用户名和密码</h3>
		</c:otherwise>
	</c:choose>
</body>
</html>