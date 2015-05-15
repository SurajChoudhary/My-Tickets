<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<title>Showtimes | MyTickets.com</title>
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/datepicker.css" />"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-2.0.3.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootswatch.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap-datepicker.js" />"></script>

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
				<div class="well">
					<h3>Your Selections :</h3>
					City : ${sessionScope.bookMovieForm.cityName}<br> Cinema :
					${sessionScope.bookMovieForm.cinemaName} <br>Movie :
					${sessionScope.bookMovieForm.movieShowing.movie.name}
				</div>
				<c:if test="${!empty message1}">
					<div class="alert alert-dismissable alert-info">
						<button type="button" class="close" data-dismiss="alert">×</button>
						<strong>Sorry!</strong> ${message1}<br>${message2}
					</div>
				</c:if>
			</div>
			<div class="span8"  align="center"><br><br><br><br>
				<form class="form-horizontal" method="post"
					action="${pageContext.request.contextPath}/buyTicketForward"  align="left">
					<fieldset>
						<legend class="legend">Select a date : </legend>
						<div class="form-group">
							<label class="col-lg-2 control-label" for="date"> </label>
							<div class="col-lg-10">
								<input type="text" id="datepicker" name="datePicked"
									data-date-format="mm/dd/yyyy" placeholder="Date" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-10 col-lg-offset-2">
								<button type="submit" value="confirm"
									class="btn btn-primary btn-sm">Confirm</button>
							</div>


						</div>

					</fieldset>
				</form><br><br><br><br><br><br><br><br>
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
		
		<div class="footer">
			<p>&copy;Suraj 2014</p>
		</div>
	</div></div>
	<div id="fb-root"></div>

	<script>
		$(function() {
			$("#datepicker").datepicker({
				dateFormat : 'dd-mm-yyyy'
			});
		});
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_US/all.js#xfbml=1";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));

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
	</script>


</body>
</html>