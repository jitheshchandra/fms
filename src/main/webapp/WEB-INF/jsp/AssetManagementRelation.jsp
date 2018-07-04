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
			
			$scope.relations=[];
			$scope.relation="";
            
			$scope.assets=[];
			$scope.asset="";
			
			var getRelations = "getAssetRelation";
			$http.get(getRelations).success(function(data){
				$scope.relations=data;
			}).error(function(){
				alert("Sorry unable to get Relation summary");
			});
			
			var getAssets = "getAllAsset";
			$http.get(getAssets).success(function(data){
				$scope.assets=data;
			}).error(function(){
				alert("Sorry unable to get Relation summary");
			});
			
		});
	});
</script>
</head>
<body ng-app="AssetApp">
	<div class="intro-header">
		<h2 style="text-align: center">Asset Relation</h2>
		<br> <br>

		<div class="well" style="margin:0 15% 0 15%;" width=auto; ng-controller="AssetCtrl">
		<form class="form-horizontal" style="margin-top: 2%;" 
		    name="managedEntitySummary" id="managedEntitySummary" action="/fmsv1/saveRelations" method="POST">
		
		
		
		<table class="table table-bordered table-striped" align="center">
					<thead>
						<tr>
							<th class="tbh">Sl No</th>
							<th class="tbh">Asset</th>
							<th class="tbh">Relation Type</th>
							<th class="tbh">Entity Type</th>
							<th class="tbh">Entity</th>
							
						</tr>
					</thead>
					
					<tbody>
						 <td>{{$index+1}}</td>
							<td>
					       	<select class="form-control" ng-model="assetId" id="assetId" name="assetId" style="width: auto;">
							      <option value="">Please select Asset</option>
							   <option ng-repeat="asset in assets|orderBy:'name'" value="{{asset.id}}">{{asset.name}}</option>
						    </select>
							</td>
							
							<td >
								<select class="form-control" ng-model="branch"  name="relationType" id="relationType" style="width: auto;">
							     <option value="">Please Select Relation </option>
							      <option ng-repeat="relation in relations | orderBy:name" value="{{relation.id}}">{{relation.name}}</option>
						       </select>
							</td>
							
							<td><input type="text" class="form-control" id="entityType" name="entityType" value="{{relation.entityType}}"></td>
							<td><input type="text" class="form-control" id="entity" name="entity" value="{{relation.entity}}"   ></td>
						   </tr>
					</tbody>
				</table>
				   <a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="submit" class="btn btn-success" >Save</button>
				</form>
			</div>	
		</div>
	</div>
</body>
</html>
		
