<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FMS - Branch Summary</title>
<script type="text/javascript">
	$(function() {
		var branchSummaryApp = angular.module('branchSummaryApp', []);
		branchSummaryApp.controller('branchSummaryController', function($scope,
				$http) {
			$scope.organizations = [];
			$scope.organization = "";
			$scope.branches = [];

			$http.post("getOrganizations").success(function(data) {
				$scope.organizations = data;
			});

			$scope.getBranches = function() {
				$scope.branches = [];
				$http.post(
						"getBranchByOrganization?orgId="
								+ this.organization.orgId).success(
						function(data) {
							$scope.branches = data;
						});
			};
			
			$scope.delbranch=function(){
				action="delBranch?";
				param="id="+$('#deleteBranch').val();
			$http.get(action + param ).success(function() {
				     var orgId = $scope.organization;
			$http.post("getBranchByOrganization?orgId="+ this.organization.orgId).success(function(data) {
								$scope.branches = data;
							});
			
				})
			};	
		});
	});
</script>
<script type="text/javascript">
<!-- Add branch modified...... -->
function addOrganizationBranch()
{
var selectedOrg = $("#organizationId option:selected").val();
if (selectedOrg != undefined && selectedOrg != null && selectedOrg !== "Please select Organization") {
	$("#selectedOrg").val(selectedOrg);
	window.location.href = "/fmsv1/addBranch?selectedOrg=" + selectedOrg;
}
else
	{
	alert("Please Select Organization to add Branch");
	}
}
</script>
</head>
<body ng-app="branchSummaryApp">
	<div class="intro-header" ng-controller="branchSummaryController">
		<h2 style="text-align: center">Branch Summary</h2>
		<br> <br>
		<div class="well" style="margin: 0 9% 0 10%;">
			<form name="branchSummary" id="branchSummary" action="/fmsv1/addBranch" method="POST">
			<input type="hidden" id="selectedOrg" value=""></input>
				<div class="table-responsive">
					<table class="table table-striped ">
						<tr>
						<td><a href="/fmsv1/Home">
							
							<button type="button" class="btn btn-default">Back</button></a></td>
							<td style="text-align: center; size: auto;"><select
								class="form-control" name="organizationId" id="organizationId"
								ng-model="organization"
								ng-options="org.orgName for org in organizations|orderBy:'orgName' track by org.orgId"
								ng-change="getBranches()">
									<option value="">Please select Organization</option>
							</select></td>

							<td colspan="5" style="text-align: right;">
							<a href="" onClick="addOrganizationBranch();"><button type="button" class="btn btn-info"><span class="glyphicon-plus">Add Branch</span></button></a></td>

							
						
						</tr>
						<tr>
							<th>Name</th>
							<th>Address</th>
							<th>Contact Person</th>
							<th>Contact No.</th>
							<th></th>
						</tr>
						<tr ng-repeat="branch in branches|orderBy:'branchName'">
							<td><a href="/fmsv1/editBranch?bid={{branch.id}}">{{branch.branchName}}</a></td>
							<td>{{branch.address}}</td>
							<td>{{branch.contactPerson}}</td>
							<td>{{branch.contactNumber}}</td>
							<td> <button type="button" class='removeBtn btn btn-danger btn-xs'  id="deleteBranch" value="{{branch.id}}" ng-click="delbranch()">
											<span class="fa fa-times"></span>Delete
										</button></td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</body>

<div id="confirm" class="modal fade" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body" style="background-color: #70B8FF">Are
				you sure?</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-primary"
					id="delete">Delete</button>
				<button type="button" data-dismiss="modal" class="btn">Cancel</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$('button.abc').click(function(e) {
			var $form = $(this).closest('form');
			var btn_val = $(this).val();
			e.preventDefault();
			$('#confirm').modal({
				backdrop : 'static',
				keyboard : true
			}).one('click', '#delete', function(e) {
				//	window.location.href = "/fmsv1/delBranch?id="+btn_val;
			});
		});
	});
</script>
</html>