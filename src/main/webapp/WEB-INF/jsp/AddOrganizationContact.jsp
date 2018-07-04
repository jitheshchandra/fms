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
	<div class="well" style="margin : 0 25% 0  25%;width:auto;">
 	 <font color="red">${Org_Saved}</font>
	  <form class="form-horizontal" role="form" style="margin-top:5%; margin-left: 5%;"    action="/fmsv1/addNewOrgContact/"  method="POST">	  
	  	<input type="hidden" id="orgId" name="orgId" value='<c:out value="${org_id}"/>' />  	
		<label class="col-sm-4 control-label" >Name</label>
	    <input type="text" class="form-control" id="contactPerson" name="contactPerson" style="width: 30%;"  required/>	
		<br/>
		<label class="col-sm-4 control-label" >Designation</label>
	  	<input type="text" class="form-control" id="contactPersonDesignation" name="contactPersonDesignation" style="width: 30%;"   required/>
		<br/>
		<label class="col-sm-4 control-label">Email</label>
	    <input class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" id="contactMailId" name="contactMailId" type="email"  style="width: 30%;"  required/>
	    <br/>   
		<label class="col-sm-4 control-label" >Contact Number</label>
	    <input type="tel" class="form-control" placeholder="888-888-8888" title="XXX-XXX-XXXX" required pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" maxlength="12" id="contactMobileNo" name="contactMobileNo"  style="width: 30%;"/>
		<br/>
		<br/>
		<p id="b_viewedit" >
	        <input type="reset" class="btn btn-primary" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   	<input type="submit" class="btn btn-success" value="Save">
		
	    </p>
	    
		</form>
</div>
</div>
</body>
</html>

