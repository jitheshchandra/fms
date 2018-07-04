<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	
	  <style>
	  label{
	  	color:black;
		font-family: "Lato","Helvetica Neue",Helvetica,Arial,sans-serif;
		text-align: right;
	  	
	  }
  </style>

</head>
<body style="height: auto; margin-top: 5%">
<div class="intro-header">
	<h2 style="text-align:center ">Edit ManagedEntitySubType</h2>
	<br>
	<br>
	
		<div class="well" style="margin:0% 25% 0 25%; ">
			<form name="addEmployee" class="form-horizontal" role="form" style="margin-top:5%;margin-left:5%;"action="/fmsv1/updateManagedEntitySubType/"   method="POST">
					<input type="hidden" name="id" id="id" value='<c:out value="${subtypes.id}"/>' >
					 <label for="name" class="col-sm-4 control-label2">Managed Entity Sub Type Name	</label>
					<input type="text" class="form-control" id="name" name="name" style="width: 40%;" value='<c:out value="${subtypes.name}"/>' >
				
					<br>
					 <label for="name" class="col-sm-4 control-label2">Managed Entity Type</label>
							<select class="form-control" id="managedEntityTypeId" name="managedEntityTypeId" style="width: 40%;">
							<c:forEach items="${mtupe}" var="mtupe">
									<option value="${mtupe.id}">${mtupe.name}</option>
							</c:forEach>
							</select>
					<br>	
				<br>					
							  <button type="reset" class="btn btn-primary">Reset</button>&nbsp;
							  <button type="submit" class="btn btn-success">Save</button>					
			</form>
		</div>

</div>

</body>
</html>