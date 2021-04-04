<html>
	<head>
	
		<title>
			Home Page
		</title>
	
	</head>
	<body>
		<h3>Training Portal</h3>
		
<!-- Read the favorite programming language cookie -->		
		<%
			// Default.. if no cookies - Declared!!!!!
			String favLang= "Java";
			
			// Get the cookies from the browser request
			Cookie[] theCookies = request.getCookies();
			
			// find our favorite language cookie
			if(null != theCookies){
	
			    for (Cookie tempCookie : theCookies){
			        if("myApp.favoriteLanguage".equals(tempCookie.getName())) {
			            favLang = tempCookie.getValue();
			            break;
			        }		        
			    }
			}
			
		%>
		<br/> <br/>
		<!-- Now show a personalized page, based on this informat -->
		<h4>New Books for <%= favLang %>  </h4>
		<ul>
			<li>blah blah blah</li>
			<li>blah blah blah</li>
			<li>blah blah blah</li>
		</ul>

		<br/> <br/>
		<h4>Latest News report for <%= favLang %>  </h4>
		<ul>
			<li>blah blah blah</li>
			<li>blah blah blah</li>
			<li>blah blah blah</li>
		</ul>
		
		<br/> <br/>
		<h4>Hot Jobs for <%= favLang %>  </h4>
		<ul>
			<li>blah blah blah</li>
			<li>blah blah blah</li>
			<li>blah blah blah</li>
		</ul>
		
		<br/> 
		<hr>
		<a href="cookies-personalize-form.html">Personalize this page</a>
		
	</body>
</html>