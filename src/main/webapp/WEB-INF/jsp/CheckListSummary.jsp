<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="./assets/js/jquery-1.9.1.js"></script>
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
		
		managedEntitySubType.controller('ManagedEntitySubTypeController', function($scope, $http) {
			$scope.managedEntityTypes = [];
			$scope.managedEntityType = "";		
			$scope.managedEntitySubTypes = [];
			$scope.managedEntitySubType = "";	
			$scope.checkLists = [];
			//Getting Managed Entity List
			var getManagedEntitytype="getManagedEntityTypes";
			$http.post(getManagedEntitytype).success(function(data)
			{
				$scope.managedEntityTypes=data;
			});
            //Getting SubManaged Entity List
			$scope.getManagedEntitySubTypes=function(){
				var ManagedEntity_id = this.managedEntityType.id;
				$http.post("managedEntitySubtypeByType?id="+ ManagedEntity_id).success(function(data)
				{
			$scope.managedEntitySubTypes = data;
				});
			}
			//Getting Indent Details
			$scope.getIndentDetail = function() {
				var orgId = $scope.organization;
				var branchId = $scope.branch;
				var managedEntityId = $scope.managedEntity;
				action = "getListOfcheckLists?";
				param =  "manageEntityId=" + managedEntityId;
				$http.post(action + param).success(function(data) {
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
					param = 'itemId=' + itemId + "&materialIndentId="+ managedEntityId;
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
	<div class="intro-header" ng-controller="ManagedEntitySubTypeController">
		<h2 style="text-align: center">Managed Entity Sub Type and Checklist</h2>
		<br> <br>
		<div class="well" style="margin: 0 25% 0 25%;" width="auto" ng-controller="ManagedEntitySubTypeController">
			<form name="saveManagedEntityInstance" class="form-horizontal" role="form"  style="margin-top: 3%;" action="/fmsv1/savechecklistsummarry/" onsubmit="return validateForm();" method="POST">
			
			<div class="form-group">
					 <label for="ManagedEntitySubType" class="col-sm-4 control-label">Managed Entity Type</label>
					 <div class="col-sm-6">
			<select	class="form-control" name="managedEntityTypeId" id="managedEntityTypeId"ng-model="managedEntityType"ng-options="managedEntityType.name for managedEntityType in managedEntityTypes|orderBy:'name' track by managedEntityType.id"ng-change="getManagedEntitySubTypes()">
							<option value="">Please select Managed Entity Type</option>
							</select>
					</div>
				</div>
				<div class="form-group">
					<label for="ManagedEntitySubType" class="col-sm-4 control-label2">SubType Name</label>
					<div class="col-sm-6">        
			<select	class="form-control" name="managedEntitySubTypeId" id="managedEntitySubTypeId" value="managedEntitySubType.id" ng-model="managedEntitySubType" ng-options="managedEntitySubType.name for managedEntitySubType in managedEntitySubTypes|orderBy:'name' track by managedEntitySubType.id">
								<option value="">Please select Managed Entity Sub Type</option>
							</select>
					</div>
				</div>
					<label for="mainEquipment" class="col-sm-6 control-label2"><h4>CheckLists</h4></label>
					<div class="table-responsive" >
					<table class="table table-bordered table-striped" id="itemListTable" >
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
						    <td><input type="text" ng-model="checkList.name" class="form-control" id="name" name="name"  value="name"></td>
						    <td><input type="text" ng-model="checkList.description" class="form-control" name="description"  value="description"></td>
						    <td> <input type="radio" ng-model="checkList.key" class="form-control" name="checked" value="true" ></td><br>
						    <td><input type="radio" ng-model="checkList.key" class="form-control" name="unchecked" value="false"></td>
						     <td>
						       <button class="btn btn-sm btn-danger" type="button" ng-click="deleteIndentList($index)" ><span class="glyphicon glyphicon-trash"></span>
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
				<button type="button" class="btn btn-info"  ng-disabled="managedEntitySubType.length==0" ng-click="addIndentList()">Add</button>&nbsp;&nbsp;&nbsp;
			   <button type="submit" ng-disabled="checkLists.length==0" class="btn btn-success btn-default">Save</button>
			</form>
		</div>
	</div>
</body>
</html>