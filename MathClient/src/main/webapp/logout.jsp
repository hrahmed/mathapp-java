<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MathApp Logout</title>
</head>
<body>
<H1>MathApp Logout <img src = "pi.jpg" width="50" height="50" align="bottom" /></H1>
<hr>
<%session.invalidate();%>
You have logged out. Please
<a href="operation.jsp"><b>Login</b></a>
</body>
</html>