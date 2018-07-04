<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		var assetSummaryApp = angular.module('assetSummaryApp', []);
		assetSummaryApp.controller('assetSummaryController',function($scope, $http ) {
			
			$scope.assetConditionList=[];
			$http.post("getAssetCondition").success(function(data) {
				$scope.assetConditionList = data;				
			}).error(function(){
				alert("Sorry unable to get asset condition");
			});		
			
			$scope.assetCategoryList=[];
			$http.post("getAssetCategory").success(function(data) {
				$scope.assetCategoryList = data;				
			}).error(function(){
				alert("Sorry unable to get asset category");
			});	
		});
	});
  </script>

</head>
<body>
<div class="intro-header">
	<h2 style="text-align:center ">Edit AssetManagementSummary</h2>
	<br>
	<br>
	
		<div class="well" style="margin:0% 25% 0 25%; ">
			<form name="addEmployee" class="form-horizontal" role="form" style="margin-top:5%;margin-left:5%;"action="/fmsv1/updateAssetManagementSummary/"   method="POST">
			<input type="hidden" name="id" id="id" value='<c:out value="${managementSummary.id}"/>' >
					 <div class="form-group">
	        	<label class="col-sm-2 control-label" style="width: 24%">Name</label>
	    		<input type="text"  id="name" name="name" class="form-control" value='<c:out value="${managementSummary.name}"/>'style="width: 60%;"  required/>	
	    		</div>
	    		<div class="form-group">
	    		<label class="col-sm-2 control-label" style="width: 24%">Asset Category</label>	    			 
			   	<select class="form-control"  id="assetCategory" name="assetCategory" style="width: 60%" >
			   		<option value="" >--Please select--</option>
			   		<option ng-repeat="category in assetCategoryList" value="{{category.id}}">{{category.categoryName}}</option>			   						
			   	</select>
			   	</div>
			   	
			   	<div class="form-group">
	    		<label class="col-sm-2 control-label" style="width: 24%">Asset Condition</label>
	    		<select class="form-control"  id="assetCondition" name="assetCondition" style="width: 60%" ng-required="number">
			   		<option value="" >--Please select--</option>
			   		<option ng-repeat="condition in assetConditionList" value="{{condition.id}}">{{condition.conditionName}}</option>					
			   	</select>
			   	</div>
			   	
			   	<div class="form-group">
	    		<label class="col-sm-2 control-label" style="width: 24%">Code</label>
	    		<input type="text" class="form-control"  style="width: 60%" id="code" name="code" value='<c:out value="${managementSummary.code}"/>' style="width: 30%;" required/>	
	    		</div>
	    		
	    		<div class="form-group">
	    		<label class="col-sm-2 control-label" style="width: 24%">Description</label>
	    		<input type="text" class="form-control"  style="width: 60%"id="description" name="description"value='<c:out value="${managementSummary.description}"/>' style="width: 30%;" required/>	
	    		</div>
	    		
	    		<div class="form-group">
	    		<label class="col-sm-2 control-label" style="width: 24%">Type</label>
	    		<input type="text" class="form-control" style="width: 60%" id="type" name="type" value='<c:out value="${managementSummary.type}"/>' style="width: 30%;" required/>	
	    		</div>
	    		
	    		<div class="form-group">
	    		<label class="col-sm-2 control-label" style="width: 24%">Is Available</label>
	    		<select class="form-control"  style="width: 20%" name="isAvailable" id="isAvailable" ng-required="number">	    			
			   		<option selected="selected" value="1" >Yes</option>
			   		<option value="0">No</option>				
			   	</select>
			   	</div>
			   	
			   	<div class="form-group">
	    		<label class="col-sm-2 control-label" style="width: 24%">Manufacturer</label>
	    		<input type="text" class="form-control" style="width: 60%" id="manufacturer" name="manufacturer" value='<c:out value="${managementSummary.manufacturer}"/>' style="width: 30%;" required/>	
	    		</div>
	    		
	    		<div class="form-group">
	    		<label class="col-sm-2 control-label" style="width: 24%">User</label>
	    		<input type="text" class="form-control"  style="width: 60%" id="user" name="user" value='<c:out value="${managementSummary.user}"/>' style="width: 30%;" required/>	
	    		</div>
	    		
	    		<div class="form-group">
	    		<label class="col-sm-2 control-label" style="width: 24%">Supplier</label>
	    		<input type="text" class="form-control" style="width: 60%" id="supplier" name="supplier" value='<c:out value="${managementSummary.supplier}"/>' style="width: 30%;" required/>	
	    		</div>					
							  <button type="reset" class="btn btn-primary">Reset</button>&nbsp;
							  <button type="submit" class="btn btn-success">Save</button>					
			</form>
		</div>

</div>

</body>
</html>