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
<style type="text/css">
#tablediv {
	background-color: #81F7F3;
}
</style>

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
			<h4>Your City : ${sessionScope.adminCity}</h4>
			<br>
			<h3>Manage Movies</h3>
		</div>
		<c:if test="${!empty message}">
		<div class="alert alert-dismissable alert-success">
			<strong>${message}!</strong>
		</div></c:if>
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


				<form:form class="form-horizontal" method="post" align="left"
					action="${pageContext.request.contextPath}/admin/addMovie"
					commandName="movie">
					<fieldset>
						<legend class="legend">Add a Movie : </legend>
						<div class="form-group">
							<form:label path="name" for="inputName"
								class="col-lg-2 control-label">Movie Name</form:label>
							<div class="col-lg-10">
								<form:input path="name" class="form-control movieName"
									id="movieName" placeholder="Enter Movie Name" />
								<form:errors path="name" id="name" cssclass="has-error"></form:errors>

								<input type="hidden" name="categoryList">
							</div>
						</div>
						<div class="form-group">
							<form:label path="actor" for="inputActor"
								class="col-lg-2 control-label">Actor Name</form:label>
							<div class="col-lg-10">

								<form:input path="actor" type="text" class="form-control"
									id="actorName" placeholder="Enter Lead Actor Name" />
								<form:errors path="actor" id="actorName" cssclass="error" />
							</div>
						</div>
						<div class="form-group">
							<form:label path="actress" for="inputActress"
								class="col-lg-2 control-label">Actress Name</form:label>
							<div class="col-lg-10">

								<form:input path="actress" type="text" class="form-control"
									id="actressName" placeholder="Enter Lead Actress Name" />
								<form:errors path="actress" id="actressName" cssclass="error" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-10 col-lg-offset-2">
								<button type="submit" value="confirm" id="submit"
									class="btn btn-primary btn-sm">Add</button>
							</div>
						</div>
					</fieldset>
				</form:form>
				<br>
				<br>
				<br>
				<br>
				<br>

				<div id="tablediv">
					<table class="table table-striped table-hover ">
						<thead>
							<tr>
								<th>List of Movies</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="movie" items="${movieList}">
								<tr>
									<td>${movie.name}</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
			</div>
		</div>
		<hr>
		<div class="footer">
			<p>&copy;Suraj 2014</p>
		</div>
	</div>
</body>
</html>