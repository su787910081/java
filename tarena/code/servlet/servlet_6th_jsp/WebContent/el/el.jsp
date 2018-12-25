<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h3>(1). EL 可以获取常量/变量(必须存入域中)/表达式的值</h3>
		${ "Hello EL..." }
		<hr />
		<%
			String strName = "周润发";
			/* 需要将name 变量存入域中，才可以通过EL 来获取 */
			pageContext.setAttribute("name", strName);
		%>
		<!-- 到四大域中去查找，域越小优先级越高 -->
		${ name }
		<hr />
		${ 123 + 321 }
		<hr />
		
		<h3>(2). EL 可以获取域中的数组或集合中的数据</h3>
		<%
			String[] arrNames = {"陈子枢", "王海涛", "花倩"};
			request.setAttribute("names", arrNames);
		%>
		${ names[0] }, ${ names[1] }, ${ names[2] }
		<!-- 获取域中集合中的数据和获取数组的方式相同，这里不再举例 -->
		<hr />
		<h3>(3) EL 可以获取域中的map 集合中的数据</h3>
		<%
			Map map = new HashMap();
			map.put("name", "林青霞");
			map.put("age", 18);
			request.setAttribute("mm", map); 
		%>
		${ mm.name }
		${ mm.age }
	</body>
</html>