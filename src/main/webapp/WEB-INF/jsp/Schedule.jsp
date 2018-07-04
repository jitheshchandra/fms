<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<link href="/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<meta name="viewport" content="width=device-width" />
<link rel='stylesheet' href="../fmsv1/assets/css/bootstrap.min.css" />
<link rel='stylesheet' href="../fmsv1/assets/css/fullcalendar.css" />
<link rel='stylesheet' href="../fmsv1/assets/css/fullcalendar.min.css" />


<script src='../fmsv1/assets/js/jquery-1.11.2.min.js'></script>
<script src='../fmsv1/assets/js/jquery-ui.custom.min.js'></script>

<script src='../fmsv1/assets/js/bootstrap.js'></script>
<!-- <script src='../fmsv1/assets/js/bootstrap-modal.js'></script> --> 

<script src='../fmsv1/assets/js/moment.min.js'></script>
<script src='../fmsv1/assets/js/fullcalendar.js'></script>
<script src='../fmsv1/assets/js/fullcalendar.min.js'></script>
<script src='../fmsv1/assets/js/lang-all.js'></script>

<!-- Angular Included -->

<script src='../fmsv1/assets/js/angular.min.js'></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0rc1/angular-route.min.js"></script>
<style>

.fc-time{
   display : none;
}
</style>

<!-- date picker -->
	<script type="text/javascript">
	function selectBranch()
	{
		var orgId = $("#organizationId").val();
		var _url="getBranchByOrgId?orgId=" + orgId;
		
		var branches =[];
		
		
		$.ajax({
		    type: 'POST',
		    url: _url, 		 
		    success: function(responseData) {
	           
		    	branches=responseData;
		    	debugger;
		    			    	$('#branchId').children().slice(1).remove();
		    	 $.each(branches,function(i,obj)
		                 {
		                 // alert(obj.id+":"+obj.branchName);  remove this to see if branches are coming 
		                  var div_data="<option value="+obj.id+">"+obj.branchName+"</option>";
		            //     alert(div_data); // remove this to see the tag formation 
		                 $(div_data).appendTo('#branchId'); 
		                 });  
		    	
	        },
	        error : function(responseData) {
	            console.log("  in ajax, error: " + responseData.responseText); 
	        }
		});

		
	}
	</script>
	
	
	
	
	<script type="text/javascript">
	 $( document ).ready(function() {
	    
		var _url="getAllSchedules";
		
	
		var schedules =[];
			$.ajax({
	    			type: 'POST',
	   				 url: _url, 		 
	   				 
	    			success: function(responseData) {
           
	    				schedules=responseData;
	 				 $.each(schedules,function(i,obj)
						{
		 //debugger;
		 	//var d=$('#calendar').fullCalendar('getDate');
		 	//$('#calendar').fullCalendar('gotoDate',d.getFullYear(),d.getMonth(),d.getDate());
							$('#calendar').fullCalendar('renderEvent', {
							title :obj.Title,
						    time:new Date(obj.StartTime),
							start :new Date(obj.StartDate),
							end :new Date(obj.EndDate),
							allDay :($('#apptAllDay').val()== true),
						}, true);
				 	});  
				
       			 },
	    
        		error : function(responseData) {
            	  console.log("  in ajax, error: " + responseData.responseText); 
       		 }
		});
	  
	}); 
	function getmanagedEntities()
	{
		var orgId = $("#organizationId").val();
		var branchId=$("#branchId").val();
		var _url="getManagedEntitiesByOrganizationAndBranch?orgId="+orgId+"&branchId="+branchId;
		                                                                   
		var entities=[];
		
		$.ajax({
			type:'POST',
			url:_url,
			success:function(responseData){
				
				entities=responseData;
				$("#entityId").children().slice(1).remove();
				 $.each(entities,function(i,obj)
				{
					//alert("obj.id"+obj.Name);
					var div_data="<option value="+obj.id+">"+obj.name+"</option>";
					$(div_data).appendTo('#entityId');
				});
				 
			 },
			error : function(responseData) {
	            consoleDebug("  in ajax, error: " + responseData.responseText); 
	        }		 
		    
		});	
		
	}
	
function getEmployees()
	{
		var orgId = $("#organizationId").val();
		var branchId=$("#branchId").val();
		var _url="getEmployeesByOrganizationAndBranch?orgId="+orgId+"&branchId="+branchId;
		                                                                   
		var employees=[];
		
		$.ajax({
			type:'POST',
			url:_url,
			success:function(responseData){
				
				employees=responseData;
				$("#empCode").children().slice(1).remove();
				 $.each(employees,function(i,obj)
				{
					//alert("obj.id"+obj.Name);
					var div_data="<option value="+obj.id+">"+obj.empFirstName+"</option>";
					$(div_data).appendTo('#empCode');
				});
				 
			 },
			error : function(responseData) {
	            consoleDebug("  in ajax, error: " + responseData.responseText); 
	        }		 
		    
		});
	}
	
	</script>

</head>
<body >
	<div id='calendar' style="Width: 90%; padding-left: 10%"></div>
	<div  id="createEventModal" class="well modal fade" tabindex="-1" style="Width: 40%; height: 600px; margin-left: 30%; margin-top: 20px;" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true"  >
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
			<h3 id="myModalLabel1">Create a new Schedule</h3>
		</div>
		<div class="modal-body">
			<form id="createAppointmentForm" class="form-horizontal">
				<div class="form-group">
					<label for="title " class="col-sm-5 control-label2">Title</label>
					<div class="col-sm-6">
						<input type="text" name="scheduleTitle" id="scheduleTitle"	class="form-control" data-provide="typeahead"> 
						<input 	type="hidden" id="apptStartTime" /> <input type="hidden" id="apptEndTime" /> 
						<input type="hidden" id="apptAllDay" />
					</div>
				</div>

				
				<div class="form-group">
					<label for="branch " class="col-sm-5 control-label2">Start Time</label>
					<div class="col-sm-6">
						<input type="time" class="form-control" id="startTime" name="startTime">			
					</div>					
				</div>
 				<div class="form-group">
					<label for="branch " class="col-sm-5 control-label2">End Time</label>
					<div class="col-sm-6">
						<input type="time" class="form-control" id="endTime" name="endTime">		
					</div>					
				</div>
								
				<div class="form-group">
					<label for="branch " class="col-sm-5 control-label2">Organization	:</label>
					<div class="col-sm-6">
						<select class="form-control" onchange="selectBranch()" id="organizationId"	name="organizationId">
							<option value="">Please select Organization</option>
									<c:forEach items="${organizations}" var="org">				
									<option value="${org.id}">${org.name}</option>				
								</c:forEach>																			
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="branch " class="col-sm-5 control-label2">Branch:</label>
					<div class="col-sm-6">
						<select class="form-control"  onchange="getEmployees()" id="branchId"	name="branchId">
							<option value="">Please select Branch</option>
							<c:forEach items="${branches}" var="branch">				
									<option value="${branch.Id}">${branch.name}</option>				
								</c:forEach>
																							
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="branch " class="col-sm-5 control-label2">Employee:</label>
					<div class="col-sm-6">
						<select class="form-control"  onchange="getmanagedEntities()" id="empCode"	name="empCode">
							<option value="">Please select Employee</option>
							<c:forEach items="${employees}" var="emp">				
									<option value="${emp.code}">${emp.FirstName}</option>				
								</c:forEach>
																							
						</select>
					</div>
				</div>

				
				  <div class="form-group">
					<label for="branch " class="col-sm-5 control-label2">Managed
						Entity Group:</label>
					<div class="col-sm-6">

						<!-- <input type="text" name="patientName" id="patientName"
							class="form-control" style="margin: 0 auto;"
							data-provide="typeahead" data-items="4"> <input
							type="hidden" id="apptStartTime" /> <input type="hidden"
							id="apptEndTime" /> <input type="hidden" id="apptAllDay" /> -->
							
							
						<select class="form-control"   id="entityId"	name="entityId">
							<option value="">Please select Entity</option>
																											
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="branch " class="col-sm-5 control-label2">Checklist
						Type:</label>
					<div class="col-sm-6">
						<input type="text" name="patientName" id="patientName"
							style="margin: 0 auto;" data-provide="typeahead" data-items="4"
							class="form-control"> <input type="hidden"
							id="apptStartTime" /> <input type="hidden" id="apptEndTime" />
						<input type="hidden" id="apptAllDay" />
					</div>
				</div>
				<div class="form-group">
					<label for="branch " class="col-sm-5 control-label2">Add
						Recurrence Rule</label>
					<div>
						<input type="checkbox" name="isRecurrencePresent"
							id="isRecurrencePresent" style="margin: 0 auto;"
							data-provide="typeahead" data-items="4" />

					</div>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
			<button type="submit" class="btn btn-primary" id="submitButton">Save</button>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(
				function() {
					 debugger;
					$('#calendar').fullCalendar(
							{
								header : {
									left : 'prev,next today',
									center : 'title',
									right : 'month,agendaWeek,agendaDay'
								},
								defaultView : 'month',
								editable : true,
								allDaySlot : false,
								selectable : true,
								select : function(start, end,allDay) {
									// endtime = $.fullCalendar.formatRange(end,'h:mm tt');
									//starttime = $.fullCalendar.formatRange(start, 'ddd, MMM d, h:mm tt');
									var j_start = $.fullCalendar.moment(start,
										"yyyy-MM-dd HH:mm");
									var J_end = $.fullCalendar.moment(end,
											"yyyy-MM-dd HH:mm");
								//	var j_allDay=!start.hasTime() && !end.hasTime();
									/*alert("allday"+j_allDay);
									alert("start"+start.hasTime());
									alert("end"+end.hasTime());*/
									
									
									
																
								//	var mywhen = starttime + ' - ' + endtime;
									
									$('#createEventModal #apptStartTime').val(
											j_start);
									$('#createEventModal #apptEndTime').val(
											J_end);
								
									$('#createEventModal #apptAllDay').val(
										true);

							
									
									//must parse the date and fetch time and then save into schedule instance as Start Time and End Time .
								//	$('#createEventModal #when').text(mywhen); 
									$('#createEventModal').modal('show');
								}
							});
					
					
					
					$('#submitButton').on('click', function(e) {
						debugger;
						// We don't want this to act as a link so cancel the link action
						e.preventDefault();
						doSubmit();
					});

					//assuming that the organization and branches are coming , moving ahead with org: softserveglobal (1324134) and branch (5): bel road 
					function doSubmit() {
						$("#createEventModal").modal('hide');
						debugger;
						var scheduleData = {
							'Title' : $('#scheduleTitle').val(),
							'StartDate' : new Date($('#apptStartTime').val()),
							'Enddate' : new Date($('#apptEndTime').val()),
							'StartTime':$("#startTime").val(),
							'EndTime':$("#endTime").val(),
							'ManagedEntityGroupName' : 'sample',
							'Supervisor' : 'jithesh',
							'Organization' :$("#organizationId").val(),
							'Branch' : $("#branchId").val(),
							'EmpCode':$("#empCode").val(),
							//'FirstName':$("#FirstName").val(),
							
							
						};
						
						/*var time=moment(scheduleData.StartDate).format('HH:mm:ss');
						if(time=="05:30:00")
							scheduleData.StartDate=moment(scheduleData.StartDate).set('HH:mm:ss',$("#startTime").val());*/
						

						//App.ClearPopupFormValues(); write a function to clear the popup values .							
						$.ajax({
							type : 'POST',
							contentType : 'application/json; charset=utf-8',
							dataType : 'json',
							data : JSON.stringify(scheduleData),
							url : "/fmsv1/SaveSchedules",
						success: function (response) {
								console.log("saved successfully");
							}
						});
						console.log(scheduleData);
							
						/*var time={'times':new Date($('#apptStartTime').val())};
						alert(time.times.getTime());*/
						
					/*	var d=scheduleData.StartDate.getDate();
						var m=scheduleData.StartDate.getMonth();
						var y=scheduleData.StartDate.getFullYear();
						var h=scheduleData.StartDate.getHours();
						var mi=scheduleData.StartDate.getMinutes();
						var s=scheduleData.StartDate.getMilliseconds();*/
						
						
						$("#calendar").fullCalendar('renderEvent',{
						
							title : $("#scheduleTitle").val(),
							starttime:$("#startTime").val(),
							start :new Date($('#apptStartTime').val()), 
							end : new Date($('#apptEndTime').val()),
							allDay : ($('#apptAllDay').val()==false),
						}, true);
					}
				});
	</script>
</body>
</html>