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
<link href="<c:url value="/resources/css/datepicker.css" />"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-2.0.3.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap-datepicker.js" />"></script>
</head>
<body>

	<div class="container">
		<div class="jumbotron">
			<h1>MyTicket.com</h1>
		</div>

		<div class="navbar navbar-default">
			<div class="navbar-collapse collapse navbar-responsive-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a class="navbar-brand"
						href="${pageContext.request.contextPath}/adminDash">Home</a></li>
				</ul>
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
			<br>
			<h4>Your City : ${sessionScope.adminCity}</h4><br>
			<h3>Manage Movie Showing</h3>
		</div>

		<div class="row">
			<div class="span4">
				<ul class="nav nav-list">
					<li class="nav-header">Administrator Actions</li>
					<li class="active"><a
						href="${pageContext.request.contextPath}/admin/manageMovies">Manage
							Movies</a></li>
					<li><a
						href="${pageContext.request.contextPath}/admin/manageMovieShowings">Manage
							Movie Showings </a></li>
					<li><a
						href="${pageContext.request.contextPath}/admin/manageShowTimings">Manage
							Show Timings</a></li>
					<li><a
						href="${pageContext.request.contextPath}/admin/manageUser">Manage
							Users</a></li>

				</ul>
			</div>
			<div class="span8" align="center">

				<c:if test="${!empty successMessage}">
					<div class="alert alert-dismissable alert-success">
						<strong>${successMessage}</strong>
					</div>
				</c:if>
				<c:if test="${!empty sessionScope.addingMovieShowingError}">
					<div class="alert alert-dismissable alert-danger">
						<strong>${sessionScope.addingMovieShowingError}</strong> <br>Selected
						dates may be in past
					</div>
				</c:if><br>
				<form:form class="form-horizontal" method="post"
					action="${pageContext.request.contextPath}/admin/addMovieShowing"
					commandName="movieShowing" align="left">
					<fieldset>
						<legend>Enter Movie Showing Details : </legend>
						<div class="form-group">
							<label for="select" class="col-lg-2 control-label">Select
								a Movie</label>
							<div class="col-lg-10">
								<select class="form-control" name="movie" id="movie">
									<option value="">--Select--</option>
									<c:forEach var="movie" items="${movieList}">
										<option value="${movie.name}">${movie.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="select" class="col-lg-2 control-label">Select
								a Cinema</label>
							<div class="col-lg-10">
								<select class="form-control" name="cinema" id="select_cinema">
									<option value="">--Select--</option>
									<c:forEach var="cinema" items="${cinemaList}">
										<option value="${cinema.name}">${cinema.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<form:label path="movieStartDate" class="col-lg-2 control-label"
								for="movieStartDate">Starting Date</form:label>
							<div class="col-lg-10">
								<form:input path="movieStartDate" type="text" id="datepicker1" size="45"
									data-date-format="mm/dd/yyyy" placeholder="Enter Start Date" />
							</div>
						</div>
						<div class="form-group">
							<form:label path="movieEndDate" class="col-lg-2 control-label"
								for="movieEndDate">End Date</form:label>
							<div class="col-lg-10">
								<form:input path="movieEndDate" type="text" id="datepicker2" size="45"
									data-date-format="mm/dd/yyyy" placeholder="Enter End Date" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-10 col-lg-offset-2">
								<button type="submit" value="confirm" class="btn btn-primary btn-sm" >Add
								</button>
							</div>
						</div>
					</fieldset>
				</form:form><br><br><br><br><br><br><br><br>

			</div>
		</div>
		<hr>
		<div class="footer">
			<p>&copy;Suraj 2014</p>
		</div>

	</div>

	<script>
		$(function() {
			$("#datepicker1").datepicker();
		});
		$(function() {
			$("#datepicker2").datepicker();
		});

		/* $(document).ready(function(){
			$("#select_city").change(function(){
						alert("hello");



			var city = $("#select_city").val();
			alert("city="+city);
			var cinemaList = document.getElementsByTagName("option");
			var cityId; 
			for (var i=0;i<cinemaList.length;i++){
				alert(cinemaList[i].value);
					if(city==cinemaList[i].value)
						cityId=cinemaList[i].id;
				}
			alert(cityId);
			$.ajax({
				url:"${pageContext.request.contextPath}/getCinemaList?cityId=100",
				type: "GET",				
				success: function(response){
					console.log(response);
					}
				});
		
			});


			});
			
		 */
	</script>

</body>
</html>