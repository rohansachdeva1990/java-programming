<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP Fundamental - Scriplets test</title>
</head>
	<body>
		<h2>Hello World, using scriplets</h2>
		<%
			for(int i=0 ; i < 5 ; i++){
			    out.println("<br/> I like the number: " + i);
			}
		%>
	</body>
</html>