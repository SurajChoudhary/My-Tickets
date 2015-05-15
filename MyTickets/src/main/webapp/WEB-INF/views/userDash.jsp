<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<style type="text/css">
#tablediv {
	background-color: #01A9DB;
}
</style>
</head>
<body>

	<div class="container">
		<div class="jumbotron">
			<h1>MyTicket.com</h1>
			<br>
			<ul class="breadcrumb" style="margin-bottom: 5px;">
				<li><a href="${pageContext.request.contextPath}/help">Help</a></li>
				<li><a href="${pageContext.request.contextPath}/contactUs">Contact
						Us</a></li>
				<c:if test="${empty sessionScope.user}">
					<li><a href="${pageContext.request.contextPath}/createAccount">SignUp</a></li>
					<li><a href="${pageContext.request.contextPath}/login">Login</a></li>
				</c:if>
				<c:if test="${!empty sessionScope.user}">
					<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
				</c:if>
			</ul>
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
				<ul class="nav navbar-nav">
					<li class="active"><a class="navbar-brand"
						href="${pageContext.request.contextPath}/">Home</a></li>
					<li><a class="navbar-brand"
						href="${pageContext.request.contextPath}/showtime">Showtimes</a></li>
					<li><a class="navbar-brand"
						href="${pageContext.request.contextPath}/browsemovies">Movies</a></li>
					<li><a class="navbar-brand" href="#">News </a></li>
				</ul>

				<c:if test="${!empty sessionScope.user}">
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="#" class="navbar-brand"
							class="dropdown-toggle" data-toggle="dropdown">MyAccount<b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/userDash">My
										Bookings</a></li>
								<li class="divider"></li>
								<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
							</ul>
					</ul>
				</c:if>
			</div>
		</div>
		<div class="row" >
			<div class="span4" align="center">
				<form class="form-horizontal" align="left" >
					<fieldset>
						<legend class="legend">Welcome
							${sessionScope.user.firstName}!</legend>
						<div class="form-group">
							<label class="col-lg-2 control-label">Email</label>
							<div class="col-lg-10">
								<label class="form-control">${sessionScope.user.email}</label>

							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label" for="firstName">First
								Name</label>
							<div class="col-lg-10">
								<label class="form-control" id="firstName">${sessionScope.user.firstName}</label>

							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label" for="lastName">Last
								Name</label>
							<div class="col-lg-10">
								<label class="form-control" id="lastName">${sessionScope.user.lastName}</label>

							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label">Date of Birth</label>
							<div class="col-lg-10">
								<label class="form-control"> <fmt:formatDate
										value="${sessionScope.user.dob}" pattern="MM-dd-yyyy" /></label>
							</div>
						</div>

					</fieldset>
				</form>
			</div>
			<div class="span8">
				<div class="tabbable">
				<br><br><br><br>
				<div class="well well-sm">
					<h3>Your Booking History</h3>
					</div>
					<c:if test="${!empty bookingList}">
						<table class="table table-striped table-hover" id="tablediv">
							<thead>
								<tr>
									<th>BookingId</th>
									<th>Booking date</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="booking" items="${bookingList}">
									<tr class="info">
										<td>${booking.bookingId}</td>
										<td><fmt:formatDate value="${booking.bookingDate}"
												pattern="MM-dd-yyyy" /></td>
										<%-- <td><a class="btn btn-primary btn-sm"
									href="${pageContext.request.contextPath}/buyTickets${movieShowing.movieShowingId}">Book
										Movie</a></td> --%>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					<c:if test="${empty bookingList}">
						<div class="alert alert-dismissable alert-info">
							<button type="button" class="close" data-dismiss="alert"></button>
							<strong>No Bookings made!</strong> <br> Geez! You might want
							to get a life!
						</div>

					</c:if>
				<br><br>
				
				</div>
			</div>

			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Stay Connected with us:</h3>
				</div>

				<div class="well">
					<div class="fb-like"
						data-href="https://developers.facebook.com/docs/plugins/"
						data-layout="button" data-action="like" data-show-faces="true"
						data-share="false"></div>
					<a href="https://twitter.com/twitter" class="twitter-follow-button"
						data-show-count="false">Follow @twitter</a>
				</div>
			</div>
		</div>
		<div class="footer">
			<p>&copy;Suraj 2014</p>
		</div>
	</div>
	<div id="fb-root"></div>
	<script>
		!function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0], p = /^http:/
					.test(d.location) ? 'http' : 'https';
			if (!d.getElementById(id)) {
				js = d.createElement(s);
				js.id = id;
				js.src = p + '://platform.twitter.com/widgets.js';
				fjs.parentNode.insertBefore(js, fjs);
			}
		}(document, 'script', 'twitter-wjs');

		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_US/all.js#xfbml=1";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>
</body>
</html>