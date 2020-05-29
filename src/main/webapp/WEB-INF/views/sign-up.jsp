<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up Page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
</head>
<body>
	<main class="container">

		<article class="card mx-auto">
		
			<section class="card-header">

				<h1>Sign Up</h1>

			</section>
			
			<section class="card-body">

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

			</section>

		</article>

	</main>
</body>
</html>