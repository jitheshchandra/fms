<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Usertype Edit</title>
<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>
</head>
<body style="height: auto; margin-top: 4%">

<script type="text/javascript">
	//not required ...  use firefox's firebug instead;
	var ck_name = /^[A-Za-z]{3,50}$/;

	function validate(form) {
		var name = form.name.value;
		var errors = [];

		if (!ck_name.test(name)) {
			errors[errors.length] = "You valid Name .";
		}
		if (errors.length > 0) {
			reportErrors(errors);
			return false;
		}
		return true;
	}
	function reportErrors(errors) {
		var msg = "Please Enter Valide Data.....";
		alert(msg);
	}
</script>
	<div class="intro-header">
		<h2 style="text-align: center">Usertype Edit</h2>
		<div class="well" style="margin: 0 30% 0 30%; width: auto;">
			<br> <br>
			<form class="form-horizontal" style="margin-top: 5%; margin-left: 3%;" action="/fmsv1/UpdateUsertype/" method="POST" onsubmit="return validate(this);">
				<input type="hidden" id="id" name="id" value='<c:out value="${user_type.id}"/>' />
				 <label class="col-sm-4 control-label" ">Usertype</label>
				<input type="text" class="form-control" id="name" name="name" value='<c:out value="${user_type.name}"/>' style="width: 40%;" required /> <br /> 
				<input type="submit" class="btn btn-success" value="Save">
			</form>
		</div>
	</div>
</body>
</html>