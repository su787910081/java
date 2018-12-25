<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 导入相关的包 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h3>JSTL 标签库 - c:set 标签</h3>
			(1) 将属性存入某一个作用域
			<hr />
			<%-- var 属性用来指定将要存入域中的属性的属性名 --%>
			<%-- value 属性用来指定对应属性名中存入的属性值 --%>
			<%-- scope 属性用来指定该值存入到哪一个域对象中，request、response、session、page(pageContext)、application --%>
			<c:set var="name" value="张飞" scope="page"></c:set>
			${ name }
			
			<hr />
			(2) 修改作用域中已有的属性值
			<hr />
			<c:set var="name" value="刘备" scope="page" />
			${ name }
			<hr />
			(3) 修改作用域中map 集合中的属性
			<hr />
			<%
				Map map = new HashMap();
				map.put("name", "周星驰");
				map.put("age", "18");
				pageContext.setAttribute("varMap", map);
			%>
			${ varMap.name }
			${ varMap.age }
			<hr />
			<!-- 获取map 变量，并指定要修改的哪一个属性(name), 修改为哪一个新的值("周星星") -->
			<c:set target="${ varMap }" property="name" value="周星星" />
			${ varMap.name }
			<hr />
		<h3>JSTL 标签库 - c:if 标签</h3>
		<hr />
		
		<hr />
		<h3>JSTL 标签库 - c:forEach 标签</h3>
	</body>
</html>