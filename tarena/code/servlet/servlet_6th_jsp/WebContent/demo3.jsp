<%@page import="java.io.File"%>
<%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>JSP 的九大隐式对象</h3>
	<% String ip = request.getRemoteAddr(); %>
	<% session.setAttribute("name", "张飞"); %>
	
	<h3>JSP 九大隐式对象 -- pageContext</h3>
	<!-- pageContext 对象可以获取其他八个隐式对象 -->
	<% pageContext.getRequest().setAttribute("username", "刘德华"); %>
	当前WEB 应用的虚拟路径: ${ pageContext.request.contextPath }
	<br />
	<%= request.getContextPath() %>
</body>
</html>