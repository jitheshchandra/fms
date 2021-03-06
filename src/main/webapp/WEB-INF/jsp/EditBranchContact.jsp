<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FMS-Branch Contact</title>
 
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
	<h2 style="text-align:center ">Branch Contact</h2>
	<br>
	<br>
	<div class="well" style="margin: 0px 350px; width: auto; border-left-width: 1px; padding-left: 19px;">
	  <form class="form-horizontal" role="form" style="margin-top:5%; margin-left: 20%;"    action="/fmsv1/updateBranchContact/"  method="POST">
	       <input type="hidden" id="contactId" name="contactId" value='<c:out value="${branchContact.id}"/>' />
	       <input type="hidden" id="branchId" name="branchId" value='<c:out value="${branch_id}"/>'>
		   <label class="col-sm-4 control-label" >Name</label>
	       <input type="text" class="form-control" id="contactPerson" name="contactPerson" style="width: 30%;"  value='<c:out value="${branchContact.contactPerson}"/>' required/>
		   <br/>
		   <label class="col-sm-4 control-label" >Designation</label>
	  	   <input type="text" class="form-control" id="contactPersonDesignation" name="contactPersonDesignation" style="width: 30%;"  value='<c:out value="${branchContact.contactPersonDesignation}"/>' required/>
		   <br/>
		   <label class="col-sm-4 control-label">Email</label>
	       <input type="text" class="form-control" id="contactMailId" name="contactMailId" style="width: 30%;"  value='<c:out value="${branchContact.contactPersonMailId}"/>' required/>
		    <br/>
		   <label class="col-sm-4 control-label" >Contact Number</label>
	       <input type="text" class="form-control" id="contactMobileNo" name="contactMobileNo" style="width: 30%;"  value='<c:out value="${branchContact.contactPersonMobile}"/>' required/>
		    <br/>
		<br/>
		<br/>
		    <p id="b_viewedit" >
	         <input type="reset" class="btn btn-primary" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   	 <input type="submit" class="btn btn-primary" value="Save">
		    </p>
		</form>
</div>
</div>
</body>
</html>

