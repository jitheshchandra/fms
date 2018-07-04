<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Employee</title>

<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>



</head>
<body  >
	<div class="intro-header" >
		<h2 style="text-align: center">Relation</h2>
		<br> <br>
    <div class="well" style="margin: 0 25% 0 25%; width: auto;">
			
			<form class="form-horizontal" style="margin-top: 2%;"  action="/fmsv1/saveRelation" method="POST">

				<label for="name" class="col-sm-4 control-label2">Name  </label>

				<input type="text"  class="form-control" id="name" name="name" style="width: 40%;" required> 
				 <br/>  
				 
			    <label for="description"class="col-sm-4 control-label2">Description</label>	
			    <input type="text" class="form-control" id="description" name="description" style="width: 40%;"required> 
			    <br>
			    
			     
				<button type="reset" class="btn btn-primary">Reset</button>&nbsp;
				<button type="submit" class="btn btn-success">Save</button>

			</form>
		</div>

	</div>
	

</body>
</html>