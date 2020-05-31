<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:page>
	<jsp:attribute name="title">My Tasks</jsp:attribute>
	<jsp:body>
		<h1><c:out value="${user.name}"/>'s Tasks</h1>
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
					<td>${task.complete ? "✔" : "---"}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<a href="/add-task">Add a Task</a> <a class="btn btn-info" href="/task-edit">Edit</a>
	</jsp:body>
</t:page>
