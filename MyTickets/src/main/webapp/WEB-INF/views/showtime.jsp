<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<title>Showtimes | MyTickets.com</title>
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-2.0.3.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootswatch.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
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
		};
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
		};
		xmlhttp.open("GET", "getMovieList?cinemaName=" + cinemaName, true);
		xmlhttp.send();
	}
</script>
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

		<div class="row" align="center">
			<div class="span4"><br><br><br><br>
				<form action="${pageContext.request.contextPath}/buyTickets" method="post"
					 class="form-horizontal" align="left">
					<fieldset>
						<legend class="legend">Book a Ticket</legend>
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
								a Cinema</label>
							<div class="col-lg-10">
								<%-- 	<select class="form-control" id="cinemanamesperregion" name="cinemaName">
							<c:forEach var="cinema" items="${cinemaList}">
								<option value="${cinema.name}">${cinema.name}</option>
							</c:forEach>
						</select> --%>
								<div id="cinemaList">
									<select class="form-control" name="cityName" id="cityName">
										<option value="">--Select--</option>
									</select>

								</div>

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
								<button type="submit" class="btn btn-primary btn-sm">Submit</button>
							</div>
						</div>

					</fieldset>

				</form><br><br><br><br><br><br><br><br>
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

		<div class="footer">
			<p>&copy;Suraj 2014</p>
		</div>
	</div>
	<div id="fb-root"></div>
	<script>
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


	<!-- <script type="text/javascript">
		function change_category(id) {
			alert("${pageContext.request.contextPath}");
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/getCinemaList",
				data : "pid=" + id,
				success : function(data) {
					$.each(data,function(key,val){
							$("#cinemanamesperregion").append("<option id=\""+val.cinemaId+"\" data-cid = \""+val.cinemaId+"\">"+val.name+"</option>");
							$("#"+val.name).bind('click',function(event){
								event.preventDefault();
								getMovieList($(this).html(),$(this).data("cid"));
							});
						});
				}
			});
		}

		function getMovieList(name,id){
				$.post("${pageContext.request.contextPath}/getMovieList",{cid:id},function(data){
						$.each(data,function(key,val){
								console.log(val);	
							});
					});
			}
	</script> -->
</body>
</html>