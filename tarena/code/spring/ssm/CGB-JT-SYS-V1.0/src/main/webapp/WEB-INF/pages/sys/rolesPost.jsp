<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function getRequestObject() {
		if (window.XMLHttpRequest) {
			// Opera, Safari, Mozilla, Chrome,Internet Explorer 7, and IE 8.
			return (new XMLHttpRequest());
		} else if (window.ActiveXObject) {
			// Version for Internet Explorer 5.5 and 6
			return (new ActiveXObject("Microsoft.XMLHTTP"));
		} else {
			//Fails on older and nonstandard browsers.
			return (null);
		}
	}

	function doFundPageObjects() {
		// 1. 获取ajax 请求对象
		var request = getRequestObject();
		// 2. 发送请求
		// 2.1 设置响应处理函数
		request.onreadystatechange = function() {
			// 3. 处理响应，异步刷新
			handleResponse(request);
		};
		// 2.2 初始化请求参数
		// get 请求方式
		// 请求URL
		// true: 异步处理  false: 同步处理
		var url = "doFindPageObjects.do";
		var data="pageCurrent=1";
		request.open("POST", url, true);
		// 2.3 发送异步 请求
		request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		request.send(data); // GET 请求此方法中不传参数 
	}
	// 处理ajax 响应
	function handleResponse(request) {
		if (request.readyState == 4 && request.status == 200) {
			alert(request.responseText);
			// console(request.responseText);
			// htmlInsert(resultRegion, request.responseText);
			
			var div = document.getElementById("container");
			div.innerHTML=request.responseText;
		}
	}
</script>
</head>
<body>
	<h1>hello roles</h1>
	<button onclick="doFundPageObjects()">doQuery</button>
	<!-- 将服务端返回的数据呈现在此位置 -->
	<div id="container">
		
	</div>
</body>
</html>

