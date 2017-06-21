<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
if(request.getParameter("list")!=null) request.setAttribute("list", request.getParameter("list").split(","));
%>
<!doctype html>
<html>
<body>
	<c:forEach var="item" items="${list}">
	<c:set var="num">${num+1}</c:set>
	<ul style="list-style:none;">
		<li>${num}: 
			<c:choose>
				<c:when test="${item!=null && item!=\"\"}">
					${item}
				</c:when>
				<c:otherwise>
					Anonymous
				</c:otherwise>
			</c:choose>
		</li>
	</ul>
	</c:forEach>
	<form id="form" action="" method="post">
		Names: <input name="list" style="border:1px solid #888;background-color:#f8f8f8;" readonly /><br /><br />
		Name: <input id="name" autofocus />
		<button type="button" onclick="addName()">Add</button>
		<button>Submit</button>
		<button type='button' onclick='window.history.back()'>Back</button>
	</form>
	<script>
	function addName(){
		var list = form.list.value?form.list.value.split(","):[];
		list.push(form.name.value);
		form.list.value = list.join(",");
		form.name.value = "";
		form.name.focus();
	}
	</script>
</body>
</html>