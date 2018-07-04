<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shifts</title>


<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
	font-size: 16px;
}
</style>
<script src="./assets/js/jquery-1.9.1.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
	
</script>


<script>
	$(document)
			.ready(
					function() 
					{
						debugger;
						$("#anc_add")
								.click(
										function()
										{
											debugger;
											$(this).attr('disabled', true);
											var orgName = $('#organizationId')
													.val();
											var branchName = $('#branchId')
													.val();

											if (orgName != ''
													&& branchName != '') 
											{
												$('#shifttable tr')
														.last()
														.after(
																'<tr><td><input type="text" class="form-control" id="name" name="name"></td><td><input type="time" class="form-control" id="startTime" name="startTime"></td><td><input type="time" class="form-control" id="endTime" name="endTime"></td></tr>');

											} else 
											{
												alert('Sorry Add Organization & Branch');
											}

										});
					});
	</script>
</head>
<body>

	<script type="text/javascript">
		var ck_name = /^[A-Za-z]{3,50}$/;
		function validate(form) {
			debugger;
			//$(this).form('disabled', false);
			var organizationId = form.organizationId.value;

			if (!organizationId == 0 || null || "")

			{
				var branchId = form.branchId.value;
				if (!branchId == 0 || null || "") {
					var name = form.name.value;

					if (!name == 0 || null || "") {
						var startTime = form.startTime.value;
						var endTime = form.endTime.value;
						var errors = [];
						//var noOfHours=console.log(moment(moment.duration(startTime.diff(endTime))).format("hh:mm:ss"));
						if (!ck_name.test(name)) {
							errors[errors.length] = "You valid Name .";
						}

						if (startTime == endTime) {
							errors[errors.length] = "";

						}
						if (organizationId == "" || branchId == ""
								|| organizationId == 0 || branchId == 0
								|| organizationId == null || branchId == null) {
							errors[errors.length] = "";
						}
						if (errors.length > 0) {
							reportErrors(errors);
							return false;
						}
						return true;

					}
					alert("***** Please Add The Shift *****");
					return false;
				}
				alert("***** Please Select The Branch or Enter The Field *****");
				$('#save').attr('disabled', true);
				return false;
			}

			alert("***** Please Select Organization ******");
			$('#save').attr('disabled', true);
			return false;
		}
		//return true;
		function reportErrors(errors) {
			var msg = "Shift Name Should Be in Letters,Statrting and Ending Time Should Not Be Same";
			alert(msg);
		}
	</script>

	<div class="intro-header">
		<br> <br>
		<h1 style="text-align: center">Shifts</h1>
		<div class="well" style="margin: 0% 25% 0 25%">
			<input type="hidden" id="availableShifts" name="availableShifts"
				value='<c:out value="${shifts}"/>' />
			<form class="form-horizontal" 
				style="margin-top: 6%;" method="GET" onsubmit="return validate(this);" action="/fmsv1/addShifts">
				<div class="form-group">
					<label for="Organization" class="col-sm-4 control-label2">Organization</label>
					<div class="col-sm-6">
						<select class="form-control" name="organizationId" id="organizationId">
							<option value="">Please Select Organization</option>
							<c:forEach items="${OrganizationName}" var="organization">
								<option value="${organization.id}"
									${param.organizationId == organization.id ? 'selected' : ''}>${organization.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="Branch" class="col-sm-4 control-label2">Branch</label>
					<div class="col-sm-6">
						<select class="form-control" name="branchId" id="branchId"
							style="width: auto;">
							<c:if test="${empty branchId}">
								<option value="">Please select Branch</option>
							</c:if>
							<c:forEach items="${branch}" var="branch">
								<option value="${branch.id}"
									${param.branchId == branch.id ? 'selected' : ''} >${branch.branchName}</option>
							</c:forEach>
						</select>
					</div>
				</div>


				<div class="row clearfix">
					<div class="table-responsive">
						<table class="table table-bordered table-striped" id="shifttable">
							<thead>
								<tr>
									<th class="tbh">Shift name</th>
									<th class="tbh">Start Time</th>
									<th class="tbh">End Time</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach items="${shifts}" var="shifts">
									<tr>

										<td id="shiftName"><a
											href="/fmsv1/editShift?id=${shifts.id}">${shifts.shiftName}</a></td>

										<td><a href="/fmsv1/editShift?id=${shifts.id}">${shifts.startTime}</a></td>

										<td><a href="/fmsv1/editShift?id=${shifts.id}">${shifts.endTime}</a></td>

									</tr>

								</c:forEach>

							</tbody>
						</table>
					</div>
				</div>
				<div >
				   <a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

					<button type="button" class="btn btn-info" id='anc_add'>+ Add Shift</button>&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="reset" class="btn btn-primary ">Reset</button>&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" class="btn btn-success" id="save">Save</button>
				</div>

			</form>
		</div>
	</div>
	<script type="text/javascript">
		$(".form-control")
				.change(
						function()
						{

							debugger;
							var orId = $('#organizationId').val();
							var brId = $('#branchId').val();
							if (brId == "" || orId=="") 
							{

								window.location.href = "/fmsv1/selectBranch?organizationId="
										+ orId + "&branchId=" + brId;
							} 
							else
							{

								window.location.href = "/fmsv1/selectBranch?organizationId="
										+ orId + "&branchId=" + brId;
							}
						});
	</script>

</body>
</html>