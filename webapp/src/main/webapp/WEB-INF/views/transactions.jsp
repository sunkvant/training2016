<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
				<li><a href="/user">Home</a></li>
				<li><a href="#">Profile</a></li>
				<li><a href="j_spring_security_logout">Exit</a></li>
			</ul>
			<h5>Welcome, ${numberCard}</h5>
		</div>

	</div>

	<div class="container">

		<div class="card-block">

			<div class="row">


				<div style="padding: 20px;">

					<p style="float: left; margin-right: 35px;">
						<strong>Все операции по текущей карте:</strong>
						${numberCard}
					</p>


				</div>

				<h5 style="margin-left: 10px; margin-top: 30px; margin-bottom: 20px">
					<strong>Операции:</strong>
				</h5>

				<c:forEach var="transaction" items="${listTransactions}">


					<div
						style="height: 130px; margin-left: 30px; margin-right: 20px; margin-bottom: 20px; padding: 20px">

						<div
							style="float: left; background-image: none; box-shadow: none;">
							<p>
								<strong>Дата и время:</strong>
								<fmt:formatDate 
									 value="${transaction.dateCompletion}" pattern="yyyy-MM-dd HH:mm" />
							</p>

							<p>
								<strong>Сумма:</strong> ${transaction.sum}
							</p>



							<p>
								<c:if test="${transaction.sum<0}">
									<strong>Номер карты получателя:
										${transaction.fromTo}</strong>
								</c:if>
								<c:if test="${transaction.sum>0}">
									<strong>Номер карты отправителя:
										${transaction.fromTo}</strong>
								</c:if>
							</p>
						</div>

					</div>

				</c:forEach>






			</div>

		
		</div>
		
		</div>
</body>

</html>