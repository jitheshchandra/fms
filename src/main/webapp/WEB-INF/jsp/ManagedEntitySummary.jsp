<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Managed Entity  Summary</title>

	<style>
	  	label{
	  	color:black;
		font-family: "Lato","Helvetica Neue",Helvetica,Arial,sans-serif;
		text-align: right;
	  	
	  }
  </style>

	<script type="text/javascript">
	$(function()
			{
		var escalationApp = angular.module('managedentitysummaryApp', []);
		escalationApp.controller('ManagedentitysummaryCtrl', function($scope, $http)
				{
			

			$scope.organizations = [];
			$scope.organization = "";
			
			$scope.branches = [];
			$scope.branch = "";
			$scope.managedEntities = [];
			
			var getOrganization = "getOrganizations";
			$http.post(getOrganization).success(function(data) {
				$scope.organizations = data;
			});

			 $scope.selectBranch = function() {
				 debugger;
				$scope.branches = [];
				$scope.branch = "";
				
			
				var orgId = $scope.organization;
				var action = "getBranchByOrgId?";
				var param = "orgId=" + orgId;

				$http.post(action + param).success(function(data) {
					$scope.branches = data;
				});
			};

			$scope.getManagedEntities = function(){
				
				var orgId = $scope.organization;
				var branchId = $scope.branch;
				var action = "getManagedEntitiyInstancesByOrganizationAndBranch?";
				var org = "orgId=" + orgId;
				var branch = "branchId="+branchId;

				$http.post(action + org+"&"+branch).success(function(data) {
					$scope.ManagedEntities = data;
				});
			};
			$scope.delManagdEntity=function(){
				action="delManagdEntity?";
				param="id="+$('#delManagedEntity').val();
				
				$http.get(action + param ).success(function() {
					
					var orgId = $scope.organization;
					var branchId = $scope.branch;
					var action = "getManagedEntitiyInstancesByOrganizationAndBranch?";
					var org = "orgId=" + orgId;
					var branch = "branchId="+branchId;

					$http.post(action + org+"&"+branch).success(function(data) {
						$scope.ManagedEntities = data;
					});
				});
			};	

									
		});
});

</script>
	
	<script type="text/javascript">
<!-- Add branch modified...... -->
function addNewMangdPage()
{
var selectdOrgn = $("#organizationId option:selected").val();
var selectedbran=$("#branchId option:selected").val();
if (selectdOrgn != undefined && selectdOrgn != null && selectdOrgn !== "Please select Organization" && selectedbran!=undefined && selectedbran!=null && selectedbran!=="Please select Branch") {
	$("#selectdOrgn").val(selectdOrgn);
	$("#selectedbran").val(selectedbran);
	window.location.href = "/fmsv1/addNewManaged?selectdOrgn=" +selectdOrgn+"&selectedbran="+selectedbran;
		
}
else
	{
	alert("Please Select Organization and Branch");
	}
}
</script>
</head>
<body ng-app="managedentitysummaryApp">
    <div class="intro-header">
	<h2 style="text-align:center ">Managed Entity Summary</h2>
	<br>
	<br>
	
	<div class="well" style="margin : 0 15% 0  15%;" width=auto; ng-controller="ManagedentitysummaryCtrl" >
			<form class="form-horizontal" role="form"  action="/fmsv1/addNewManaged" method="POST" name="myForm">
			
			
			
				<div class="form-group">
		
					 <label for="orgnazation" class="col-sm-4 control-label">Organization</label>
					 <div class="col-sm-4">
                           <select class="form-control" ng-model="organization" ng-change="selectBranch()" id="organizationId" name="organizationId" >
                           <option value="">Please select Organization</option>
                           <option ng-repeat="organization in organizations|orderBy:'orgName'" value="{{organization.orgId}}">{{organization.orgName}}</option>				
						</select>
					 </div>
				</div>
				<div class="form-group">
					 <label for="branch " class="col-sm-4 control-label2">Branch</label>
					  <div class="col-sm-6">
                            <select  required class="form-control" ng-model="branch" ng-change="getManagedEntities()" name="branchId" id="branchId" name="branchId" style="width: auto;"  >
							<option value="">Please Select Branch</option>
							<option value="{{br.id}}" ng-repeat="br in branches | orderBy:branchName">{{br.branchName}}</option>
						</select>
						</div>
				</div>
		<div class="table-responsive">
			<table class="table table-bordered table-striped" id="summarrtabley">
				<thead>
					<tr>
						<th class = "tbh">Sl No</th>
						<th class = "tbh">Master Name</th>
					    <th class = "tbh">Instance Name</th>
						<th class = "tbh">Is Main Entity</th>
						<th class = "tbh">Main Entity Name</th>
						<th class = "tbh">Serial No</th>
						<th class = "tbh">Capacity
						<th class = "tbh"></th>
						
						
						</tr>
				</thead>
				<tbody>
					<tr ng-repeat="ME in ManagedEntities|orderBy:'name'" id="managedentitysummary">
						
						
						     <td>{{$index+1}}</td>
						
							<td>{{ME.masterName}}</td>
							<td>{{ME.name}}</td> 
							<td>{{ME.isMainEntity}}</td>	
							<td>{{ME.mainEntityName}}</td>	
							<td>{{ME.serialNumber}}</td>
							<td>{{ME.capacity}}</td>
							<td> <button type="button" class='removeBtn btn btn-danger btn-xs'  id="delManagedEntity" value="{{ME.id}}" ng-click="delManagdEntity()">
											<span class="fa fa-times"></span>Delete
										</button></td>
						</tr>
					
				</tbody>
			</table>
		</div>
			<!--  For delete confirmation -->


			<div id="confirm" class="modal fade" style="display: none;">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-body" style="background-color: #587DC6">
							Are you sure?</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal"
								class="btn btn-primary" id="delete">Delete</button>
							<button type="button" data-dismiss="modal" class="btn">Cancel</button>
						</div>
					</div>
				</div>
			</div>
	
			 <div class="row clearfix">
										<div class="col-md-12 column">
									<a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="" onClick="addNewMangdPage()">
										<button type="button" class="btn btn-info" ng-disabled="branches.length==0"><span class="glyphicon-plus">create New</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
											</div>
					</div>
	</form>
	
	</div>
</div>
</body>
</html>