<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Teacher Assistant Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous" >
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: purple">
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/listCalendar"
					class="nav-link">Calendar</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Students</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/listBatch"
					class="nav-link">Batches</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">

		<div class="container">
			<h3 class="text-center">
			</h3>
			
			<hr>
			<div class="container text-left">
			<a href="<%=request.getContextPath()%>/listBatch" class="btn btn-success" style="background-color: indigo">Current Batches</a>
			</div>
				
			<br>
			<table class="table table-bordered">
				<thead>
					<tr style="background-color: pink">
						<th>ID</th>
						<th>Start Time</th>
						<th>End Time</th>
						<th>Level</th>
						<th> </th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="batch" items="${listBatch}">
					

						<tr>
							<td><a href="studentsInBatchDisplay?batchId=<c:out value='${batch.batchId}' />">${batch.batchId}</a></td>
							<td><c:out value="${batch.startTime}" /></td>
							<td><c:out value="${batch.endTime}" /></td>
							<td><c:out value="${batch.level}" /></td>
							<td><a
								href="restoreBatch?batchId=<c:out value='${batch.batchId}' />">Restore</a>
								</td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
