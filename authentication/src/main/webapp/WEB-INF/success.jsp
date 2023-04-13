<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>Dashboard</title>
</head>
<body>
	<div class="container">
		<h1>Welcome, <c:out value="${user.userName}"/></h1>
		<p>This is your dashboard. Nothing to see here yet.</p>
		<a href="/logout">logout</a>
	</div>
	
</body>
</html>