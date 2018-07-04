<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FMS-ORGSummary</title>
<script src="./assets/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
	$(function() {
		var managedEntitySubTypeApp = angular.module('managedEntitySubTypeApp', []);
		managedEntitySubTypeApp.controller('managedEntitySubTypeController', function($scope,
				$http) {
			$scope.managedEntityTypes = [];
			$scope.managedEntityType = "";		
			$scope.managedEntitySubTypes = [];
			$scope.managedEntitySubType = "";	
			//get all managed entity types .
			
			$http.post("getManagedEntityTypes").success(function(data) {
				$scope.managedEntityTypes = data;
			});

			
			$scope.getManagedEntitySubTypes = function() {
				
				$scope.managedEntitySubTypes = [];
				$scope.managedEntitySubType = "";	
				
				var ManagedEntity_id = this.managedEntityType.id;
				$http.post("managedEntitySubtypeByType?id="+ ManagedEntity_id).success(function(data) {
							$scope.managedEntitySubTypes = data;
						});
			};
			
			
			// delete managed entity sub type .
			$scope.delmanagedEntitySubtypeByType=function(){
				action="delmanagedEntitySubtypeByType?";
				param="id="+this.managedEntitySubType.id;
			$http.get(action + param ).success(function() {
				var ManagedEntity_id = this.managedEntityType.id;
				$http.post(
						"managedEntitySubtypeByType?id="
								+ ManagedEntity_id).success(
						function(data) {
							$scope.managedEntitySubTypes = data;
						});
			
				});
			};	
		});
	});
</script>
  
</head>
<body ng-app="managedEntitySubTypeApp">
	<div class="intro-header" ng-controller="managedEntitySubTypeController">
	<h2 style="text-align:center ">Managed Entity Sub-Type and CheckList</h2>
	<br>
	<br>
	
	<div class="well" style="margin : 0 9% 0  10%;">
	<div class="table-responsive">
			<table class="table table-striped ">
			 <tr>
						<td><a href="/fmsv1/Home">
							
							<button type="button" class="btn btn-default">Back</button></a></td>
							<td style="text-align: center; size: auto;">
							<select	class="form-control" name="managedEntityTypeId" id="managedEntityTypeId"	
								ng-model="managedEntityType"
								ng-options="managedEntityType.name for managedEntityType in managedEntityTypes|orderBy:'name' track by managedEntityType.id"
								ng-change="getManagedEntitySubTypes()">
									<option value="">Please select Managed Entity Type</option>
							</select></td>

						
							<td colspan="2" style="text-align:right;"><a href="/fmsv1/addManagedEntitySubType"><button type="button" class="btn btn-info" ng-disabled="managedEntityType.length==0"><span class="glyphicon-plus">Add Managed Entity SubType</span></button></a></td>

							
						
						</tr>
		<tr>
		
		
		<tr>
			<th>Id</th>
			<th>Managed Entity Sub Type Name</th>
			<th>Managed Entity Type</th>			
			<th colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
		</tr>
		
		<tr ng-repeat="managedEntitySubType in managedEntitySubTypes">
		<td>{{$index+1}}</td>
							<td><a href="/fmsv1/editManagedEntitySubType?mid={{managedEntitySubType.id}}">{{managedEntitySubType.name}}</a></td>
							
							<td>{{managedEntitySubType.managedEntityTypeName}}</td>
						
							<td> <button type="button" class='removeBtn btn btn-danger btn-xs'  id="deleteBranch" value="{{branch.id}}" ng-click="delmanagedEntitySubtypeByType()">
											<span class="fa fa-times"></span>Delete
										</button></td>
						</tr>
		
		
	</table>
	</div>
	</div>
	
	<!--  For delete confirmation -->
	
	
<div id="confirm" class="modal fade" style="display:none;">
<div class="modal-dialog">
    <div class="modal-content">
  <div class="modal-body" style="background-color:#587DC6 ">
    Are you sure?
  </div>
  <div class="modal-footer">
    <button type="button" data-dismiss="modal" class="btn btn-primary" id="delete">Delete</button>
    <button type="button" data-dismiss="modal" class="btn">Cancel</button>
  </div>
  </div>
  </div>
</div>
  <script type="text/javascript">
$(document).ready(function(){
  $('button.removeBtn').click(function(e){
	  //not required ...  use firefox's firebug instead;
	    var $form = $(this).closest('form');
	    var btn_val =$(this).val(); 
	    e.preventDefault();
	    $('#confirm').modal({ backdrop: 'static', keyboard: true })
	        .one('click', '#delete', function (e) {
	    	
	        	window.location.href = "/fmsv1/delOrg?id="+btn_val;
        });
  });
});
 
  </script>
	</div>
</body>
	
	
</html>