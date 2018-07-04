<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
  $(function(){
	 var editBranchApp=angular.module("editBranchApp",[]);
	 editBranchApp.controller("editBranchController",function($scope,$http){
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
			$http.post("getStates?cntrId="+$scope.country).success(function(data)
					{
				$scope.states=data;
					});
		};
		
		var countryId=${branch.country.id};
		$http.post("getStates?cntrId="+countryId).success(function(data)
				{
			$scope.states=data;
				});
	 });
  });
  </script>
 
</head>
<body style="height:auto;margin-top:4%" ng-app="editBranchApp">
<div class="intro-header" ng-controller="editBranchController">
	<h2 style="text-align:center ">Branch</h2>
	<br>
	<br>
	<div class="well" style="margin : 0 25% 0  25%;width:auto;">
     <ul class="nav nav-tabs" role="tablist">
       <li class="active"><a href="#Branch" role="tab" data-toggle="tab">Branch</a></li>
       <li><a href="#Contact" role="tab" data-toggle="tab">Contact Details</a></li>
     </ul>

<!-- Tab panes -->
<form class="form-horizontal" role="form" style="margin-top:5%;margin-left: 3%;"action="/fmsv1/updateBranch/" method="POST">
<div class="tab-content">
  <div class="tab-pane active" id="Branch">
 		  
 		<input type="hidden" id="id" name="id" value='<c:out value="${branch_id}"/>' />  	  
	    <label class="col-sm-4 control-label" >Branch Name</label>
	    <input type="text" class="form-control" id="branchName" name="branchName" value='<c:out value="${branch.branchName}"/>'style="width: 30%;"  required/>
		<br/>
		<label class="col-sm-4 control-label">Organization</label>
		   
		<select class="form-control" id="organizationId" name="organizationId" ng-model="organization" style="width: 30%;" required>
		<option value="">Please select Organization</option>
		<option ng-repeat="org in organizations | orderBy:'orgName'" ng-selected="${branch.organization.id }=={{org.orgId}}" value="{{org.orgId}}">{{org.orgName}}</option>
		</select>   
		<br/>    
	    <label class="col-sm-4 control-label" >Address Line 1</label>
	    <input type="text" class="form-control" id="address1" name="address1" value='<c:out value="${branch.address1}"/>' style="width: 30%;" required/>
		<br/>   
		<label class="col-sm-4 control-label" >Address Line 2</label>
	    <input type="text" class="form-control" id="address2" name="address2" value='<c:out value="${branch.address2}"/>' style="width: 30%;" required/>
		<br/>    	
		<label class="col-sm-4 control-label" >Address Line 3</label>
	    <input type="text" class="form-control" id="address3" name="address3" value='<c:out value="${branch.address3}"/>'  style="width: 30%;"/>
		<br/>
		<label class="col-sm-4 control-label" >Country</label>
		<select class="form-control" style="width: 30%;" name="branchCountry" id="branchCountry" ng-model="country" ng-change="getState()">
		<option value="">Please select Country</option>
		<option ng-repeat="cntr in countries|orderBy:'name'" ng-selected="${branch.country.id }=={{cntr.id}}" value="{{cntr.id}}">{{cntr.name}}</option>
		</select>    
	    <br>
		<label class="col-sm-4 control-label" >State</label>
		<select class="form-control" style="width: 30%;" name="branchState" id="branchState" ng-model="state">
		<option value="">Please select State</option>
		<option ng-repeat="state in states|orderBy:'name'" ng-selected="${branch.stateId.id }=={{state.id}}" value="{{state.id}}">{{state.name}}</option>
		</select>
		<br/>
		<label class="col-sm-4 control-label" >PinCode</label>
	    <input type="number" class="form-control" id="pinCode" name="pinCode" value='<c:out value="${branch.pinCode}"/>' style="width: 30%;" required/>
		<br/>	
		<p id="b_viewedit" >
	      <input type="reset" class="btn btn-primary" value="Reset">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	      <input type="submit" class="btn btn-success" value="Save">
	    </p>
	</div>
  <div class="tab-pane table-responsive" id="Contact">
    <table class="table table-striped ">
		<tr>
			<td colspan="6" style="text-align:right;"><a href="/fmsv1/addBranchContact?bid=${branch_id}"><button type="button" class="btn btn-info"><span class="glyphicon-plus">Add New Contact</span></button></a></td>
		</tr>
		<tr>
			<th>Name</th>
			<th>Designation</th>
			<th>Email</th>
			<th>Contact Number</th>
	    </tr>	
			<c:forEach items="${branchContacts}" var="branchContacts">
				<tr>
				<td><a href="/fmsv1/editBranchContact?bcid=${branchContacts.contactId}">${branchContacts.contactPerson}</a></td>
				<td>${branchContacts.contactPersonDesignation}</td>
				<td>${branchContacts.contactMailId}</td>
				<td>${branchContacts.contactMobileNo}</td>	
				<td  style="width: auto;">
				 <button value="${branchContacts.contactId}"class='removeBtn btn btn-danger btn-xs' value="delete"><span class="fa fa-times"></span>Delete</button>
			    </td>
				</tr>			
			</c:forEach>
	</table>
  </div>
</div>  
</form>
</div>
</div>
</body>
<div id="confirm" class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body" style="background-color:#70B8FF" >
				Are you sure?
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn btn-primary"
						id="delete">Delete</button>
					<button type="button" data-dismiss="modal" class="btn">Cancel</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
							$('button.removeBtn').click(
											function(e) {
												var $form = $(this).closest('form');
												var btn_val = $(this).val();
												e.preventDefault();
												$('#confirm').modal(
																{
																	backdrop : 'static',
																	keyboard : true
																})
														.one('click','#delete',
																function(e)
																{
																	window.location.href = "/fmsv1/deleteBranchContact?branchContactId="+ btn_val+"&branchId=${branch_id}";
																});
											});
						});
	</script>
</html>

