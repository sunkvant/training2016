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

<style>
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background: url(resources/img/bg-body.png) repeat
}

.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}


.bg-danger {
	padding: 10px;
	border-radius: 5px;
	background-color: #f2bebe;
}

.input-lg {

	font-size: 14px;

}
</style>

</head>
<body>
	<div class="container"> 


		<form class="form-signin" action="j_spring_security_check"
			method="POST">
			<h2 class="form-signin-heading">Please sign in</h2>
			<c:if test="${not empty login.failed}">
				<p class="bg-danger">${login.failed}</p>
			</c:if>
			<div class="form-group ">
				<input type="text" class="form-control input-lg" name="user_login"
					placeholder="Enter email or login" required="required">
			</div>
			<div class="form-group">
				<input type="password" class="form-control input-lg" name="user_password"
					placeholder="Password" required="required">
			</div>

			<button type="submit" class="btn btn-lg btn-primary btn-block">Войти</button>
		</form>

	</div>




</body>

</html>