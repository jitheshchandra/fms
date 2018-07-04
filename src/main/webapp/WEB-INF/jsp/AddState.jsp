<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add State</title>
<script src="./assets/js/jquery-1.9.1.js"></script>
<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>
</head>
</head>
<body>

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
		<h2 style="text-align: center">Add State</h2>
		<br> <br>
		<div class="well" style="margin: 0 34% 0 34%;">
			<form class="form-horizontal" action="/fmsv1/saveState" method="POST" onsubmit="return validate(this);">
				<div class="form-group">
					<label class="col-sm-4 control-label">Country</label> 
					<select	class="form-control" id="cntrId" name="cntrId" onchange="getCountry(this)" style="width: auto;">
						<c:forEach items="${cnt}" var="country">
							<option value="${country.id}">${country.name}</option>
						</c:forEach>
				    </select> 
					<br /> <label class="col-sm-4 control-label">State</label> <input
						type="text" class="form-control" id="name" name="name"
						style="width: 30%;" />
				</div>
				 <a	href="/fmsv1/StateList"><button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="submit" class="btn btn-success">Add State</button>
				<br /> <br />
			</form>
		</div>
	</div>


	<script type="text/javascript">
		function getCountry(sel) {
			//not required ...  use firefox's firebug instead;
			alert(sel);		
		}
		//not required ...  use firefox's firebug instead;
		var selectedCountry = ${countee.id};
		$('#cntrId').val(selectedCountry);
	</script>
</body>
</html>