<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Managed Entity Group Summary</title>


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
		<h2 style="text-align: center">Managed Entity Master</h2>
		<br> <br>

		<div class="well" style="margin: 0 25% 0 25%;">

			<form class="form-horizontal" style="margin-top: 3%;"
				action="/fmsv1/testEM" method="GET">
				<div class="form-group">
					<label for="Organization" class="col-sm-5 control-label2">Organization</label>
					<div class="col-sm-6">
						<select class="form-control" name="dd1" onchange="submit()">
							<c:forEach items="${dd1options}" var="option">
								<option value="${option.id}"
									${param.dd1 == option.id ? 'selected' : ''}>${option.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="Branch" class="col-sm-5 control-label2">Branch</label>
					<div class="col-sm-6">
						<select class="form-control" name="dd2" onchange="submit()">
							<c:if test="${empty dd2options}">
								<option>Please select parent</option>
							</c:if>
							<c:forEach items="${dd2options}" var="option">
								<option value="${option.id}"
									${param.dd2 == option.id ? 'selected' : ''}>${option.branchName}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="table-responsive">

					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th class="tbh">Name</th>
								<th class="tbh">Item</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${summaries}" var="entity">
									<tr>
										<td><a href="#">${entity.name}</a></td>
										<td><a href="#" class="btn btn-primary btn-sm"
											role="button">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp; <a href="#"
											class="btn btn-sm btn-danger"><span
												class="glyphicon glyphicon-trash"></span> </a></td>
									</tr>
							
							</c:forEach>


						</tbody>
					</table>
				</div>
				<a href="/fmsv1/ManagedEntityRegistry" class="btn btn-success"
					type="button">Create New</a>
			</form>
		</div>
	</div>
</body>
</html>