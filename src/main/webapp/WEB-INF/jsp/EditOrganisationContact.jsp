<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FMS-ORG Contact</title>
 
<style>
	  	label{
	  	color:black;
		font-family: "Lato","Helvetica Neue",Helvetica,Arial,sans-serif;
		text-align: right;
	  	
	  }
  </style> 
 
</head>
<body style="height:auto;margin-top:4%">
<div class="intro-header">
	<h2 style="text-align:center ">Organization Contact</h2>
	<br>
	<br>	
	<div class="well" style="margin : 0 20% 0  20%;width:auto;">
<!-- Tab panes -->
 	 <font color="red">${Org_Saved}</font> 
	  <form class="form-horizontal" role="form" style="margin-top:5%; margin-left: 20%;"action="/fmsv1/UpdateOrgContact" method="Post">	  
	  	<input type="hidden" id="contactId" name="contactId" value='<c:out value="${EditOrgContact.id}"/>' />	  	
		<label class="col-sm-4 control-label" >Name</label>
	    <input type="text" class="form-control" id="contactPerson" name="contactPerson" style="width: 30%;"  value='<c:out value="${EditOrgContact.contactPerson}"/>' required/>		
		<br/>
		<label class="col-sm-4 control-label" >Designation</label>
	  	<input type="text" class="form-control" id="contactPersonDesignation" name="contactPersonDesignation" style="width: 30%;" value='<c:out value="${EditOrgContact.contactPersonDesignation}"/>'  required/>	
		<br/>
		<label class="col-sm-4 control-label">Email</label>
	    <input class="form-control" id="contactMailId" name="contactMailId" type="email"  style="width: 30%;" value='<c:out value="${EditOrgContact.contactMailId}"/>'   required/>	
		<br/>		   
		<label class="col-sm-4 control-label" >Contact Number</label>
	    <input type="text" class="form-control" id="contactMobileNo" name="contactMobileNo" value='<c:out value="${EditOrgContact.contactMobileNo}"/>' style="width: 30%;"/>	
		<br/>		
		<br/>
		<br/>
		  <p id="b_viewedit" style="width: 30%;margin-left:20%">
	        <input type="reset" class="btn btn-primary" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   	<input type="submit" class="btn btn-primary" value="Save">
		   </p>		
		</form>
</div>
</div>	
</body>
</html>

