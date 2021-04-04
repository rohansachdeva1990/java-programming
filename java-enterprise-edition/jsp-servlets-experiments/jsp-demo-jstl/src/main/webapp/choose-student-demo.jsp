<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import = "java.util.*, com.jsp.demo.jstl.Student"%>

<!-- Setting up sample data -->
<%
	List<Student> data = new ArrayList<Student>();
	data.add(new Student("Rohan", "Sachdeva", true));
	data.add(new Student("Rajat", "Sachdeva", false));
	data.add(new Student("Rajpal", "Sachdeva", true));
	
	pageContext.setAttribute("myStudents", data);
%>

<html>
<body>

	<table border="1">
	
	<tr>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Gold Member</th>
	</tr>
	
	<c:forEach var="tempStudent" items = "${myStudents}">
	<tr>
		<td>${tempStudent.firstName}</td>
		<td>${tempStudent.lastName}</td>
		<td>
		
			<c:choose>

				<c:when test="${tempStudent.goldMember}">
					Special Discount
				</c:when>
			
				<c:otherwise>
					No soup for you!!
				</c:otherwise>
			
			</c:choose>
			
		</td>
	</tr>
		
		
	</c:forEach>
		</table>
				
</body>

</html>
