<%@ page language="java" import="java.util.*,autologin.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>Welcome To Issue Manage System</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<!-- 	<form>
		<label>姓名:<input type="text" id="name" /></label><br /> <label>密码:<input
			type="text" id="password" /></label><br /> <input type="checkbox"
			id="remAutoLogin" /><label>两周自动登录</label><br /> <input type="button"
			title="登录" value="登录" />
	</form> -->
	<form action="login">
		用户名：<input type="text" name="username"><br /> 
		密 码：<input type="text" name="password"><br /> 
		<input type="checkbox" name="saveTime" /><label>两周自动登录</label>
		<input type="submit" value="登录" />
	</form>
</body>
</html>
