<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mterial Master</title>

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
		<h2 style="text-align: center">Material Master</h2>
		<br> <br>
		<div class="well" style="margin: 0 25% 0 25%;">
			<form class="form-horizontal" style="margin-top:5%;" action="/fmsv1/AddMaterial" method="POST">
				<div class="form-group">
					<label for="item" class="col-sm-4 control-label2">Item Code</label>
					<div class="col-sm-6">
						<input type="text" pattern="[0-9]*"  class="form-control" id="itemCode" name="itemCode" required>
					</div>
				</div>
				<div class="form-group">
					<label for="item" class="col-sm-4 control-label2">Item Name</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="name" name="name" required>
					</div>
				</div>
				<div class="form-group">
					<label for="Branch" class="col-sm-4 control-label2" required>UOM</label>
					<div class="col-sm-6">
						<select class="form-control" name="uomId" id="uomId" style="width: auto;" required>
							<option value="">Please Select UOM</option>
							<c:forEach items="${uoms}" var="uoms">
								<option value="${uoms.id}">${uoms.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="Branch" class="col-sm-4 control-label2" required>Item Type</label>
					<div class="col-sm-6">
						<select class="form-control" name="typeId" id="typeId" style="width: auto;" required>
							<option value="">Please Select Item Type</option>
							<c:forEach items="${itemTypes}" var="itemTypes">
								<option value="${itemTypes.id}">${itemTypes.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>	
				<button type="submit" class="btn btn-success">Save</button>
			</form>
		</div>
	</div>
</body>
</html>