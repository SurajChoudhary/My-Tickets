<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home | MyTicket.com</title>
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/bootswatch-min.css" />"
	rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-2.0.3.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootswatch.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/moviedatafetch.js" />"></script>

<script type="text/javascript">
	$(document).ready(
			function() {
				var v = document.getElementById('search').value;
				$("#movieName").click(
						function() {
							$("#searchresult").load(
									"${pageContext.request.contextPath}/search/movieName/?name="
											+ v);
						});
			});
</script>
<style type="text/css">
#tablediv {
	background-color: #FA8258;
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
		<div class="row">
			<div class="span4">
				<div style="height: 50px; width: 1150px">
					<form class="navbar-form navbar-left">
						<input type="text" name="searchValue" size="100" 
							class="form-control col-lg-8 search-bar" id="search"
							placeholder="Search Movies">
						<div class="btn-group">
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown">
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a id="movieName">Name</a></li>
								<li><a href="#">Actor</a></li>
								<li><a href="#">Actress</a></li>
							</ul>
						</div>
					</form>
				</div>


				<br> <input type="hidden" value="${movieNameList}" id="mvname" />
				<div class="photobanner well well-sm" id="tb"
					style="height: 120px; width: 1150px; background-color: #000000; border-color: #000000;"></div>
 			</div>


			<div class="span8">
				<div id="searchresult" class="tabbable">

					<ul class="nav nav-tabs" style="margin-bottom: 15px;">
						<li class="active"><a href="#home" data-toggle="tab">Now
								Showing</a></li>
						<li><a href="#profile" data-toggle="tab">Coming Soon</a></li>
					</ul>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane fade active in" id="home">
							<table id="tablediv" class="table table-striped table-hover"
								style="width: 500px">
								<thead>
									<tr>
										<th>Movies</th>
										<th>Actor</th>
										<th>Actress</th>
										<th>&nbsp;</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="movieShowing" items="${currentMovieList}">
										<tr class="info">
											<td>${movieShowing.movie.name}</td>
											<td>${movieShowing.movie.actor}</td>
											<td>${movieShowing.movie.actress}</td>
											<td><a class="btn btn-primary btn-xs"
												href="${pageContext.request.contextPath}/showtime">Buy
													Ticket</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="tab-pane fade" id="profile">
							<table id="tablediv" class="table table-striped table-hover"
								style="width: 500px;">
								<thead>
									<tr>
										<th>Movies</th>
										<th>Actor</th>
										<th>Actress</th>
										<th>&nbsp;</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="upcomingShowing" items="${upcomingMovieList}">
										<tr class="info">
											<td>${upcomingShowing.movie.name}</td>
											<td>${upcomingShowing.movie.actor}</td>
											<td>${upcomingShowing.movie.actress}</td>
											<td><a class="btn btn-primary btn-xs" href="#">Advance
													Booking </a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>

			</div>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
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
