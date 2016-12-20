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
<style>
body {
	background:
		url(${pageContext.request.contextPath}/resources/img/bg-body.png)
		repeat
}

.container {
	max-width: 950px;
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

.row div {
	height: 60px;
	margin-left: 10px;
	border-radius: 10px;
	background-image:
		url(${pageContext.request.contextPath}/resources/img/bg_creditcard-blue.jpg);
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
				<li><a href="/user"><spring:message code="menu.home"/></a></li>
				<li><a href="#"><spring:message code="menu.profile"/></a></li>
				<li><a href="j_spring_security_logout"><spring:message code="menu.exit"/></a></li>
			</ul>
			<h5><spring:message code="welcome"/>, ${bankaccount.name}</h5>
		</div>

	</div>

	<div class="container">

		<div class="card-block">

			<div class="row">


				<div style="padding: 20px;">

					<p style="float: left; margin-right: 35px;">
						<strong><spring:message code="bankaccount.number"/></strong> ${bankAccount.numberAccount}
					</p>
					<p style="float: left; margin-right: 35px;">
						<strong><spring:message code="bankaccount.amount"/></strong> ${bankAccount.sum}
					</p>
					<p style="float: left; margin-right: 35px;">
						<strong><spring:message code="bankaccount.status"/></strong> ${bankAccount.status}
					</p>

				</div>

				<h5 style="margin-left: 10px; margin-top: 30px; margin-bottom: 20px"><strong>Карты
					привязанные к этому счету:</strong></h5>

				<c:forEach var="creditCard" items="${listCreditCards}">


					<div
						style="height: 130px; margin-left: 30px; margin-right: 20px; margin-bottom: 20px; padding: 20px">

<div style="float:left; 	background-image: none; box-shadow: none;">
						<p>
							<strong>Номер карты:</strong> ${creditCard.numberCard}
						</p>
						
												<p>
							<strong>Срок окончания:</strong> ${creditCard.validity}
						</p>
						
											
						
												<p>
							<strong>Статус блокировки:</strong> ${creditCard.status}
						</p>
						</div>
						<a href="/user/transactions/card/${creditCard.numberCard}"><button class="btn btn-info btn-sm" style="float: right;" type="button">Просмотр операций</button></a>
<button class="btn btn-info btn-sm" style="float: right;margin-top: 40px;margin-left: 100px; display: block;" type="button">Удалить</button>
					</div>

				</c:forEach>






			</div>

		</div>
		
		</div>
</body>

</html>