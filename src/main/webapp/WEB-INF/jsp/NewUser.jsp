<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
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
		var addNewUserApp = angular.module('addNewUserApp', []);
		addNewUserApp.controller('addNewUserController', function($scope,
				$http, $filter) {
			$scope.branches = [];
			$scope.organizations = [];
			$scope.UserTypes = [];
			var getOrganization = "getOrganizations";
			$http.post(getOrganization).success(function(data) {
				$scope.organizations = data;
			});

			$http.post('getAllUserTypes').success(function(data) {
				$scope.UserTypes = data;
			});

			$scope.getBranch = function($event, orgId) {
				debugger;
				var checkbox = $event.target;
				var action = "getBranchByOrgId?";
				var param = "orgId=" + orgId;

				if (checkbox.checked) {
					$http.post(action + param).success(function(data) {
						angular.forEach(data, function(branch, key) {
							$scope.branches.push(branch);
						});
					});
				} else {
					$scope.branches = $filter('filter')($scope.branches, {
						organizationId : orgId
					}, function(e, a) {
						return e != a;
					});
				}
			};

			$scope.addCheckList = function() {
				var data = {
					oraganizationId : $scope.organization,
					branchId : $scope.branch,
					checkListForms : $scope.checkLists
				}
				var addCheckList = 'restTest/addCheckList';
				$http.post(addCheckList, data).success(function(data) {
					alert("Data saved seccessfully");
					$scope.checkLists = data.checkList;
					$scope.checkListTypes = data.checkListType;
				});
			};
			
			$scope.reset=function()
			{
				$scope.branches=[];
			};

			/* $scope.saveNewUser = function() {
				debugger;
				var data = {
					userName : $scope.userName,

					password : $scope.password,

					includedOrganizations : $scope.includedOrganizations,

					includeBranch : $scope.includeBranch,

					userTypeId : $scope.userType,

					isDefaultOrganization : $scope.isDefaultOrganization,

					isDefaultBranch : $scope.isDefaultBranch

				};

				action = "restTest/saveUserType";
				$http.post(action, data).success(function(data) {
					alert('Save')
				}).erorr(function(data) {
					alert('Error');
				});
			}; */
		});
	});
</script>
	<script type="text/javascript">
		function checkForm(form) { 
					if (form.password.value != ""
							&& form.password.value == form.confirmPassword.value) {
						if (form.password.value.length < 8) {
							alert("Error: Password must contain at least 8 characters!");
							form.password.focus();
							return false;
						}
						re = /[0-9]/;
						if (!re.test(form.password.value)) {
							alert("Error: password must contain at least one number (0-9)!");
							form.pwd.focus();
							return false;
						}
						re = /[a-z]/;
						if (!re.test(form.password.value)) {
							alert("Error: password must contain at least one lowercase letter (a-z)!");
							form.password.focus();
							return false;
						}
						re = /[A-Z]/;
						if (!re.test(form.password.value)) {
							alert("Error: password must contain at least one uppercase letter (A-Z)!");
							form.password.focus();
							return false;
						}
					} else {
						alert("Error: Please check that you've entered and confirmed your password!");
						form.password.focus();
						return false;
					}
					return true;	
		}
	</script>

</head>
<body ng-app="addNewUserApp">

	<div class="intro-header">
		<h2 style="text-align: center">New User</h2>
		<br> <br>

		<div class="well" style="margin: 0 28% 0 28%;padding:2% 10% 0 5%;" ng-controller="addNewUserController">

			<form class="form-horizontal" action="/fmsv1/restTest/saveUserType" method="POST" onsubmit="return checkForm(this);">
				<div class="form-group">
					<label for="orgnazation" class="col-sm-4 control-label">UserName
					</label>
					<div class="col-sm-6">
						<input class="form-control" type="email" ng-model="userName" id="userName" name="userName" required />
						<span style="color:red">(Username should be a email ex: sample@abc.com)</span>
						
					</div>
				</div>
				<div class="form-group">
					<label for="orgnazation"  class="col-sm-4 control-label">Password
					</label>
					<div class="col-sm-6">
						<input type="password" ng-model="password" class="form-control" id="password"
							name="password" required />
					</div>
				</div>
				<div class="form-group">
					<label for="orgnazation" class="col-sm-4 control-label">Confirm
						Password</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" id="confirmPassword"
							name="confirmPassword" required />
					</div>
				</div>
				<div class="form-group">
					<label for="orgnazation" class="col-sm-4 control-label">User&nbsp;Type</label>
					<div class="col-sm-6">
						<select ng-model="userType" class="form-control" name="userTypeId" id="userTypeId" required>
						<option value="">Please select UserType</option>
						<option ng-repeat="userType in UserTypes" value="{{userType.id}}">{{userType.name}}</option>
						</select>
					</div>
				</div>


				<div class="form-group" style="height:auto; padding-left: 154px; padding-right: 0px; border-left-width: 23px; margin-left: -248px; margin-right: -110px;">
					<label for="orgnazation" class="col-sm-4 control-label">Organizations
					</label>
					<div class="col-sm-6" >
						<table class="table table-striped ">
							<thead style="position: relative;display:block; color:black;font-weight:bolder;">
								<tr>
									<td style="width: 200px;">Organization</td>
									<td style="width: 50px;">Include</td>
									<td style="width: 50px;">Default</td>
								</tr>
							</thead>
							<tbody style="display: block;overflow:auto">
								<tr ng-repeat="organization in organizations |orderBy:orgName">
									<td style="width: 225px;"><input type="hidden" value="{{organization.orgId}}">{{organization.orgName}}</td>
									<td style="width: 50px;"><input ng-model="organization.includedOrganizations" name="includedOrganizations[{{$index}}]" type="checkbox" value="{{organization.orgId}}" ng-click="getBranch($event,organization.orgId)" /></td>
									<td style="width: 50px;"><input type="radio" ng-model="organization.isDefaultOrganization" name="isDefaultOrganization" id="isDefaultOrganization" value="{{organization.orgId}}" /></td>
								</tr>
						  </tbody>
						</table>
					</div>
				</div>

				<div class="form-group" style="border-left-width: 0px; margin-left: -206px; margin-right: -205px;">
					<label for="orgnazation" class="col-sm-4 control-label">Branch
					</label>
					<div class="col-sm-6">
						<table class="table table-striped" >
							<thead style="position: relative;display:block; color:black;font-weight:bolder;">
								<tr>
									<td style="width: 150px; padding-left: 0px;">Organization</td>
									<td style="width: 125px;">Branch</td>
									<td  style="width: 35px; padding-left: 0px;">Include</td>
									<td style="word-break: 31px; padding-left: 27px; height: 50px; padding-right: 0px;">Default</td>
								</tr>
							</thead>
							<tbody style="display: block;overflow:auto" >
								<tr ng-repeat="branch in branches|orderBy:branchName" >
									<td style="width: 150px;"><input type="hidden">{{branch.organization}}</td>
									<td style="width: 125px;"><input type="hidden" value="{{branch.id}}">{{branch.branchName}}</td>
									<td style="width: 50px;text-align: center;"><input ng-model="branch.includeBranch" name="includeBranch[{{$index}}]" id="selectedOrg" value="{{branch.id}}" type="checkbox" /></td>
									<td style="width: 100px; text-align: center;"><input ng-model="branch.isDefaultBranch" name="isDefaultBranch" id="isDefaultBranch" type="radio" value="{{branch.id}}" /></td>
								</tr>
						</table>
					</div>
				</div>
				  <p id="b_viewedit" >
		  <a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	      <input type="reset" class="btn btn-primary" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  <input type="submit" class="btn btn-success" value="Save">
		  
		  </p>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>