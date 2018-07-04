<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Incident Category</title>
<link rel="stylesheet" type="text/css" href="assets/css/sri.css">
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
		<div class=""></div>
		<h2 style="text-align: center">Incident Category</h2>
		<br> <br>

		<div class="well" style="margin: 0 25% 0 25%;">
			<form:form class="form-horizontal" style="margin-top:3%;"action="/fmsv1/saveIncidentCategoryEntity" method="GET"modelAttribute="employeeAttandenceForm">
				<div class="form-group">
					<label for="name" class="col-sm-4 control-label2">Name</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="name" required>
					</div>
				</div>
				
				<div class="form-group">
					<label for="branch " class="col-sm-4 control-label2">Parent Category</label>
					<div class="col-sm-6">
						<select class="form-control" name="parentCategoryId">
							<option value="">Default</option>
							<c:forEach items="${categories}" var="category">
								<option value="${category.id}">${category.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="submit" class="btn btn-success">Save</button>
			</form:form>
		</div>
	</div>
	<br />
	<br />
</body>
</html>