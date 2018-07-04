<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="./assets/js/jquery-1.9.1.js"></script>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<title>Employee Attendance</title>
<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>
<script type="text/javascript">
	$(function() {
		var employeeAttendanceApp = angular.module('employeeAttendanceApp', []);
		employeeAttendanceApp.controller('employeeAttendanceController',
				function($scope, $http) {

					$scope.organizations = [];
					$scope.organization = "";

					$scope.branches = [];
					$scope.branch = "";

					$scope.shifts = [];
					$scope.shift = "";
					$scope.attendanceDates=[];
					$scope.attendanceDate="";

					$scope.employees = [];

					var param = "getOrganizations";

					$http.post(param).success(function(data) {
						$scope.organizations = data;
					});

					$scope.getBranch = function() {
						$scope.branches = [];
						$scope.branch = "";

						$scope.shifts = [];
						$scope.shift = "";

						$scope.employees = [];

						var action = "getBranchByOrgId?";
						var param = 'orgId=' + $scope.organization;

						$http.post(action + param).success(function(data) {
							$scope.branches = data;
						});
					};

					$scope.getShift = function() {
						$scope.shifts = [];
						$scope.shift = "";
						$scope.employees = [];

						var action = "getShiftByOrgIdAndBrchId?";
						var param = 'orgId=' + $scope.organization
								+ '&branchId=' + $scope.branch;

						$http.post(action + param).success(function(data) {
							$scope.shifts = data.shifts;
						});
					};

					/*$scope.getAttendanceBasedOnDate = function() {
						debugger;
						var action="GetEmployeesByOrgBranchAndShift?""
					//	 data = {
					//		organizationId : $scope.organization,
					//		branchId:$scope.branch,
					//		shiftId : $scope.shift,
					//		attendanceDate : $scope.attendanceDate
					//	}; 
  
						 var param='orgId=' + $scope.organization+ '&branchId=' + $scope.branch+ "&shiftId=" + $scope.shift;
								
						$http.post(action+ param).success(
								function(data) {
									
									$scope.attendanceDates = data.attendanceDate;
									
								});
					};*/
					$scope.getEmployee = function() {
						$scope.employees = [];
						$('#addEmployee').removeAttr('disabled');
						$('#save').removeAttr('disabled');
						var action = "getEmployeeByOrgAndShift?";
						var param = "orgId=" + $scope.organization
								+ "&shiftId=" + $scope.shift+ '&branchId=' + $scope.branch+ '&attendanceDate=' + $scope.attendanceDate;
						$http.post(action + param).success(function(data) {
							$scope.attendance = 'P';
							$scope.employees = data.employees;
							alert('..');
						});
					};
		
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

	/* var ck_name = /^[A-Za-z]{3,50}$/;
	function validate(form) {
		////not required ...  use firefox's firebug instead;
		var organizationId = form.organizationId.value;
		if (!organizationId == 0 || null || "")

		{
			var branchId = form.branchId.value;
			if (!branchId == 0 || null || "") {
				var date = form.attendanceDate.value;
				if (!date == "" || null) {

					var shiftId = form.shiftId.value;

					if (!shiftId == 0 || null || "") {

						if (organizationId == "" || branchId == ""
								|| organizationId == 0 || branchId == 0
								|| organizationId == null || branchId == null) {
							errors[errors.length] = "";
						}
						if (errors.length > 0) {
							reportErrors(errors);
							return false;
						}
						return true;
					}
					alert("***** Please select shift *****");
					return false;

				}
				alert("***** Please select date *****");
				return false;

			}
			alert("***** Please select Branch *****");
			return false;
		}

		alert("***** Please select respective fields******");
		return false;
	}
	//return true;
	function reportErrors(errors) {
		var msg = "Shift Name Should Be in Letters,Starting and Ending Time Should Not Be Same";
		alert(msg);
	} */

	/* $(document).ready(function() 
			{$("#addEmployee").click(function() 
								{						
									var orgName = $('#organizationId').val();												
									var branchName = $('#branchId').val();												
									var shiftName=$('#shiftId').val();		
									var date=$('#attandenceDate').val();
									if (orgName == ""|| branchName == "" || shiftName== "" || date== "")
									{
										alert('Sorry Select And Enter The Field');
										return false;
									} 
									else
									{
										window.location.href = "/fmsv1/AddEmergencyEmployee?organizationId="+orgName+"&branchId="+branchName+"&shiftId="+shiftName;
									}
								});
			}); */
</script>
</head>
<body ng-app="employeeAttendanceApp">
	<div class="intro-header">
		<h2 style="text-align: center">Employee Attendance</h2>
		<br> <br>
		<div class="well" style="margin: 0 25% 0 25%;"
			ng-controller="employeeAttendanceController">
			<form:form class="form-horizontal" action="/fmsv1/saveAttendance/"
				method="POST" style="margin-top: 3%;"
				onsubmit="return validate(this);"
				modelAttribute="employeeAttandenceForm">
				<div class="form-group">
					<label for="Organization" class="col-sm-4 control-label2">Organization</label>
					<div class="col-sm-3">
						<select class="form-control form-page" ng-model="organization"
							name="organizationId" id="organizationId" style="width: auto;"
							ng-change="getBranch()" requerd>
							<option value="">Please Select Organization</option>
							<option
								ng-repeat="organization in organizations | orderBy:orgName"
								value="{{organization.orgId}}">{{organization.orgName}}</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="Branch" class="col-sm-4 control-label2" required>Branch</label>
					<div class="col-sm-3">
						<select class="form-control form-page" name="branchId"
							id="branchId" style="width: auto;" ng-model="branch"
							ng-change="getShift()">
							<option value="">Please select Branch</option>
							<option ng-repeat="branch in branches |orderBy:branchName"
								value="{{branch.id}}">{{branch.branchName}}</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="user type" class="col-sm-4 control-label2">Shift</label>
					<div class="col-sm-3">
						<select class="form-control form-page" name="shiftId" id="shiftId" style="width: auto;"  ng-model="shift" requerd>
							<option value="">Please select Shift</option>
							<option ng-repeat="shift in shifts" value="{{shift.id}}">{{shift.name}}</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="user type" class="col-sm-4 control-label2">Date</label>
					<div class="col-sm-3">
						<input type="date" class="form-control" id="attendanceDate"	ng-model="attendanceDate" name="attendanceDate"	style="width: auto;" ng-change="getEmployee()" />
					</div>
				</div>

				<table class="table table-bordered table-condensed" align="center">
					<thead>
						<tr>
							<th class="tbh">Srl No</th>
							<th class="tbh">Employee Name</th>
							<th class="tbh">Present</th>
							<th class="tbh">Absent</th>
							<th class="tbh">Weekly Off</th>
							<th class="tbh">Leave</th>
						</tr>
					</thead>

					<tbody>
						<tr ng-repeat="employee in employees">
							<td>{{$index+1}}</td>
							<td><input type="hidden"
								name="employees[{{$index}}].employeeId"
								id="employees[{{$index}}].employeeId" value="{{employee.id}}">{{employee.firstName}}
								{{employee.lastName}}</td>
							<td><input type="radio" id="employees[{{$index}}].status"
								name="employees[{{$index}}].status"
								ng-checked="employee.attendanceStatus" ng-model="attendance"
								value="P"></td>
							<td><input type="radio" id="employees[{{$index}}].status"
								name="employees[{{$index}}].status"
								ng-checked="employee.attendanceStatus" ng-model="attendance"
								value="A"></td>
							<td><input type="radio" id="employees[{{$index}}].status"
								name="employees[{{$index}}].status"
								ng-checked="employee.attendanceStatus" ng-model="attendance"
								value="WO"></td>
							<td><input type="radio" id="employees[{{$index}}].status"
								name="employees[{{$index}}].status"
								ng-checked="employee.attendanceStatus" ng-model="attendance"
								value="L"></td>
						</tr>
					</tbody>
				</table>
				<button type="button" class="btn btn-info" disabled="disabled"
					data-toggle="modal" data-target="#myModal" id="addEmployee">+New
					Employee</button>
				<button type="submit" class="btn btn-success" disabled="disabled"
					id="save">Save</button>
			</form:form>
			<form:form action="#" method="POST" style="margin-top: 3%;"
				onsubmit="return validate(this);">
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document" style="width: 850px;">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel" style="color: black;">Add
									New Employee</h4>
							</div>
							<div class="modal-body">


								    <input type="hidden" name="organizationId" id="organizationId"
									    value='<c:out value="${organizationId}"/>' /> 
									<input type="hidden" name="branchId" id="branchId"
									   value='<c:out value="${branchId}"/>' /> 
									<input type="hidden" name="shiftId" id="shiftId" 
									   value='<c:out value="${shiftId}"/>' />
								     <input type="hidden" name="attendanceDate"  id="attendanceDate"/>
								           
								           <table class="table table-striped">
									<tr >
										<td colspan="2"><label for="empcode" class="col-sm-4 control-label2">EmpCode</label>
											<input type="text" class="form-control" id="empCode"
											name="empCode" style="width: 50%;" maxlength="20"
											placeholder="Enter Emplyee Code" required /></td>
									</tr>
									<tr>

										<td><label for="firstname"
											class="col-sm-4 control-label2">FirstName</label> <input
											type="text" class="form-control" id="firstName"
											name="firstName" style="width: 50%;" maxlength="50"
											placeholder="Enter Emplyee FirstName" required /></td>
										<td><label for="lastname" class="col-sm-4 control-label2">LastName</label>
											<input type="text" class="form-control" id="lastName"
											name="lastName" style="width: 50%;" maxlength="14"
											maxlength="50" placeholder="Enter Emplyee LastName" required />
										</td>
									</tr>
									<tr>
										<td><label for="lastname" class="col-sm-4 control-label2">Supervisor</label>
										<select class="form-control" id="supervisorId" name="supervisorId" style="width: 50%;">
											<option value="" selected="selected" value="">Please Select Supervisor</option>
												<c:forEach items="${supervisor}" var="supervisor">
													<option value="1">${supervisor.firstName}</option>
												</c:forEach>
										</select></td>

										<td><label for="designation"
											class="col-sm-4 control-label2">Designation</label><select
											class="form-control" id="designationId" name="designationId"
											style="width: 50%;">
												<option value="" selected="selected">Please Select
													Designation</option>
												<c:forEach items="${designations}" var="designations">
													<option value="1">${designations.name}</option>
												</c:forEach>
										</select></td>
									</tr>
									<tr >
										<td colspan="2"><label class="col-sm-4 control-label2">Phone</label><input
											type="text" maxlength="14" value="+91 " class="form-control"
											id="phone" name="phone" style="width: 50%;" required /></td>
									</tr>

									<tr >
										<td colspan="2"><label class="col-sm-4 control-label2">Address
												Line 1</label><input type="text" class="form-control" id="address1"
											name="address1" style="width: 50%;" maxlength="50"
											placeholder="Enter Address" required></td>
									</tr>
									<tr >
										<td colspan="2"><label class="col-sm-4 control-label2">Address
												Line 2</label><input type="text" class="form-control" id="address2"
											name="address2" style="width: 50%;" maxlength="50"
											placeholder="Enter Address" required></td>
									</tr>
									<tr >
										<td colspan="2"><label class="col-sm-4 control-label2">Address
												Line 3</label> <input type="text" class="form-control" id="address3"
											name="address3" style="width: 50%;" maxlength="50"
											placeholder="Enter Address" required></td>
									</tr>
									<tr  >
										<td colspan="2"><label for="city" class="col-sm-4 control-label2">City</label>
											<input type="text" class="form-control" id="city" name="city"
											style="width: 50%;" maxlength="50" placeholder="Enter City"
											required></td>
									</tr>

									<tr>
										<td colspan="2" ><label for="country" class="col-sm-4 control-label2">Country</label>
											<select class="form-control" name="countryId" id="countryId" ng-model="country" ng-change="getState()" 
											                    style="width: 30%;" required>
	                                                             <option value="">Please select Country</option>
	                                           <option ng-repeat="cntr in countries|orderBy:cntr.name" value="{{cntr.id}}">{{cntr.name}}</option>
	                                        </select></td>
									</tr>
									<tr>
										<td colspan="2" ><label for="state" class="col-sm-4 control-label2">State</label>
											<select class="form-control" name="stateId" id="stateId" style="width: 30%;"required> 
		                          <option value="">Please select State</option>
		                        <option ng-repeat="sts in states|orderBy:sts.name" value="{{sts.id}}">{{sts.name}}</option>
		                                  </select></td>
									</tr>
								</table>
							</div>
							<div class="modal-footer">
								<br>
								<button type="reset" class="btn btn-primary">Reset</button>
								&nbsp;
								<button type="submit" class="btn btn-success">Save</button>
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<br />
	<br />
</body>
</html>