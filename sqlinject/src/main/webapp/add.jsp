<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<button>新增</button>
		<button type="button" onclick="javascript:window.location.href='${pageContext.request.contextPath}';">返回</button>
	</form>
<ul>
<c:forEach var="user" items="${users}" varStatus="status">
	<li>${status.count}:&nbsp;${user.username}</li>
</c:forEach>
</ul>
</body>
</html>