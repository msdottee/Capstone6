<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:login>
	<jsp:attribute name="title">Sign-Up</jsp:attribute>
	<jsp:attribute name="name">Sign-Up</jsp:attribute>
	<jsp:body>
		<form action="/signup-submit" method="post">

			<div class="form-group">
				<label>Name:</label>
				<input class="form-control" name="name" type="text" required />
			</div>

			<div class="form-group">
				<label>Email:</label>
				<input class="form-control" name="email" type="text" required />
			</div>

			<div class="form-group">
				<label>Password</label>
				<input class="form-control" name="password" type="password" required />
			</div>

			<button class="btn btn-block btn-secondary">Sign Me Up</button>

		</form>
	</jsp:body>
</t:login>
