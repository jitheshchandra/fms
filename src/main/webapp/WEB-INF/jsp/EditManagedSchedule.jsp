<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Edit schedules</title>

	<style>
	  	label{
	  	color:black;
		font-family: "Lato","Helvetica Neue",Helvetica,Arial,sans-serif;
		text-align: right;
	  	
	  }
  </style>

	<script type="text/javascript">
	
</script>
	
	<script type="text/javascript">

</script>
</head>
<body >
    <div class="intro-header">
	<h2 style="text-align:center ">Edit Schedules</h2>
	<br>
	<br>
	
	<div class="well" style="margin : 0 25% 0  25%;" width=auto;  >
			


			<form id="createAppointmentForm" class="form-horizontal" action="/fmsv1/SaveEditSchedule" role="form" method="POST">
			<input type="hidden" id="sid" name="sid" value='<c:out value="${scheduleData.id}"/>'/>
			<input type="hidden" id="supervisor" name="supervisor" value='abc'/>
			<input type="hidden" id="managedEntityGroupName" name="managedEntityGroupName" value='abc'/>
			
				<div class="form-group">
					<label for="title " class="col-sm-5 control-label2">Title</label>
					<div class="col-sm-6">
						<input type="text" name="title" id="title" value='<c:out value="${scheduleData.title}"/>'	class="form-control" data-provide="typeahead" readonly/> 
						
					</div>
				</div>

				
				<div class="form-group">
					<label for="starttime " class="col-sm-5 control-label2">Start Time</label>
					<div class="col-sm-6">
						<input type="time" class="form-control" id="startTime"  name="startTime" value='<c:out value="${scheduleData.startTime}"/>' >			
					</div>					
				</div>
 				<div class="form-group">
					<label for="endtime " class="col-sm-5 control-label2">End Time</label>
					<div class="col-sm-6">
						<input type="time" class="form-control" id="endTime" name="endTime" value='<c:out value="${scheduleData.endTime}"/>' >		
					</div>					
				</div>
								
				<div class="form-group">
					<label for="organization " class="col-sm-5 control-label2">Organization	:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="organization" name="organization" value='<c:out value="${Org}"/>' readonly>
					</div>
				</div>

				<div class="form-group">
					<label for="branch " class="col-sm-5 control-label2">Branch:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="branch" name="branch" value='<c:out value="${Branch}"/>' readonly>
					</div>
				</div>
		
				<div class="form-group">
					<label for="employee " class="col-sm-5 control-label2">Employee:</label>
					<div class="col-sm-6">
						<select class="form-control" id="empCode" name="empCode"  style="width: 30%;" required>
							<option value="">Please select/Re Assign Employee</option>
		               	<c:forEach items="${emp}" var="emp">				
									<option value="${emp.id}">${emp.firstName}  ${emp.lastName}</option>				
								</c:forEach>
		            </select>												
								
					</div>
				</div>
			
				   <div class="form-group">
					<label for="managedentitygroup " class="col-sm-5 control-label2">Managed
						Entity Group:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="managedEntityGroupName" name="managedEntityGroupName" readonly>
																											
						</select>
					</div>
				</div> 

				<div class="form-group">
					<label class="col-sm-5 control-label2">ChecklistType:</label>
					<div class="col-sm-6">									
					 	<input type="hidden" id="startDate" name="StartDate" value="" /> 
						<input type="hidden" id="endDate"  name="EndDate" value="" />
						    
					</div>
				</div>
				<p id="b_viewedit" >
		  <a href="/fmsv1/ManageSchedules"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	      <input type="reset" class="btn btn-primary" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  <input type="submit" class="btn btn-success" value="Save">
		  
		  </p>
			</form>
				
				

	
	</div>
</div>
</body>

</html>