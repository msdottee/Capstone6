<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:page>
	<jsp:attribute name="title">Edit Task</jsp:attribute>
	<jsp:body>
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
							<td>${task.description}</td>
							<td>${task.dueDate}</td>
							<td>${task.complete ? "✔" : "---"}</td>
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
	</jsp:body>
</t:page>
