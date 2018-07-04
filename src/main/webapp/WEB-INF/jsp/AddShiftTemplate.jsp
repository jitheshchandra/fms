<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shifts</title>


<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
	font-size: 16px;
}
</style>

</head>
<body>
	<div class="intro-header">
		<br> <br>
		<h1 style="text-align: center">Shifts Template</h1>


		<div class="well" style="margin: 4% 25% 0 25%">
			<form class="form-horizontal" action="/fmsv1/saveShiftTemplet" 
				style="margin-top: 6%;" method="POST">

				<div class="row clearfix">
					<div class="table-responsive">
						<table class="table table-bordered table-striped">
							<thead>
								<tr>
									<th class="tbh">Shift name</th>
									<th class="tbh">Start Time</th>
									<th class="tbh">End Time</th>
								</tr>
							</thead>

							<tbody>
								<tr>
									<td>
									
									<!--  <input type="text" class="form-control" id="name" name="name" style="width: 30%;" required> -->
				
									<select class="form-control" id="name" name="name">
											<option value="NONE">Please Select Shift</option>
											<option value="one">Morning</option>
											<option value="two">General</option>
											<option value="two">Night</option>
									</select> </td>
									 <td><input type="time" class="form-control" id="startTime" name="startTime"></td>
									<td><input type="time" class="form-control" id="endTime" name="endTime"></td>		
								</tr>
							</tbody>
							<!--  <tbody>
								<tr>
                                   	<td>  	
                                   	<select class="form-control">
                                   	        <option value="NONE">Please Select Shift</option>
											<option value="one">Morning</option>
											<option value="two">General</option>
											<option value="two">Night</option>
									</select>
									</td>
									<td><input type="time" class="form-control" id="time">
									</td>
									<td><input type="time" class="form-control" id="time">
									</td>

								</tr>
								<tr>

									<td><select class="form-control">
									<option value="NONE">Please Select Shift</option>
											<option value="one">Morning</option>
											<option value="two">General</option>
											<option value="two">Night</option>
									</select></td>
									<td><input type="time" class="form-control" id="time">
									</td>
									<td><input type="time" class="form-control" id="time">
									</td>

								</tr>
								<tr>

									<td><select class="form-control">
									<option value="NONE">Please Select Shift</option>
											<option value="one">Morning</option>
											<option value="two">General</option>
											<option value="two">Night</option>
									</select></td>
									<td><input type="time" class="form-control" id="time">
									</td>
									<td><input type="time" class="form-control" id="time">
									</td>

								</tr>
								<tr>

									<td><select class="form-control">
									<option value="NONE">Please Select Shift</option>
											<option value="one">Morning</option>
											<option value="two">General</option>
											<option value="two">Night</option>
									</select></td>
									<td><input type="time" class="form-control" id="time">
									</td>
									<td><input type="time" class="form-control" id="time">
									</td>

								</tr>
								<tr>

									<td><select class="form-control">
									<option value="NONE">Please Select Shift</option>
											<option value="one">Morning</option>
											<option value="two">General</option>
											<option value="two">Night</option>
									</select></td>
									<td><input type="time" class="form-control" id="time">
										</select></td>
									<td><input type="time" class="form-control" id="time">
									</td>


								</tr>
							</tbody> -->
						</table>
					</div>
				</div>

				<div style="margin-left: 20%;">
					<button type="reset" class="btn btn-primary ">Reset</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" class="btn btn-success" value="Add">
					<!--  <button class="btn btn-success" type="submit">Save</button>-->
					
				</div>


			</form>
		</div>
	</div>
</body>
</html>