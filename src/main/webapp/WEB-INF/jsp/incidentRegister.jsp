<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Incident Register</title>
<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>
</head>
<script type="text/javascript">
	$(function() {
		var registerIncidentApp = angular.module('registerIncidentApp', []);
		registerIncidentApp.controller('RegisterIncidentCtrl', function($scope,$http) {
			$scope.organization = "";
			$scope.branch = "";
			//$scope.incDate = "31/11/2014";
			//$scope.incTime = "12:12";

			$scope.organizations = [];
			var getOrg = "getOrganizations";
			$http.post(getOrg).success(function(data) {
				$scope.organizations = data;
			});

			$scope.categories = [];
			$scope.orgChanged = function() {
				var orgId = $scope.organization;
				$scope.branch = "";
				$scope.branches = [];
				if (orgId == '') {
					return;
				}
				var action = "getBranchByOrgId?";
				var param = 'orgId=' + orgId;
				$http.post(action + param).success(function(data) {
					$scope.branches = data;
				});
			};

			$scope.branchChanged = function() {
				$scope.managedEntities = [];
				$scope.categories = [];
				$scope.managedEntity = '';
				var branchId = $scope.branch;
				var action = "getManagedEntitiesForBranch?";
				var param = 'branchId=' + branchId;
				
				$http.post(action + param).success(function(data) {
					$scope.managedEntities = data;
				});

				$scope.category = '';
				var getInc = "getIncidentCategoriesHavingEscalationMatrix?"+param;
				$http.post(getInc).success(function(data) {
					$scope.categories = data;
				});
			};

			$scope.escalations = [];
			$scope.getEscalationMatrix = function() {
				$scope.escalations = [];
				if ($scope.branch == '' || $scope.category == undefined|| $scope.category == '') {
					return;
				}
				var action = "getEscalationMatrix?";
				var param = 'branchId=' + $scope.branch + '&categoryTypeId='+ $scope.category;
				$http.post(action + param).success(function(data) {
					$scope.escalations = data.escList;
				});
			};
		});
	});
</script>
<body ng-app="registerIncidentApp">
	<div class="intro-header">
		<h2 style="text-align: center">Incident Register</h2>
		<br> <br>
		<div class="well" style="margin: 0 25% 0 25%;" ng-controller="RegisterIncidentCtrl">
			<div class="form-responsive">
				<form:form class="form-horizontal" action="saveIncident" method="POST" style="margin-top: 3%;" onsubmit="return validate(this);" modelAttribute="incidentInfoForm">
					<div class="form-group">
						<label for="organization" class="col-sm-4 control-label2">Organization</label>
						<div class="col-sm-6">
							<select class="form-control form-page" name="organizationId" ng-model="organization" id="organizationId" style="width: auto;" ng-change="orgChanged()"required="required">
								<option value="">Please Select Organization</option>
								<option ng-selected="org.orgId==organization" value="{{org.orgId}}" ng-repeat="org in organizations | orderBy:orgName">{{org.orgName}}</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="branch" class="col-sm-4 control-label2">Branch</label>
						<div class="col-sm-6">
							<select class="form-control" ng-model="branch" ng-change="branchChanged();getEscalationMatrix()" name="branchId" style="width: auto;"required="required">
								<option value="">Please Select Branch</option>
								<option ng-selected="br.id==branch" value="{{br.id}}" ng-repeat="br in branches | orderBy:branchName">{{br.branchName}}</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="managedEntityId" class="col-sm-4 control-label2">Managed Entity</label>
						<div class="col-sm-6">
							<select class="form-control" ng-model="managedEntity" id="managedEntityId" name="managedEntityId" style="width: auto;" required="required">
								<option value="">Please Select Branch</option>
								<option ng-selected="me.id==managedEntity" value="{{me.id}}" ng-repeat="me in managedEntities | orderBy:name">{{me.name}}</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="date" class="col-sm-4 control-label2">Date</label>
						<div class="col-sm-3">
							<input type="date" name="incidentDate" class="form-control" id="date" ng-model="incDate" value="{{incDate}}"required/>
						</div>
					</div>
					<div class="form-group">
						<label for="time" class="col-sm-4 control-label2">Time</label>
						<div class="col-sm-3">
							<input type="time" name="incidentTime" class="form-control" id="time" ng-model="incTime" value="{{incTime}}"required/>
						</div>
					</div>
					<div class="form-group">
						<label for="incident" class="col-sm-4 control-label2">Incident Type</label>
						<div class="col-sm-6">
							<select class="form-control" ng-model="category" name="incidentTypeId" style="width: auto;" required="required" ng-change="getEscalationMatrix()">
								<option value="">Please Select Incident Type</option>
								   <option ng-selected="it.id==category" value="{{it.id}}" ng-repeat="it in categories | orderBy:name">{{it.name}}</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="Severity" class="col-sm-4 control-label2">Severity</label>
						<div class="col-sm-3">
							<select class="form-control" required="required">
								<option value="">--Select--</option>
								<option value="H">High</option>
								<option value="M">Medium</option>
								<option value="L">Low</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="additionalinfo" class="col-sm-4 control-label2">Additional Info</label>
						<div class="col-sm-6">
							<textarea class="form-control" rows="3" name="remarks"></textarea>
						</div>
					</div>
					<div class="row clearfix">
						<div class="col-md-12 column" title="{{escalations.length==0?'Please select the combination of Branch and Category to Save':''}}">
                             <a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                 <button type="submit" class="btn btn-success">Save</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
<br />
<br />
</html>