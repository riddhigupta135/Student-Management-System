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


		var startTime = document.getElementById("startTime").value;
		var endTime = document.getElementById("endTime").value;
		var validTimeRegex = /(((0[1-9])|(1[0-2])):([0-5])(0|5))/;

		  if (validTimeRegex.test(startTime) && validTimeRegex.test(endTime)) {
		    return true;
		  } else if (validTimeRegex.test(startTime)){
		    alert("Invalid end time: enter in the format hh:mm");
		    document.addBatchForm.startTime.focus();
            event.preventDefault();
		    return false;
		  } else if (validTimeRegex.test(endTime)){
			    alert("Invalid start time: enter in the format hh:mm");
			    document.addBatchForm.endTime.focus();
	            event.preventDefault();
			    return false;
			  }
		  else{
			  alert("Invalid start time AND end time: enter in the format hh:mm");
			    document.addBatchForm.startTime.focus();
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
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Batches</a></li>
			</ul>
			
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<form name="addBatchForm" action="insertBatch" method="post">

				<caption>
					<h2>
					
            			Add New Batch

					</h2>
				</caption>

				<fieldset class="form-group">
					<label>Day</label>
					<select name="day" id="day" >
					<option value="Monday"><c:out value="Monday" ></c:out></option> 
    				<option value="Tuesday"><c:out value="Tuesday" ></c:out></option>
    				<option value="Wednesday"><c:out value="Wednesday" ></c:out></option>
    				<option value="Thursday"><c:out value="Thursday" ></c:out></option>
    				<option value="Friday"><c:out value="Friday" ></c:out></option>
  					</select>
  				</fieldset>
				
				<fieldset class="form-group">
					<label>Start Time</label> <input id = "startTime" type="text"
						value="<c:out value='${batch.startTime}' />" class="form-control"
						name="startTime" required="required">
				</fieldset>
				

				<fieldset class="form-group">
					<label>End Time</label> <input id = "endTime" type="text"
						value="<c:out value='${batch.endTime}' />" class="form-control"
						name="endTime" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Homework</label> <input type="text"
						value="<c:out value='${batch.homework}' />" class="form-control"
						name="homework">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Level</label>
					<select name="level" id="level" >
					<option value="1" ${batch.level == "1" ? 'selected' : ' '}><c:out value="1" ></c:out></option> 
    				<option value="2" ${batch.level == "2" ? 'selected' : ' '}><c:out value="2" ></c:out></option>
    				<option value="3" ${batch.level == "3" ? 'selected' : ' '}><c:out value="3" ></c:out></option>
    				<option value="4" ${batch.level == "4" ? 'selected' : ' '}><c:out value="4" ></c:out></option>
    				<option value="5" ${batch.level == "5" ? 'selected' : ' '}><c:out value="5" ></c:out></option>
    				<option value="6" ${batch.level == "6" ? 'selected' : ' '}><c:out value="6" ></c:out></option>
    				<option value="7" ${batch.level == "7" ? 'selected' : ' '}><c:out value="7" ></c:out></option> 
    				<option value="8" ${batch.level == "8" ? 'selected' : ' '}><c:out value="8" ></c:out></option>
    				<option value="9" ${batch.level == "9" ? 'selected' : ' '}><c:out value="9" ></c:out></option>
    				<option value="10" ${batch.level == "10" ? 'selected' : ' '}><c:out value="10" ></c:out></option>
  					</select>
				</fieldset>
				
				<fieldset class="form-group">
					<label>Lesson Plan</label> <input type="text"
						value="<c:out value='${batch.lessonPlan}' />" class="form-control"
						name="lessonPlan">
				</fieldset>
				

				<button onclick = "validate()" type="submit" class="btn btn-success" style="background-color: indigo">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
