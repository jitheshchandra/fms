<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>

<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>
<script type="text/javascript">
	$(function() {
		var RelationApp = angular.module('RelationApp', []);
		RelationApp.controller('RelationAppController',function($scope, $http ) {
			
			$scope.relations=[];
			$scope.relation="";

			var getRelations = "getAssetRelation";
			$http.get(getRelations).success(function(data){
				$scope.relations=data;
			}).error(function(){
				alert("Sorry unable to get Relation summary");
			});
		});
	});
</script>
<script type="text/javascript">
function AddNewRelation()
{
	window.location.href = "/fmsv1/AddRelation";
}
</script>
</head>
<body ng-app="RelationApp" ng-controller="RelationAppController">
	<div class="intro-header">
		<h2>Relation</h2>
		<br> <br>
		<div class="well" style="margin: 0% 20% 0% 25%;">
			<form class="form-horizontal"    >
		  	</br>
			
			
			
			
				<div class="table-responsive">
					<table class="table table-bordered table-striped"
						id="table">
						<thead>
							<tr>
							    <th class="tbh">S No</th>
							    <th class="tbh">Name</th>
								<th class="tbh">Description</th>
								<th class="tbh"><a href="" onclick="AddNewRelation()"><button
									type="button" class="btn btn-info"  id="new">
									<span class="glyphicon-plus">Add Relation</span>
								</button></a></th>
							</tr>
						</thead>
						<tbody id="List">
							
								<tr ng-repeat="relation in relations |orderBy:'name'" >   
									<td>{{relation.id}}</td>
									<td><a href="#">{{relation.name}}</a></td>
									<td>{{relation.description}}</td>
									<td>
										<button value="{{relation.id}}"
											class='removeBtn btn btn-danger btn-xs' value="delete">
											<span class="fa fa-times"></span>Delete
										</button>
									</td>
								</tr>
							
							
						</tbody>

					</table>
				</div>

				<td colspan="2" style="text-align: left;"><a href="/fmsv1/Home">
						<button type="button" class="btn btn-default">Back</button>
				</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
			</form>
		</div>

	</div>


</body>
</html>