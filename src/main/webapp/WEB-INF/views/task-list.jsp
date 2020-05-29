<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Task List Page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
</head>
<body>
<h1><c:out value="${user.name}"></c:out>'s Tasks</h1>
	<table class="table">
		<thead>
			<tr>
				<th>Description</th>
				<th>Due Date</th>
				<th>Complete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="task" items="${tasks}">
				<tr>
					<td><c:out value="${task.description}" /></td>
					<td><c:out value="${task.dueDate}" /></td>
					<td><c:out value="${task.complete}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/add-task">Add a Task</a>
</body>
</html>