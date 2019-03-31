<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>message manager</h1>
	<h2><font color="red">${msg}</font></h2>
	<form action="msg" method="post">
		<textarea name="content" rows="5" cols="50"></textarea> <br />
		<input type="submit" value="save"></input>
	</form>
</body>
</html>


