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
			Calendar
			</h3>
			
			<hr>
			
			<br>
			<table class="table table-bordered">
				<tbody>
					<tr> 
						<th style="background-color: pink"> Monday </th>
						<c:forEach var="batchMonday" items="${mondayBatch}">
						<td><a href="oneBatchDisplay?batchNumber=<c:out value='${batchMonday.batchId}' />">Batch 
							<c:out value='${batchMonday.batchId}' /></a>
						<BR>
						<c:out value='${batchMonday.startTime}' /> - <c:out value='${batchMonday.endTime}' /></td>
						</c:forEach>
					</tr>
					
					
					
					
					<tr> 
						<th style="background-color: pink"> Tuesday </th>
						<c:forEach var="batchTuesday" items="${tuesdayBatch}">
						<td><a href="oneBatchDisplay?batchNumber=<c:out value='${batchTuesday.batchId}' />">Batch <c:out value='${batchTuesday.batchId}' /></a>
						<BR>
						<c:out value='${batchTuesday.startTime}' /> - <c:out value='${batchTuesday.endTime}' /></td>
						</c:forEach>
					</tr>
					
					
					
					
					<tr> 
						<th style="background-color: pink"> Wednesday </th>
						<c:forEach var="batchWednesday" items="${wednesdayBatch}">
						<td><a href="oneBatchDisplay?batchNumber=<c:out value='${batchWednesday.batchId}' />">Batch <c:out value='${batchWednesday.batchId}' /></a>
						<BR>
						<c:out value='${batchWednesday.startTime}' /> - <c:out value='${batchWednesday.endTime}' /></td>
						</c:forEach>
					</tr>
					
					
					
					
					<tr> 
						<th style="background-color: pink"> Thursday </th>
						<c:forEach var="batchThursday" items="${thursdayBatch}">
						<td><a href="oneBatchDisplay?batchNumber=<c:out value='${batchThursday.batchId}' />">Batch <c:out value='${batchThursday.batchId}' /></a>
						<BR>
						<c:out value='${batchThursday.startTime}' /> - <c:out value='${batchThursday.endTime}' /></td>
						</c:forEach>
					</tr>
					
					
					
					<tr> 
						<th style="background-color: pink"> Friday </th>
						<c:forEach var="batchFriday" items="${fridayBatch}">
						<td><a href="oneBatchDisplay?batchNumber=<c:out value='${batchFriday.batchId}' />">Batch <c:out value='${batchFriday.batchId}' /></a>
						<BR>
						<c:out value='${batchFriday.startTime}' /> - <c:out value='${batchFriday.endTime}' /></td>
						</c:forEach>
					</tr>
					
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
