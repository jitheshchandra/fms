<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Designation Summary</title>
<script src="./assets/js/jquery-1.9.1.js"></script>
<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>
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
		<h2 style="text-align: center">Designation Summary</h2>
		<br> <br>
		<div class="well" style="margin: 0 25% 0 25%;padding-top:3%;">
			<form class="form-horizontal" action="/fmsv1/addDesignation" role="form" method="POST" onsubmit="return validate(this);">
				<label class="col-sm-4 control-label">Designation</label> 
				<input type="text" class="form-control" id="name" name="name" style="width: 30%;" required>
				<br />   
				<input type="submit" class="btn btn-info" value="Add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button  type="reset" value="Reset" class="btn btn-default">Cancel</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<br /> <br />
				<div class="table-responsive" style="height:300px;overflow:scroll;">
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>No</th>
								<th>Designation</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<%int i = 0;%>
							<c:forEach items="${designation}" var="designation">
								<tr>
								<td><%!int i;%> <%i++;%><%=i%></td>
									<td><a href="/fmsv1/editDesignation?id=${designation.id}">${designation.name}</a></td>
									<td>
										<button value="${designation.id}" class='removeBtn btn btn-danger btn-xs' value="delete"><span class="fa fa-times"></span>Delete</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				 <a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</form>
		</div>
	</div>
	<div id="confirm" class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body" style="background-color: #587DC6">Are you sure?</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn btn-primary" id="delete">Delete</button>
					<button type="button" data-dismiss="modal" class="btn">Cancel</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('button.removeBtn').click(function(e) {
				var $form = $(this).closest('form');
				var btn_val = $(this).val();
				e.preventDefault();
				$('#confirm').modal({
				backdrop : 'static',
				keyboard : true
				})
			   .one('click','#delete',function(e) {
					window.location.href = "/fmsv1/deleteDestination?id="+ btn_val;
							});
					});
			});
	</script>


</body>
</html>