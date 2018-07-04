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
  <title>Employee Grooming  Report</title>
  	  <style>
	  label{
	  	color:black;
		font-family: "Lato","Helvetica Neue",Helvetica,Arial,sans-serif;
		text-align: right;
	  	
	  }
  </style> 
</head>

<script type="text/javascript">
$(function(){
	var employeeGroomingApp=angular.module('employeeGroomingApp',[]);
	employeeGroomingApp.controller('employeeGroomingController',function($scope,$http){
		$scope.groomingDay=new Date();
		
		$scope.organizations=[];
		$scope.organization="";
		
		$scope.branches=[];
		$scope.branch="";
		
		$scope.shifts=[];
		$scope.shift="";
		
		$scope.employees=[];
		$scope.employee="";
		
		$scope.checkLists=[];
		
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
			
			$scope.checkLists=[];
			
			var action = "getBranchByOrgId?";
			var param = 'orgId=' +$scope.organization;
			$http.post(action+param).success(function(data){
				$scope.branches=data;
			})
		};
		
		$scope.resetAll=function(){
			$scope.shifts=[];
			$scope.shift="";
			
			$scope.employees=[];
			$scope.employee="";
			
			$scope.checkLists=[];
		};
		
		$scope.getShift=function(){			
			$scope.shifts=[];
			$scope.shift="";
			
			$scope.employees=[];
			$scope.employee="";
			
			$scope.checkLists=[];
			
			var action="getShiftByOrgIdAndBrchId?";
			var param='orgId=' + $scope.organization+'&branchId='+$scope.branch;
			
			$http.post(action+param).success(function(data){
				$scope.shifts=data.shifts;
			});
		};
		
		/* private Long id;
		private Long organizationId;
		private Long branchId;
		private Long shiftId;
		private Date groomingDay;
		private Long employeeId;
		private List<EmloyeeGroomingCheckList> checkList;
		private Long modifiedBy;
		private Date ModifiedOn; */
		
		$scope.getEmployee=function(){	
			$scope.employees=[];
			$scope.employee="";
			
			$scope.checkLists=[];
			
			data={
					organizationId:$scope.organization,
					branchId:$scope.branch,
					shiftId:$scope.shift,
					groomingDay:$scope.groomingDay,
					employeeId:$scope.employee			
			}	
			action="getEmployeeBygroomingDay";
			$http.post(action,data).success(function(data){
				$scope.employees=data.employees;
			});
		};
		
		$scope.getCheckList=function(){	
			
			$scope.checkLists=[];
			
			data={
					organizationId:$scope.organization,
					branchId:$scope.branch,
					shiftId:$scope.shift,
					groomingDay:$scope.groomingDay,
					employeeId:$scope.employee			
			}	
			action="getCheckListForGrooming";
			$http.post(action,data).success(function(data){
				$scope.checkLists=data.checkList;
			}).erorr(function(){
				alert('Sorry no Check List');
			});
		};
	});
});

		var ck_name = /^[A-Za-z]{3,50}$/;
		var ck_noHr= /^[ 0-9][.,:-]$/;
	
		function validate(form) {
			debugger;
			
			var organizationId = form.organizationId.value;
			if (!organizationId == 0 || null || "")

			{
				var branchId = form.branchId.value;
				if (!branchId == 0 || null || "") {
					var date = form.groomingDay.value;
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
	</head>
	<body ng-app="employeeGroomingApp">
	<div class="intro-header" ng-controller="employeeGroomingController">
		<h2 style="text-align: center">Employee Grooming</h2>
		<br> 
		<br>
		<div class="well" style="margin: 0 24% 0 25%;">
			<form :form class="form-horizontal" action="/fmsv1/saveEmployeeGrooming" method="POST" style="margin-top: 3%;" onsubmit="return validate(this);"
			        modelAttribute="employeeGroomingForm">	
				<div class="form-group">
					<label for="Organization" class="col-sm-5 control-label2">Organization</label>
					<div class="col-sm-3">
						<select class="form-control form-page" name="organizationId" id="organizationId" ng-model="organization" ng-change="getBranch()" style="width: auto;" required>	
							<option value="">Please Select Organization</option>
							<option ng-repeat="organization in organizations|orderBy:'orgName'" value="{{organization.orgId}}">{{organization.orgName}}</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="Branch" class="col-sm-5 control-label2">Branch</label>
					<div class="col-sm-3">
						<select class="form-control form-page" name="branchId"
							id="branchId" ng-model="branch" ng-change="resetAll()" style="width: auto;" required>
							<option value="">Please select branch</option>
							<option ng-repeat="branch in branches|orderBy:'branchName'" value="{{branch.id}}">{{branch.branchName}}</option>
						</select>
					</div>
				</div>
				
				<c:set var="now" value="<%=new java.util.Date()%>" />		
				<div class="form-group">
					<label for="user type" class="col-sm-5 control-label2">Date</label>
					<div class="col-sm-3">
						 <input type="date" class="form-control" id="groomingDay" ng-model="groomingDay" name="groomingDay"  ng-change="getShift()" style="width: auto;" value="${now}" required/>
					</div>
				</div>
		
				<div class="form-group">
					<label for="user type" class="col-sm-5 control-label2">Shift</label>
					<div class="col-sm-3">
						<select class="form-control form-page" name="shiftId" id="shiftId"
							style="width: auto;" ng-change="getEmployee()" ng-model="shift" required>
							<option value="">Please select Shift</option>
							<option ng-repeat="shift in shifts" value="{{shift.id}}">{{shift.name}}</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="user type" class="col-sm-5 control-label2">Employee</label>
					<div class="col-sm-3">
					 <select class="form-control form-page" name="employeeId" id="employeeId" ng-model="employee"
							style="width: auto;" ng-change="getCheckList()" required>
							<option value="">Please select employee</option>
							<option ng-repeat="employee in employees" value="{{employee.id}}">{{employee.firstName}} {{employee.lastName}}</option>
						</select>
					</div>
				</div>
				
				
				
				<div class="table-responsive">
					<table style="margin: auto;">
						<thead>
							<tr>
								<th style="padding-top: 10px">List Of Check List</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="checkList in checkLists">
							<td style="padding-top: 10px"><input type="checkbox" ng-checked="{{checkList.defaultChecklist}}" name="checkList[{{$index}}].checkListId" id="checkList[{{$index}}].checkListId" value="{{checkList.id}}"/>{{checkList.checkListName}}</td> 
							</tr>
						</tbody>
					</table>
				</div>
				<br/>
	<a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="submit" class="btn btn-success">Save</button>	
</form>
</div>
</div>
</body>
</html>
