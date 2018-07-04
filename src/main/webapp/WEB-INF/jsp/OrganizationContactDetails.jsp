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
	 var editOrganization=angular.module('editOrganization',[]);
	 editOrganization.controller("editOrgController",function($scope,$http){
		$scope.countries=[];
		$scope.country="";
		
		$scope.states=[];
		$scope.state="";
		
		$http.post("getCountry").success(function(data) {
			$scope.countries = data;
		});
		
		$scope.getState=function(){
			$http.post("getStates?cntrId="+$scope.country).success(function(data)
					{
				$scope.states=data;
					});
		};
		
		var countryId=${Org.countryId.id};
		$http.post("getStates?cntrId="+countryId).success(function(data)
				{
			$scope.states=data;
				});
	
	 });
  });

 </script>
</head>
<body style="height:auto;margin-top:4%" ng-app="editOrganization">
<div class="intro-header" ng-controller="editOrgController">
	<h2 style="text-align:center ">Organizations</h2>
	<br>
	<br>
	
	<div class="well" style="margin : 0 25% 0  25%;width:auto;">
      <ul class="nav nav-tabs" role="tablist">
         <li class="active"><a href="#Organizations" role="tab" data-toggle="tab">Organization</a></li>
         <li><a href="#Contact" role="tab" data-toggle="tab">Contact Details</a></li>
     </ul>

<!-- Tab panes -->
<form class="form-horizontal" role="form" style="margin-top:5%;margin-left: 5%;"action="/fmsv1/updateOrg" method="POST">
<div class="tab-content">
  <div class="tab-pane active" id="Organizations">
 	 <font color="red">${Org_Saved}</font>
	  	<input type="hidden" id="id" name="id" value='<c:out value="${Org.id}"/>' />
		 <label class="col-sm-4 control-label" >Name</label>
	    <input type="text" class="form-control" id="name" name="name" value='<c:out value="${Org.name}"/>'style="width: 30%;"  required/>	
		<br/>
		 <label class="col-sm-4 control-label" >Organization Type</label>
	   	<select class="form-control" id="organizationTypesId" name="organizationTypesId" style="width: 30%;">
	   		<option value="1" >Service Provider</option>
			<option value="2" selected="selected">Service Consumer</option>
	   	</select>	
		<br/>
		 <label class="col-sm-4 control-label">Registration Number</label>
	    <input type="text" pattern="[0-9]*" class="form-control" id="regNumber" name="regNumber" style="width: 30%;"/>
		    <br/>	   
		   <label class="col-sm-4 control-label" >Registration Date</label>
	    <input type="date" class="form-control" id="regdate" name="regdate" style="width: 30%;"/>
		    <br/>	    
			 <label class="col-sm-4 control-label" >Address Line 1</label>
	    <input type="text" class="form-control" id="address1" name="address1" value='<c:out value="${Org.address1}"/>' style="width: 30%;" required/>
		    <br/>	    
		    <label class="col-sm-4 control-label" >Address Line 2</label>
	    <input type="text" class="form-control" id="address2" name="address2" value='<c:out value="${Org.address2}"/>' style="width: 30%;" required/>	
		    <br/>		    	
			 <label class="col-sm-4 control-label" >Address Line 3</label>
	    <input type="text" class="form-control" id="address3" name="address3" value='<c:out value="${Org.address3}"/>'  style="width: 30%;"/>
		    <br/>
		   <label class="col-sm-4 control-label" >Country</label>
	        <select class="form-control" name="orgCountry" id="orgCountry" ng-model="country" ng-change="getState()" style="width: 30%;" required>
	          <option value="">Please select Country</option>
	          <option ng-repeat="cntr in countries|orderBy:cntr.name" ng-selected="${Org.countryId.id}=={{cntr.id}}" value="{{cntr.id}}">{{cntr.name}}</option>
	        </select>
		 <br/>
		    <label class="col-sm-4 control-label" >State</label>
		      <select class="form-control" name="orgState" id="orgState" style="width: 30%;">
		         <option value="">Please select State</option>
		         <option ng-repeat="sts in states|orderBy:sts.name" ng-selected="${Org.stateId.id}=={{sts.id}}" value="{{sts.id}}">{{sts.name}}</option>
		      </select>
		 <br/>		    
		     <label class="col-sm-4 control-label" >PinCode</label>
	    <input type="number" pattern="[0-9]*"  maxlength="6"  title="pincode 6 Digits" class="form-control" id="pinCode" name="pinCode" value='<c:out value="${Org.pinCode}"/>' style="width: 30%;" required/>	
		    <br/>		
		<br/>
		<br/>
	
	<p id="b_viewedit" >
	<input type="reset" class="btn btn-primary" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="submit" class="btn btn-success" value="Save">
	 
    </p>	
	</div>
  <div class="tab-pane table-responsive" id="Contact">
    <table class="table table-striped ">
		<tr>
			<td colspan="6" style="text-align:right;"><a href="/fmsv1/addOrgContact?Oid=${Org.id}"><button type="button" class="btn btn-default"><span class="glyphicon-plus">Add New Contact</span></button></a></td>
		</tr>
		<tr>
			<th>Name</th>
			<th>Designation</th>
			<th>Email</th>
			<th>Contact Number</th>
	    </tr>	
			<c:forEach items="${orgContact}" var="orgContact">
				<tr>
				<td><a href="/fmsv1/editOrgContact?id=${orgContact.id}">${orgContact.contactPerson}</a></td>
				<td>${orgContact.contactPersonDesignation }</td>
				<td>${orgContact.contactMailId}</td>
				<td>${orgContact.contactMobileNo}</td>			
				<td  style="width: auto;">
										<button value="${orgContact.id}"
											class='removeBtn btn btn-danger btn-xs' value="delete">
											<span class="fa fa-times"></span>Delete
										</button>
									</td>
				</tr>			
			</c:forEach>
	</table>
  </div> 
</div>
</form>
</div>	
</div>
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
																}).one('click','#delete',
																function(e)
																{
																	window.location.href = "/fmsv1/deleteOrganizationContact?orgContactId="+ btn_val+"&orgId=${Org.id}";
																});
											});
						});
	</script>
</body>
</html>

