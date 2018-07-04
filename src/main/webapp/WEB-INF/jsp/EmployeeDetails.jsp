<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

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
<!--country reading  -->
<script type="text/javascript">
	$(function() {
		var app = angular.module('app', [])
		app.controller('employeeController',
						function($scope, $http) {

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
						});});
</script>

</head>
<body style="height:auto;" ng-app="app">
	<div class="intro-header" >
		<h2 style="text-align: center">Employee Details</h2>
		<br> <br>
<div class="well" style="margin: 0 25% 0 25%; width: auto;"ng-controller="employeeController">
			
			<form class="form-horizontal" style="margin-top: 2%;"  action="/fmsv1/addNewEmployee" method="POST">

				<label for="empcode" class="col-sm-4 control-label2">EmpCode   </label>

				<input type="text" pattern="[0-9]*" class="form-control" id="empCode" name="empCode" style="width: 40%;" required> 
				 <br/>  
				 
			    <label for="firstname"class="col-sm-4 control-label2">FirstName</label>	
			    <input type="text" class="form-control" id="firstName" name="firstName" style="width: 40%;"required> 
			    <br>
			    
			    <label for="middlename" class="col-sm-4 control-label2">MiddleName</label>		
			    <input type="text" class="form-control" id="middleName" name="middleName" style="width: 40%;"required> 
			    <br>
					  
				<label for="lastname" class="col-sm-4 control-label2">LastName</label>
			    <input type="text" class="form-control" id="lastName" name="lastName" style="width: 40%;"required> 
				<br>	
							  
				<div class="form-group">
							<label for="Organization" class="col-sm-4 control-label2">Organization</label>
							<div class="col-sm-5 control-label2">
								<input type="text" class="form-control" name="organization" id="organization" value="${SelectedOrganization.name}" readonly />
								<input type="hidden" class="form-control" name="organizationId" id="organizationId" value="${SelectedOrganization.id}"  />
							</div>
						</div>
						<div class="form-group">
							<label for="branch " class="col-sm-4 control-label2">Branch</label>
							<div class="col-sm-5 control-label2">
								 <input type="text" class="form-control"  name="branch"	id="branch"  value="${SelectedBranch.branchName}" readonly  />
								 <input type="hidden" class="form-control"  name="branchId"	id="branchId"  value="${SelectedBranch.id}"   />
							</div>
						</div>
				 
			    <label for="designation" class="col-sm-4 control-label2">Designation</label>
				<select class="form-control" id="designationId" name="designationId" style="width: 40%;"required> 
					<option value="" selected="selected">Please Select Designation</option>
					<c:forEach items="${designations}" var="designations">
					     <option value="${designations.id}">${designations.name}</option>
					</c:forEach>
				</select> 
				<br> 
				
				<label for="supervisor" class="col-sm-4 control-label2">Supervisor</label>
				<select class="form-control" id="supervisorId" name="supervisorId"
					style="width: 40%;"required> 
					<option value="" selected="selected">Please Select Supervisor</option>
					<c:forEach items="${supervisor}" var="supervisor">
					
						<option value="${supervisor.id}">${supervisor.firstName}</option>
					</c:forEach>
				</select>
				 <br> 
				 
				 <label class="col-sm-4 control-label2">DOB</label> 
				 <input type="date" class="form-control" id="DOB" name="DOB" style="width: 40%;"required> 
				 <br>
				 
			     <label class="col-sm-4 control-label2">Email</label> 
			     <input type="text" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" class="form-control" id="email" name="email" style="width: 40%;"required> 
			     <span style="color:red;margin-left: 5%;">( Email ex: sample@abc.com)</span>
				 <br>
				 
				 <label class="col-sm-4 control-label2">Phone</label>
				 <input type="tel" placeholder="888-888-8888" title="XXX-XXX-XXXX" required pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" maxlength="12"   class="form-control" id="phone" name="phone" style="width: 40%;"required> 
				  <br>
				  
				 <label class="col-sm-4 control-label2">Address Line 1</label> 
				 <input type="text" class="form-control" id="address1" name="address1" style="width: 40%;"required> 
				 <br>
				 
				 <label class="col-sm-4 control-label2">Address Line 2</label>	 
			     <input type="text" class="form-control" id="address2" name="address2" style="width: 40%;">
				 <br>
				 
				 <label class="col-sm-4 control-label2">Address Line 3</label>	
				 <input type="text" class="form-control" id="address3" name="address3" style="width: 40%;">
				 <br> 
				 
				 <label for="city" class="col-sm-4 control-label2">City</label>
				 <input type="text" class="form-control" id="city" name="city" style="width: 40%;"required> 
				 <br>
					

				 <label for="country" class="col-sm-4 control-label2">Country</label>
				 <select class="form-control" name="countryId" id="countryId" ng-model="country" ng-change="getState()" style="width: 30%;" required>
	          <option value="">Please select Country</option>
	          <option ng-repeat="cntr in countries|orderBy:cntr.name" value="{{cntr.id}}">{{cntr.name}}</option>
	        </select>
				<br>
				
				 <label for="state" class="col-sm-4 control-label2">State</label>
				<select class="form-control" name="stateId" id="stateId" style="width: 30%;"required> 
		         <option value="">Please select State</option>
		         <option ng-repeat="sts in states|orderBy:sts.name" value="{{sts.id}}">{{sts.name}}</option>
		      </select>
			    <br>
			    
			     <input type="hidden" class="form-control" id="attendanceStatus" name="attendanceStatus" style="width: 40%;" value="sample"></input>
			     <input type="hidden" class="form-control" id="noOfHourWorked" name="noOfHourWorked" style="width: 40%;" value="2"></input>
				<button type="reset" class="btn btn-primary">Reset</button>&nbsp;
				<button type="submit" class="btn btn-success">Save</button>

			</form>
		</div>

	</div>
	

</body>
</html>