<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="zh-CN">
<body>
	<form id="form" action="demo">
		Request Parameter: <br />
		<textarea name="param" style="width:500px;height:200px;"><script>alert('hello world!');</script><img src='https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png' /></textarea>
		<br /><button>Submit</button>
		<button type='button' onclick='window.history.back()'>Back</button>
	</form>
</body>
</html>