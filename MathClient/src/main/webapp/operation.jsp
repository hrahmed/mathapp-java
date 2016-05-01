<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Math Example</title>

</head>
<body>

<%String str = request.getParameter("j_username");

session.setAttribute("UserName", request.getParameter("j_username"));%>


<H1>MathApp Operation  <img src = "pi.jpg" width="50" height="50" align="bottom" /></H1>
<hr>
Welcome  <%session.getAttribute("UserName");%>
<br>
<a href="logout.jsp"><b>Logout</b></a>
<hr>
<hr>
<h3>Simple Java</h3>
<FORM ACTION="/MathClient/MathClientServlet"
      METHOD="POST">
  Operation:
  <SELECT NAME="operation">
	<OPTION VALUE="add">Add
	<OPTION VALUE="subtract">Subtract
	<OPTION VALUE="multiply">Multiply
	<OPTION VALUE="divide">Divide
	<OPTION VALUE="stall">Stall
	<OPTION VALUE="error">Error
  </SELECT>
  <br>
  Value1:
  <INPUT TYPE="TEXT" NAME="value1">
  Value2:
  <INPUT TYPE="TEXT" NAME="value2">
  <BR>
  <INPUT TYPE="SUBMIT" VALUE="Submit">
  <input type="reset" value="Reset" name="reset">
</FORM>
<hr>
<hr>
<h3>Simple Node</h3>
<FORM ACTION="/MathClient/MathNodeServlet"
      METHOD="POST">
  Operation:
  <SELECT NAME="operation">
	<OPTION VALUE="add">Add
	<OPTION VALUE="subtract">Subtract
	<OPTION VALUE="multiply">Multiply
	<OPTION VALUE="divide">Divide
	<OPTION VALUE="sqrt">Square Root
  </SELECT>
  <br>
  Value1:
  <INPUT TYPE="TEXT" NAME="value1">
  Value2:
  <INPUT TYPE="TEXT" NAME="value2">
  <BR>
  <INPUT TYPE="SUBMIT" VALUE="Submit">
  <input type="reset" value="Reset" name="reset">
</FORM>
<hr>
<hr>
<h3>Simple DotNet</h3>
<FORM ACTION="/MathClient/MathDotNetServlet"
      METHOD="POST">
  Operation:
  <SELECT NAME="operation">
	<OPTION VALUE="add">Add
	<OPTION VALUE="multiply">Multiply
  </SELECT>
  <br>
  Value1:
  <INPUT TYPE="TEXT" NAME="value1">
  Value2:
  <INPUT TYPE="TEXT" NAME="value2">
  <BR>
  <INPUT TYPE="SUBMIT" VALUE="Submit">
  <input type="reset" value="Reset" name="reset">
</FORM>
  <hr>
  <hr>
  <h3>Complex</h3>
<FORM ACTION="/MathClient/MathComplexServlet"
      METHOD="POST">
  Operation:
  <SELECT NAME="operation">
	<OPTION VALUE="mean">Mean
	<OPTION VALUE="median">Median
	<OPTION VALUE="mode">Mode
	<OPTION VALUE="python">Python
	<OPTION VALUE="barcode">Barcode
	<OPTION VALUE="flush">Flush
  </SELECT>
  <br>
    Values (comma delimited):
  <INPUT TYPE="TEXT" NAME="values"><BR>
  <INPUT TYPE="SUBMIT" VALUE="Submit">
  <input type="reset" value="Reset" name="reset">
</FORM>
  <hr>
  <hr>
  <h3>Result</h3>
  <%
  if (request.getAttribute("complexresult") != null){
	  out.println("  " + request.getAttribute("oper")
			  + " for (" + request.getAttribute("vs") + ")"
			  + " = " + request.getAttribute("complexresult"));
  } else if (request.getAttribute("result") != null){
	  out.println("  " + request.getAttribute("v1") 
			  + " " + request.getAttribute("oper")
			  + " " + request.getAttribute("v2")
			  + " = " + request.getAttribute("result"));
	  out.println("\nStatus= " + request.getAttribute("status"));
  } else if (request.getAttribute("noderesult") != null) {
	  out.println(request.getAttribute("noderesult"));
	  out.println("\nStatus= " + request.getAttribute("status"));
  }
  %>
  <hr>
  <BR><BR>
</body>
</html>