<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>State Summary</title>
<script src="./assets/js/jquery-1.9.1.js"></script>

<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$('#cntrId').change(function() {
			var cntrId = $(this).val();
			if(cntrId!="")
				{
				window.location.href = "/fmsv1/StateList?cntrId=" + cntrId;
				}
			else
				{
				return false;
				}		
		});
	});
</script>

</head>

<body>
	<div class="intro-header">
		<h2 style="text-align: center">State Summary</h2>
		<br> <br>

		<div class="well" style="margin: 0 34% 0 34%;">

			<form class="form-horizontal"role="form" method="POST">
				<div class="form-group">
					<label class="col-sm-4 control-label">Country</label> <select
						class="form-control" id="cntrId" name="cntrId" onchange="submit()"
						style="width: auto;">
						<option value="" selected>Please Select Country</option>
						<c:forEach items="${CountrySummaryDetail}" var="country">
							<option value="${country.id}">${country.name}</option>
						</c:forEach>
					</select>
				</div>
				<br />
				<div class="table-responsive">
					<table class="table table-bordered table-striped ">
						<thead>
							<tr>
								<th class="tbh">SL No</th>
								<th class="tbh">State Name</th>
								<th class="tbh">Delete</th>
							</tr>
						</thead>
						<tr>
							<td colspan=3 style="text-align: center;">&nbsp;Please&nbsp;Choose&nbsp;a&nbsp;Country&nbsp;</td>
						</tr>
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
		$(document).ready(function() {
			//not required ...  use firefox's firebug instead;
			$('button.removeBtn').click(function(e) {
				//not required ...  use firefox's firebug instead;
				var $form = $(this).closest('form');
				var btn_val = $(this).val();
				e.preventDefault();
				$('#confirm').modal({
					backdrop : 'static',
					keyboard : true
				}).one('click', '#delete', function(e) {

					window.location.href = "/fmsv1/delState?id=" + btn_val;
				});
			});
		});
	</script>	
	<script type="text/javascript">
	function getCountry(sel) {
	    var value = sel.value;  
	}
	</script>

</body>
</html>