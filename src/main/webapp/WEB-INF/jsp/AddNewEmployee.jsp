<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Employee</title>

<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>
<script type="text/javascript">
$(function(){
	 var employeedetailsApp=angular.module('employeedetailsApp',[]);
	 addOrgApp.controller("employeedetailsController",function($scope,$http){
		$scope.countries=[];
		$scope.country="";
		
		$scope.states=[];
		$scope.state="";
		
		$http.post("getCountry").success(function(data) {
			$scope.countries = data;
		});
		
		$scope.getState=function(){
			debugger;
			$http.post("getStates?cntrId="+$scope.country).success(function(data)
					{
				$scope.states=data;
					});
		};
	 });
 });
</script>
</head>
<body>
	<div class="intro-header">
		<h2 style="text-align: center">Employee Details</h2>
		<br> <br>

		<div class="well" style="margin: 2% 26% 0 26%; padding-right: 4%">
			<form name="addEmployee" class="form-horizontal" role="form" style="margin-top: 5%; margin-left: 3%;" action="/fmsv1/SaveEmergencyEmployee" method="POST">
				
				<input type="hidden" name="organisationId" id="organisationId" value='<c:out value="${organizationId}"/>'/>
				<input type="hidden" name="branchId" id="branchId" value='<c:out value="${branchId}"/>'/>
				<input type="hidden" name="shiftId" id="shiftId" value='<c:out value="${shiftId}"/>'/> />
				<input type="hidden" name="date"/>
				
				<label for="empcode" class="col-sm-5 control-label2">EmpCode</label>
				<input type="text" class="form-control" id="empCode" name="empCode" style="width: 40%;" maxlength="20" placeholder="Enter Emplyee Code" required/>
				 <br>
				 
			    <label for="firstname"class="col-sm-5 control-label2">FirstName</label>	
			    <input type="text" class="form-control" id="firstName" name="firstName" style="width: 40%;" maxlength="50" placeholder="Enter Emplyee FirstName" required/>
			    <br>
			 			  
				<label for="lastname" class="col-sm-5 control-label2">LastName</label>
			    <input type="text" class="form-control" id="lastName" name="lastName" style="width: 40%;" maxlength="14" maxlength="50" placeholder="Enter Emplyee LastName" required/>
				<br> 
				
				<label for="supervisor" class="col-sm-5 control-label2">Supervisor</label>
				<select class="form-control" id="supervisorId" name="supervisorId"
					style="width: 40%;">
					<option value="" selected="selected">Please Select Supervisor</option>
					<c:forEach items="${supervisor}" var="supervisor">
					
						<option value="${supervisor.id}">${supervisor.firstName}</option>
					</c:forEach>
				</select>
				 <br> 
			    
			     <br>
				 
			    <label for="designation" class="col-sm-5 control-label2">Designation</label>
				<select class="form-control" id="designationId" name="designationId" style="width: 40%;">
					<option value="" selected="selected">Please Select Designation</option>
					<c:forEach items="${designations}" var="designations">
					     <option value="${designations.id}">${designations.name}</option>
					</c:forEach>
				</select> 
				<br> 
			    
			    		  	 
				 <label class="col-sm-5 control-label2">Phone</label>
				 <input type="text" maxlength="14" value="+91 " class="form-control" id="phone" name="phone" style="width: 40%;" required/>
				  <br>
				  
				 <label class="col-sm-5 control-label2">Address Line 1</label> 
				 <input type="text" class="form-control" id="address1" name="address1" style="width: 40%;" maxlength="50" placeholder="Enter Address" required>
				 <br>
				 
				 <label class="col-sm-5 control-label2">Address Line 2</label>	 
			     <input type="text" class="form-control" id="address2" name="address2" style="width: 40%;" maxlength="50" placeholder="Enter Address" required>
				 <br>
				 
				 <label class="col-sm-5 control-label2">Address Line 3</label>	
				 <input type="text" class="form-control" id="address3" name="address3" style="width: 40%;" maxlength="50" placeholder="Enter Address" required>
				 <br> 
				 
				 <label for="city" class="col-sm-5 control-label2">City</label>
				 <input type="text" class="form-control" id="city" name="city" style="width: 40%;" maxlength="50" placeholder="Enter City"  required>
				 <br>
					

				 <label for="country" class="col-sm-5 control-label2">Country</label>
				 <select class="form-control" id="countryId" name="countryId" style="width: 40%;">
					<option value="">Please select Country</option>			
					   <c:forEach items="${countries}" var="countries">
						  <option value="${countries.id}">${countries.name}</option>
					   </c:forEach>
				</select> 
				<br>
				
				 <label for="state" class="col-sm-5 control-label2">State</label>
				 <select class="form-control" id="stateId" name="stateId" style="width: 40%;">	
					<option value="">Please select State</option>		
					  <c:forEach items="${states}" var="states">
						  <option value="${states.id}">${states.name}</option>
					  </c:forEach>
				</select>
			    <br>
			    	    
				<button type="reset" class="btn btn-primary">Reset</button>
				&nbsp;
				<button type="submit" class="btn btn-primary">Save</button>

			</form>
		</div>

	</div>
	

</body>
</html>