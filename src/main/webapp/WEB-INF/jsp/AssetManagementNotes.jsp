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
	var checkListApp = angular.module('notesApp', []);
	checkListApp.controller('notesCntr',function($scope, $http) {
		
		$scope.notesList=[];		
		$http.post("getAssetNotes").success(function(data){	
			debugger;
			$scope.notesList=data;								
		}).error(function(){
			$scope.confermeFail();
		});
						
		$scope.updatenotes=function(){				
			$http.post("updateAssetNotes",$scope.notesList).success(function(data){			
				$scope.notesList=data;
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
<body ng-app="notesApp" ng-controller="notesCntr">
	<div class="intro-header">
		<h2 style="text-align: center">Asset Management Notes</h2>
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
					    Asset notes update successfully.
					</div>
					<div class="alert alert-warning" id="alertFail"  style="width: 775px;padding-top: 5px;padding-bottom: 5px;display: none;">
					    <button type="button" class="close" data-dismiss="alert">x</button>
					    <strong>Warning!</strong>
					    Sorry unable to get asset notes.
					</div> 
					<div class="alert alert-warning" id="alertDelete"  style="width: 775px;padding-top: 5px;padding-bottom: 5px;display: none;">
					    <button type="button" class="close" data-dismiss="alert">x</button>
					    <strong>Delete!</strong>
					   Sorry unable to update asset notes.
					</div>
					<div class="alert alert-success" id="alertSave"  style="width: 775px;padding-top: 5px;padding-bottom: 5px;display: none;">
					    <button type="button" class="close" data-dismiss="alert">x</button>
					    <strong>Success!</strong>
					    Asset notes saved successfully.
					</div>
				</div>						       
		  	</div>
			<div class="table-responsive">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th class="tbh">S No</th>
							<th class="tbh">Asset Name</th>
							<th class="tbh">Asset Code</th>
							<th class="tbh">Notes</th>
							<th class="tbh">Attachment Id</th>
							<th class="tbh">User Id</th>											
						</tr>
					</thead>
					<tbody>					
						<tr ng-repeat="notes in notesList | orderBy:'assetId' |filter:search">
							<td>{{$index+1}}</td>
							<td>{{notes.assetId}}</td>
							<td>{{notes.assetCode}}</td>
							<td>{{notes.notes}}</td>
							<td>{{notes.attachmentId}}</td>
							<td>{{notes.userId}}</td>						
						</tr>
					</tbody>					
				</table>
			</div>			
		</div>
	</div>
</body>
</html>