<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FMS</title>
<script src="./assets/js/jquery-1.9.1.js"></script>

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
		<h2 style="text-align: center">Edit State</h2>
		<br> <br>
		<div class="well" style="margin: 0 35% 0 35%; width: auto;">
			<form class="form-horizontal" action="/fmsv1/updateState" method="POST" onsubmit="return validate(this);">	
		      <input type="hidden" id="id" name="id" value='<c:out value="${state.id}"/>' /> 
			  <input type="hidden" id="countryId" name="countryId" value='<c:out value="${state.countryId}"/>' />
			  <input type="hidden" id="MyID" name="MyID"/>
    	 	 <input type="hidden" id="cId" name="cId" value='<c:out value="${state.countryId.id}"/>' />			
      					<label class="col-sm-2 control-label" style="width: auto;">State</label>
						<input type="text" class="form-control" id="name" name="name" value='<c:out value="${state.name}"/>' style="width: auto;" required />
						<br /> 	
					    <input type="submit" class="btn btn-primary btn-lg" value="Save"/>
			</form>
		</div>
	</div>
 	<script type="text/javascript">
	 	$('#save').click(function(e) {
			//not required ...  use firefox's firebug instead;
			var hiddeCntrId = $('#countryId').val();
			var pattern = /[0-9]+/g;
			var countryID = hiddeCntrId.match(pattern);
			countryID = parseInt(countryID);
			$('#MyID').val(countryID);
			window.location.href = "/fmsv1/updateState?id=" + countryID;
		});
	</script>
</body>
</html>