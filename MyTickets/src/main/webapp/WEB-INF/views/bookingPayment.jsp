<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Payment | MyTickets.com</title>
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/datepicker.css" />"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<script type="text/javascript"
		src="<c:url value="/resources/js/jquery-2.0.3.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/bootstrap.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/bootstrap-datepicker.js" />"></script>
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
				<div class="alert alert-dismissable alert-success">
					<button type="button" class="close" data-dismiss="alert"></button>
					<strong>Almost there!!</strong>
				</div>

				<div class="well">
					 <h3>Your Selections : </h3>
					City : ${sessionScope.bookMovieForm.cityName}<br>
					Cinema : ${sessionScope.bookMovieForm.cinemaName} <br>Movie
					: ${sessionScope.bookMovieForm.movieShowing.movie.name}<br>
					Show Date :
					<fmt:formatDate value="${sessionScope.bookMovieForm.showtime.date}"
						pattern="MM-dd-yyyy" /><br>
					Show Time : ${sessionScope.bookMovieForm.showtime.showStartTime}<br>
				</div>
				<c:if test="${!empty accountCreatedMessage}">
					<div class="alert alert-dismissable alert-success">
						<button type="button" class="close" data-dismiss="alert"></button>
						<strong>${accountCreatedMessage}</strong>
					</div>
				</c:if>
			</div>
			<div class="span8" align="center">
				<form:form class="form-horizontal" commandName="payment" method="post" align="left"
					action="${pageContext.request.contextPath}/customer/paymentSuccess">
					<fieldset>
						<legend class="legend">Payment </legend>
						<div class="form-group">
							<label class="col-lg-2 control-label">Card Type</label>
							<div class="col-lg-10">
								<form:select path="paymentType">
									<form:option value="visa">Visa</form:option>
									<form:option value="masterCard">Master Card</form:option>
								</form:select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label">Card Number</label>
							<div class="col-lg-10">
								<form:input path="cardNumber" id="cardNumber" name="cardNumber" type="text"
									class="form-control" placeholder="Enter Card Number" />
							<form:errors path="cardNumber" id="cardNumber" cssclass="has-error"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label">Card Security
								Number</label>
							<div class="col-lg-10">
								<form:input path="cardId" id="cardId" name="cardId" type="text"
									class="form-control"
									placeholder="Enter Number on back of your card" />
								<form:errors path="cardId" id="cardId" cssclass="has-error"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<form:label path="cardExpirationDate"
								class="col-lg-2 control-label">Card Expiration Date</form:label>
							<div class="col-lg-10">
								<form:input path="cardExpirationDate" type="text"
									id="datepicker" data-date-format="mm/dd/yyyy" size="45"
									placeholder="Enter card expiration date" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label">Address </label>
							<div class="col-lg-10">
								<form:textarea path="address" class="form-control"
									placeholder="Enter address" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label">Total Payable amount</label>
							<div class="col-lg-10">
								<label>${totalAmount}</label>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-10 col-lg-offset-2">
								<button type="submit" value="confirm" class="btn btn-primary btn-sm">Pay
								</button>
							</div>
						</div>
					</fieldset>
				</form:form><br><br><br><br><br><br>
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
		$(function() {
			$("#datepicker").datepicker({
				dateFormat : 'dd-mm-yyyy'
			});
		});
	</script>
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