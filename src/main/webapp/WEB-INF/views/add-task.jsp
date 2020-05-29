<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add a Task Page</title>
</head>
<body>
<h1>Add a Task</h1>
	<form action="/add-task" method="post">
	<label>Description:</label>
	<input type="text" name="description"/>
	<label>Due Date:</label>
	<input type="text" name="dueDate"/>
	<input type="hidden" name="user" value="${user.id}"/>
	<input type="submit"/>
	</form>
</body>
</html>