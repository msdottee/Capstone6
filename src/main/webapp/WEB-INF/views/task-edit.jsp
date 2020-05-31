<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Edit Your Tasks</h1>
		<form action="/task-edit" method="post">
	<table class="table">
		<thead>
			<tr>
				<th>Description</th>
				<th>Due Date</th>
				<th>Complete</th>
				<th>Change Status</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="task" varStatus="status" items="${tasks}">
				<input name="tasks[${status.index}].id" type="hidden" value="${task.id}">
				<tr>
					<td><c:out value="${task.description}" /></td>
					<td><c:out value="${task.dueDate}" /></td>
					<td><c:out value="${task.complete}" /></td>
					<td>
						<label for="task-done">Task Done?</label>
						<input id="task-done" type="checkbox" name="tasks[${status.index}].complete"
							${tasks[status.index].complete ? "checked" : ""}>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
			<button type="submit" class="btn btn-primary">Save Changes</button>
			<a class="btn btn-light" href="/">Cancel</a>
			</form>
	</div>
			<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous"></script>
</body>
</html>