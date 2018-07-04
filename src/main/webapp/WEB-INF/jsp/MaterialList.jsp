<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FMS</title>
<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>

<script type="text/javascript">
	$(function() {
		var materialListApp = angular.module('materialListApp', []);
		
		/* materialListApp.config(['$httpProvider', function ($httpProvider) {    
			$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
		}]); */
		
		materialListApp.controller('MaterialListCtrl', function($scope, $http, $filter) {
			$scope.organizations = [];
			$scope.organization = "";

			$scope.branches = [];
			$scope.branch = "";

			$scope.managedEntitys = [];
			$scope.managedEntity = "";

			$scope.indentDetails = [];

			//Getting Organization List
			var getOrg = "getOrganizations";
			$http.post(getOrg).success(function(data) {
				$scope.organizations = data;
			});
			//Getting Branch List
			$scope.getBranches = function() {

				$scope.branches = [];
				$scope.branch = "";

				$scope.managedEntitys = [];
				$scope.managedEntity = "";

				$scope.indentDetails = [];

				var orgId = $scope.organization;

				var action = "getBranchByOrgId?";
				var param = "orgId=" + orgId;

				$http.post(action + param).success(function(data) {
					$scope.branches = data;
				});
			};

			//Getting Managed Entity List
			$scope.getManagedEntity = function() {

				$scope.managedEntitys = [];
				$scope.managedEntity = "";

				$scope.indentDetails = [];

				var branchId = $scope.branch;

				var action = "getManagedEntitiesForBranch?";
				var param = "branchId=" + branchId;

				$http.post(action + param).success(function(data) {
					$scope.managedEntitys = data;
				});

			};

			//Getting Indent Details
			$scope.getIndentDetail = function() {
				var orgId = $scope.organization;
				var branchId = $scope.branch;
				var managedEntityId = $scope.managedEntity;

				action = "getListOfIndentDetails?";
				param = "organizationId=" + orgId + "&branchId=" + branchId
						+ "&manageEntityId=" + managedEntityId;

				$http.post(action + param).success(function(data) {
					debugger;
					$scope.items = data.items;
					$scope.indentDetails = data.indentLists;

				});
				$('#addNewItemToList').removeAttr('disabled');

			};

			//Deleting Item
			$scope.deleteIndentList = function(index) {
				debugger;
				var indentList = $scope.indentDetails[index];
				if (indentList.id != '') {
					var action = 'deleteIndentDetailList?';

					var managedEntityId = indentList.id;
					var itemId = indentList.itemId;

					param = 'itemId=' + itemId + "&materialIndentId="
							+ managedEntityId;
					doAjaxCall(action, param, function(data) {
						debugger;
						if (data) {
							$scope.indentDetails.splice(index, 1);
						}
					}, function(respones) { //on error, dont do anything. 
					});
				} else {
					//just delete from the page 
					$scope.indentDetails.splice(index, 1);
				}

			};

			//Adding rows dynamically
			$scope.addIndentList = function() {
				len = $scope.indentDetails.length;
				$scope.indentDetails.push({
					id : ''
				});
			};
			
			//Saving Indent Detail
			$scope.addIndentDetailData = function() {
				debugger;
				var dataDetail = {
						branchId : $scope.branch,
						organizationId : $scope.organization,
						managedEntityId : $scope.managedEntity,
						indentDetails : $scope.indentDetails
				}
				debugger;
				var addIndentDetailData = 'rest/finalTryToSaveMaterialList';
				$http.post('/rest/finalTryToSaveMaterialList', dataDetail).success(function(data) {
					alert('got');
				}).error(function() {
					alert('sorry din`t get');
				});
			};
		});
	});
</script>
</head>


<body ng-app="materialListApp">
	<div class="intro-header">
	<h2 style="text-align:center ">Material List</h2>
	<br>
	<br>
	<div class="well" style="margin : 0 25% 0  25%;"  ng-controller="MaterialListCtrl">	
    <form  class="form-horizontal" action="/fmsv1/finalTryToSaveMaterialList" method="POST" mstyle="margin-top: 3%;"> 
	<!-- <form class="form-horizontal" ng-submit="addIndentDetailData()" > --> 
				<div class="form-group">	
					<label for="Organization" class="col-sm-5 control-label2">Organization</label>
					<div class="col-sm-6">
						<select class="form-control form-page" name="organizationId" id="organizationId" ng-model="organization" style="width: auto;" ng-change="getBranches()">
							<option value="">Please Select Organization</option>
							<option ng-repeat="org in organizations | orderBy:'orgName'" value="{{org.orgId}}">{{org.orgName}}</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="branch " class="col-sm-5 control-label2">Branch</label>
					<div class="col-sm-6">
						<select class="form-control" ng-model="branch" name="branchId" id="branchId" style="width: auto;" ng-change="getManagedEntity()">
							<option value="">Please Select Branch</option>
							<option ng-repeat="branch in branches|orderBy:'branchName'" value="{{branch.id}}">{{branch.branchName}}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="branch " class="col-sm-5 control-label2">Manage Entity</label>
					<div class="col-sm-6">
						<select class="form-control" ng-model="managedEntity" name="managedEntityId" id="managedEntityId" style="width: auto;" ng-change="getIndentDetail()">
							<option value="" selected="selected">Please Select Manage Entity</option>
							<option ng-repeat="managedEntity in managedEntitys|orderBy:'name'" value="{{managedEntity.id}}">{{managedEntity.name}}</option>
						</select>
					</div>
				</div>
				
				<div class="table-responsive">
					<table class="table table-bordered table-striped" id="itemListTable">
						<thead>
							<tr>
								<th class="tbh">S.No</th>
								<th class="tbh">Item</th>
								<th class="tbh">Quantity</th>
								<th class="tbh">ReorderLevel</th>
								<th class="tbh"></th>
							</tr>
						</thead>
						<tbody id='listBody'>	
						 <tr ng-repeat="indentList in indentDetails">
						    <td>{{$index+1}}</td>
						    <td>
						     <select class="form-control" ng-model="indentList.itemId" name="indentDetails[{{$index}}].itemId">
							    <option value="">Please Select Item</option>
							    <option ng-repeat="item in items|orderBy:'name'" ng-selected="item.id=={{indentList.itemId}}" value="{{item.id}}">{{item.name}}</option>
						     </select>
						    </td>
						    <td><input type="text" ng-model="indentList.orderQuantity" class="form-control" name="indentDetails[{{$index}}].orderQuantity"  value="{{indentList.orderQuantity}}"></td>
						    <td><input type="text" ng-model="indentList.reOrderlevel" class="form-control" name="indentDetails[{{$index}}].reOrderlevel" value="{{indentList.reOrderlevel}}"></td>
						     <td>
						       <button class="btn btn-sm btn-danger" type="button" ng-click="deleteIndentList($index)" >
										<span class="glyphicon glyphicon-trash"></span>
							   </button>
						     </td>
						  </tr> 
						</tbody>
					</table>
				</div>
				<a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-info"  ng-click="addIndentList()">Add</button>&nbsp;&nbsp;&nbsp;
				<!-- <button type="button" class="btn btn-success" ng-disabled="indentDetails.length==0"  ng-click="addIndentList()">Add</button>&nbsp;&nbsp;&nbsp; -->
			<button type="submit" ng-disabled="indentDetails.length==0" class="btn btn-success btn-default">Save</button>	
        </form>
 </div>
</div>
</body>
</html>