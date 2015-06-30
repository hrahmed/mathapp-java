<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<H1>MathApp Login Error <img src = "pi.jpg" width="50" height="50" align="bottom" /></H1>
<hr>
<font size='4' color='red'>
  The username and password you supplied are not valid.
</p>
Click <a href='<%= response.encodeURL("operation.jsp") %>'>here</a> 
to retry login
</body>
</html>