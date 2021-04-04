<html>

	<head>
		<title>Student Confirmation Page</title>
	</head>

	<body>
		 The Student is confirmed: ${param.firstName}  ${param.lastName} 
		 <br/><br/>
		 The Student favorite programming languages are: 
		 <ul>
			 <%
				 String langs[] = request.getParameterValues("programmingLanguage");
				 for (String lang : langs){
				     out.println("<li>"+ lang + "</li>");
				 }
			 %>
		 </ul>
	</body>
</html>