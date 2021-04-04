<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

	<body>
		<h2>Split Demo</h2>
		<br/>
		 <c:set var="data" value="Goku, Vegita, Gohan, Trunks"/> 
		 <c:set var="dbzChars" value = "${fn:split(data, ',')}"/>
		 
		 <c:forEach var="dbzChar" items="${dbzChars}">
			${dbzChar} <br/>
		</c:forEach>
		
		<h2>Join Demo</h2>
		<br/> 
		 <c:set var="joinedDbzChars" value="${fn:join(dbzChars, '*')}"/>	
		 <br/>
		 Result of joined string : ${joinedDbzChars} 
	</body>

</html>