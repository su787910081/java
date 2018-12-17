<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表页面</title>
</head>
<body>
	<h1>点击下面的商品将商品加入购物车</h1>
	<hr />
	<a href="${ pageContext.request.contextPath }/BuyServlet?prod=小米手机">小米手机</a><br /><br />
	<a href="${ pageContext.request.contextPath }/BuyServlet?prod=海尔洗衣机">海尔洗衣机</a><br /><br />
	<a href="${ pageContext.request.contextPath }/BuyServlet?prod=阿迪王皮鞋">阿迪王皮鞋</a><br /><br />
	<a href="${ pageContext.request.contextPath }/BuyServlet?prod=IPhone X">IPhone X</a><br /><br />
	<hr />
	<a href="${ pageContext.request.contextPath }/PayServlet">支付</a><br /><br />
	<hr />
</body>
</html>