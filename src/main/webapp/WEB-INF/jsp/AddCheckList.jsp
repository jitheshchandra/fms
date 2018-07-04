<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Managed Entity Instance</title>


<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>
	<script type="text/javascript">
	$(function() {
		var managedEntitySubType = angular.module('managedEntitySubType', []);
		
		/* materialListApp.config(['$httpProvider', function ($httpProvider) {    
			$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
		}]); */
		
		managedEntitySubType.controller('ManagedEntitySubTypeController', function($scope, $http, $filter) {
			$scope.organizations = [];
			$scope.organization = "";

			$scope.branches = [];
			$scope.branch = "";

			$scope.managedEntitys = [];
			$scope.managedEntity = "";

			$scope.checkLists = [];

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

				$scope.checkLists = [];

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

				$scope.checkLists = [];
				/*var orgId = $scope.organization;

				var action = "getBranchByOrgId?";
				var param = "orgId=" + orgId;*/

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

				action = "getListOfcheckLists?";
				param = "organizationId=" + orgId + "&branchId=" + branchId
						+ "&manageEntityId=" + managedEntityId;

				$http.post(action + param).success(function(data) {
					debugger;
					$scope.items = data.items;
					$scope.checkLists = data.indentLists;

				});
				$('#addNewItemToList').removeAttr('disabled');

			};

			//Deleting Item
			$scope.deleteIndentList = function(index) {
				debugger;
				var indentList = $scope.checkLists[index];
				if (indentList.id != '') {
					var action = 'deleteIndentDetailList?';

					var managedEntityId = indentList.id;
					var itemId = indentList.itemId;

					param = 'itemId=' + itemId + "&materialIndentId="
							+ managedEntityId;
					doAjaxCall(action, param, function(data) {
						debugger;
						if (data) {
							$scope.checkLists.splice(index, 1);
						}
					}, function(respones) { //on error, dont do anything. 
					});
				} else {
					//just delete from the page 
					$scope.checkLists.splice(index, 1);
				}

			};

			//Adding rows dynamically
			$scope.addIndentList = function() {
				len = $scope.checkLists.length;
				$scope.checkLists.push({
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
						checkLists : $scope.checkLists
				}
				debugger;
				var addIndentDetailData = 'rest/finalTryToSaveMaterialList';
				$http.post('/rest/finalTryToSaveMaterialList', dataDetail).success(function(data) {
					alert('got');
				}).error(function() {
					alert('sorry could not save to database');
				});
			};
		});
	});
</script>
</head>
<body ng-app="managedEntitySubType">
	<div class="intro-header">
		<h2 style="text-align: center">Managed Entity Sub Type and Checklist</h2>
		<br> <br>

		<div class="well" style="margin: 0 15% 0 15%;" width="auto" ng-controller="ManagedEntitySubTypeController">
          <font color="red">${mgd_Saved}</font>
			<form name="saveManagedEntityInstance" class="form-horizontal" role="form"  style="margin-top: 3%;" action="/fmsv1/saveManagedEntity/" onsubmit="return validateForm();" method="POST">
			<input type="hidden" name="isMainEqup" id ="isMainEqup" value="false"/>
			<input type="hidden" name="mainEqup" id ="mainEqup" value=""/>
			<input type="hidden" name="orgId" id ="orgId" value="${SelectedOrganization.id}"/>
			<input type="hidden" name="branchId" id ="branchId" value="${SelectedBranch.branchName}"/>
			<input type="hidden" name="masterEntityId" id ="masterEntityId"  value=""/>
				<div class="form-group">
					<label for="Organization" class="col-sm-5 control-label2">SubType Name</label>
					<div class="col-sm-6">
						<input class="form-control" type="text"  id="name" name="name" required />
					</div>
				</div>
				<div class="form-group">
					 <label for="orgnazation" class="col-sm-5 control-label">Managed Entity Type</label>
					 <div class="col-sm-6">
					<select class="form-control" ng-model="ManagedEntitiy"  ng-change="fillHiddenData()" name="ManagedEntitiy" id ="ManagedEntitiy"  style="width: auto;">
							<option value="">Please Select Entity</option>
							<option value="{{M.id}}" ng-repeat="M in ManagedEntityTypes | orderBy:name" >{{M.name}}</option>
						</select>
					</div>
				</div>
						
			
			
					<label for="mainEquipment" class="col-sm-5 control-label2">CheckLists</label>
					<div class="table-responsive">
					<table class="table table-bordered table-striped" id="itemListTable">
						<thead>
							<tr>
								<th class="tbh">S.No</th>
								<th class="tbh">Name</th>
								<th class="tbh">Description</th>
								<th class="tbh">Yes</th>
								<th class="tbh">No</th>
								<th class="tbh"></th>
							</tr>
						</thead>
						<tbody id='listBody'>	
						 <tr ng-repeat="checkList in checkLists">
						    <td>{{$index+1}}</td>
						    <td><input type="text" ng-model="checkList.name" class="form-control" name="checkLists[{{$index}}].name"  value="{{checkList.name}}"></td>
						    <td><input type="text" ng-model="checkList.description" class="form-control" name="checkLists[{{$index}}].description"  value="{{checkList.description}}"></td>
						    <td><input type="checkbox" ng-model="checkList.checked" class="form-control" name="checkLists[{{$index}}].checked"  ></td>
						    <td><input type="checkbox" ng-model="checkList.unchecked" class="form-control" name="checkLists[{{$index}}].unchecked"></td>
						     <td>
						       <button class="btn btn-sm btn-danger" type="button" ng-click="deleteIndentList($index)" >
										<span class="glyphicon glyphicon-trash"></span>
							   </button>
						     </td>
						  </tr> 
						</tbody>
					</table>
				</div>
			
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				
			<a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-success"  ng-click="addIndentList()">Add</button>&nbsp;&nbsp;&nbsp;
				<!-- <button type="button" class="btn btn-success" ng-disabled="checkLists.length==0"  ng-click="addIndentList()">Add</button>&nbsp;&nbsp;&nbsp; -->
			<button type="submit" ng-disabled="checkLists.length==0" class="btn btn-success btn-default">Save</button>
			</form>
		</div>
	</div>
</body>
</html>