<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<style>
	  	label{
	  	color:black;
		font-family: "Lato","Helvetica Neue",Helvetica,Arial,sans-serif;
		text-align: right;
	  	
	  }
  </style>
</head>
<body>
	<div class="intro-header">
	<h2 style="text-align:center ">Managed Entity Registry</h2>
	<br>
	<br>
	
	<div class="well" style="margin : 0 25% 0  25%;">
 		
			<form class="form-horizontal" style="margin-top:3%;">
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
					 <label for="date" class="col-sm-5 control-label2" >Date </label>
					<div class="col-sm-6" >
						<input type="date" class="form-control" id="date">
					</div>
				</div>
				
				<div class="form-group">
					<label for="time" class="col-sm-5 control-label2">Time  </label>
					<div class="col-sm-6" >
						<input type="time" class="form-control" id="time">
					</div>
				</div>
				
			
				<div class="table-responsive">
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th class = "tbh">
									Functionality
								</th>
								<th class = "tbh">
									Sanitised
								</th>
								
							</tr>
						</thead>
						<tbody>
							
							<tr >
								<td>
									1
								</td>
								<td>
									<input type="checkbox" id="inlineCheckbox1" value="option1">
								</td>
								
							</tr>
							<tr >
								<td>
									2
								</td>
								<td>
									<input type="checkbox" id="inlineCheckbox1" value="option1">
								</td>
								
							</tr>
							<tr >
								<td>
									3
								</td>
								<td>
									<input type="checkbox" id="inlineCheckbox1" value="option1">
								</td>
								
							</tr>
							<tr>
								<td>
									4
								</td>
								<td>
									<input type="checkbox" id="inlineCheckbox1" value="option1">
								</td>
								
							</tr>
						</tbody>
					</table>
				</div>
			
			 <button type="button" class="btn btn-primary" style="margin-left:60%;">Save</button>
	
		</form>
	</div>
</div>
             
</body>
</html>