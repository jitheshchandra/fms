<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Country</title>

<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>
<script type="text/javascript">
	$('#name').blur(function() {
		//not required ...  use firefox's firebug instead;
		var enteredText = $.trim($(this).val());
		if (enteredText == '' || enteredText == undefined) {
			alert('Please Enter Shift Name');
		}
	});
</script>
</head>
<body style="height: auto; margin-top: 4%">

	<script type="text/javascript">
		var ck_name = /^[A-Za-z]{3,50}$/;
		function validate(form) {

			//not required ...  use firefox's firebug instead;
			var name = form.name.value;
			var startTime = form.startTime.value;
			var endTime = form.endTime.value;
			var errors = [];
			if (!ck_name.test(name)) {
				errors[errors.length] = "You valid Name .";
			}
			if (startTime == endTime) {
				errors[errors.length] = "";

			}
			if (errors.length > 0) {
				reportErrors(errors);
				return false;
			}
			return true;
		}
		function reportErrors(errors) {
			var msg = "Shift Name Should Be in Letters,Statrting and Ending Time Should Not Be Same";
			alert(msg);
		}
	</script>

	<div class="intro-header">
		<h2 style="text-align: center">Edit Shift</h2>
		<div class="well" style="margin: 0 35% 0 35%; width: auto;">
			<br> <br>
			<form class="form-horizontal"
				style="margin-top: 5%; margin-left: 3%;" action="/fmsv1/updateShift"
				method="POST" onsubmit="return validate(this);">
				
				<div class="form-group">
					<label for="Organization" class="col-sm-5 control-label2">Organization</label>
					<div class="col-sm-6">
						<input type="text" readonly="readonly" class="form-control" id="oId" name="oId" value='<c:out value="${shift.organizationId.name}"/>'/>
					</div>
				</div>
				
				<div class="form-group">
					<label for="Organization" class="col-sm-5 control-label2">Branch</label>
					<div class="col-sm-6">
					  <input type="text" readonly="readonly" class="form-control" id="bId" name="bId" value='<c:out value="${shift.branchId.branchName}"/>'/>
					</div>
				</div>
				<input type="hidden" id="id" name="id" value='<c:out value="${shift.id}"/>'/>
				<div class="row clearfix">
					<div class="table-responsive">
						<table class="table table-bordered table-striped">
							<thead>
								<tr>
									<th class="tbh">Shift name</th>
									<th class="tbh">Start Time</th>
									<th class="tbh">End Time</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input type="text" class="form-control" id="name"
										name="name" value='<c:out value="${shift.shiftName}"/>' /></td>
									<td><input type="time" class="form-control" id="startTime"
										name="startTime" value='<c:out value="${shift.startTime}"/>'></td>
									<td><input type="time" class="form-control" id="endTime"
										name="endTime" value='<c:out value="${shift.endTime}"/>'></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<br /> <input type="submit" class="btn btn-primary btn-lg"
					value="Save">

			</form>
		</div>
	</div>
</body>
</html>