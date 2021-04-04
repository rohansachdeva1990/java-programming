
<%@page import="java.util.*"%>

<html>
<body>

	<!-- Step 1: Create HTML form -->
	<form action="todo-demo.jsp">
		Add new item: <input type="text" name="theItem" /> <input
			type="submit" value="Submit" />
	</form>

	<!-- Step 2: Add nw item to "To Do" list -->
	<%
	    // Get the TO DO items from the session 
	    List<String> items = (List<String>) session.getAttribute("myTodoList");

	    // if the TODO items doesn't exist, then create a new one
	    if (items == null) {
	        items = new ArrayList<String>();
	        session.setAttribute("myTodoList", items);
	    }

	    // see if there is form data to add
	    String theItem = request.getParameter("theItem");
	    if (theItem != null) {
	        items.add(theItem);
	    }
	%>

	<!--  Step3: Display all "To Do" items from a session -->
	<hr>
	<b> To List Items:</b>
	<br />

	<ol>
		<%
		    for (String temp : items) {
		        out.println("<li>" + temp + "</li>");
		    }
		%>
	</ol>
</body>
</html>