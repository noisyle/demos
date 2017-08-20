<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tld" uri="http://noisyle.com/demo/tld" %>
<!doctype html>
<html lang="zh-CN">
<body>
<tag:demo></tag:demo>
<br />
<tld:demo id="tld" value="tld_value">tld</tld:demo>
<br />
${tld:sayHello("el")}
</body>
</html>