<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>State Summary</title>
<script src="./assets/js/jquery-1.9.1.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
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
		<h2 style="text-align: center">State Summary</h2>
		<br> <br>
		<div class="well" style="margin: 0 34% 0 34%;">
			<form class="form-horizontal" action="/fmsv1/test" method="GET">
				<div class="table-responsive">
					<label class="col-sm-4 control-label">Country</label> 
					 <select  class="form-control" name="cntr" id ="cntr"  onchange="submit()" style="width: auto;" >
						<option value="" selected="selected">Please Select Country</option> 
						<c:forEach items="${CountrySummaryDetail}" var="country">
							<option value="${country.id}" ${param.cntr== country.id ? 'selected' : ''}>${country.name}</option>
						</c:forEach>
					</select> <br />
					   
					
					<a><button type="button" id = "addState" class="btn btn-primary">Add State</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					<br/>				
					<br/>
					<div class="table-responsive" style="height:300px;overflow:scroll;">				
					<table class="table table-bordered table-striped " >
						<thead>
							<tr>
								<th class="tbh">SL No</th>
								<th class="tbh">State Name</th>
								<th class="tbh">Delete</th>
							</tr>
						</thead>
						 <%
							int i = 0;
						 %> 
						<c:forEach items="${stateId}" var="sts">
							<tr>
								<td><%!int i;%> <%i++;%><%=i%></td>
 	                            <td><a href="/fmsv1/editState?id=${sts.id}">${sts.name}</a></td>
								<td>
									<button value="${sts.id}"
										class='removeBtn btn btn-danger btn-xs' value="delete" id="del">
										<span class="fa fa-times"></span>Delete
									</button>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				</div>
			     <br>
				 <a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
		$(document).ready(function() {
				$('button.removeBtn').click(function(e) {
				var $form = $(this).closest('form');
				var btn_val = $(this).val();
				e.preventDefault();
				$('#confirm').modal({
					backdrop : 'static',
					keyboard : true
				}).one('click', '#delete', function(e) {
					var countryId = $('#cntr :selected').val();
					window.location.href = "/fmsv1/delState?id=" + btn_val+"&countryId="+countryId;
				});
			});
		});
		$('#addState').click(function(e){
			var countryDropdown = $('#cntr :selected');
			if(countryDropdown.text() == 'Please Select Country' && countryDropdown.val() == '')
			{
				alert('Please Select the Country.');
				return false;
				e.preventDefault();
			}
			else
			{
				var selectedCountry = countryDropdown.val();
				window.location.href = "/fmsv1/AddState?id1=" + selectedCountry;			
			}			
		});
	</script>
	<script type="text/javascript">
	function submit() {
		window.location.href = "/fmsv1/StateList";
	    alert(this.value);
	}
	</script>

</body>
</html>