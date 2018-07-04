<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Managed Entity Group Summary</title>
<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>
<script>
	$(document)
			.ready(
					function() {
						$("#add-Entities")
								.click(
										function() {
											$(this).attr('disabled', true);
											var orgName = $('#organizationId')
													.val();
											var branchName = $('#branchId')
													.val();
											if (orgName != ''
													&& branchName != '') {
												$('#managedentitygrouptable tr')
														.last()
														.after(
																'<tr>	<td><input type="hidden" class="form-control" id="mgd" ></td></tr>');
											} else {
												alert('---Please Add Organization & Branch--');

											}
										});
					});
</script>
<script type="text/javascript">
	$(function() {
		var escalationApp = angular.module('managedentitygroupsummaryApp', []);
		escalationApp
				.controller(
						'ManagedentitygroupsummaryCtrl',
						function($scope, $http) {
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
								$http.post(action + param).success(
										function(data) {
											$scope.branches = data;
										});
							};
							$scope.addNew = function() {
								len = $scope.ManagedEntities.length;
								$scope.ManagedEntities.push({
									id : ''
								});
							};

							$scope.getManagedEntities = function() {
								var orgId = $scope.organization;
								var branchId = $scope.branch;
								var action = "getManagedEntitiesByOrganizationAndBranch?";
								var org = "orgId=" + orgId;
								var branch = "branchId=" + branchId;
								$http.post(action + org + "&" + branch)
										.success(function(data) {
											$scope.ManagedEntities = data;
										});
							};

							$scope.delManagdGroup = function() {
								action = "delManagdGroup?";
								param = "id="
										+ $('#delManagedEntityGroup').val();
								$http
										.post(action + param)
										.success(
												function() {
													var orgId = $scope.organization;
													var branchId = $scope.branch;
													var action = "getManagedEntitiesByOrganizationAndBranch?";
													var org = "orgId=" + orgId;
													var branch = "branchId="
															+ branchId;
													$http
															.post(
																	action
																			+ org
																			+ "&"
																			+ branch)
															.success(
																	function(
																			data) {
																		$scope.ManagedEntities = data;
																	});
												});
							};
						});
	});
</script>
<script type="text/javascript">
function AddNewManagedEntityGroup()
{
var selectdOrgn = $("#organizationId option:selected").val();
var selectedbran=$("#branchId option:selected").val();
if (selectdOrgn != undefined && selectdOrgn != null && selectdOrgn !== "Please select Organization" && selectedbran!=undefined && selectedbran!=null && selectedbran!=="Please select Branch") {
	$("#selectdOrgn").val(selectdOrgn);
	$("#selectedbran").val(selectedbran);
	window.location.href = "/fmsv1/addNewManagedEntityGroup?selectdOrgn=" +selectdOrgn+"&selectedbran="+selectedbran;
		
}
else
	{
	alert("Please Select Organization and Branch");
	}
}
</script>

</head>
<body ng-app="managedentitygroupsummaryApp">

	<div class="intro-header">
		<h2 style="text-align: center">Managed Entity Group Summary</h2>
		<br> <br>

		<div class="well" style="margin: 0 10% 0 10%;" width=auto;
			ng-controller="ManagedentitygroupsummaryCtrl">
			<form class="form-horizontal" style="margin-top: 2%;"
				name="managedEntitySummary" id="managedEntitySummary"
				action="#" method="POST">

				<div class="form-group">
					<label for="orgnazation" class="col-sm-4 control-label">Organization</label>
					<div class="col-sm-4">
						<select class="form-control" ng-model="organization"
							id="organizationId" name="organizationId"
							ng-change="selectBranch()">
							<option value="">Please select Organization</option>
							<option
								ng-repeat="organization in organizations|orderBy:'orgName'"
								value="{{organization.orgId}}">{{organization.orgName}}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="branch " class="col-sm-4 control-label2">Branch</label>
					<div class="col-sm-6">
						<select class="form-control" ng-model="branch"
							ng-change="getManagedEntities()" name="branchId" id="branchId"
							name="branchId" style="width: auto;">
							<option value="">Please Select Branch</option>
							<option value="{{br.id}}"
								ng-repeat="br in branches | orderBy:branchName">{{br.branchName}}</option>
						</select>
					</div>
				</div>
				<div class="table-responsive">
					<table class="table table-bordered table-striped"
						id="employeetable">
						<thead>
							<tr>
								<th class="tbh">S No</th>
								<th class="tbh">Name</th>
								<th class="tbh">Description</th>

								<th class="tbh"><a href="#" onClick="AddNewManagedEntityGroup()"><button
											type="button" class="btn btn-info"
											ng-disabled="EmployeeSummary.length==0" id="newManagedEntity">
											<span class="glyphicon-plus">Add Managed Entity Group</span>
										</button></a></th>
							</tr>
						</thead>
						<tbody>

							<tr ng-repeat="ME in ManagedEntities|orderBy:'id'">
								<td>{{$index+1}}</td>
								<td>{{ME.name}}</td>


								<td>{{ME.description}}</td>

								<td>
									<button type="button" value="{{ME.id}}"
										class='removeBtn btn btn-danger btn-xs' ide="deleteEmployee"
										ng-click="delEmployee()">
										<span class="fa fa-times"></span>Delete
									</button>
								</td>


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
			</form>
			<script type="text/javascript">
				$(document)
						.ready(
								function() {
									$('button.removeBtn')
											.click(
													function(e) {
														//not required ...  use firefox's firebug instead;
														var $form = $(this)
																.closest('form');
														var btn_val = $(this)
																.val();
														e.preventDefault();
														$('#confirm')
																.modal(
																		{
																			backdrop : 'static',
																			keyboard : true
																		})
																.one(
																		'click',
																		'#delete',
																		function(
																				e) {
																			window.location.href = "/fmsv1/delEmployee?id="
																					+ btn_val;
																		});
													});
								});
			</script>
		</div>
	</div>
</body>

</html>