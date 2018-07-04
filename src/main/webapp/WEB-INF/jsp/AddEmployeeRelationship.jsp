<%@ page language="java" contentType="text/html; charset=ISO-8859-1"pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Employee Relation</title>
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
		<h2 style="text-align: center">Add Employee Relation</h2>
		<br> <br>

		<div class="well" style="margin: 0 34% 0 34%;">

			<form class="form-horizontal" action="/fmsv1/saveEmployeeRelation"
				role="form" method="POST">

				<label class="col-sm-4 control-label">Relation Name</label> 
				<input type="text" class="form-control" id="name" name="name" style="width: 55%;" required/>
				 <input type="hidden" class="form-control" id="employeeId" name="employeeId" value='<c:out value="${employee.id}"/>' /> 
					<br/> 
					
					<label class="col-sm-4 control-label">Relationship</label>
					  <select class="form-control" name="relationshipId" style="width: auto;">
					    <option value="NONE" selected="selected">Please Select Relationship</option>
					      <c:forEach items="${relationshipId}" var="relationships">
						     <option value="${relationships.id}" >${relationships.name}</option>
					    </c:forEach>	
				     </select> <br />
				<div style="margin-left: 20%;">
					<button type="reset" class="btn btn-primary ">Reset</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" class="btn btn-success">Save</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>