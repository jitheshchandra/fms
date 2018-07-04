<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Managed Entity  Summary</title>

	<style>
	  	label{
	  	color:black;
		font-family: "Lato","Helvetica Neue",Helvetica,Arial,sans-serif;
		text-align: right;
	  	
	  }
  </style>

	<script type="text/javascript">
	$(function()
			{
		var manadesScheduleApp = angular.module('managedScheduleApp', []);
		manadesScheduleApp.controller('ManagedScheduleCtrl', function($scope, $http)
				{
			

			$scope.schedules = [];
			$scope.schedule = "";
		   
			
			
			$scope.getAllSchedules = function() {	
			var FromDay =	 new Date(this.ScheduleDateFrom).getDate();
			var FromMonth =  new Date(this.ScheduleDateFrom).getMonth()+1;
			var FromYear =  new Date(this.ScheduleDateFrom).getFullYear();
			
			var from = FromYear+"/"+FromMonth+"/"+FromDay;
			
			var ToDay =	 new Date(this.ScheduleDateTo).getDate();
			var ToMonth =  new Date(this.ScheduleDateTo).getMonth()+1;
			var ToYear =  new Date(this.ScheduleDateTo).getFullYear();
			
			var To = ToYear+"/"+ToMonth+"/"+ToDay;
					
				    var ScheduleDateTo=Date.parse(this.ScheduleDateTo);
				
				$http.post("getAllSchedulesByDate?FromDate="+from+"&ToDate="+To
						).success(
						function(data) {
							$scope.schedules = data;
						});
			};
				
			

									
		});
});

</script>
	
	<script type="text/javascript">
<!-- Add branch modified...... -->
function addNewMangdPage()
{
var selectdOrgn = $("#organizationId option:selected").val();
var selectedbran=$("#branchId option:selected").val();
if (selectdOrgn != undefined && selectdOrgn != null && selectdOrgn !== "Please select Organization" && selectedbran!=undefined && selectedbran!=null && selectedbran!=="Please select Branch") {
	$("#selectdOrgn").val(selectdOrgn);
	$("#selectedbran").val(selectedbran);
	window.location.href = "/fmsv1/addNewManaged?selectdOrgn=" +selectdOrgn+"&selectedbran="+selectedbran;
		
}
else
	{
	alert("Please Select Organization and Branch");
	}
}
</script>
</head>
<body ng-app="managedScheduleApp">
    <div class="intro-header">
	<h2 style="text-align:center ">Manage Schedules</h2>
	<br>
	<br>
	
	<div class="well" style="margin : 0 10% 0  10%;" width="auto;" ng-controller="ManagedScheduleCtrl">
		<form>	
			
				<div class="form-group">
					<label for="user type" class="col-sm-2 control-label2"> From Date</label>
					<div class="col-sm-3">
						<input type="date" class="form-control" id="ScheduleDateFrom"	ng-model="ScheduleDateFrom" name="ScheduleDateFrom"		style="width: auto;"  required/>
						
					</div>
				
					<label for="user type" class="col-sm-2 control-label2">To Date</label>
					<div class="col-sm-3">
						<input type="date" class="form-control" id="ScheduleDateTo"	ng-model="ScheduleDateTo" name="ScheduleDateTo"	style="width: auto;" required/>
						
					</div>
						<button type="submit" class="btn btn-success" ng-click="getAllSchedules() "  >Find</button>
				</div>
				<table class="table table-bordered table-condensed" align="center">
					<thead>
						<tr>
							<th class="tbh">Title</th>
							<th class="tbh">StartTime</th>
							<th class="tbh">EndTime</th>
							<th class="tbh">OrganizationId</th>
							<th class="tbh">BranchId</th>
							<th class="tbh">EmployeeCode</th>
							<th class="tbh">Employee Name</th>
							<th>&nbsp;&nbsp;&nbsp;</th>
						</tr>
						</thead>
						<tbody>
						
						<tr ng-repeat="schedule in schedules|orderBy:'Title'">
							<td><a href="/fmsv1/editSchedules?sid={{schedule.id}}">{{schedule.title}}</a></td>
							<td>{{schedule.startTime}}</td>
							<td>{{schedule.endTime}}</td>
							<td>{{schedule.organization}}</td>
							<td>{{schedule.branch}}</td>
							<td> {{schedule.employeeCode}}</td>
							<td> {{schedule.employeeName}}</td>
							<td><button value="{{schedule.id}}" class='removeBtn btn btn-danger btn-xs' value="delete">
				<span class="fa fa-times"></span>Delete</button>
				</td>
							
						</tr>
						</tbody>
						</table>
			

	</form>
	</div>
	<!--  For delete confirmation -->
	
	
<div id="confirm" class="modal fade" style="display:none;">
<div class="modal-dialog">
    <div class="modal-content">
  <div class="modal-body" style="background-color:#587DC6 ">
    Are you sure?
  </div>
  <div class="modal-footer">
    <button type="button" data-dismiss="modal" class="btn btn-primary" id="delete">Delete</button>
    <button type="button" data-dismiss="modal" class="btn">Cancel</button>
  </div>
  </div>
  </div>
</div>
  <script type="text/javascript">

  $(document).on("click", "button.removeBtn", function(){ // This is the changed line
	  $('button.removeBtn').click(function(e){
		  //not required ...  use firefox's firebug instead;
		   var $form = $(this).closest('form');
		    var btn_val =$(this).val(); 
		    e.preventDefault();
		    $('#confirm').modal({ backdrop: 'static', keyboard: true })
		        .one('click', '#delete', function (e) {
		    	
		        	//window.location.href = "/fmsv1/delSchedule?id="+btn_val;
		        	window.location.href = "/fmsv1/delSchedule?id="+btn_val;
	        });
	  });
  });
  </script>
</div>
</body>
</html>