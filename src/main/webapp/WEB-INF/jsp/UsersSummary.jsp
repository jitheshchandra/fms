<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>FMS</title>
	<style>
	  label{
	  	color:black;
		font-family: "Lato","Helvetica Neue",Helvetica,Arial,sans-serif;
		text-align: right;  	
	  }
  </style>
<script type="text/javascript">
	$(function() {
		var userSummaryApp = angular.module('userSummaryApp', []);
		userSummaryApp.controller('userSummaryController', function($scope,
				$http,$filter) {
			$scope.organizations = [];
			$scope.organization = "";
			
			$scope.branches = [];
			$scope.branch = "";
			
			$scope.users=[];

			var getOrganization = "getOrganizations";
			$http.post(getOrganization).success(function(data) {
				$scope.organizations = data;
			});

			 $scope.selectBranch = function() {
				 debugger;
				$scope.branches = [];
				$scope.branch = "";
				
				$scope.users=[];

				var orgId = $scope.organization;
				var action = "getBranchByOrgId?";
				var param = "orgId=" + orgId;

				$http.post(action + param).success(function(data) {
					$scope.branches = data;
				});
			};
			
			$scope.getUserList=function(){
				debugger;
				$scope.users=[];
				
				var orgId=$scope.organization;
				var brchId=$scope.branch;
				
				var action="getAllUser?";
				var param="orgId="+orgId+"&brchId="+brchId;
				
				$http.post(action+param).success(function(data){
					$scope.users=data.users;
				});
			};
			
			$scope.editUser=function(userId){
				action="EditUser?";
				param="userId="+userId;
				$http.post(action+param).success(function(data){
					$scope.users=data.users;
				});
			};
			
			$scope.deleteUser=function(userId){
				action="deleteUser?";
				param="userId="+userId;
				$http.post(action+param).success(function(data){
					if(data==true)
						{
						$scope.users = $filter('filter')($scope.users, {
							id : userId
						}, function(e, a) {
							return e != a;
						});
						};
				});
			};	
			$scope.addUser=function()
			{
				if($scope.organization==""||$scope.branch=="")
					{
					alert('Please select Organization or Branch');
					return false;
					}
				window.location.href = "/fmsv1/AddUser";
			};
		});
	});
</script>
</head>
<body ng-app="userSummaryApp">
	<div class="intro-header">
	<h2 style="text-align:center ">Users Summary</h2>
	<br>
	<br>
	<div class="well" style="margin : 0 25% 0  25%;" ng-controller="userSummaryController">
			<form class="form-horizontal" style="margin-top: 3%;" role="form">
				<div class="form-group">
		
					 <label for="orgnazation" class="col-sm-4 control-label">Organization</label>
					 <div class="col-sm-6">
                           <select class="form-control" ng-model="organization" ng-change="selectBranch()">
                           <option value="">Please select Organization</option>
                           <option ng-repeat="organization in organizations|orderBy:'orgName'" value="{{organization.orgId}}">{{organization.orgName}}</option>				
						</select>
					 </div>
				</div>
				<div class="form-group">
					 <label for="branch" class="col-sm-4 control-label">Branch</label>
					 <div class="col-sm-6">
                           <select class="form-control" ng-model="branch" ng-change="getUserList()">
                           <option value="">Please select Branch</option>
                           <option ng-repeat="branch in branches|orderBy:'branchName'" value="{{branch.id}}">{{branch.branchName}}</option>
						</select>
					 </div>
				</div>
				<br/>
				<br/>
			<div class="table-responsive">
			<table class="table table-bordered table-striped" style="size:auto;">
				<thead >
					<tr>
						<th>
							User Name
						</th>
						<th>
							User Type
						</th>
						<th style="width: 75px;text-align: center;">
							<button type="button" ng-disabled="branches.length==0"  id="add" class="btn btn-info" ng-click="addUser()">Add</button>
						</th>			
					</tr>
				</thead>
				
				<tbody>
				  <tr ng-repeat="user in users | orderBy:'userName'">
				  <td><a href="/fmsv1/EditUser?userId={{user.id}}">{{user.userName}}</a></td>
				  <td>{{user.userType}}</td> 
				  <td style="width: 75px;text-align: center;"><a href="#" class="btn btn-sm btn-danger" ng-click="deleteUser(user.id)"><span class="glyphicon glyphicon-trash"></span></a></td>
				  </tr>
				</tbody>
			</table> 
			</div>
			<a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</form>
		</div>
	</div>	
</body>
</html>