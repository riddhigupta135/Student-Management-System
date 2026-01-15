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
			<h3 class="text-center">List of Students</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/listStudent" class="btn btn-success" style="background-color: indigo">List All Current Students</a>
				
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr style="background-color: pink">
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Phone </th>
						<th>Address</th>
						<th>IsFeeDue  </th>
						<th>FeeDueAfter </th>
						<th>Batch Number</th>
						<th> </th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="student" items="${listStudent}">
					

						<tr>
							<td><c:out value="${student.studentId}" /></td>
							<td><c:out value="${student.name}" /></td>
							<td><c:out value="${student.email}" /></td>
							<td><c:out value="${student.phone}" /></td>
							<td><c:out value="${student.address}" /></td>
							<td><c:out value= "${student.isFeeDue}" /></td>
							<td><c:out value="${student.feeDueAfter}" /></td>
							<td><c:out value="${student.batchNumber}" /></td>
							<td><a href="updateStudentWithFeeDue?studentId=<c:out value='${student.studentId}' />">Fee paid</a>
								</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
