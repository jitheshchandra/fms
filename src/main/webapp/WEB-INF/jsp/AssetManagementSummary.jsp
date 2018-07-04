<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>

<style type="text/css">
.row{
height: 40px;
}
.control-label{
text-align: right;
}
.ngdialog.ngdialog-theme-default .ngdialog-content {    
    width: 610;
}
.ngdialog.ngdialog-theme-default {
    padding-bottom: 160px;
    padding-top: 32px;
}
</style>
<script type="text/javascript">
	$(function() {
		var assetSummaryApp = angular.module('assetSummaryApp', []);
		assetSummaryApp.controller('assetSummaryController',function($scope, $http ) {
			
			$scope.assetUses=[];
			$http.post("getAssetSummary").success(function(data){
				$scope.assetUses=data;
			}).error(function(){
				alert("Sorry unable to get asset summary");
			});
			
			$scope.getSummary=function(){
				$http.post("getAssetSummary").success(function(data){
					$scope.assetUses=data;
				}).error(function(){
					$scope.confermeFail();
				});
			};
			
			
			$scope.assetList=[];
			$http.post("getAllAsset").success(function(data) {
				$scope.assetList = data;				
			}).error(function(){
				alert("Sorry unable to get asset condition");
			});	
			
			
			
			
			
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
			
			$scope.addAsset=function(diffrentiate){					
				$scope.assetUses=[];
				var data;
				if(diffrentiate==1)
					data={
						id:$('#id').val(),
						 name:$('#name').val(),
						 assetCategory:$('#assetCategory').val(),
						 assetCondition:$('#assetCondition').val(),
						 code:$('#code').val(),
						 description:$('#description').val(),
						 type:$('#type').val(),
						 isAvailable:$('#isAvailable').val(),
						 manufacturer:$('#manufacturer').val(),
						 user:$('#user').val(),
						 supplier:$('#supplier').val(),
						 model:$('#model').val(),
						 assetUseId:$('#assetUseId').val()
					}
				else
					data={
						id:$('#id1').val(),
						 name:$('#name1').val(),
						 assetCategory:$('#assetCategory1').val(),
						 assetCondition:$('#assetCondition1').val(),
						 code:$('#code1').val(),
						 description:$('#description1').val(),
						 type:$('#type1').val(),
						 isAvailable:$('#isAvailable1').val(),
						 manufacturer:$('#manufacturer1').val(),
						 user:$('#user1').val(),
						 supplier:$('#supplier1').val(),
						 model:$('#model1').val(),
						 assetUseId:$('#assetUseId1').val()
					};
						
				$http.post("saveAsset",data).success(function(data){
						$('#myModal').modal('hide');
						$scope.assetUses=data;
						if(diffrentiate==1)
							$scope.confermeSave();
						else
							$scope.confermeUpdate();
						$scope.makeNull();						
				}).error(function(){				
					$scope.confermeFail();
					$scope.getSummary();
				});
			 };			 
			 
				
				//Alert for save						
				$scope.confermeSave=function(){
					$('#alertSave').css({"display":""});
					$scope.alertSave=true;
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
					
				//Edit asset 
			    $scope.editAsset = function (assetId) {					
					var assetUserId="assetUseId="+assetId.id;
					$http.post("editAsset?"+assetUserId).success(function(data){										
						$scope.openAssetEdit(data,assetId.id);
					}).error(function(){				
						$scope.confermeFail();
						$scope.getSummary();
					});								        
			    }; 
			    
			    $scope.deleteAsset=function(assetId){
			    	$http.post("deleteAseet?"+"assetId="+assetId).success(function(data){										
			    		$scope.assetUses=data;
			    		$scope.confermeDelete();
					}).error(function(){				
						$scope.confermeFail();
						$scope.getSummary();
					});	
			    }
			    
			    $scope.openAssetEdit=function(asset,assetUseId){			    	
					$scope.assetCategory=asset.assetCategory;
					$scope.assetCondition=asset.assetCondition;
					$scope.isAvailable=asset.isAvailable.toString();			    	
			    	ngDialog.open({            
			            template:'<form ng-submit="addAsset(2);closeThisDialog()"><div class="modal-header" style="	padding-top: 0px; padding-bottom: 0px;"><h4 style="text-align: center;">Update Asset  "<i>'+asset.name+'</i>"</h4></div>'+'<div style="padding-top: 15px;"><div class="row">'+
				        	'<label class="col-sm-2 control-label" style="width: 24%">Name</label>'+
				        	'<input type="text" id="id1" name="id1" value="'+asset.id+'" style="display:none;"/>'+
				        	'<input type="text" id="assetUseId1" name="assetUseId1" value="'+assetUseId+'" style="display:none;"/>'+
				    		'<input type="text" class="form-control" style="width: 60%" id="name1" name="name1" value="'+asset.name+'" required></div>'+
				    		
				    		'<div class="row"><label class="col-sm-2 control-label" style="width: 24%">Asset Category</label>'+				    			    		
						   '<select class="form-control" ng-model="assetCategory" id="assetCategory1" name="assetCategory1" style="width: 60%" required>'+
						   '<option value="">--Please select--</option>'+
						   '<option ng-repeat="category in assetCategoryList"  value="{{category.id}}">{{category.categoryName}}</option></select></div>'+			   						
						   							   						   
						   	'<div class="row"><label class="col-sm-2 control-label" style="width: 24%">Asset Condition</label>'+
				    		
				    		'<select class="form-control" ng-model="assetCondition" id="assetCondition1" name="assetCondition1" style="width: 60%" required>'+
						   		'<option value="" >--Please select--</option>'+
						   		'<option ng-repeat="condition in assetConditionList" value="{{condition.id}}">{{condition.conditionName}}</option></select></div>'+					
						   	
						   	
						   	'<div class="row"><label class="col-sm-2 control-label" style="width: 24%">Code</label>'+				    		
				    		'<input type="text" class="form-control"  style="width: 60%" id="code1" name="code1" value="'+asset.code+'"/></div>'+				    						    	
				    		
				    		'<div class="row"><label class="col-sm-2 control-label" style="width: 24%">Description</label>'+
				    		
				    		'<input type="text" class="form-control"  style="width: 60%"id="description1" name="description1" value="'+asset.description+'"/></div>'+
				    		
				    		'<div class="row"><label class="col-sm-2 control-label" style="width: 24%" >Type</label>'+
				    		
				    		'<input type="text" class="form-control"  style="width: 60%" id="type1" name="type1" value="'+asset.type+'"/></div>'+
				    		
				    		
				    		'<div class="row"><label class="col-sm-2 control-label" style="width: 24%">Is Available</label>'+
				    		
				    		'<select class="form-control" ng-model="isAvailable" style="width: 20%" name="isAvailable1" id="isAvailable1">'+				    			
						   		'<option value="1">Yes</option>'+
						   		'<option value="0">No</option></select></div>'+										   							   	
						   	'<div class="row"><label class="col-sm-2 control-label" style="width: 24%">Manufacturer</label>'+				    		
				    		'<input type="text" class="form-control"  style="width: 60%" id="manufacturer1" name="manufacturer1" value="'+asset.manufacturer+'"/></div>'+
				    		
				    		
				    		'<div class="row"><label class="col-sm-2 control-label" style="width: 24%" >User</label>'+
				    		
				    		'<input type="text" class="form-control"  style="width: 60%" id="user1" name="user1" value="'+asset.user+'"/></div>'+
				    	
				    		
				    		'<div class="row"><label class="col-sm-2 control-label" style="width: 24%">Supplier</label>'+
				    		
				    		'<input type="text" class="form-control"  style="width: 60%" id="supplier1" name="supplier1" value="'+asset.supplier+'"/></div>'+
				    		
				    		
				    		'<div class="row"><label class="col-sm-2 control-label" style="width: 24%" >Model</label>'+
				    		
				    		'<input type="text" class="form-control"  style="width: 60%" id="model1" name="model1" value="'+asset.model+'"/></div>'+	
				    		''+
				    		
				    		'<div class="modal-footer"><button type="submit" class="btn btn-success" title="Save new asset">Update</button>'+
						      	
						        '<button type="button" class="btn btn-default" ng-click="closeThisDialog()" title="Go back to asset summary">Close</button></div></div></form>'
						    ,
			            plain: true,
			            scope:$scope			           
			        });				
			    };
			    
			   $scope.openAssetDeletePoup=function(asset){
				   ngDialog.open({            
			            template:'<div id="myModal">'+
						        '<div class="modal-header">'+
						             '<h4>Delete Asset</h4></div>'+
									'<div class="modal-body">'+
						            '<p>You are about to delete '+asset.name+'?</p></div>'+						          				
						        '<div class="modal-footer">'+
						          '<button type="button" class="btn btn-danger" ng-click="deleteAsset('+asset.id+');closeThisDialog()">Yes</button>'+
							        '<button type="button" class="btn btn-default" ng-click="closeThisDialog()">No</button></div></div>',
				            plain: true,
				            scope:$scope			           
				        });	
			   };
			    
			   $scope.makeNull=function(){				   	
				 $('#id').val("");
				 $('#name').val("");
				 $('#assetCategory').val("");
				 $('#assetCondition').val(""),
				 $('#code').val("");
				 $('#description').val("");
				 $('#type').val("");
				 $('#isAvailable').val("1");
				 $('#manufacturer').val("");
				 $('#user').val("");
				 $('#supplier').val("");
				 $('#model').val("");
				 $('#assetUseId').val("");
			   };
		});
	});
</script>
</head>
<body ng-app="assetSummaryApp" ng-controller="assetSummaryController">
	<div class="intro-header">
		<h2 style="text-align: center">Asset Management Summary</h2>
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
					    Asset update successfully.
					</div>
					<div class="alert alert-warning" id="alertFail"  style="width: 775px;padding-top: 5px;padding-bottom: 5px;display: none;">
					    <button type="button" class="close" data-dismiss="alert">x</button>
					    <strong>Warning!</strong>
					    Sorry unable to save asset.
					</div> 
					<div class="alert alert-danger" id="alertDelete"  style="width: 775px;padding-top: 5px;padding-bottom: 5px;display: none;">
					    <button type="button" class="close" data-dismiss="alert">x</button>
					    <strong>Delete!</strong>
					    Asset delete successfully.
					</div>
					<div class="alert alert-success" id="alertSave"  style="width: 775px;padding-top: 5px;padding-bottom: 5px;display: none;">
					    <button type="button" class="close" data-dismiss="alert">x</button>
					    <strong>Success!</strong>
					    Asset saved successfully.
					</div>
				</div>						       
		  	</div>
		
		
			<div class="table-responsive">				
				<table class="table table-bordered table-striped" id="assetTable">
					<thead>
						<tr>
							<th class="tbh" style="width: 7%;">Sl No</th>
							<th class="tbh">Name</th>
							<th class="tbh">Asset Id</th>
							<th class="tbh">Parent Id</th>
							<th class="tbh">Description</th>						
							<th class="tbh" style="width: 5%;" >
								<a data-toggle="tooltip" title="Add new asset" style="text-align: center;">
									<button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">
											<span class="glyphicon-plus">Add Asset</span>
									</button>
								</a>
							</th>																		
						</tr>		
					</thead>	
					<tbody id="assetList">		
						<tr ng-repeat="asset in assetUses |filter:search| orderBy:'name'" >
							<td>{{$index+1}}</td>						
							
							<td><a href="/fmsv1/editAssetManagementSummary?id={{asset.id}}">{{asset.name}}</a></td>
							<td>{{asset.assetCode}}</td>
							<td>{{asset.parentCode}}</td>
							<td>{{asset.description}}</td>
							<td style="text-align: center;">						
								<button class="btn btn-sm btn-danger" ng-click="openAssetDeletePoup(asset)" style="text-align: center;">
											<span class="glyphicon glyphicon-trash"></span>
							    </button>									    				   
						   </td>					   
						</tr>
					</tbody>
				</table>
			</div>
			<!--  For delete confirmation -->
			<div ng-show="ddd==true">
				<div id="myModalDelete" class="modal fade" style="display: none;">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-body" style="background-color: #587DC6">Are you sure?</div>
							<div class="modal-footer">
								 <button type="button" class="btn btn-danger" >Delete</button>
						        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>					       
						     </div>
						</div>
					</div>
				</div>	
			</div>	
		</div>
	</div>
	
	<!-- Asset model -->
	<div id="myModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title" style="text-align: center;">Add New Asset</h4>
	      </div>
	      <div class="modal-body tab-content">
	      
	        <form class="form-horizontal" ng-submit="addAsset(1)">
		        
		        <div class="row">
	        	<label class="col-sm-2 control-label" style="width: 24%">Name</label>
	    		<input type="text"  id="name"  class="form-control" style="width: 60%" required/>
	    		</div>
	    		<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%">Asset Category</label>	    			 
			   	<select class="form-control"  id="assetCategory" name="assetCategory" style="width: 60%" required>
			   		<option value="" >--Please select--</option>
			   		<option ng-repeat="category in assetCategoryList" value="{{category.id}}">{{category.categoryName}}</option>			   						
			   	</select>
			   	</div>
			   	
			   	<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%">Asset Condition</label>
	    		<select class="form-control"  id="assetCondition" name="assetCondition" style="width: 60%" ng-required="number">
			   		<option value="" >--Please select--</option>
			   		<option ng-repeat="condition in assetConditionList" value="{{condition.id}}">{{condition.conditionName}}</option>					
			   	</select>
			   	</div>
			   	
			   	
			   	
			   	
			   		<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%">Parent Asset</label>
	    		<select class="form-control"  id="parentAsset" name="parentAsset" style="width: 60%" ng-required="number">
			   		<option value="" >--Please select--</option>
			   		<option ng-repeat="asset in assetList" value="{{asset.id}}">{{asset.name}}</option>					
			   	</select>
			   	</div>
			   	
			   	
			   	<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%">Code</label>
	    		<input type="text" class="form-control"  style="width: 60%" id="code" name="code" required/>
	    		</div>
	    		
	    		<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%">Description</label>
	    		<input type="text" class="form-control"  style="width: 60%"id="description" name="description"/>
	    		</div>
	    		
	    		<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%">Type</label>
	    		<input type="text" class="form-control" style="width: 60%" id="type" name="type"/>
	    		</div>
	    		
	    		<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%">Is Available</label>
	    		<select class="form-control"  style="width: 20%" name="isAvailable" id="isAvailable" ng-required="number">	    			
			   		<option selected="selected" value="1" >Yes</option>
			   		<option value="0">No</option>				
			   	</select>
			   	</div>
			   	
			   	<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%">Manufacturer</label>
	    		<input type="text" class="form-control" style="width: 60%" id="manufacturer" name="manufacturer" required/>
	    		</div>
	    		
	    		<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%">User</label>
	    		<input type="text" class="form-control"  style="width: 60%" id="user" name="user"/>
	    		</div>
	    		
	    		<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%">Supplier</label>
	    		<input type="text" class="form-control" style="width: 60%" id="supplier" name="supplier" required/>
	    		</div>
	    		
	    		<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%" >Model</label>
	    		<input type="text" class="form-control" style="width: 60%" id="model" name="model" required/>	
	    		</div>
	    		<div class="modal-footer">
			      	<button type="submit" class="btn btn-success"  title="Save new asset">Save</button>
			        <button type="button" class="btn btn-default" data-dismiss="modal" data-toggle="tooltip" title="Go back to asset summary">Close</button>
			    </div>	    	        
	        </form>
	      </div>	      
	    </div>	
	 </div>
	</div>
</body>
</html>