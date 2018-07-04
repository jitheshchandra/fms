<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Managed Entity Instance</title>
<script src="./assets/js/jquery-1.9.1.js"></script>




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
		}]); */
		managedEntitySubType.controller('managedEntitySubTypesController', function($scope, $http) {
				$scope.managedEntityTypes = [];
				$scope.managedEntityType = "";		
				$scope.managedEntitySubTypes = [];
				$scope.managedEntitySubType = "";	
				//Getting Managed Entity List
				var getManagedEntitytype="getManagedEntityTypes";
				$http.post(getManagedEntitytype).success(function(data){
					$scope.managedEntityTypes=data;
					
				});});});
	
</script>
</head>
<body ng-app="managedEntitySubType">
	<div class="intro-header">
		<h2 style="text-align: center">Managed Entity Sub Type and Checklist</h2>
		<br> <br>

		<div class="well" style="margin: 0 28% 0 28%;" width="auto" ng-controller="managedEntitySubTypesController">
         
			<form name="saveManagedEntityInstance" class="form-horizontal" role="form"  style="margin-top: 3%;" action="/fmsv1/saveManagedEntitySubType/" onsubmit="return validateForm();" method="POST">
		
				
				<div class="form-group">
					 <label for="orgnazation" class="col-sm-4 control-label">Managed Entity Type</label>
					 <div class="col-sm-6">
					<select	class="form-control" name="managedEntityTypeId" id="managedEntityTypeId"	
								ng-model="managedEntityType"
								ng-options="managedEntityType.name for managedEntityType in managedEntityTypes|orderBy:'name' track by managedEntityType.id"
								ng-change="getManagedEntitySubTypes()" value="managedEntityType.id" required>
									<option value="">Please select Managed Entity Type</option>
							</select>
					</div>
				</div>
				<div class="form-group">
					<label for="Organization" class="col-sm-4 control-label2">SubType Name</label>
					<div class="col-sm-6">
						<input class="form-control" type="text"  id="name" name="name" required />
					</div>
				</div>
	
			
				
			<a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;								
			<button type="submit" ng-disabled="checkLists.length==0" class="btn btn-success">Save</button>
			</form>
		</div>
	</div>
</body>
</html>