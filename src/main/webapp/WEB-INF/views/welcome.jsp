<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:page>
    <jsp:attribute name="title">Welcome</jsp:attribute>
    <jsp:body>
        <h1>Welcome <c:out value="${user.name}"/>!</h1>
        <p>
            <a href="/my-tasks">My Tasks</a>
        </p>
        <p>
            <img src="/assets/img/pusheencar.gif" class="img-fluid" alt="Get Stuff Done!">
        </p>
    </jsp:body>
</t:page>
