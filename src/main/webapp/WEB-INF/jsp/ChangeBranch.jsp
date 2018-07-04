<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Branch</title>


<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
	font-size: 16px;
}
</style>
<script src="./assets/js/jquery-1.9.1.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
	
</script>
<script type="text/javascript">
	function checkForm(form) {
		//not required ...  use firefox's firebug instead;
		if(form.branchId.value==""||form.organizationName.value=="")
			{
			alert('Please select respective field');
			return false;
			}
		return true;
	};
	
	$(function(){
		var changeBranchApp=angular.module('changeBranchApp',[]);
		changeBranchApp.controller('changeBranchAppController',function($scope,$http){
			$scope.branches=[];
			var orgId=$('#organizationId').val();
			var action = "getBranchByOrgId?";
			var param = 'orgId=' +orgId;
			$http.post(action+param).success(function(data){
				$scope.branches=data;
			})
		});
	});
</script>

</head>
<body ng-app="changeBranchApp">
	<div class="intro-header">
		<br> <br>
		<h1 style="text-align: center">Change Branch</h1>
		<div class="well" style="margin: 4% 30% 0 30%" ng-controller="changeBranchAppController">
			<form class="form-horizontal" action="/fmsv1/updateUserBranch" style="margin-top: 6%;"
				method="POST" onsubmit="return checkForm(this);">
				<input type="hidden" name="userId" value='<c:out value="${userId}"/>'>
				<div class="form-group">
					<label for="Organization" class="col-sm-5 control-label2">Organization</label>
					<input type="text" readonly="readonly" class="form-control" style="width: auto;" value='<c:out value="${defaultOrganization.name}"/>'/>	
				</div>
				<input type="hidden" name="organizationId" id="organizationId" value='<c:out value="${defaultOrganization.id}"/>'>
				<div class="form-group">
					<label for="Branch" class="col-sm-5 control-label2" required>Branch</label>
					<div class="col-sm-6">
						<select class="form-control" name="defaultBranchId" id="defaultBranchId" style="width: auto;">
						<option value="">Please select branch</option>
						<option ng-repeat="branch in branches" ng-selected="${defaultBranchId}=={{branch.id}}" value="{{branch.id}}">{{branch.branchName}}</option>
						</select>
					</div>
				</div>
				<div style="margin-left: 20%;">
			<a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" class="btn btn-success">Save</button>
				</div>

			</form>
		</div>
	</div>
</body>
</html>