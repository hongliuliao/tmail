<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello world!</title>
<script type="text/javascript" src="js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>
</head>
<body>
	hello world!<br />
	<textarea id="responseArea" cols="40" rows="20"></textarea><br />
	<input type='button' onclick="refreshInfo()" value="刷新" />
	<input type='button' onclick="getRecentMailIntroductions()" value="刷新2" />
	<hr />
	<input type='text' /><br />
	<textarea cols="40" rows="10"></textarea><br />
	<input type='button' value="确定" />
</body>
</html>