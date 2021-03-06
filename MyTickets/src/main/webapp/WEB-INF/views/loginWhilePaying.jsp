<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Login | MyTickets.com</title>
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
				</ul>
		</div>
	
		<div class="row">
		<div class="span8" style="width: 300px">
				<div class="alert alert-dismissable alert-info">
					<strong>New User!</strong> Click <a href="${pageContext.request.contextPath}/createUserForward" 
					class="alert-link">here</a> to sign up
				</div>
			</div>
			<div class="span4" align="center">
				<c:if test="${!empty message}">
					<div class="alert alert-dismissable alert-success">
						<button type="button" class="close" data-dismiss="alert"></button>
						<strong>${message}</strong>
					</div>
				</c:if><br><br><br><br>
				<form:form class="form-horizontal" method="post"
					action="${pageContext.request.contextPath}/loginUserWhilePaying"
					commandName="user"  align="Left">
					<fieldset>
						<legend class="legend">Login</legend>
						<div class="form-group">
							<form:label path="email" for="inputEmail"
								class="col-lg-2 control-label">Email</form:label>
							<div class="col-lg-10">
								<form:input path="email" class="form-control" id="inputEmail"
									placeholder="Email" />
								<form:errors path="email" id="inputEmail" cssclass="error"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<form:label path="password" for="inputPassword"
								class="col-lg-2 control-label">Password</form:label>
							<div class="col-lg-10">
								<form:input path="password" type="password" class="form-control"
									id="inputPassword" placeholder="Password" />
								<form:errors path="password" id="inputPassword" cssclass="error"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-10 col-lg-offset-2">
								<button type="submit" value="Login" class="btn btn-primary btn-sm">Sign
									in</button>
							</div>
						</div>
					</fieldset>
				</form:form><br><br><br><br>
			</div>
			<br><br><br><br>
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