<%@ page import="java.util.*, com.rohan.mvc.model.*"%>
<!DOCTYPE html>
<html>

<head>
<title>Student Tracker App</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<%
		// Get the students from the request object (sent by the servlet)
		Object o = request.getAttribute("student_list");
		List<Student> theStudents = (List<Student>) o;
%>
<body>

	<div id="warpper">
		<div id="header">
			<h2>WoW University</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<table>
				<tr>
					<th>First name</th>
					<th>Last name</th>
					<th>Email</th>
				</tr>

				<%for (Student tempStudent : theStudents) { %>
					<tr>
						<td><% out.println(tempStudent.getFirstName()); %> </td>
						<td><%= tempStudent.getLastName()  %> </td>
						<td><%= tempStudent.getEmail() %> </td>
					</tr>
				<% }%>
			</table>

		</div>
	</div>

</body>
</html>