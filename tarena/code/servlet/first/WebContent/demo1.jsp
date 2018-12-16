<%@ page language="java" contentType="text/html;"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<title>Insert title here</title>
</head>
<body>
	<h1><a href="/first/RequestDemo6">查看个人资源</a></h1>
	姓名: <%= request.getAttribute("name") %><br />
	姓名: ${name} <br />
	昵称: ${nickname} <br />
	年龄: ${age} <br />
</body>
</html>

