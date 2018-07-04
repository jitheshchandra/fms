<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	
	  <style>
	  label{
	  	color:black;
		font-family: "Lato","Helvetica Neue",Helvetica,Arial,sans-serif;
		text-align: right;
	  	
	  }
  </style>

</head>
<body>
<div class="intro-header">
	<h2 style="text-align:center ">Employee Details</h2>
	<br>
	<br>
	
		<div class="well" style="margin:0% 25% 0 25%; ">
			<form name="addEmployee" class="form-horizontal" role="form" style="margin-top:5%;margin-left:5%;"action="/fmsv1/updateEmployee/"   method="POST">
				
					<input type="hidden" name="id" id="id" value='<c:out value="${emp.id}"/>' >
				
					 <label for="empcode" class="col-sm-4 control-label2">EmpCode</label>
				
						<input type="text" class="form-control" id="empCode" name="empCode" style="width: 40%;" value='<c:out value="${emp.empCode}"/>' >
				
					<br>
	
					 <label for="firstname" class="col-sm-4 control-label2">FirstName</label>
	
						<input type="text" class="form-control" id="firstName" name="firstName" style="width: 40%;" value='<c:out value="${emp.firstName}"/>' >
	
					<br>	
					
					 <label for="middlename" class="col-sm-4 control-label2">MiddleName</label>
	
						<input type="text" class="form-control" id="middleName" name="middleName" style="width: 40%;" value='<c:out value="${emp.middleName}"/>' >
	
					<br>
	
					 <label for="lastname" class="col-sm-4 control-label2">LastName</label>
	
						<input type="text" class="form-control" id="lastName" name="lastName" style="width: 40%;" value='<c:out value="${emp.lastName}"/>' >
						
					<br>
					
					 <label for="Organization" class="col-sm-4 control-label2">Organization</label>
							<select class="form-control" id="organizationId" name="organizationId" style="width: 40%;">
								<c:forEach items="${organizations}" var="organizations">
									<option value="${organizations.id}">${organizations.name}</option>
								</c:forEach>							
							</select>
					 <br>
					 
					
					<br>
					 <label for="Organization" class="col-sm-4 control-label2">Branch</label>
							<select class="form-control" id="organizationId" name="organizationId" style="width: 40%;">
								<c:forEach items="${branch}" var="branch">
									<option value="${branch.id}">${branch.branchName}</option>
								</c:forEach>							
							</select>
				<br>
						 <label for="designation" class="col-sm-4 control-label2">Designation</label>
							<select class="form-control" id="designationId" name="designationId" style="width: 40%;">
							<c:forEach items="${designations}" var="designations">
									<option value="${designations.id}">${designations.name}</option>
							</c:forEach>
							</select>
					
					 <br>
					 <label for="supervisor" class="col-sm-4 control-label2">Supervisor</label>
					
					<select class="form-control" id="supervisorId" name="supervisorId" style="width: 40%;">
					<c:forEach items="${supervisor}" var="supervisor">
						<option value="${supervisor.id}">${supervisor.firstName}</option>
					</c:forEach>
					</select>
				<br>
					 <label  class="col-sm-4 control-label2">DOB</label>
						<input type="date" class="form-control" id="DOB" name="DOB" style="width: 40%;" required/>
				<br>	
					 <label  class="col-sm-4 control-label2">Email</label>
						<input type="text" class="form-control" id="email" name="email" style="width: 40%;" value='<c:out value="${emp.email}"/>' >
				<br>	
					 <label  class="col-sm-4 control-label2">Phone</label>
						<input type="text" class="form-control" id="phone" name="phone" style="width: 40%;" value='<c:out value="${emp.phone}"/>' >
				<br>
				
					 <label  class="col-sm-4 control-label2">Address Line 1</label>
					 <input type="text" class="form-control" id="address1" name="address1" style="width: 40%;" value='<c:out value="${emp.address1}"/>' >
				
				<br>
					 <label  class="col-sm-4 control-label2">Address Line 2</label>
					<input type="text" class="form-control" id="address2" name="address2" style="width: 40%;" value='<c:out value="${emp.address2}"/>' >	
				<br>
				 <label  class="col-sm-4 control-label2">Address Line 3</label>
				<input type="text" class="form-control" id="address3" name="address3" style="width: 40%;" value='<c:out value="${emp.address3}"/>' >
				<br>		
					 <label for="city" class="col-sm-4 control-label2">City</label>
						<input type="text" class="form-control" id="city" name="city" style="width: 40%;"  value='<c:out value="${emp.city}"/>' >
				<br>		
					 <label for="state" class="col-sm-4 control-label2">State</label>
						<select class="form-control" id="stateId" name="stateId" style="width: 40%;">
							<c:forEach items="${states}" var="states">
								<option value="${states.id}">${states.name}</option>
							</c:forEach>
						</select>
				<br>
					 <label for="country" class="col-sm-4 control-label2">Country</label>
						<select class="form-control" id="countryId" name="countryId" style="width: 40%;">
							<c:forEach items="${countries}" var="countries">
								<option value="${countries.id}">${countries.name}</option>
							</c:forEach>
						</select>
				<br>
				<br>					
							 <button type="button" class="btn btn-primary" id="empFamily">Family</button>
							 <button type="button" class="btn btn-primary">Documents</button> 
							 <a href="/fmsv1/EmployeeJobAllocation/"><button type="button" class="btn btn-primary">Job Allocation</button></a>
						      <button type="button" class="btn btn-primary">Job Movement</button>
							  <button type="reset" class="btn btn-primary">Reset</button>&nbsp;
							  <button type="submit" class="btn btn-success">Save</button>					
			</form>
		</div>

</div>
<script type="text/javascript">
$(document)
			.ready(
					function() {
						//not required ...  use firefox's firebug instead;
						$("#empFamily")
								.click(
										function() {
											//not required ...  use firefox's firebug instead;
											/*$this.attr('disabled', true);*/
											var empId = $('#id')
													.val();											
												window.location.href = "/fmsv1/EmployeeFamilyDetails?eid="+empId;														
										});
					});
</script>

</body>
</html>