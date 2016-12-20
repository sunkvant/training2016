<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<html>
<head>
<title>Login</title>
<spring:url value="resources/css/bootstrap.css" var="bootstrap" />
<link href="${bootstrap}" rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<style>
body {
	background: url(resources/img/bg-body.png) repeat
}

.container {
	max-width: 950px;
}

.header {
	height: 70px;
	margin-bottom: 30px;
	border-bottom: 1px solid #bfb7b7;
	background: url("resources/img/bg-header.png") repeat-x;
	padding-top: 15px;
}

.card-block {
	height: 195px;
	margin-left: 50px;
	border-radius: 10px;
	background-image: url("resources/img/bg_creditcard-blue.jpg");
	background-position: right top;
	background-repeat: no-repeat;
	box-shadow: 3px -4px 17px -8px #000000;
}
</style>

</head>
<body>

	<div class="header">

		<div class="container">
			<ul class="nav nav-pills pull-right">
				<li class="active"><a href="/admin">Home</a></li>
				<li><a href="j_spring_security_logout">Exit</a></li>
			</ul>
			<h5>Welcome, ${currentUser.firstName} ${currentUser.lastName}
				${currentUser.middleName}</h5>
		</div>

	</div>

	<div class="container">


		<table class="table table-hover table-condensed">


			<tr>
				<th>Id</th>
				<th>Username</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th width="150px">#</th>
			</tr>
			<c:forEach var="user" items="${listUsers}">
				<tr>
					<td>${user.id}</td>
					<td>${user.login}</td>
					<td>${user.firstName}</td>
					<td>${user.middleName}</td>
					<td><button class="btn btn-info btn-sm" type="button">View</button>
						<button class="btn btn-danger btn-sm" type="button">Delete</button>
					</td>
				</tr>
			</c:forEach>
		</table>

	</div>




</body>

</html>