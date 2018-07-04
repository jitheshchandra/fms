<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Family Summary</title>
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
	<div class="intro-header">
		<h2 style="text-align: center">Employee Family Details</h2>
		<br> <br>

		<div class="well" style="margin: 0 34% 0 34%;">

			<form class="form-horizontal" action="/fmsv1/addDesignation"
				role="form" method="POST">

				<label class="col-sm-5 control-label" >Employee Name</label>
					 <input type="text" class="form-control" id="employeeName" name="employeeName"  value='<c:out value="${employee.firstName}"/>' style="width: 40%;" required\>
					
					 <input type="hidden" id="employeeId" name="employeeId" value='<c:out value="${employee.id}"/>' /> 
				

				<br /> 
				<input type="button" class="btn btn-info" id="addRelation" value="Add Relation"/>
					
					<br /> <br />

				<div class="table-responsive">
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>No</th>
								<th>Name</th>
								<th>Relationship</th>
								<th></th>

							</tr>
						</thead>
						<tbody>
							<%
								int i = 0;
							%>

							<c:forEach items="${EmpRelDetail}" var="familyDetails">

								<tr>
									<td><%!int i;%> <%i++; %><%=i%></td>

									<td><a href="/fmsv1/editRelation?relId=${familyDetails.id}">${familyDetails.name}</a></td>
									
									<td><a href="/fmsv1/editRelation?relId=${familyDetails.id}">${familyDetails.relationShipType}</a></td>

									<td>
										<button value="${familyDetails.id}"
											class='removeBtn btn btn-danger btn-xs' value="delete">
											<span class="fa fa-times"></span>Delete
										</button>
									</td>


								</tr>
							</c:forEach>


						</tbody>
					</table>
				</div>
			</form>
		</div>
	</div>
	<div id="confirm" class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body" style="background-color: #587DC6">Are
					you sure?</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn btn-primary"
						id="delete">Delete</button>
					<button type="button" data-dismiss="modal" class="btn">Cancel</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('button.removeBtn')
									.click(
											function(e) {
												//not required ...  use firefox's firebug instead;
												var $form = $(this).closest(
														'form');

												var btn_val = $(this).val();
												e.preventDefault();
												$('#confirm')
														.modal(
																{
																	backdrop : 'static',
																	keyboard : true
																})
														.one(
																'click',
																'#delete',
																function(e) {
																	var empId = $('#employeeId').val();

																	window.location.href = "/fmsv1/deleteRelation?id="+ btn_val+"&eid="+empId;

																});
											});
					});
		
		
		$('#addRelation').click(function(e){
			//not required ...  use firefox's firebug instead;
			
				var empId = $('#employeeId').val();
				window.location.href = "/fmsv1/AddEmployeeRelationship?empId=" + empId;
				
			
				
		});
		
	</script>


</body>
</html>