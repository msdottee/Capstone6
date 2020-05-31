<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:login>
	<jsp:attribute name="title">Login</jsp:attribute>
	<jsp:attribute name="name">Login</jsp:attribute>
	<jsp:body>
		<form action="/login-submit">

			<div class="form-group">
				<label>Email:</label> <input class="form-control"
											 name="email" type="text" required />

			</div>

			<div class="form-group">
				<label>Password:</label> <input class="form-control"
												name="password" type="password" required />
			</div>

			<button class="btn btn-block btn-secondary">Login</button>

		</form>

		<a href="/sign-up">Sign up</a>
	</jsp:body>
</t:login>
