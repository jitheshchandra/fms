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
$(function(){
	var employeeOverTimeApp=angular.module('employeeOverTimeApp',[]);
	employeeOverTimeApp.controller('employeeOverTimeController',function($scope,$http){
		$scope.overTimeDate=new Date();
		
		$scope.organizations=[];
		$scope.organization="";
		
		$scope.branches=[];
		$scope.branch="";
		
		$scope.shifts=[];
		$scope.shift="";
		
		$scope.employees=[];
		$scope.employee="";
		
		$http.post('getOrganizations').success(function(data){
			$scope.organizations=data;
		});
		
		$scope.getBranch=function(){
			$scope.branches=[];
			$scope.branch="";
			
			$scope.shifts=[];
			$scope.shift="";
			
			$scope.employees=[];
			$scope.employee="";
			
			var action = "getBranchByOrgId?";
			var param = 'orgId=' +$scope.organization;
			$http.post(action+param).success(function(data){
				$scope.branches=data;
			})
		};
		
		$scope.getShift=function(){
			
			$scope.shifts=[];
			$scope.shift="";
			
			$scope.employees=[];
			$scope.employee="";
			
			var action="getShiftByOrgIdAndBrchId?";
			var param='orgId=' + $scope.organization+'&branchId='+$scope.branch;
			
			$http.post(action+param).success(function(data){
				$scope.shifts=data.shifts;
			});
		};
		/* $scope.getEmployee=function(){
			
			$scope.employees=[];
			$scope.employee="";
			
			$scope.overTimeDate="";
			
			action="getEmployeeByOrgIdAndShiftId?";
			param="orgId="+$scope.organization+"&shiftId="+$scope.shift;
			
			$http.post(action+param).success(function(data){
				debugger;
				//$scope.employees=data.employees;
			});
		}; */
		
		$scope.gettingOverTimeByDate=function(){
			$scope.employees=[];
			$scope.employee="";
	
			data={ 
				organizationId:$scope.organization,
				branchId:$scope.branch,
				shiftId:$scope.shift,
				overTimeDate:$scope.overTimeDate
			}
			
			action="getEmployeeOverTimeByDate?"
			$http.post(action,data).success(function(data){
				$scope.employees=data.employees;
			}).erorr(function(data){
				alert('nooo')
			});
		};
		

		$scope.addEmployeeOverTime=function(){
			data={
					organizationId:$scope.organization,
					shiftId:$scope.shift,
					overTimeDate:$scope.overTimeDate,
				    employees:$scope.employees	
			     }
			action="saveOverTime";
			$http.post(action,data).success(function(data){
				
			}).erorr(function(data){
				alert('Sorry cont save overtime')
			});
			};
			
			$scope.resetField=function()
			{
				$scope.overTimeDate=new Date();
				$scope.shifts=[];
				$scope.shift="";
				
				$scope.employees=[];
			};
	});
});
</script>
</head>

<!-- action="/fmsv1/addEmployeeOverTime" method="POST" ng-submit=addEmployeeOverTime()-->

<body ng-app="employeeOverTimeApp">
	
	<div class="intro-header" ng-controller="employeeOverTimeController">
		<h2 style="text-align: center">Employee OverTime</h2>
		<br> 
		<br>
		<div class="well" style="margin: 0 24% 0 25%;">
			<form class="form-horizontal" action="/fmsv1/addEmployeeOverTime" method="POST" style="margin-top: 3%;" onsubmit="return validate(this);">	
				<div class="form-group">
					<label for="Organization" class="col-sm-5 control-label2">Organization</label>
					<div class="col-sm-3">
						<select class="form-control form-page" name="organizationId" id="organizationId" ng-model="organization" ng-change="getBranch()" style="width: auto;">	
							<option value="">Please Select Organization</option>
							<option ng-repeat="organization in organizations|orderBy:orgName" value="{{organization.orgId}}">{{organization.orgName}}</option>					
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="Branch" class="col-sm-5 control-label2">Branch</label>
					<div class="col-sm-3">
						<select class="form-control form-page" name="branchId"
							id="branchId" ng-model="branch"  ng-change="resetField()" style="width: auto;">
							<option value="">Please select branch</option>
							<option ng-repeat="branch in branches|orderBy:branchName" value="{{branch.id}}">{{branch.branchName}}</option>					
						</select>
					</div>
				</div>
				
				<c:set var="now" value="<%=new java.util.Date()%>"/>		
				<div class="form-group">
					<label for="user type" class="col-sm-5 control-label2">Date</label>
					<div class="col-sm-3">
						 <input type="date" class="form-control" id="overTimeDate" name="overTimeDate" ng-model="overTimeDate" ng-change="getShift()" style="width: auto;" value="${now}" required/>
					</div>
				</div>
		
				<div class="form-group">
					<label for="user type" class="col-sm-5 control-label2">Shift</label>
					<div class="col-sm-3">
					 <select class="form-control form-page" name="shiftId" id="shiftId" ng-model="shift"  ng-change="gettingOverTimeByDate()" 
							style="width: auto;">
							<option value="">Please select shift</option>
							<option ng-repeat="shift in shifts" value="{{shift.id}}">{{shift.name}}</option>				
						</select>
					</div>
				</div>
				
				
				<table class="table table-bordered table-condensed" align="center">
					<thead>
						<tr>
						<th class="tbh">Sl No</th>
							<th class="tbh">Employee Code</th>
							<th class="tbh">Employee Name</th>
							<th class="tbh">Hours Worked</th>
						</tr>
					</thead>
					<tbody>
					   <tr ng-repeat="employee in employees">
					    <td>{{$index+1}}</td>
					    <td>{{employee.empCode}}</td>
					    <td><input type="hidden" name="employees[{{$index}}].employeeId" value="{{employee.id}}">{{employee.firstName}} {{employee.lastName}}</td>
					    <td><input type="text"  name="employees[{{$index}}].noOfHours" class="form-control" value="{{employee.noOfHourWorked}}" required></td>
					   </tr>
					</tbody>				
				</table>
				<a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="submit" class="btn btn-success">Save</button>				
			</form>
		</div>
	</div>
	<br />
	<br />
	
	<script type="text/javascript">
		var ck_name = /^[A-Za-z]{3,50}$/;
		var ck_noHr= /^[ 0-9][.,:-]$/;
	
		function validate(form) {
			var organizationId = form.organizationId.value;
			if (!organizationId == 0 || null || "")
			{
				var branchId = form.branchId.value;
				if (!branchId == 0 || null || "") {
					var date = form.attendanceDate.value;
					if (!date == "" || null) {

						var shiftId = form.shiftId.value;

						if (!shiftId == 0 || null || "") 
						{

							if (organizationId == "" || branchId == ""
									|| organizationId == 0 || branchId == 0
									|| organizationId == null
									|| branchId == null) 
							{
								errors[errors.length] = "";
							}
							
							if (errors.length > 0) 
							{
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
			var msg = "Shift Name Should Be in Letters,Statrting and Ending Time Should Not Be Same";
			alert(msg);
		}
	</script>
	
</body>
</html>