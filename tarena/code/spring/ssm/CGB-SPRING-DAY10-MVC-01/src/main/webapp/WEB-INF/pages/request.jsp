<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>hello requests</h1>
	<ul>
		<li><a href="doSaveObject.do?id=100&content=A">添加对象</a></li>
		<li><a href="doSaveObject02.do?tid=200&content=A">添加对象02</a></li>
		<li><a href="doSaveObject03.do?tid=300&content=object_03">添加对象03</a></li>
		<li><a href="doUpdateObject.do?id=20">修改对象</a></li>
		<li><a href="doDeleteObject/30.do">删除对象</a></li>
		<li><a href="doFindObjects.do?pageCurrent=1">查询对象</a></li>
	</ul>
</body>
</html>