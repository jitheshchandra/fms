
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
		var managedEntitySubType = angular.module('managedEntitySubType', []);

		/* materialListApp.config(['$httpProvider', function ($httpProvider) {    
			$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
		}]); */

		managedEntitySubType
				.controller(
						'ManagedEntitySubTypeController',
						function($scope, $http) {

							$scope.managedEntityGroups = [];
							$scope.managedEntityGroup = "";
							$scope.managedEntities = [];
							$scope.managedEntity = "";
							$scope.checkLists = [];
							//Getting Managed Entity List
							var getManagedEntityGroups = "getManagedEntityGroups";
							$http.post(getManagedEntitytype).success(
									function(data) {
										$scope.managedEntityTypes = data;
									});
							//Getting SubManaged Entity List
							$scope.getManagedEntitySubTypes = function() {
								var ManagedEntity_id = this.managedEntityType.id;
								$http
										.post(
												"managedEntitySubtypeByType?id="
														+ ManagedEntity_id)
										.success(
												function(data) {
													$scope.managedEntitySubTypes = data;
												});
							}
							//Get default check lists by type and subtype if already present 
							$scope.getIndentDetail = function() {
								var orgId = $scope.organization; //type
								var branchId = $scope.branch;//sub type

								//action = "getListod default checklists by type and sub type ?";
								//param =  "manageEntityId=" + managedEntityId;
								//$http.post(action + param).success(function(data) {
								//	$scope.items = data.items;
								//	$scope.checkLists = data.indentLists;
								//});
								//$('#addNewItemToList').removeAttr('disabled');
							};
							//Deleting Item
							$scope.deleteIndentList = function(index) {
								debugger;
								var indentList = $scope.checkLists[index];
								if (indentList.id != '') {
									var action = 'deleteIndentDetailList?';
									var managedEntityId = indentList.id;
									var itemId = indentList.itemId;
									param = 'itemId=' + itemId
											+ "&materialIndentId="
											+ managedEntityId;
									doAjaxCall(action, param, function(data) {
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
								var dataDetail = {
									managedEntityId : $scope.managedEntity,
									checkLists : $scope.checkLists
								}
								var addIndentDetailData = 'rest/finalTryToSaveMaterialList';
								$http.post('/rest/finalTryToSaveMaterialList',
										dataDetail).success(function(data) {
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
	<div class="intro-header"
		ng-controller="ManagedEntitySubTypeController">
		<h2 style="text-align: center">Managed Entity Checklists</h2>
		<br> <br>
		<div class="well" style="margin: 0 25% 0 25%;" width="auto"
			ng-controller="ManagedEntitySubTypeController">
			<form class="form-horizontal" role="form" style="margin-top: 3%;"
				action="/fmsv1/saveDefaultChecklists"
				onsubmit="return validateForm();" method="POST">
				<div class="form-group">
					<label for="orgnazation" class="col-sm-4 control-label">Managed
						Entity Group</label>
					<div class="col-sm-4">
						<select class="form-control" name="managedEntityTypeId"
							id="managedEntityTypeId" ng-model="managedEntityType"
							ng-options="managedEntityType.name for managedEntityType in managedEntityTypes|orderBy:'name' track by managedEntityType.id"
							ng-change="getManagedEntitySubTypes()">
							<option value="">Please select Managed Entity Type</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="Organization" class="col-sm-4 control-label2">Managed
						Entity</label>
					<div class="col-sm-4">
						<select class="form-control" name="managedEntitySubTypeId"
							id="managedEntitySubTypeId" ng-model="managedEntitySubType"
							ng-options="managedEntitySubType.name for managedEntitySubType in managedEntitySubTypes|orderBy:'name' track by managedEntitySubType.id">
							<option value="">Please select Managed Entity Sub Type</option>
						</select>

					</div>
				</div>

				<label for="mainEquipment" class="col-sm-5 control-label2">CheckLists</label>
				<div class="table-responsive">
					<table class="table table-bordered table-striped"
						id="itemListTable">
						<thead>
							<tr>
								<th class="tbh">S.No</th>
								<th class="tbh">ChecklistName<span
									style="padding-left: 22px; text-color: gray;"> (ex- Are
										the lights working ?)</span></th>
								<th class="tbh">Yes</th>
								<th class="tbh">No</th>
								<th class="tbh"></th>
							</tr>
						</thead>
						<tbody id='listBody'>


							<tr ng-repeat="checkList in checkLists">
								<td>{{$index+1}}</td>
								<td><input type="text" ng-model="checkList.name"
									class="form-control" name="checkLists[{{$index}}].name"
									value="{{checkList.name}}"></td>
								<td><input type="checkbox" id="checked" ng-true-value="1"
									class="form-control" ng-model="checkList.checked"
									name="checkLists[{{$index}}].checked"></td>
								<td><input type="checkbox" id="unchecked" ng-true-value="0"
									class="form-control" ng-model="checkList.checked"
									name="checkLists[{{$index}}].unchecked"></td>
								<td>
									<button class="btn btn-sm btn-danger" type="button"
										ng-click="deleteIndentList($index)">
										<span class="glyphicon glyphicon-trash"></span>
									</button>
								</td>
							</tr>

							
						</tbody>
					</table>
					<div>
					<label for="orgnazation" class="col-sm-4 control-label">(Or) Upload a file containing the checklists</label>
								<input type="file" name="image_file"
									id="image_file" onchange=""  accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" style="color: black;"/>
					</div>
					
				</div>
				<br /> <br /> <br /> <br /> <br /> <br /> <a href="/fmsv1/Home">
					<button type="button" class="btn btn-default">Back</button>
				</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-success"
					ng-disabled="managedEntityType.length==0"
					ng-click="addIndentList()">Add</button>
				&nbsp;&nbsp;&nbsp;
				<button type="submit" ng-disabled="checkLists.length==0"
					class="btn btn-success btn-default">Save</button>
			</form>
		</div>
	</div>
</body>
</html>