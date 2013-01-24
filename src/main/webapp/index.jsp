<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello world!</title>
<script type="text/javascript" src="js/common/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="js/common/json2.js"></script>
<script type="text/javascript" src="js/common/jquery.cookie.js"></script>
<script type="text/javascript" src="js/common/jquery.jsoncookie.js"></script>

<script type="text/javascript" src="js/main.js"></script>
</head>
<body>
	<form id='loginForm'>
		email:<input id='accountEmail' type='text' name="email" value="" />
		password:<input id='accountPassword' name="password" type='password' />
		<input id='addAccountBtn' type='button' value="添加" >
	</form>
	<div id='accountInfo'></div>
	
	<br />
	<div id="responseArea" style="width=40;rows=20"></div><br />
	Command:<input id='command' type='text' value="list" />
	<input id='sendCommandBtn' type='button' value='确定' /><div id='processArea'></div>
	<hr />
	<input type='text' /><br />
	<textarea cols="40" rows="10"></textarea><br />
	<input type='button' value="确定" />
	
	
</body>
</html>