<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>

<script type="text/javascript">
	$(function() {
		var AssetApp = angular.module('AssetApp', []);
		AssetApp.controller('AssetCtrl', function($scope, $http) {
			          
			$scope.assetCategories=[];
			$scope.assetCategory="";
			$scope.assetCategoryAttribute="";
			$scope.assetCategoryAttributes=[];
			$scope.assetAttributeType="";
			$scope.assetAttributeTypes=[];
					
			var getAssets = "getAssetCategory";
			$http.get(getAssets).success(function(data){
				$scope.assetCategories=!data?[]:data;
			}).error(function(){
				alert("Sorry unable to get Categories");
			});
			
			
			var getAssetAttributeTypes = "getAssetAttributeTypes";
			$http.get(getAssetAttributeTypes).success(function(data){
				$scope.assetAttributeTypes = !data?[]:data;
			}).error(function(){
				alert("sorry unable to get attribute types ");
			});
			
			$scope.getAllAssetCategoryAttributes = function(){
				var getAttributes = "getAssetCategoryAttributes";
				var category = $scope.assetCategory;
				$http.get(getAttributes+"?catId="
						+category).success(function(data){														
					$scope.assetCategoryAttributes=!data?[]:data;
				}).error(function(){
					alert("Sorry unable to get Category Attributes");
				});
			};
			
			$scope.addAssetCategoryAttributeList = function() {				
				$scope.assetCategoryAttributes.push({
					id : '',
					name:'',
					attributeType:''
				});
				
			};
			
			
			
			
			$scope.deleteAssetCategoryAttributeList = function(index) {
				debugger;
				var assetCategoryAttr = $scope.assetCategoryAttributes[index];
				if (assetCategoryAttr.id != '') {
					var action = 'deleteAssetCategoryAttribute?';
					var assetCategoryId = assetCategoryAttr.id;
					//var itemId = assetCategoryAttr.itemId;
					param = 'id=' + assetCategoryId +'&cid='+$scope.assetCategory;
					$http.post(action+param).success(function(data) {
						  $scope.assetCategoryAttributes = data;
						  alert("deleted successfully");
					})
					.error(function(data, status) {
					  console.error('Repos error', status, data);
					});
				} else {
					//just delete from the page its not saved no id
					$scope.assetCategoryAttributes.splice(index, 1);
					
				}
			};
			
			
			$scope.saveAssetCategoryAttributes = function() {
				debugger;
				var dataDetail = {
						category : $scope.assetCategory,
						assetAttributes : $scope.assetCategoryAttributes
				};
				var assetData = 'SaveAssetCategoryAttribute';
			
				$http.post(assetData+"?dataDetail="+JSON.stringify(angular.toJson(dataDetail))).success(function(data) {
					alert('Asset Category Attributes saved successfully');
				}).error(function() {
					alert('sorry could not save to database');
				});
			};
			
			
		});
	});
</script>
</head>
<body ng-app="AssetApp">
	<div class="intro-header">
		<h2 style="text-align: center">Asset Category Attribute</h2>
		<br>
 		<br>
		<div class="well" style="margin:0 15% 0 15%;" width=auto; ng-controller="AssetCtrl">
		<form class="form-horizontal" style="margin-top: 2%;" 
		    name="AssetCategoryAttribute" id="AssetCategoryAttribute" ng-submit="saveAssetCategoryAttributes()" method="POST">
		
		
		<div class="form-group">
					<label for="Organization" class="col-sm-4 control-label2">Asset Category</label>
					<div class="col-sm-6">
						<Select class="form-control form-page" name="category" id="assetCategory" style="width:auto;" ng-change="getAllAssetCategoryAttributes()" ng-model="assetCategory" required>
						<option value="">Please select Asset Category</option>
						<option ng-repeat="assetCategory in assetCategories" value="{{assetCategory.id}}">{{assetCategory.categoryName}}</option>
						</Select>
					</div>
				</div>
				<table class="table table-bordered table-striped" id="itemListTable" >
						<thead>
							<tr>
								<th class="tbh">S.No</th>
								<th class="tbh">Attribute Name<span style="padding-left: 22px ;text-color:gray;"> (ex- Asset Warranty Start)</span></th>
								<th class="tbh">Attribute Type</th>
								<th class="tbh"></th>
							</tr>
						</thead>
						<tbody id='listBody'>	
						 <tr ng-repeat="assetCategoryAttribute in assetCategoryAttributes">
						    <td><input type="hidden" name='id' ng-model="assetCategoryAttribute.id" value="{{assetCategoryAttribute.id}}"/>{{$index+1}}</td>
						    <td><input type="text" ng-model="assetCategoryAttribute.name" class="form-control" name="name"  value=""></td>
						   
						    <td><Select id="attributeType" ng-model="assetCategoryAttribute.attributeType"  name="attributeType">
						    <option value="">--Select--</option>
						    <option ng-repeat="assetAttributeType in assetAttributeTypes">{{assetAttributeType.name}}</option>
						    </Select></td>
						     <td>
						       <button class="btn btn-sm btn-danger" type="button" ng-click="deleteAssetCategoryAttributeList($index)" ><span class="glyphicon glyphicon-trash"></span>
							   </button>
						     </td>
						  </tr> 
						</tbody>
					</table>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
			    <a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-success" ng-disabled="managedEntityType.length==0" ng-click="addAssetCategoryAttributeList()">Add</button>&nbsp;&nbsp;&nbsp;
			    <button type="submit" ng-disabled="assetCategoryAttributes.length==0" class="btn btn-success btn-default">Save</button>
		
		</form>
		</div>
		</div>
		
</body>
</html>
		
