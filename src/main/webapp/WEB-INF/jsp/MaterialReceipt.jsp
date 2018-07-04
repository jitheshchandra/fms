<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	
	<style>
	  	label{
	  	color:black;
		font-family: "Lato","Helvetica Neue",Helvetica,Arial,sans-serif;
		text-align: right;
	  	
	  }
  </style>
<script type="text/javascript">	
	$(function() {
		var materialReceiptApp = angular.module('materialReceiptApp', []);
		materialReceiptApp.controller('materialReceiptController', function(
				$scope, $http) {
			$scope.values=new Date();
			
			$scope.organizations = [];
			$scope.organization = "";
			
			$scope.branches=[];
			$scope.branch="";
			
			$scope.managedEntitys=[];
			$scope.managedEntity="";
			
			$scope.indentDetails=[];
			
			var getOrg = "getOrganizations";
			$http.post(getOrg).success(function(data) {
				$scope.organizations = data;
			});

			$scope.getBranches = function() {
				
				$scope.branches=[];
				$scope.branch="";
				
				$scope.managedEntitys=[];
				$scope.managedEntity="";
				
				$scope.indentDetails=[];
				
				var orgId = $scope.organization;

				var action = "getBranchByOrgId?";
				var param = "orgId=" + orgId;

				$http.post(action + param).success(function(data) {
					$scope.branches = data;
				});
			};
			$scope.getManagedEntity = function() {
				
				$scope.managedEntitys=[];
				$scope.managedEntity="";
				
				$scope.indentDetails=[];
				
				var branchId = $scope.branch;

				var action = "getManagedEntitiesForBranch?";
				var param = "branchId=" + branchId;

				$http.post(action + param).success(function(data) {
					$scope.managedEntitys = data;
				});

			};
			
			$scope.getIndentList=function(){
				var orgId = $scope.organization;
				var branchId = $scope.branch;
				var managedEntityId=$scope.managedEntity;
				
				var action="getListOfIndentDetail?";
				var param="organizationId="+orgId+"&branchId=" + branchId + "&manageEntityId="+ managedEntityId;
				$http.post(action+param).success(function(data){
					$scope.indentDetails=data.indentLists;
				});
			};
		});

	});
	
	
	function validate(form) {
		var ck_name = /^[A-Za-z0-9]{3,50}$/;
		if ($('#listBody tr').length == 0) {
			alert('Nothing to save...');
			return false;
		}
		var receiptNo = form.receiptNumber.value;
		if (!ck_name.test(receiptNo)) {
			alert('Please enter valid ReceiptNo');
			return false;
		}
	}
</script>

</head>
<body ng-app="materialReceiptApp">
 <div class="intro-header" >
	<h2 style="text-align:center ">Material Receipt</h2>
	<br>
	<br>
	<div class="well" style="margin : 0 25% 0  25%;" ng-controller="materialReceiptController">
			<form id="mainForm" class="form-horizontal" action="/fmsv1/saveMaterialReceipt" method="POST" style="margin-top: 3%;" onsubmit="return validate(this);">
				
				<div class="form-group">
					<label for="Organization" class="col-sm-5 control-label2">Organization</label>
					<div class="col-sm-6">
						<select class="form-control form-page" ng-model="organization" name="organizationId" id="organizationId" style="width: auto;" ng-change="getBranches()"required/>
							<option value="">Please Select Organization</option>
							<option ng-repeat="organization in organizations | orderBy:'orgName'" value="{{organization.orgId}}">{{organization.orgName}}</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="branch " class="col-sm-5 control-label2">Branch</label>
					<div class="col-sm-6">
						<select class="form-control" ng-model="branch" name="branchId" id="branchId" style="width: auto;" ng-change="getManagedEntity()"required/>
							<option value="">Please Select Branch</option>
							<option ng-repeat="branch in branches|orderBy:'branchName'" value="{{branch.id}}">{{branch.branchName}}</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="branch " class="col-sm-5 control-label2">Manage Entity</label>
					<div class="col-sm-6">
						<select class="form-control" ng-model="managedEntity" name="manageEntityId" id="manageEntityId" style="width: auto;" ng-change="getIndentList()">
							<option value="" selected="selected">Please Select Manage Entity</option>
							<option ng-repeat="managedEntity in managedEntitys|orderBy:'name'" value="{{managedEntity.id}}">{{managedEntity.name}}</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-5 control-label2">Reciept No</label>
					<div class="col-sm-6">
						<input type="text" pattern="[0-9]*"   name="receiptNumber" id="receiptNumber"
							class="form-control" style="width: auto;" maxlength="50" required/>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-5 control-label2">Reciept Date</label>
					<div class="col-sm-6">
						<input type="date" name="receiptDate" id="receiptDate"
							class="form-control" ng-model="values" style="width: auto;"required/>
					</div>
				</div>
				<div class="table-responsive">
					<table class="table table-bordered table-striped"
						id="itemListTable" >
						<col style="width: auto;">
                        <col style="width: auto;">
                         <col style="width: auto;">
						<thead>
							<tr>
								<th class="tbh">S.No</th>
								<th class="tbh">Item</th>
								<th class="tbh">Quantity</th>
							</tr>
						</thead>
						<tbody id='listBody'>
							<tr ng-repeat="indentDetail in indentDetails|orderBy:'name'">
								<td><input type="hidden" name="materialIndentId" id="materialIndentId" value="{{indentDetail.id}}">{{$index+1}}</td>
								<td><input type="hidden" name="indentDetail[{{$index}}].itemId" id="indentDetail[{{$index}}].itemId" value="{{indentDetail.itemId}}" />{{indentDetail.name}}</td>
								<td><input type="number" name="indentDetail[{{$index}}].quantity" id="indentDetail[{{$index}}].quantity" class="form-control" value="{{indentDetail.orderQuantity}}"></td>
							</tr>
						</tbody>
					</table>
				</div>
		<a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="submit" ng-disabled="indentDetails.length==0" class="btn btn-success btn-default">Save</button>	
        </form>
	</div>
</div>
</body>
</html>