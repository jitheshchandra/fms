<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Designation Summary</title>
<script src="./assets/js/jquery-1.9.1.js"></script>
<style>"src/main/webapp/WEB-INF/assets/js/angular.min.js"
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
		<h2 style="text-align: center">AssetManagement Category</h2>
		<br> <br>
		<div class="well" style="margin: 0 25% 0 25%;">
			<form class="form-horizontal" action="/fmsv1/saveAssetManagementCategory" role="form" method="POST" 
			onsubmit="return validate(this);">
			
				<label class="col-sm-5 control-label" style="color: black;font-size: large;">Name</label>
				 <input type="text" class="form-control" id="categoryName" name="categoryName" style="width: 30%;" required>
				<br />   
				<label class="col-sm-5 control-label" style="color: black;font-size: large;">Description</label>
				 <input type="text" class="form-control" id="description" name="description" style="width: 30%;" required>
				<br />
				<input type="submit" class="btn btn-info" value="Add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button  type="reset" value="Reset" class="btn btn-default">Cancel</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<br /> <br />
				<div class="table-responsive" style="height:300px;overflow:scroll;">
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>No</th>
								<th>Name</th>
								 <th>Description</th>
							</tr>
						</thead>
						<tbody>
							<%int i = 0;%>
							<c:forEach items="${assetManagementCategory}" var="assetManagementCategory">
								<tr>
								<td><%!int i;%> <%i++;%><%=i%></td>
									<td><a href="/fmsv1/editAssetManagementCategory?id=${assetManagementCategory.id}">${assetManagementCategory.categoryName}</a></td>
									<td>${assetManagementCategory.description}</td>									
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				 <a href="/fmsv1 /Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</form>
		</div>
	</div>
	
</body>
</html>