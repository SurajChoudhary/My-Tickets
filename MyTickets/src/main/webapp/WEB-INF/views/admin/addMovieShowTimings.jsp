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
<link href="<c:url value="/resources/css/datepicker.css" />"
	rel="stylesheet" type="text/css" />
	<style type="text/css">
	.form-horizontal{
		width: 1100px;
	}
	</style>
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
			<h3>Manage Movies Show Timings</h3>
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
			<div class="span8">

				<c:if test="${!empty message}">
					<div class="alert alert-dismissable alert-success">
						<button type="button" class="close"></button>
						<strong>${message}</strong>
					</div>
				</c:if>
				<c:if test="${!empty sessionScope.addingMovieShowtimeError}">
					<div class="alert alert-dismissable alert-danger">
						<button type="button" class="close" ></button>
						<strong>${sessionScope.addingMovieShowtimeError}</strong>
						<br>
					</div>
				</c:if>
				<form:form class="form-horizontal" method="post"
					action="${pageContext.request.contextPath}/admin/addMovieShowtime"
					commandName="showtime">
					<fieldset>
						<legend class="legend">Create Movie Shows : </legend>
						<div class="form-group">
							<label for="select" class="col-lg-2 control-label">Select a movie showing:</label>
							<div class="col-lg-10">
								<table class="table table-striped table-hover ">
									<thead>
										<tr>
											<th></th>
											<th>Movie Name</th>
											<th>Cinema Name</th>
											<th>Start Date</th>
											<th>End Date</th>
											<th>Select</th>
										</tr>
									</thead>
									<tbody>

										<c:forEach var="movieShowing" items="${movieShowingList}">
											<tr class="info">
												<td></td>
												<td>${movieShowing.movie.name}</td>
												<td>${movieShowing.cinema.name}</td>
												<td><fmt:formatDate
														value="${movieShowing.movieStartDate}"
														pattern="MM-dd-yyyy" /></td>
												<td><fmt:formatDate
														value="${movieShowing.movieEndDate}" pattern="MM-dd-yyyy" /></td>
												<td>
													<div class="radio">
														<label> <input type="radio"
															value="${movieShowing.movieShowingId}"
															name="movieShowingId" />
														</label>
													</div>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>

						<div class="form-group">
							<form:label path="ticketPrice" for="select"
								class="col-lg-2 control-label">Enter
							Ticket Price</form:label>
							<div class="col-lg-10">
								<form:input path="ticketPrice" class="form-control"
									name="tickePrice" id="tickePrice"
									placeholder="Enter Ticket Price" />
								<form:errors path="ticketPrice" id="ticketPrice"
									cssclass="error"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<form:label path="timings" class="col-lg-2 control-label"
								for="timings">Select Show Timings</form:label>

							<div class="col-lg-10 checkbox">
								<table class="table">
									<tbody>
										<tr>
											<td><form:checkbox path="timings" value="12.00 pm"
													class="checkbox" /> 12.00 pm</td>
											<td><form:checkbox path="timings" value="6.00 pm"
													class="checkbox" /> 6.00 pm</td>
										</tr>
										<tr>
											<td><form:checkbox path="timings" value="3.00 pm"
													class="checkbox" /> 3.00 pm</td>
											<td><form:checkbox path="timings" value="9.00 pm"
													class="checkbox" /> 9.00 pm</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-10 col-lg-offset-2">
								<button type="submit" value="confirm" class="btn btn-primary">Add
								</button>
							</div>
						</div>
					</fieldset>
				</form:form>

			</div>
		</div>
		<hr>
<div class="footer">
			<p>&copy;Suraj 2014</p>
		</div>
	</div>


</body>
</html>