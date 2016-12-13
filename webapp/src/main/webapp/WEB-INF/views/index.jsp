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
}
</style>

</head>
<body>
	<div class="container">

		<table class="table table-hover table-striped table-bordered">
			<tr>
				<th>Id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Middle Name</th>
				<th width="150px">#</th>
			</tr>
			<%
				for (int i = 0; i < 10; i++) {
			%>
			<tr>
				<td><%=i%></td>
				<td>Бандура</td>
				<td>Антон</td>
				<td>Александрович</td>
				<td><button class="btn btn-info" type="button">View</button>
				<button class="btn btn-danger" type="button">Delete</button>
				</td>
			</tr>
			<%
				}
			%>

		</table>

	</div>




</body>

</html>