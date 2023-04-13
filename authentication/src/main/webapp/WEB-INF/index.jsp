<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>Login and Registration</title>
</head>
<body>
	<div class="container">
		<div class="row">
				<h1 style="color:rgb(153,0,255)">Welcome!</h1>
				<p>Join our growing community.</p>
		</div>
		<div class="row">
			<div class="col-6">
				<h1>Register</h1>
				<form:form action="/register" method="POST" modelAttribute="newUser" class="form">
					<div class="form-group mb-3">
						<form:label path="userName" class="form-label">User Name: </form:label>
						<form:errors path="userName" class="text-danger"/>
						<form:input path="userName" type="text" class="form-control"/>
					</div>
					<div class="form-group mb-3">
						<form:label path="email" class="form-label">Email: </form:label>
						<form:errors path="email" class="text-danger"/>
						<form:input path="email" type="text" class="form-control"/>
					</div>
					<div class="form-group mb-3">
						<form:label path="password" class="form-label">Password: </form:label>
						<form:errors path="password" class="text-danger"/>
						<form:input path="password" type="password" class="form-control"/>
					</div>
					<div class="form-group mb-3">
						<form:label path="confirm" class="form-label">Confirm PW: </form:label>
						<form:errors path="confirm" class="text-danger"/>
						<form:input path="confirm" type="password" class="form-control"/>
					</div>
					<input type="submit" value="Submit" class="btn btn-primary float-end">
				</form:form>
			</div>
			<div class="col-6">
				<h1>Login</h1>
				<form:form action="/login" method="POST" modelAttribute="newLogin" class="form">
					<div class="form-group mb-3">
						<form:label path="email" class="form-label">Email: </form:label>
						<form:errors path="email" class="text-danger"/>
						<form:input path="email" type="text" class="form-control"/>
					</div>
					<div class="form-group mb-3">
						<form:label path="password" class="form-label">Password: </form:label>
						<form:errors path="password" class="text-danger"/>
						<form:input path="password" type="password" class="form-control"/>
					</div>
					<input type="submit" value="Submit" class="btn btn-primary float-end">
				</form:form>
			</div>
		</div>
			
	</div>
</body>
</html>