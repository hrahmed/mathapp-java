<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<title>Math Application Login</title>
</head>
<br>
<body>

<H1>MathApp Login <img src = "pi.jpg" width="50" height="50" align="bottom" /></H1>
<hr>

<form action='j_security_check' method='post'>
<table>
 <tr><td>Username:</td>
   <td><input type='text' name='j_username'></td></tr>
 <tr><td>Password:</td> 
   <td><input type='password' name='j_password'></td>
 </tr>
</table>
<br>
  <td align="center"> 
  <input type='submit' value='login'>
  <input type='reset' value='reset'> 
  </td>
  
</form>

</body>

</html>