<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FMS-ORGSummary</title>
 
 
 <style>
	  	label{
	  	color:black;
		font-family: "Lato","Helvetica Neue",Helvetica,Arial,sans-serif;
		text-align: right;
	  	
	  }
  </style>
   <script type="text/javascript">
 	function validateForm()
 	{
 		debugger;
 		//not required ...  use firefox's firebug instead;
 		var contactName = document.forms["addBranch"]["contactPerson"].value;
 		var contactEmail = document.forms["addBranch"]["contactMailId"].value;
 		var contactMobile = document.forms["addBranch"]["contactMobileNo"].value;
 		if(contactName==""||contactEmail==""||contactMobile=="")
		{
 			alert('Please fill in proper data under contact details tab \n \n **One Contact Required** ');
 			return false;
		}
 	}
 	
 	$(function(){
 		 var addBranchApp=angular.module('addBranchApp',[]);
 		addBranchApp.controller("addBranchController",function($scope,$http){
 			$scope.countries=[];
 			$scope.country="";
 			
 			$scope.states=[];
 			$scope.state="";
 			
 			$scope.organizations=[];
 			$scope.organization="";
 			
 			$http.post("getOrganizations").success(function(data){
 				$scope.organizations=data;
 			});
 			
 			$http.post("getCountry").success(function(data) {
 				$scope.countries = data;
 			});
 			
 			$scope.getState=function(){
 				debugger;
 				$http.post("getStates?cntrId="+$scope.country).success(function(data)
 						{
 					$scope.states=data;
 						});
 			};
 		 });
 	  });
 </script>
   
 
</head>
<body style="height:auto;margin-top:4%" ng-app="addBranchApp">
<div class="intro-header" ng-controller="addBranchController">
	<h2 style="text-align:center ">Branch</h2>
	<br>
	<br>
	
	<div class="well" style="margin :  0 25% 0  25%;width:auto">
    <ul class="nav nav-tabs" role="tablist">
    <li class="active"><a href="#Branch" role="tab" data-toggle="tab">Branch</a></li>
    <li><a href="#Contact" role="tab" data-toggle="tab">Contact Details</a></li>
    </ul>

<!-- Tab panes -->
<form  name="addBranch" class="form-horizontal" role="form" style="margin-top:5%;margin-left:3%;"action="/fmsv1/addNewBranch"  onsubmit="return validateForm();" method="POST">
<div class="tab-content">
  <div class="tab-pane active" id="Branch">	  
	    <label class="col-sm-4 control-label" >Branch Name</label>
	    <input type="text" class="form-control" id="branchName" name="branchName" style="width: 30%;"  required/>
		<br/>
		<label class="col-sm-4 control-label">Organization</label>
	   <input type="text" class="form-control" id="organizationName" name="organizationName" style="width: 30%;"    value="${SelectedOrganization.name}" readonly />
	  
		
		<br/>    
			 <label class="col-sm-4 control-label" >Address Line 1</label>
	        <input type="text" class="form-control" id="address1" name="address1"  style="width: 30%;" required/>
		    <br/>
		     <label class="col-sm-4 control-label" >Address Line 2</label>
	         <input type="text" class="form-control" id="address2" name="address2" style="width: 30%;" required/>
		     <br/>
			 <label class="col-sm-4 control-label" >Address Line 3</label>
	         <input type="text" class="form-control" id="address3" name="address3" style="width: 30%;"/>
		     <br/>
		     <label class="col-sm-4 control-label" >Country</label>
		     <select class="form-control" name="branchCountry" id="branchCountry" ng-model="country" ng-change="getState()" style="width: 30%;" required>
		     <option value="">Please select Country</option>
		     <option ng-repeat="cntr in countries" value="{{cntr.id}}">{{cntr.name}}</option>
		     </select>
		    <br/>
		    <label class="col-sm-4 control-label" >State</label>
	        <select class="form-control" name="branchState" id="branchState" style="width: 30%;" required>
	        <option value="">Please select State</option>
	        <option ng-repeat="state in states" value="{{state.id}}">{{state.name}}</option>
	        </select>
		    <br/>
		
		     <label class="col-sm-4 control-label" >PinCode</label>
	       <input type="text" pattern="[0-9]*"  maxlength="6"  title="pincode 6 Digits" class="form-control" id="pinCode" name="pinCode"  style="width: 30%;" required />
		    <br/>	
		    <font color="red">** Please fill in the Necessary Information under  contact details.</font>
		<br/>
		<br/>   
	</div>
    <div class="tab-pane table-responsive" id="Contact"> 
 		<label class="col-sm-4 control-label" >Name</label>
	    <input type="text" pattern="[a-zA-Z]*" class="form-control" id="contactPerson" name="contactPerson" style="width: 30%;"  required/>
		<br/>
		 <label class="col-sm-4 control-label" >Designation</label>
	  	 <input type="text" class="form-control" id="designation" name="designation" style="width: 30%;"   required/>
		<br/>
		 <label class="col-sm-4 control-label">Email</label>
	    <input class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"  id="contactMailId" name="contactMailId" type="email"  style="width: 30%;"  required/>
		    <br/>   
		   <label class="col-sm-4 control-label" >Contact Number</label>
	    <input type="tel" placeholder="888-888-8888" title="XXX-XXX-XXXX" required pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" maxlength="12" class="form-control" id="contactMobileNo" name="contactMobileNo" required style="width: 30%;"/>
	
		    <br/>
    </div>
  </div>
	 <p id="b_viewedit" >
	 <a href="/fmsv1/branchsummary"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" class="btn btn-primary" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
	 <input type="submit" class="btn btn-primary" value="Save">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							
	</p>
</form>
</div>
</div>
</body>
</html>

