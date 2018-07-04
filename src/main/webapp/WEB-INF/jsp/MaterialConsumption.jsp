<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		var materialConsumptionApp = angular.module('materialConsumptionApp',
				[]);
		materialConsumptionApp.controller('materialConsumptionController',
				function($scope, $http) {
			$scope.values=new Date();

					$scope.organizations = [];
					var getOrg = "getOrganizations";

					$http.post(getOrg).success(function(data) {
						$scope.organizations = data;
					});

					$scope.getBranches = function() {
						$scope.branches = [];
						$scope.branch = "";

						var orgId = $scope.organization;

						action = "getBranchByOrgId?";
						param = "orgId=" + orgId;

						$http.post(action + param).success(function(data) {
							$scope.branches = data;
						});
					};

					$scope.getManagedEntity = function() {
						var managedEntitys = [];
						var managedEntity = "";

						var branchId = $scope.branch;

						action = "getManagedEntitiesForBranch?";
						param = "branchId=" + branchId;

						$http.post(action + param).success(function(data) {
							$scope.managedEntitys = data;
						});
					};

					$scope.getIndentList = function() {
						var orgId = $scope.organization;
						var branchId = $scope.branch;
						var managedEntityId = $scope.managedEntity;

						$scope.indentLists = [];

						action = "getItemStockLevel?";
						param = "organizationId=" + orgId + "&branchId="
								+ branchId + "&manageEntityId="
								+ managedEntityId;

						$http.post(action + param).success(function(data) {
							$scope.indentLists = data;
						});
					};

				});
	});

	function validate(form) {
		if ($('#listBody tr').length == 0) {
			alert('Nothing to save...');
			return false;
		}
		
	}
</script>
</head>
<body ng-app="materialConsumptionApp">
 <div class="intro-header">
	<h2 style="text-align:center ">Material Consumption</h2>
	<br>
	<br>
	<div class="well" style="margin : 0 25% 0  25%;" ng-controller="materialConsumptionController">
			<form id="mainForm" class="form-horizontal" action="/fmsv1/saveMaterialConsumption" method="POST" style="margin-top: 3%;" onsubmit="return validate(this);">
				
				<div class="form-group">
					<label for="Organization" class="col-sm-5 control-label2">Organization</label>
					<div class="col-sm-6">
						<select class="form-control form-page" ng-model="organization" name="organizationId" id="organizationId" style="width: auto;" ng-change="getBranches()">
							<option value="">Please Select Organization</option>
							<option ng-repeat="organization in organizations | orderBy:'orgName'" value="{{organization.orgId}}">{{organization.orgName}}</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="branch " class="col-sm-5 control-label2">Branch</label>
					<div class="col-sm-6">
						<select class="form-control" ng-model="branch" name="branchId" id="branchId" style="width: auto;" ng-change="getManagedEntity()">
							<option value="" selected="selected">Please Select Branch</option>
							<option ng-repeat="branch in branches | orderBy:'branchName'" value="{{branch.id}}">{{branch.branchName}}</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="branch " class="col-sm-5 control-label2">Manage Entity</label>
					<div class="col-sm-6">
						<select class="form-control" ng-model="managedEntity" name="manageEntityId" id="manageEntityId" style="width: auto;" ng-change="getIndentList()">
							<option value="">Please Select Manage Entity</option>
							<option ng-repeat="managedEntity in managedEntitys |orderBy:'name'" value="{{managedEntity.id}}">{{managedEntity.name}}</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
				  <label for="branch " class="col-sm-5 control-label2">Date</label>
					<div class="col-sm-6">
					<input type="date" style="width: auto;" class="form-control" id="consumptionDate" ng-model="values" name="consumptionDate" required/> 	
					</div>
				</div>    
			
				<div class="table-responsive">
					<table class="table table-bordered table-striped" id="itemListTable">
						<thead>
							<tr>
								<th class="tbh">S.No</th>
								<th class="tbh">Item</th>
								<th class="tbh">Available Quantity</th>
								<th class="tbh">Consumed Quantity</th>
							</tr>
						</thead>
						<tbody id='listBody'>
						 <tr ng-repeat="indendtList in indentLists | orderBy:'name'">
						    <td><input type="hidden" name="materialIndentId" id="materialIndentId" value="{{indendtList.id}}">{{$index+1}}</td>
						    <td><input type="hidden" name="materialConsumptionList[{{$index}}].itemId" id="materialConsumptionList[{{$index}}].itemId" value="{{indendtList.itemId}}">{{indendtList.name}}</td>
						    <td><input type="hidden" value="{{indendtList.quantity}}">{{indendtList.orderQuantity}}</td>
						    <td><input type="number" max="{{indendtList.quantity}}" name="materialConsumptionList[{{$index}}].consumedQuantity" id="materialConsumptionList[{{$index}}].consumedQuantity" class="form-control"></td>
						 </tr>
						</tbody>
					</table>
				</div>
				<a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="submit" ng-disabled="indentLists.length==0" class="btn btn-success btn-default">Save</button>	
        </form>
</div>
</div>
</body>
</html>