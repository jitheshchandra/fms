<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="assets/css/sri.css">
</head>
<body>
  <div class="container">
	<div class="row">
	<div class="hero-unit">
	<div class="col-sm-6 col-md-4 col-md-offset-4">
			<h3 class="text-center">
				Access Control List
			</h3>
		
	<div class="row clearfix">
	<div class="col-md-12 column">
		<div class="well">
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
				<div class="form-group">
					<label for="user type" class="col-sm-5 control-label2">User Type</label>
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
					</form>
				</div>
                </div>
			<br/>
			<div class="row clearfix">
				<div class="col-md-12 column">
					<table class="table table-bordered" align="center" style="width: 30%;">
						<thead>
							<tr>
							<th class ="tbh">
									S.NO.
								</th>
								<th class ="tbh">
									Functionality
								</th>
								<th class ="tbh">
									

                                Accessable

								</th>
								
							</tr>
						</thead>
						<tbody>
							
							<tr >
								<td>
									1
								</td><td></td>
								<td>
									<input type="checkbox" id="inlineCheckbox1" value="option1">
								</td>
								
							</tr>
							<tr >
								<td>
									2
								</td><td></td>
								<td>
									<input type="checkbox" id="inlineCheckbox1" value="option1">
								</td>
								
							</tr>
							<tr >
								<td>
									3
								</td><td></td>
								<td>
									<input type="checkbox" id="inlineCheckbox1" value="option1">
								</td>
								
							</tr>
							<tr>
								<td>
									4
								</td><td></td>
								<td>
									<input type="checkbox" id="inlineCheckbox1" value="option1">
								</td>
								
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	
	<div class="row clearfix">
		<div class="col-md-12 column">
			 <button type="button" class="btn btn-primary" style="margin-left:60%; margin-bottom: 3%;">Save</button>
		</div>
	</div>
</div>
</div>
</div>
</div>
</body>
</html>