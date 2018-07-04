<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="./assets/js/jquery-1.9.1.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>

<script type="text/javascript">
	$(function() {
		var employeeJobMovementApp = angular.module('employeeJobMovementApp',[]);
		employeeJobMovementApp.controller('employeeJobMovementController',function($scope, $http, $filter) {
							$scope.organizations = [];
							$scope.organization = "";
							$scope.shifts = [];
							$scope.designations = [];
							$scope.jobAllocations = [];
							$scope.employees = [];
							$scope.jobMovements = [];
							
							var getOrg = "getOrganizations";
							//Get all organization
							$http.post(getOrg).success(function(data) {
								$scope.organizations = data;
							});

							//Get all branches by organization
							$scope.getBranch = function() {
								var orgId = $scope.organization;
								$scope.branches = [];
								$scope.branch = "";
								$scope.jobAllocations = [];
								$scope.jobMovements = [];
								$scope.search="";

								var action = "getBranchByOrgId?";
								var param = "orgId=" + orgId;
								$http.post(action + param).success(function(data) {
								$scope.branches = data;
										});
						            	};

							//Get job-movement list by organization & branch
							$scope.getJobMovementList = function() {
								$scope.search="";
								action = "gettingJobMovementList?";
								param = "organizationId=" + $scope.organization+ "&branchId=" + $scope.branch;
								$http.post(action + param).success(function(data)
												 {
													$scope.shifts = data.shifts;
													$scope.designations = data.designations;
													$scope.jobAllocations = data.jobAllocations;
													$scope.employees = data.employees;
												})
										.error(
												function() {
													alert('Sorry no data found for this combination');
												});
							};
							
							//Add row dynamicaly
							$scope.addJobAllocationRow = function() {
								if ($scope.organization
										&& $scope.branch != null) {
									if ($scope.jobMovements.length < $scope.employees.length) {
										$scope.jobMovements.push({
											id : ''
										});
									} else {
										alert('Sorry no more employees');
									};
								} else {
									alert('Please select organization & branch');
								};
							};
							
							//Filter row with slected drop-down
							$scope.filterEmployees=function(emplId){
								debugger;
								 $scope.employees = $filter('filter')(
										$scope.employees, {
											employeeId : emplId
										}, function(e, a) {
											return e != a;
										});
							}
						});
	});
</script>
</head>
<body ng-app="employeeJobMovementApp">
	<div class="intro-header">
		<h2 style="text-align: center">Employee Job Movement</h2>
		<br> <br>
		<div class="well" ng-controller="employeeJobMovementController">
			<form class="form-horizontal" style="margin:auto;" action="/fmsv1/saveEmployeeJobMovement" method="POST">
			<div class="well" style="margin:0% 25% 0% 25%;">
				<div class="form-group">
					<label for="Organization" class="col-sm-4 control-label2">Organization</label>
					<div class="col-sm-6">
						<select class="form-control form-page" name="organizationId"  ng-change="getBranch()" id="organizationId" ng-model="organization">
							<option value="">Please select Organization</option>
							<option ng-repeat="organization in organizations|orderBy:orgName" value="{{organization.orgId}}">{{organization.orgName}}</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="Branch" class="col-sm-4 control-label2">Branch</label>
					<div class="col-sm-6">
						<select class="form-control form-page" name="branchId" ng-model="branch" id="branchId" ng-change="getJobMovementList()">
							<option value="">Please select Branch</option>
							<option ng-repeat="branch in branches|orderBy:branchName" value="{{branch.id}}">{{branch.branchName}}</option>
						</select>
					</div>
				</div>
			</div>
				<br/>	
			<div class="input-group" style="width: 250px;">
                <input type="text" placeholder="Search by EmpCode/EmpName" class="form-control" ng-model="search.$"/>
                <span class="input-group-addon">
                <i class="fa fa-search"></i>
                </span>
            </div>
			<div class="well">
				<div class="table-responsive">
					<table class="table table-bordered table-striped" align="center">
						<thead>
							<tr>
								<th class="tbh">Employee Code</th>
								<th class="tbh">Employee Name</th>
								<th class="tbh">Shift</th>
							    <th class="tbh">Designation</th>	
								<th class="tbh">Comments</th>
								<th class="tbh">Mobile No</th>
							</tr>
						</thead>
						
						<tbody id="rowData">
						 <tr ng-repeat="employee in employees | filter:search">  
						 
						  <td><input type="hidden" name="employees[{{$index}}].fromOrganizationId" value="{{employee.organizationId}}" >
						 {{employee.empCode}}
						  </td>   
						 
						  <td><input class="form-control"  name="employees[{{$index}}].employeeId" id="employees[{{$index}}].employeeId" type="hidden" value="{{employee.id}}">{{employee.firstName}} {{employee.lastName}}</td>
						  
						  <td>
						    <select class="form-control form-page" name="employees[{{$index}}].toShiftId" id="employees[{{$index}}].toShiftId" required >
						     <option value="">Please select shift</option>
						     <option ng-repeat="shift in shifts"  value="{{shift.id}}" >{{shift.name}}</option>
						   </select>
						  </td>
						  
						  <td>
						     <input type="hidden" name="employees[{{$index}}].fromDesignation" id="employees[{{$index}}].fromDesignation" value="{{employee.designationId}}">
						     <select class="form-control form-page" name="employees[{{$index}}].toDesignation" id="employees[{{$index}}].toDesignation" required> 
						       <option value="">Please select Designation</option>
						       <option ng-repeat="designation in designations"  value="{{designation.id}}">{{designation.name}}</option>
						     </select>
						  </td>
						  
						  <td><input type="text" name="employees[{{$index}}].remarks" id="employees[{{$index}}].remarks" class="form-control" maxlength="100"></td>
						  
						  <td><input type="hidden" value="{{employee.phone}}">{{employee.phone}}</td>
						
						</tr> 
						</tbody>
					</table>
					</div>
				</div>
				<a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 <a href="/fmsv1/AddEmergencyEmployee"><button type="button" class="btn btn-info" ng-disabled="branches.length==0" ng-click="addJobAllocationRow()"><span class="glyphicon-plus">Add New Employee</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
			
				<!-- <a href="/fmsv1/AddEmergencyEmployee"><button type="button" class="btn btn-success" disabled="disabled">Add New Employee</button></a>
				&nbsp;&nbsp;&nbsp;&nbsp; -->
				<button type="submit" class="btn btn-success">Save</button>

			</form>
		</div>
		
		
	</div>
</body>
</html>