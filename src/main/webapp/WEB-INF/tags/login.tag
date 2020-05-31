<%@tag description="Login Page Template" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@attribute name="name" fragment="true"%>
<%@attribute name="title" fragment="true"%>

<t:base>
    <jsp:attribute name="title">
        <jsp:invoke fragment="title"/>
    </jsp:attribute>
    <jsp:body>
        <main class="login container">

            <article class="card mx-auto">

                <section class="card-header">

                    <h1><jsp:invoke fragment="name"/></h1>

                </section>

                <section class="card-body">

                    <c:if test="${null != message}">
                        <div class="alert alert-danger" role="alert">
                                ${message}
                        </div>
                    </c:if>

                    <jsp:doBody/>

                </section>

            </article>

        </main>
    </jsp:body>
</t:base>
