<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<title>Browse Movies | MyTickets.com</title>
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet" type="text/css" />
</head>

<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-2.0.3.js" />"></script>

<script>
	function populateCinemaList(city) {
		if (city.length == 0) {
			document.getElementById("cityName").innerHTML = "";
			return;
		}
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				if (xmlhttp.responseText == "") {
					document.getElementById("cinemaList").innerHTML = "";
					return;
				} else {
					document.getElementById("cinemaList").innerHTML = xmlhttp.responseText;
				}
			}
		}
		xmlhttp.open("GET", "getCinemaList?cityName=" + city, true);
		xmlhttp.send();
	}

	function populateMovieList(cinemaName) {
		if (cinemaName.length == 0) {
			document.getElementById("cinemaName").innerHTML = "";
			return;
		}
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				if (xmlhttp.responseText == "") {
					document.getElementById("movieList").innerHTML = "";
					return;
				} else {
					document.getElementById("movieList").innerHTML = xmlhttp.responseText;
				}
			}
		}
		xmlhttp.open("GET", "getMovieList?cinemaName=" + cinemaName, true);
		xmlhttp.send();
	}
</script>
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

				<div class="alert alert-dismissable alert-info">
					
					<strong>Under Construction!</strong>
				</div>
				<%-- <form action="${pageContext.request.contextPath}/buyTickets" method="post" class="form-horizontal">
					<fieldset>
						<legend>Book a Ticket</legend>
						<div class="form-group">
							<label for="select" class="col-lg-2 control-label">Select
								a City</label>
							<div class="col-lg-10">
								<select class="form-control" name="cityName" id="cityName"
									onchange="populateCinemaList(this.value);">
									<option value="">--Select--</option>
									<c:forEach var="city" items="${cityList}">
										<option value="${city.cityId}">${city.cityName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label for="select" class="col-lg-2 control-label">Select
								a Movie</label>
							<div class="col-lg-10">
								<div id="movieList"></div>
							</div>

						</div>
						<div class="form-group">
							<div class="col-lg-10 col-lg-offset-2">
								<button class="btn btn-default">Cancel</button>
								<button type="submit" class="btn btn-primary">Submit</button>
							</div>
						</div>

					</fieldset>

				</form> --%>
			</div>

		</div>

		<div class="span8">


			<%-- <div class="tabbable">
			<h3>Movies now showing in ${cinemaName}</h3>
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Movies</th>
							<th>Book</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="movieShowing" items="${movieShowingList}">
							<tr class="info">
								<td>${movieShowing.movie.name}</td>
								<td><a class="btn btn-primary btn-sm"
									href="${pageContext.request.contextPath}/buyTickets${movieShowing.movieShowingId}">Book
										Movie</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div> --%>
		</div>
	</div>



</body>
</html>