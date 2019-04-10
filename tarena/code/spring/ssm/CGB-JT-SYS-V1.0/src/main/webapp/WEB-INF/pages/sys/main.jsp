<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/libs/jquery-3.2.1.min.js"></script>
</head>
<body>
	<h1>MAIN</h1>
	<button id="load-role-id" >角色管理</button>
	<!-- 内容呈现区 -->
	<div id="content">
	
	</div>
	
	<script type="text/javascript">
		$(function(){
			console.log("page load ok.");
			$("#load-role-id").click(function() {
				$("#content").load("listUI.do");
			});
		});
	</script>
</body>
</html>

