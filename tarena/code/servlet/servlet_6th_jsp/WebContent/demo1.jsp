<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>JSP表达式，不能以分号(';')结束</h3>
	<%= "Hello JSP" %>
	<hr />
	<%= new Date().toString() %>
	<hr />
	<%= 100 *123 %>
	<hr />
	
	<h3>JSP 的脚本片段, 必须以分号(';')结束</h3>
	<% 
		String str = "hello jsp"; 
		out.write(str);
	%>
	<hr />
	<!-- 
		需求：在JSP 中通过脚本片段输出5 次hello jsp...
	 -->
	<% for (int i = 0; i < 5; i++) { %>
		hello jsp... <br />
	<% } %>
	<hr />
	
	<h3>JSP 注释</h3>
	下面是注释: <%-- 这是一段注释，但是你看不见。。。 --%>
	<hr />
</body>
</html>