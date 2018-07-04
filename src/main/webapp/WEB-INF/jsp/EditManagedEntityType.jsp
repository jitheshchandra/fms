<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FMS</title>


<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>

</head>
<body style="height: auto; margin-top: 4%">
	<div class="intro-header">
		<h2 style="text-align: center">Edit ManagedEntityType</h2>
		<br> <br>
		<div class="well" style="margin: 0 30% 0 30%; width: auto;">
			<form class="form-horizontal" role="form" style="margin-top: 5%; margin-left: 3%;" action="/fmsv1/updateManagedEntityType/" method="POST" onsubmit="return validate(this);">
				<div class="tab-content">
					<div class="tab-pane active" id="Designation">
						<input type="hidden" id="id" name="id" value='<c:out value="${ManagedEntityType.id}"/>' /> 
						<label class="col-sm-4 control-label" >Managed Entity Type</label>
						<input type="text" class="form-control" pattern="/^[A-Za-z]{3,50}$/" id="name" name="name" value='<c:out value="${ManagedEntityType.name}"/>' style="width: 30%;" required /><br /> 
							<input type="submit" class="btn btn-success" value="Save">
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>