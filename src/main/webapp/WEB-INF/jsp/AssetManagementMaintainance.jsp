<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link rel="stylesheet" type="text/css" href="assets/css/sri.css">
</head>
<body>
	<div class="intro-header">
		<h2 style="text-align: center">Asset Management Maintainance</h2>
		<br> <br>

		<div class="well" style="margin: 0 9% 0 10%;">
			<div class="table-responsive">
				<table class="table table-bordered table-striped">
					<tr>
						<th class="tbh">S No</th>
						<th class="tbh">Asset ID</th>
						<th class="tbh">Area</th>
						<th class="tbh">Category</th>
						<th class="tbh">Group</th>
						<th class="tbh">Condition</th>
						<th class="tbh">Criticality</th>
						<th class="tbh">Last Check Date</th>
						<th class="tbh"></th>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</div>
			<!--  For delete confirmation -->
			<div id="confirm" class="modal fade" style="display: none;">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-body" style="background-color: #587DC6">
							Are you sure?</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal"
								class="btn btn-primary" id="delete">Delete</button>
							<button type="button" data-dismiss="modal" class="btn">Cancel</button>
						</div>
					</div>
				</div>
			</div>		
		</div>
	</div>
</body>
</html>