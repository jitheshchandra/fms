<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"pageEncoding="ISO-8859-1"%>
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
	var AssetManagementApp = angular.module('AssetManagementApp', []);
	AssetManagementApp.controller('administartionController',function($scope, $http ) {
		
		$scope.administrationList=[];		
		$http.post("getAssetAdministration").success(function(data){
			debugger;
			$scope.administrationList=data;			
		}).error(function(){
			$scope.confermeFail();
		});
		
		$scope.updateAdministration=function(){
			debugger;
			var data={
					admst1:$scope.administrationList
			}
			
			alert(data);
		}
		
		$scope.show=function(asset1){
			$scope.asset=asset1;
			document.getElementById("dDate").value = asset1.disposalDate;
			document.getElementById("sDate").value = asset1.amortaizationStart;
			document.getElementById("eDate").value = asset1.amortaizationEnd;
			$('#myModal').modal('show');
		}
		
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
		
		$scope.updateAsset=function(){
			debugger;
			var data=$scope.asset;
			$('#myModal').modal('hide');
			$http.post('updateAssetAdministration',data).success(function(data){				
				$scope.administrationList=data;
				$scope.confermeSave();
			}).error(function(){
				$scope.confermeFail();
			});
		};
	});
});
</script>
</head>
<body ng-app="AssetManagementApp" ng-controller="administartionController">
	<div class="intro-header">
		<h2 style="text-align: center">Asset Management Administration</h2>
		<br> <br>
		<div class="well" style="margin: 0 5% 0 5%;">
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
					    Asset administration update successfully.
					</div>
					<div class="alert alert-warning" id="alertFail"  style="width: 775px;padding-top: 5px;padding-bottom: 5px;display: none;">
					    <button type="button" class="close" data-dismiss="alert">x</button>
					    <strong>Warning!</strong>
					    Sorry unable to get asset administration.
					</div> 
					<div class="alert alert-warning" id="alertDelete"  style="width: 775px;padding-top: 5px;padding-bottom: 5px;display: none;">
					    <button type="button" class="close" data-dismiss="alert">x</button>
					    <strong>Delete!</strong>
					   Sorry unable to update asset administration.
					</div>
					<div class="alert alert-success" id="alertSave"  style="width: 775px;padding-top: 5px;padding-bottom: 5px;display: none;">
					    <button type="button" class="close" data-dismiss="alert">x</button>
					    <strong>Success!</strong>
					    Asset saved successfully.
					</div>
				</div>						       
		  	</div>
			<div class="table-responsive">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th class="tbh" style="width: 5%;">S No</th>
							<th class="tbh">Asset Name</th>
							<th class="tbh">Asset Code</th>
							<th class="tbh">Purchase Price</th>		
							<th class="tbh">Purchase Request</th>
							<th class="tbh">Invoice No</th>
							<th class="tbh">Current Value</th>		
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="admst in administrationList | orderBy:'assetId' |filter:search">
							<td>{{$index+1}}</td>
							<td><a href="" ng-click="show(admst)">{{admst.assetId}}</a></td>	
							<td>{{admst.assetCode}}</td>						
							<td>{{admst.purchasePrice}}</td>
							<td>{{admst.purchaceRequest}}</td>
							<td>{{admst.invoiceNo}}</td>
							<td>{{admst.currentValue}}</td>																		
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="12" align="right">								
			        			<a href="/fmsv1/Home"><button type="button" class="btn btn-default"  title="Go to home page">Back</button></a>
							</td>						
						</tr>
					</tfoot>
				</table>
			</div>			
		</div>
	</div>
	
	<div id="myModal" class="modal fade" role="dialog">
	  <div class="modal-dialog" style="width: 758px;">	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title" style="text-align: center;">Asset Administration</h4>
	      </div>
	      <div class="modal-body tab-content">
	      
	        <form class="form-horizontal" ng-submit="updateAsset()">
	        	        		        
		        <div class="row">
	        	<label class="col-sm-2 control-label" style="width: 24%">Name</label>	        	
	    		<input type="text" ng-model="asset.assetId" class="form-control" style="width: 60%" readonly="readonly"/>
	    		</div>
	    		
	    		<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%">Purchase Price</label>
	    		<input type="text" ng-model="asset.purchasePrice" class="form-control" style="width: 60%" />
	    		</div>
			   	
			   	<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%">Purchase Request</label>
	    		<input type="text" ng-model="asset.purchaceRequest"  class="form-control" style="width: 60%"/>
	    		</div>
			   	
			   	<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%">Invoice No</label>
	    		<input type="text" ng-model="asset.invoiceNo"  class="form-control" style="width: 60%"/>
	    		</div>
	    		
	    		<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%">Current Value</label>
	    		<input type="text" ng-model="asset.currentValue"  class="form-control" style="width: 60%"/>
	    		</div>
	    		
	    		<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%">Disposal Date</label>
	    		<input type="date" style="width: 25%;" ng-model="asset.disposalDate" id="dDate" class="form-control"/>
	    		</div>
			   	
			   	<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%">Year of Life</label>
	    		<input type="text" ng-model="asset.yearOfLife"  class="form-control" style="width: 25%"/>
	    		</div>
	    		
	    		<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%">Amortaization Start</label>
	    		<input type="date" style="width: 25%;" ng-model="asset.amortaizationStart" id="sDate" class="form-control"/>
	    		</div>
	    		
	    		<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%">Amortaization End</label>
	    		<input type="date" style="width: 25%;" ng-model="asset.amortaizationEnd" id="eDate" class="form-control"/>
	    		</div>
	    		
	    		<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%" >Annual Amortaization</label>
	    		<input type="text" style="width: 60%" ng-model="asset.annualAmortaization"  class="form-control"/>	
	    		</div>
	    		
	    		<div class="row">
	    		<label class="col-sm-2 control-label" style="width: 24%" >Fully Depricated</label>
	    		<input type="text" ng-model="asset.fullyDepricated" class="form-control" style="width: 60%"/>		    		
	    		</div>
	    		<div class="modal-footer">			
	    			<button type="submit" class="btn btn-success" title="Update asset administration">Save</button>      	
			        <button type="button" class="btn btn-default" data-dismiss="modal" data-toggle="tooltip" title="Go back to asset summary">Close</button>
			    </div>	    	        
	        </form>
	      </div>	      
	    </div>	
	 </div>
	</div>
	
</body>
</html>