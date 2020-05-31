<%@tag description="Page Template" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@attribute name="title" fragment="true"%>

<t:base>
    <jsp:attribute name="title">
        <jsp:invoke fragment="title"/>
    </jsp:attribute>
    <jsp:body>
        <jsp:doBody/>
    </jsp:body>
</t:base>
