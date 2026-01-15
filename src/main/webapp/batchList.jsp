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
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">
			<c:if test="${pageName == 1}">
			List of Batches
			</c:if>
			<c:if test="${pageName == 2}">
			Batch ${batchNum}
			</c:if>
			</h3>
			<hr>
			<div class="container text-left">

			<c:if test="${pageName == 1}">
				<a href="<%=request.getContextPath()%>/newBatch" class="btn btn-success" style="background-color: indigo">Add
					New Batch</a>
					<a href="<%=request.getContextPath()%>/previousBatches" class="btn btn-success" style="background-color: indigo">
					Old Batches</a>
			</c:if>
				
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr style="background-color: pink">
						<th>ID</th>
						<th>Day</th>
						<th>Start Time</th>
						<th>End Time</th>
						<th>Homework </th>
						<th>Level</th>
						<th>Lesson Plan</th>
						<th> </th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="batch" items="${listBatch}">
					

						<tr>
							<td><a href="studentsInBatchDisplay?batchId=<c:out value='${batch.batchId}' />">${batch.batchId}</a></td>
							<td><c:out value="${batch.day}" /></td>
							<td><c:out value="${batch.startTime}" /></td>
							<td><c:out value="${batch.endTime}" /></td>
							<td><c:out value="${batch.homework}" /></td>
							<td><c:out value="${batch.level}" /></td>
							<td><c:out value= "${batch.lessonPlan}" /></td>
							<td><a href="editBatch?batchId=<c:out value='${batch.batchId}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <BR> <a
								href="deleteBatch?batchId=<c:out value='${batch.batchId}' />">Delete</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <BR>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
