<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Schedule Add</title>
<link rel="stylesheet" type="text/css" href="assets/css/sri.css">
</head>
<body>
   <div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<h3 class="text-center" style="margin: 3%;">
				Schedule Add
			</h3>
		</div>
	</div>
	<div class="row clearfix" >
		<div class="col-md-14 column" >
			<form class="form-horizontal"  >
				<div class="form-group">
					 <label for="selection" class="col-sm-2 control-label2">Managed Entity Group</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputEmail3" style="width: 20%;">
					</div>
				</div>
				<div class="form-group">
					 <label for="selection" class="col-sm-2 control-label2">Supervisor</label>
					<div class="col-sm-10">
						 <select class="form-control" style="width: 20%;">
							<option value="one">One</option>
							<option value="two">Two</option>
							<option value="three">Three</option>
							<option value="four">Four</option>
							<option value="five">Five</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					 <label for="selection" class="col-sm-2 control-label2">Check List</label>
					<div class="col-sm-10">
						 <select class="form-control" style="width: 20%;">
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
	</div><br/><br/>
<table border=2 align="center" style="width: 80%;">
		<tbody >
			<tr>
   			<td>
	<div class="row clearfix">
		<div class="col-md-4 column">
			<form class="form-horizontal">
				<div class="form-group">
					 <label for="time" class="col-sm-2 control-label2"><small>Start</small></label>
					<div class="col-sm-10">
						<input type="time" class="form-control" id="time" style="width: 60%;">
					</div>
				</div>
			</form>
		</div>
		<div class="col-md-4 column">
			<form class="form-horizontal">
				<div class="form-group">
					 <label for="time" class="col-sm-2 control-label2"><small>End</small></label>
					<div class="col-sm-10">
						<input type="time" class="form-control" id="time" style="width: 60%;">
					</div>
				</div>
				
			</form>
		</div>
		<div class="col-md-4 column">
			<form class="form-horizontal">
				<div class="form-group">
					 <label for="duration" class="col-sm-2 control-label2"><small>Duration</small></label>
					<div class="col-sm-10">
						<input type="duration" class="form-control" id="duration" style="width: 45%; margin-left: 20%;">
					</div>
				</div>
				
			</form>
		</div>
		
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="row clearfix">
				<div class="col-md-4 column">
					<table class="table table-bordered" >
						<tbody >
							<tr>
								<td>
									<h5>Recurrence Pattern</h5>
									<input type="radio" name="my-radio" checked >
									<label for="daily "><small>Daily</small></label><br/>
									<input type="radio" name="my-radio" checked >
									<label for="weekly " ><small>Weekly</small></label><br/>
									<input type="radio" name="my-radio" checked >
									<label for="monthly " ><small>Monthly</small></label><br/>
									<input type="radio" name="my-radio" checked >
									<label for="yearly " ><small>Yearly</small></label><br/>
								</td>
								</tr>
						</tbody>
					</table>
				</div>
				<div class="col-md-8 column">
					<table class="table table-bordered">
						<thead>
							<tr>
								<td>
								<h5>Recurr Every Week (s) on</h5><br/>
								    <input type="checkbox" name="my-checkbox" checked>
									<label for="day" ><small>Sunday</small></label> &nbsp;&nbsp;&nbsp;
                                    <input type="checkbox" name="my-checkbox" checked>
									<label for="day " ><small>Monday</small></label> &nbsp;&nbsp;&nbsp;
                                     <input type="checkbox" name="my-checkbox" checked>
									<label for="day " ><small>Tuesday</small></label>&nbsp;&nbsp;&nbsp;
                                     <input type="checkbox" name="my-checkbox" checked>
									<label for="day " ><small>Wednesday</small></label><br/><br/>
                                    <input type="checkbox" name="my-checkbox" checked>
									<label for="day " ><small>Thursday</small></label>&nbsp;&nbsp;&nbsp;
                                    <input type="checkbox" name="my-checkbox" checked>
									<label for="day " ><small>Friday</small></label> &nbsp;&nbsp;&nbsp;
                                    <input type="checkbox" name="my-checkbox" checked>
									<label for="day " ><small>Satuarday</small></label>							
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			</div>
			</div>
			<div class="col-md-12 column">
			<table class="table table-bordered">
				<thead>
					<tr>
						<td>
						<h5>Range of Recurrence</h5><br/>
						<label for="date" >Start</label>&nbsp;&nbsp;&nbsp;&nbsp;
					    <input type="date"  id="date" style="width: 20%;">
						<input type="radio" name="my-radio" checked style="margin-left: 8%;">
									<label for="branch " ><small>No End Date</small></label><br/>
									<input type="radio" name="my-radio" checked style="margin-left: 35%;">
									<label for="branch " ><small>End Day After</small></label><br/>
									<input type="radio" name="my-radio" checked style="margin-left: 35%;">
									<label for="branch " ><small>End by</small></label><br/>
						</td>
					</tr>
				</tbody>
			</table>
			</div>
		
	
	<div class="row clearfix">
		<div class="col-md-12 column" style ="margin-left: 60%;">
			 <button type="button" class="btn btn-primary" >Cancel</button > 
			 <button type="button" class="btn btn-primary">OK</button>
		</div>
	</div>
 
  </td>
	</tr>
	</tbody>
 </table>
	</div>
</body>
</html>