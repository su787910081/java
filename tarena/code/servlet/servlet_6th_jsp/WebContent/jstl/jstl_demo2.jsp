<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
		<h3>JSTL 标签库 - c:if 标签</h3>
		<!-- test 属性为条件表达式，如果条件为真，则输出yes -->
		<!-- 在JSTL 标签中没有提供else 语句的标签，可以通过对c:if 中的条件取反，来模拟else 语句 -->
		<c:if test="${ 3>5 }" >yes</c:if>
		<c:if test="${ !(3>5) }" >no</c:if>
		<hr />
		
		<h3>JSTL 标签库 - c:forEach 标签</h3>
		<hr />
		<%
			List list = new ArrayList();
			list.add("北京");
			list.add("上海");
			list.add("广州");
			list.add("深圳");
			pageContext.setAttribute("varList", list);
		%>
		<!-- items: 将集合或者数组中的元素取出来遍历 -->
		<!-- 每一次取到的元素用var="element" 变量来保存 -->
		<c:forEach items="${ varList }" var="element">
			${ element }, 
		</c:forEach>
		<hr />
		
		<%
			Map map = new HashMap();
			map.put("name", "周星驰");
			map.put("age", "18");
			pageContext.setAttribute("varMap", map);
		%>
		<c:forEach items="${ varMap }" var="entry">
			${ entry.key } : ${ entry.value }<br />
		</c:forEach>
		<hr />
		遍历从1 到100 之间的奇数，将位置是3 的倍数的数字颜色设置为红色<br />
		<c:forEach begin="1" end="100" step="2" var="i" varStatus="status">
			<%-- 如果当前元素所在的位置是3 的倍数，则设置为红色 --%>
			<c:if test="${ status.count % 3 == 0 }">
				<span style="color:red">${ i }</span>
			</c:if>
			<c:if test="${ !(status.count % 3 == 0) }">
				${ i }
			</c:if>
			<%-- 判断i 是否是最后一个元素，如果是就没有',' --%>
			<c:if test="${ !status.last }">,</c:if>
		</c:forEach>
		
	</body>
</html>