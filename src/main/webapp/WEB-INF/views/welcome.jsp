<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:page>
    <jsp:attribute name="title">Welcome</jsp:attribute>
    <jsp:body>
        <h1>Welcome <c:out value="${user.name}"/>!</h1>
        <a href="/my-tasks">To Do List</a> <a href="/logout">Logout</a>
    </jsp:body>
</t:page>
