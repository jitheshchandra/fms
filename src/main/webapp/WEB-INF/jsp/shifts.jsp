<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shifts</title>


  <style>
	  label{
	  	color:black;
		font-family: "Lato","Helvetica Neue",Helvetica,Arial,sans-serif;
		text-align: right;
	  	font-size: 16px;
	  }
  </style>

</head>
<body>
<div class="intro-header">
	<br>
	<br>
	<h1 style="text-align:center"> Shifts </h1>
	
	
   <div class="well" style="margin:4% 25% 0 25%">
			<form class="form-horizontal" style="margin-top:6%;">
				<div class="form-group">
					 <label for="Organization" class="col-sm-5 control-label2">Organization</label>
					 <div class="col-sm-6">
                           <select class="form-control" >
							<option value="one">One</option>
							<option value="two">Two</option>
							<option value="three">Three</option>
							<option value="four">Four</option>
							<option value="five">Five</option>
						</select>
						</div>
				</div>
				<div class="form-group">
					 <label for="branch " class="col-sm-5 control-label2">Branch</label>
					  <div class="col-sm-6">
                           <select class="form-control">
							<option value="one">One</option>
							<option value="two">Two</option>
							<option value="three">Three</option>
							<option value="four">Four</option>
							<option value="five">Five</option>
						</select>
						</div>
				</div>
				
				
					<div class="row clearfix">
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th class = "tbh">
							Shift name
						</th>
						<th class = "tbh">
							Start Time
						</th>
						<th class = "tbh">
							End Time
						</th>
						
						
					</tr>
				</thead>
				<tbody>
					<tr>
						
						<td>
					   <select class="form-control">
						<option value="one">Morning</option>
						<option value="two">General</option>
						<option value="two">Night</option>
						</select>
						</td>
						<td>
						<input type="time" class="form-control" id="time">
						</td>
						<td>
						<input type="time" class="form-control" id="time">
						</td>
						
					</tr>
					<tr >
						
						<td>
						<select class="form-control">
						<option value="one">Morning</option>
						<option value="two">General</option>
						<option value="two">Night</option>
						</select>
						</td>
						<td>
						<input type="time" class="form-control" id="time">
						</td>
						<td>
						<input type="time" class="form-control" id="time">
						</td>
						
					</tr>
					<tr >
						
						<td>
						<select class="form-control">
						<option value="one">Morning</option>
						<option value="two">General</option>
						<option value="two">Night</option>
						</select>
						</td>
						<td>
						<input type="time" class="form-control" id="time">
						</td>
						<td>
						<input type="time" class="form-control" id="time">
						</td>
						
					</tr>
					<tr >
						
						<td>
						<select class="form-control">
						<option value="one">Morning</option>
						<option value="two">General</option>
						<option value="two">Night</option>
						</select>
						</td>
						<td>
						<input type="time" class="form-control" id="time">
						</td>
						<td>
						<input type="time" class="form-control" id="time">
						</td>
						
					</tr>
					<tr >
						
						<td>
						<select class="form-control">
						<option value="one">Morning</option>
						<option value="two">General</option>
						<option value="two">Night</option>
						</select>
						</td>
						<td>
						<input type="time" class="form-control" id="time">
						</select>
						</td>
						<td>
						<input type="time" class="form-control" id="time">
						</td>
						
						
					</tr>
				</tbody>
			</table>
		</div>
	</div>

            <div style="margin-left: 20%;">
       
		     <button type="button" class="btn btn-primary " >Add</button>&nbsp;&nbsp;&nbsp;&nbsp;
		     <button type="button" class="btn btn-primary ">Reset</button>&nbsp;&nbsp;&nbsp;&nbsp;
			 <button type="button" class="btn btn-success">Save</button>
		  </div>

				
					</form>
				</div>
      </div>
</body>
</html>