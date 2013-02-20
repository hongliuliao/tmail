<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello world!</title>
<script type="text/javascript" data-main="js/main.js" src="js/common/require.js"></script>

<script type="text/html" id="talkdialogTemplate">
	<div class="talkdialog">这是聊天对话框(待建设...)</div>
</script>

<link rel="stylesheet" type="text/css" href="css/talk.css" />
</head>
<body>
	
	<a href='pages/desc.jsp'>应用简介</a>
	<form id='loginForm'>
		email:<input id='accountEmail' type='text' name="email" value="" />
		password:<input id='accountPassword' name="password" type='password' />
		<input id='addAccountBtn' type='button' value="确定" >
		<select id='accountHistorySel'></select>
	</form>
	<div id='accountInfo'></div>
	
	Command:<input id='command' type='text' value="list" />
	<input id='sendCommandBtn' type='button' value='确定' />
	<input id='listBtn' type='button' value='list' />
	<input id='removeBtn' type='button' value='remove' />
	<div id='processArea'></div>
	
	<br />
	<div id="responseArea" style="width=40;rows=20"></div><br />
	<hr />
	收件人:<input id='toText' type='text' /><br />
	主题:<input id='subjectText' type='text' /><br />
	内容:<textarea id="contextTextArea" cols="40" rows="10"></textarea><br />
	<input id='sendMailBtn' type='button' value="确定" />
	
</body>
</html>