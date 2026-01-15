<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Teacher Assistant Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
	<script>
		function validate() {


		var email = document.getElementById("email").value;
		var phone = document.getElementById("phone").value;
		var validRegexEmail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		var validRegexNumber = /^\d{10}$/;

		  if (validRegexEmail.test(email) && validRegexNumber.test(phone)) {
		    return true;
		  } else if (validRegexEmail.test(email)){
		    alert("Invalid phone number");
		    document.insertForm.phone.focus();
            event.preventDefault();
		    return false;
		  } else if (validRegexNumber.test(phone)){
			    alert("Invalid email id");
			    document.insertForm.email.focus();
	            event.preventDefault();
			    return false;
			  }
		  else{
			  alert("Invalid email id AND phone number");
			    document.insertForm.email.focus();
	            event.preventDefault();
			    return false;
		  }

		}
	</script>
	
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
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
	
				<form name="insertForm" action="insertStudent" method="post">

				<caption>
					<h2>
            			Add New Student
					</h2>
				</caption>


				<fieldset class="form-group">
					<label>Student Name</label> <input type="text"
						value="<c:out value='${student.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Student Email</label> <input id = "email" type="text"
						value="<c:out value='${student.email}' />" class="form-control"
						name="email" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Student Phone</label> <input id = "phone" type="text"
						value="<c:out value='${student.phone}' />" class="form-control"
						name="phone" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Student Address</label> <input type="text"
						value="<c:out value='${student.address}' />" class="form-control"
						name="address" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Student IsFeeDue</label> <input type="text"
						value="<c:out value="false" />" class="form-control"
						name="isFeeDue" readonly="readonly">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Student FeeDueAfter</label> <input type="text"
						value="<c:out value='8' />" class="form-control"
						name="feeDueAfter" readonly="readonly">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Student BatchNumber</label> 
					<select name="batchNumber" id="batchNumber" >
    					<c:forEach  var="batchIds"  items="${batchIds}" begin="0">
      				 	 <option>${batchIds}   </option>
    					</c:forEach>
					</select>
				</fieldset>

				<button onclick = "validate()" type="submit" class="btn btn-success" style="background-color: indigo">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
