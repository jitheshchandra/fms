<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	var employeeJobModule= angular.module("jobApp",[]);
	employeeJobModule.controller("JobController",JobController);
	function JobController($scope,$http,$filter){
		
		$scope.organizations = [];
	    $scope.branches=[];
		$scope.supervisors = [];
		$scope.shifts = [];
		$scope.unAllocatedEmployees = [];	
		
		//Erorr msg
		var onError = function(reason){
			$scope.message = "Unable to fetch data";
		};
		
		//Get all un-allocated employee
		var getEmpl= "getAllUnAllocatedEmployees";
		
		var populateEmployees = function(response){
			$scope.unAllocatedEmployees = response.data;
		};
		
		$http.post(getEmpl).then(populateEmployees,onError);
		
		//Get all organization
		var getOrg = "getOrganizations";
		
		var populateOrgs = function(response){
			$scope.organizations = response.data;
		};
		
		$http.post(getOrg).then(populateOrgs,onError);
		
		//Get all branches by organization
		$scope.getBranches = function($index){
			$scope.branches[$index]=[];
			$scope.supervisors[$index]=[];
			$scope.shifts[$index]=[];
			
			orgId =this.organization.orgId;
			param = "getBranchByOrgId?orgId="+orgId;
			var populateBranches = function(response){
				$scope.branches[$index]=response.data;
			};
			$http.post(param).then(populateBranches,onError);
		};
	
		//Get supervisors by organization and branch
		$scope.getSupervisors = function($index){
			$scope.supervisors[$index]=[];
			$scope.shifts[$index]=[];
			orgId =this.organization.orgId;	
			brchId=this.branch.id;
			if(orgId!=null&&brchId!=null)
			{
			
			//Get supervisors
			var getSupervisor = "getEmployeesByOrg?orgId="+orgId+"&branchId="+brchId;	
			var populateSupervisors = function(response){
				$scope.supervisors[$index] = response.data;
			};	
			$http.post(getSupervisor).then(populateSupervisors,onError);
			
			//Get shifts
			var getShifts = "getShiftByOrgIdAndBrchId?orgId=" +orgId+ '&branchId=' +brchId;		
		    var papulateShift=function(response){
					$scope.shifts[$index]=response.data.shifts;
				};
			$http.post(getShifts).then(papulateShift,onError);
			};
		};	
		
		 $scope.value = new Date();
	};
});

</script>
</head>
<body ng-app="jobApp"  ng-controller="JobController">				
	<div class="intro-header">
		<h2 style="text-align: center">Employee Job Allocation</h2>
		<br> <br>
		<div class="well" style="margin: 0 5% 0 5%;">
		<div class="input-group" style="width: 250px;">
                <input type="text" placeholder="Search by EmpCode/EmpName" class="form-control" ng-model="search.$"/>
                <span class="input-group-addon">
                <i class="fa fa-search"></i>
                </span>
            </div>
			<div class="table-responsive">
			<form class="form-horizontal" action="/fmsv1/saveEmployeeJobAllocation" method="POST">
				<table class="table table-bordered table-striped" align="center">
					<thead>
						<tr>
							<th class="tbh">Employee Code</th>
							<th class="tbh">Employee Name</th>
							<th class="tbh">Organization</th>
							<th class="tbh">Branch</th>
							<th class="tbh">Supervisor</th>
							<th class="tbh">Shift</th>
							<th class="tbh">Start Date</th>
						</tr>
					</thead>
					
					<tbody>
						<tr ng-model="unAllocatedEmployees" ng-repeat="emp in unAllocatedEmployees|filter:search|orderBy:'empFirstName'">
							<td>
								<input type="hidden" name="jobAlloc[{{$index}}].employeeId" id="jobAlloc[{{$index}}].employeeId" value="{{emp.id}}">{{emp.empCode}}
							</td>
							<td >
								{{emp.empFirstName}}
							</td>
							<td>
								<select class="form-control form-page" name="jobAlloc[{{$index}}].organizationId" id="jobAlloc[{{$index}}].organizationId" ng-model="organization"  ng-options="org.orgName for org in organizations |orderBy:'orgName' track by org.orgId" ng-change="getBranches($index)">
									<option value="">Please select organization</option>
								</select>
							</td>
							<td>
							  <select class="form-control form-page" name="jobAlloc[{{$index}}].branchId" id="jobAlloc[{{$index}}].branchId" ng-model="branch" ng-options="brch.branchName for brch in branches[$index]|orderBy:'branchName' track by brch.id" ng-change="getSupervisors($index)">
						    	<option value="">Please select Branch</option>
							  </select>
							</td>
							<td>
							<select class="form-control" name="jobAlloc[{{$index}}].supervisorId" id="jobAlloc[{{$index}}].supervisorId" ng-model="supervisor" ng-options="sup.empFirstName for sup in supervisors[$index]|orderBy:'empFirstName' track by sup.id" >
							<option value="">Please select Supervisor</option>
							</select>
							</td>
							<td>
							  <select class="form-control" name="jobAlloc[{{$index}}].shiftId" id="jobAlloc[{{$index}}].shiftId" ng-model="shift" ng-options="shift.name for shift in shifts[$index]|orderBy:'name' track by shift.id">
							    <option value="">Please select Shift</option>
							  </select>
							</td>
								<td>
								<input class="form-control" name="jobAlloc[{{$index}}].startDate" id="jobAlloc[{{$index}}].startDate" type="date" ng-model="value"/>
							</td>						
						</tr>
					</tbody>
				</table>
				   <a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="submit" class="btn btn-success" >Save</button>
				</form>
			</div>	
		</div>
	</div>
</body>
</html>