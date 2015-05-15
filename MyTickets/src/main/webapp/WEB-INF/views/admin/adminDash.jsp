<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>${sessionScope.user.firstName}|MyTickets.com</title>
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-2.0.3.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootswatch.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<h1>MyTicket.com</h1>
		</div>

		<div class="navbar navbar-default">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-responsive-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="navbar-collapse collapse navbar-responsive-collapse">

				<c:if test="${!empty sessionScope.user}">
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="#" class="navbar-brand"
							class="dropdown-toggle" data-toggle="dropdown">MyAccount<b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
							</ul>
					</ul>
				</c:if>
			</div>
		</div>

		<div class="well well-sm">
			<h2>Welcome ${sessionScope.user.firstName}</h2>
		</div>
		<div class="row">
<br><br><br><br>
			<div class="span8" align="center">
				<h3>${message}</h3>
				<form
					action="${pageContext.request.contextPath}/admin/adminDashForward"
					method="post" class="form-horizontal" align="Left">
					<fieldset>
						<legend class="legend">Select your City</legend>
						<div class="form-group">
							<label for="select" class="col-lg-2 control-label"></label>
							<div class="col-lg-10">
								<select name="adminCity" class="form-control" id="select">
									<option value="">--Select--</option>
									<c:forEach var="city" items="${cityList}">
										<option value="${city.cityName}">${city.cityName}</option>
									</c:forEach>
								</select>

							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-10 col-lg-offset-2">
								<button type="submit" value="confirm"
									class="btn btn-primary btn-sm">Confirm</button>
							</div>
						</div>

					</fieldset>
				</form><br><br><br><br><br><br><br><br><br><br><br><br>			</div>

		</div>
		<div class="footer">
			<p>&copy;Suraj 2014</p>
		</div>
	</div>
	<div id="fb-root"></div>


</body>
</html>