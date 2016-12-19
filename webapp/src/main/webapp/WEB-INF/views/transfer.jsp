<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<title>Login</title>
<spring:url
	value="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	var="bootstrap" />
<link href="${bootstrap}" rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<style>
body {
	background:
		url(${pageContext.request.contextPath}/resources/img/bg-body.png)
		repeat
}

.header {
	height: 70px;
	margin-bottom: 30px;
	border-bottom: 1px solid #bfb7b7;
	background:
		url(${pageContext.request.contextPath}/resources/img/bg-header.png)
		repeat-x;
	padding-top: 15px;
}

.container {
	max-width: 950px;
}

.input-lg {
	font-size: 14px;
}
</style>



</head>
<body>

	<div class="header">

		<div class="container">
			<ul class="nav nav-pills pull-right">
				<li><a href="/user">Home</a></li>
				<li class="active"><a href="/user/profile">Profile</a></li>
				<li><a href="j_spring_security_logout">Exit</a></li>
			</ul>
			<h5>Welcome, ${currentUser.firstName} ${currentUser.lastName}
				${currentUser.middleName}</h5>
		</div>

	</div>


	<div class="container">
		<div id="feedback"></div>
		<h3 style="margin-top: -10px; margin-left: 90px; margin-bottom: 50px;">Transfer</h3>


			<div class="form-group" style="margin-top: 50px;padding-left: 20px;">

				<label class="col-sm-2 control-label">Перевести с карты</label> <select
					id="listcountry" style="width: 220px; height: 30px;"
					class="col-sm-3">

					<c:forEach var="card" items="${listCards}">

						
							<option value="${card.id}" selected>${card.numberCard}</option>
							
					</c:forEach>

				</select> 
				
				
				



			</div>
			
			



			<div class="form-group">
			<label class="col-sm-2 control-label">На карту</label> 
							<div class="col-sm-4">
					<input type="text" class="form-control" id="last_name"
						placeholder="Enter last name" />

			</div>
			
			</div>
			
			<div class="form-group">
			<label class="col-sm-2 control-label">Сумма</label> 
							<div class="col-sm-4">
					<input type="text" class="form-control" id="last_name"
						placeholder="Enter last name" />

			</div>
			
			</div>
			
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" id="act" class="btn btn-default">Перевести</button>
				</div>
			</div>




	<script>
		jQuery(document).ready(function($) {

			$("#act").click(function(event) {

				var search = {}
				search["id"] = $(this).attr('value');
				search["countryName"] = $(this).text();

				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/user/transfer/",
					data : JSON.stringify(search),
					dataType : 'json',
					timeout : 100000,
					success : function(data) {
						console.log("SUCCESS: ", data);
						display(data);
					},
					error : function(e) {
						console.log("ERROR: ", e);
						display(e);
					},
					done : function(e) {
						console.log("DONE");
						enableSearchButton(true);
					}
				});

			});

		});

		function display(data) {
			var json = "<h4>Ajax Response</h4><pre>"
					+ JSON.stringify(data, null, 4) + "</pre>";
			var obj = jQuery.parseJSON(JSON.stringify(data, null, 4));

			$('#listcity').html("");

			for (var i = 0; i < obj.length; i++) {

				$('#listcity').append(
						"<option value="+obj[i].id+">" + obj[i].cityName
								+ "</option>");

			}

		}
	</script>




</body>

</html>