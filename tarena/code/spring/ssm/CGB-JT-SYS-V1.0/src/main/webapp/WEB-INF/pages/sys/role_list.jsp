<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<table border="1" cellpadding="1" cellspacing="1">
	<thead>
		<tr>
			<th>id</th>
			<th>name</th>
			<th>note</th>
		</tr>
	</thead>

	<tbody id="tabBodyId">
	</tbody>
</table>

<script type="text/javascript">
	$(function(){
		// doGetObjects01();
		// doGetObjects02();
		doPostObjects03();
	});

	function doGetObjects01() {
		// jquery 提供的ajax 函数
		$.ajax({
			url:"doFindPageObjects.do",
			type:"GET",
			dataType:"json",
			data:{"pageCurrent": 1},	// 提交的数据
			success:function(result) {	// result: 用于接收服务端返回的数据
				doSetTBody(result);
			},
			error:function(result) {
				console.log(result);
			}, 
			cache:false
		});
	}
	
	// 借助jquery 的getJSION 函数发起异步GET请求
	function doGetObjects02() {
		var url = "doFindPageObjects.do";
		var data = {"pageCurrent":1};
		$.getJSON(url, data, function(result){
			doSetTBody(result);
		});
	}
	
	// 借助jquery 的getJSION 函数发起异步POST请求
	function doPostObjects03() {
		var url = "doFindPageObjects.do";
		var data = {"pageCurrent":1};
		$.post(url, data, function(result){
			doSetTBody(result);
		});// 表单数据提交 时一般建议使用POST
	}
	
	function doSetTBody(data) {
		// debugger;
		console.log(data);

		var tBody = $("#tabBodyId");

		for (var i in data) {
			var tr = $("<tr></tr>");
			tr.append("<td>" + data[i].id + "</td>");
			tr.append("<td>" + data[i].name + "</td>");
			tr.append("<td>" + data[i].note + "</td>");
			tBody.append(tr);
		}
	}



</script>
