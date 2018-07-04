<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
	$(function(){
		var escalationApp = angular.module('managedentityinstance', []);
		escalationApp.controller('ManagedentityinstanceCtrl', function($scope, $http)
				{
			$scope.checked="";
			$scope.managedEntityTypes = [];
			$scope.managedEntityType = "";		
			$scope.managedEntitySubTypes = [];
			$scope.managedEntitySubType = "";	
			$scope.checkLists = [];
		
			$scope.getManagedEntities = function(){
				var orgId = $scope.organization;
				var branchId = $scope.branch; 
				var action = "getManagedEntitiyInstancesByOrganizationAndBranch?";
				var org = "orgId=" + orgId;
				var branch = "branchId="+branchId;
				$http.post(action + org+"&"+branch).success(function(data) {
					$scope.ManagedEntities = data;
				}); 
			}
			$scope.fillHiddenData = function(){	
				$('#isMainEntity').val()=='on'?$('#masterEntity').val('0'):'';
			}
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
			$scope.organizations = [];
			$scope.organization = "";
			$scope.branches = [];
			$scope.branch = "";
			$scope.managedEntities = [];
			$scope.ManagedEntitiy ="";
			$scope.ManagedEntityMasters =[];
			$scope.ManagedEntityMaster ="";
			
			var getManagedEntityMasters = "getAllManagedEntityMaster";
			$http.post(getManagedEntityMasters).success(function(data) {
				$scope.ManagedEntityMasters = data;
			});

		});
});
</script>
</head>
<body ng-app="managedentityinstance">
	<div class="intro-header">
		<h2 style="text-align: center">Managed Entity Instance</h2>
		<br> <br>
		<div class="well" style="margin: 0 25% 0 25%;" width="auto" ng-controller="ManagedentityinstanceCtrl">
			<form name="saveManagedEntity" class="form-horizontal" role="form"  style="margin-top: 3%;" action="/fmsv1/saveManagedEntity" onsubmit="return validateForm();" method="POST">
								
				<div class="form-group">
					<label for="Organization" class="col-sm-4 control-label2">Name</label>
					<div class="col-sm-6">
						<input class="form-control" type="text"  id="name" name="name" required />
					</div>
				</div>
				<div class="form-group">
					 <label for="orgnazation" class="col-sm-4 control-label">Organization</label>
					 <div class="col-sm-6">
					 <input type="hidden" class="form-control" name="organization" id="organization" value="${SelectedOrganization.id}" readonly />
                        <input type="text" class="form-control" name="organizationName" id="organizationName" value="${SelectedOrganization.name}" readonly />
					 </div>
				</div>
				<div class="form-group">
					 <label for="branch " class="col-sm-4 control-label2">Branch</label>
					  <div class="col-sm-6">
					  <input type="hidden" class="form-control"  name="branch"	id="branch"  value="${SelectedBranch.id}" readonly  />
                           <input type="text" class="form-control"  name="branchName"	id="branchName"  value="${SelectedBranch.branchName}" readonly  />
						</div>
				</div>
				
				<div class="form-group">
					<label for="capacity" class="col-sm-4 control-label2">Capacity</label>
					<div class="col-sm-6">
						<input class="form-control" type="text"   pattern="[0-9]*"  id="capacity" name="capacity"   required />
					</div>
				</div>
				<div class="form-group">
					<label for="slrNo" class="col-sm-4 control-label2">Serial Number</label>
					<div class="col-sm-6">
						<input class="form-control" type="text" pattern="[0-9]*"    id="slrNo" name="slrNo" required />
					</div>
				</div>
				<div class="form-group">
					<label for="isMainEntity" class="col-sm-4 control-label2">Is Main Entity</label>
					<div class="col-sm-1">
						<input id="isMainEntity" name="isMainEntity"  type="checkbox"  ng-model="checked" ng-change="fillHiddenData()"/>
					</div>
				</div>
				<div class="form-group" ng-style="{'display': (checked) ? 'none' : 'block'}" >
			
					<label for="masterEnity" class="col-sm-4 control-label2">Master Entity Name</label>
					<div class="col-sm-6">
					<select class="form-control" ng-model="ManagedEntityMaster"  name="masterEntity" id ="masterEntity"  style="width: auto;">
							<option value="0">Please Select MasterName</option>
							<option value="{{ME.id}}" ng-repeat="ME in ManagedEntityMasters| orderBy:name" >{{ME.name}}</option>
						</select>
					</div>
					
				</div>									
				
			<a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="submit" ng-disabled="indentLists.length==0" class="btn btn-success btn-default">Save</button>	
			</form>
		</div>
	</div>
</body>
</html>