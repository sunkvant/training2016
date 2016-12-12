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
</head>
<body>
	<div class="container">
		<form action="j_spring_security_check" method="POST">
			<div class="form-group col-sm-6">
				<label for="exampleInputEmail1">Email or login</label> <input
					type="text" class="form-control" name="user_login"
					placeholder="Enter email or login">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Пароль</label> <input
					type="password" class="form-control" name="user_password"
					placeholder="Password">
			</div>
			<div class="form-group">
				<label for="exampleInputFile">File input</label> <input type="file"
					id="exampleInputFile">
				<p class="help-block">Example block-level help text here.</p>
			</div>
			<div class="checkbox">
				<label> <input type="checkbox"> Проверить меня
				</label>
			</div>
			<button type="submit" class="btn btn-primary">Отправить</button>
		</form>

	</div>




</body>

</html>