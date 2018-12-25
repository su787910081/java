<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>POST 提交</h3>
	<form action="${ pageContext.request.contextPath }/ServletDemo3" method="POST">
		用户名: <input type="text" name="username" /><br />
		昵称: <input type="text" name="nickname" /><br />
		<input type="submit" value="POST提交" />
	</form>
	<h3>GET 提交</h3>
	<form action="${ pageContext.request.contextPath }/ServletDemo3" method="GET">
		用户名: <input type="text" name="username" /><br />
		昵称: <input type="text" name="nickname" /><br />
		<input type="submit" value="GET提交" />
	</form>
</body>
</html>
