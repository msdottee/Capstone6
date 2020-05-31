<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:page>
	<jsp:attribute name="title">Add Task</jsp:attribute>
	<jsp:body>
		<h1>Add a Task</h1>
		<form action="/add-task" method="post">
			<label>Description:</label>
			<input type="text" name="description"/>
			<label>Due Date:</label>
			<input type="text" name="dueDate"/>
			<input type="hidden" name="user" value="${user.id}"/>
			<input type="submit"/>
		</form>
	</jsp:body>
</t:page>
