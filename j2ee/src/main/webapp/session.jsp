<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="zh-CN">
<body>
<%
String sessionId = request.getSession().getId();
response.setHeader("Set-Cookie", "JSESSIONID="+sessionId+"; HttpOnly");
out.write("SessionID: "+sessionId);
%>
</body>
</html>