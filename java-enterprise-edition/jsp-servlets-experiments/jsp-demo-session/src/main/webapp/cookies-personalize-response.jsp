<html>
<head>
	<title>
		Confirmation
	</title>

</head>
<%	
	// Read form data
	String favLang = request.getParameter("favoriteLanguage");

	// Create the cookie
	Cookie theCookie = new Cookie("myApp.favoriteLanguage", favLang);
	
	// Set the life span
	theCookie.setMaxAge(60*60*24*365);
	
	// send cookie to browser
	response.addCookie(theCookie);
%>

<body>
	Thanks! We have set your favorite language to: ${param.favoriteLanguage}
	
	<br/> <br/>
	<a href="cookies-homepage.jsp">Return to homepage</a>
</body>
</html>