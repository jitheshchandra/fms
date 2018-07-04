<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link rel="stylesheet" type="text/css" href="assets/css/sri.css">
<style type="text/css">
.row{
height: 40px;
}
.control-label{
text-align: right;
}
.ngdialog.ngdialog-theme-default .ngdialog-content .modal-dialog{    
    width: 800;
}
.ngdialog.ngdialog-theme-default {
    padding-bottom: 160px;
    padding-top: 32px;
}
</style>
<script type="text/javascript">
$(function() {
	var checkListApp = angular.module('installationApp', []);
	checkListApp.controller('installationCntr',function($scope, $http) {
		
		$scope.installationList=[];		
		$http.post("getAssetInstallation").success(function(data){		
			$scope.installationList=data;			
		}).error(function(){
			$scope.confermeFail();
		});
		
		$scope.updateInstallation=function(){
			var data1=$scope.installationList;		
			$http.post("updateAssetInstallation",data1).success(function(data){			
				$scope.installationList=data;
				$scope.confermeSave();
			}).error(function(){
				$scope.confermeFail();
			});			
		};
		
		//Alert for save						
		$scope.confermeSave=function(){
			$('#alertSave').css({"display":""});		
			$("#alertSave").fadeIn();
	        $("#alertSave").fadeIn("slow");
	        $("#alertSave").fadeOut(8000);
		};
		
		//Alert for fail				
		$scope.confermeFail=function(){
			$('#alertFail').css({"display":""});
			$("#alertFail").fadeIn();
	        $("#alertFail").fadeIn("slow");
	        $("#alertFail").fadeOut(8000);
		};
		
		//Alert update				
		$scope.confermeUpdate=function(){
			$('#alertUpdate').css({"display":""});
			$("#alertUpdate").fadeIn();
	        $("#alertUpdate").fadeIn("slow");
	        $("#alertUpdate").fadeOut(8000);
			$scope.alertUpdate=true;
		};
		
		//Alert to delete				
		$scope.confermeDelete=function(){
			$('#alertDelete').css({"display":""});
			$scope.alertDelete=true;
			$("#alertDelete").fadeIn();
	        $("#alertDelete").fadeIn("slow");
	        $("#alertDelete").fadeOut(8000);
		};				
	});
});
</script>
</head>
<body ng-app="installationApp" ng-controller="installationCntr">
	<div class="intro-header">
		<h2 style="text-align: center">Asset Management Technical Installation</h2>
		<br> <br>
		<div class="well" style="margin: 0 9% 0 10%;">
		<div class="row">
		  		<div class="col-md-3">
		           <div class="input-group" style="width: 250px;">
			                <input type="text" placeholder="Search" class="form-control" ng-model="search.$"/>
			                <span class="input-group-addon">
			                <i class="fa fa-search"></i>
			                </span>
		            	</div>
		        </div>
		       <div class="col-md-3">
			       <div class="alert alert-info" id="alertUpdate"  style="width: 775px;padding-top: 5px;padding-bottom: 5px;display: none;">
					    <button type="button" class="close" data-dismiss="alert">x</button>
					    <strong>Info!</strong>
					    Asset installation update successfully.
					</div>
					<div class="alert alert-warning" id="alertFail"  style="width: 775px;padding-top: 5px;padding-bottom: 5px;display: none;">
					    <button type="button" class="close" data-dismiss="alert">x</button>
					    <strong>Warning!</strong>
					    Sorry unable to get asset installation.
					</div> 
					<div class="alert alert-warning" id="alertDelete"  style="width: 775px;padding-top: 5px;padding-bottom: 5px;display: none;">
					    <button type="button" class="close" data-dismiss="alert">x</button>
					    <strong>Delete!</strong>
					   Sorry unable to update asset installation.
					</div>
					<div class="alert alert-success" id="alertSave"  style="width: 775px;padding-top: 5px;padding-bottom: 5px;display: none;">
					    <button type="button" class="close" data-dismiss="alert">x</button>
					    <strong>Success!</strong>
					    Asset installation saved successfully.
					</div>
				</div>						       
		  	</div>
			<div class="table-responsive">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th class="tbh" width="7%">S No</th>
							<th class="tbh" width="15%">Asset Id</th>
							<th class="tbh" width="15%">Asset Code</th>
							<th class="tbh">Installation Date</th>
							<th class="tbh">Acceptance Date</th>
							<th class="tbh">Installer</th>													
						</tr>
					</thead>					
					<tbody>
						<tr ng-repeat="inst in installationList | orderBy:'assetId' |filter:search">
							<td><input type="text" style="display: none;" ng-model="inst.id" value="{{inst.id}}">{{$index+1}}</td>
							<td>{{inst.assetId}}</td>
							<td>{{inst.assetCode}}</td>
							<td><input type="date" class="form-control" ng-model="inst.insatllationDate" value="{{inst.insatllationDate}}"></td>
							<td><input type="date" class="form-control" ng-model="inst.acceptanceDate" value="{{inst.acceptanceDate}}"></td>
							<td><input type="text" class="form-control" ng-model="inst.installer" value="{{inst.installer}}"></td>							
						</tr>
					</tbody>					
					<tfoot>
						<tr>
							<td colspan="6" align="right">
								<button type="submit" ng-click="updateInstallation()" class="btn btn-success">Update</button>
								<a href="/fmsv1/Home"><button type="button" class="btn btn-default">Back</button></a>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>		
		</div>
	</div>
</body>
</html>