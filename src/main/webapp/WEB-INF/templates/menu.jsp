<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Landing Page - Start Bootstrap Theme</title>
<script src="./assets/js/jquery-1.9.1.js"></script>

<!-- Bootstrap Core CSS -->
<link href="./assets/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="./assets/css/landing-page.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="./assets/font-awesome-4.1.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">



</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation" >
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">FMS</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">

					
		     <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Master <b class="caret"></b></a>
						
						<ul class="dropdown-menu" role="menu">
							<li><a href="/fmsv1/orgSummary">Organization</a></li>
							<li><a href="/fmsv1/branchsummary">Branches</a></li>
							<li><a href="/fmsv1/UserSummary">Users</a></li>
							<li><a href="/fmsv1/AttendanceNotification">AttendanceNotification</a></li>
							<li><a href="/fmsv1/ShiftSummary">Shifts</a></li>

							

							<!-- Others DropDown -->

							<li><a class="trigger right-caret" href="#">Other</a>
								<ul class="dropdown-menu sub-menu">
								
									<li><a href="/fmsv1/DesignationSummary">Designation</a></li>
									<li><a href="/fmsv1/RelationshipSummary">Relationship</a></li>
									<li><a href="/fmsv1/UsertypeSummary">UserType</a></li>
									<li><a href="/fmsv1/ItemTypeSummary">Item Type</a></li>
								</ul></li>
						</ul></li>
           
           
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Employee <b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li><a href="/fmsv1/EmployeeSummary">Employee Summary</a></li>       
            <li><a href="/fmsv1/EmployeeAttendance">Employee Attendance</a></li>
            <li><a href="/fmsv1/EmployeeJobAllocation">Employee JobAllocation</a></li>
            <li><a href="/fmsv1/EmployeeJobMovement">Employee JobMovement</a></li>
            <li><a href="/fmsv1/EmployeeGrooming">Employee Grooming</a></li>
            <li><a href="/fmsv1/EmployeeOverTime">Employee OverTime</a></li>
          </ul>
        </li>
        
     
       <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Material <b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li><a href="/fmsv1/MaterialMaster">Material Master</a></li>
            <li><a href="/fmsv1/MaterialList">Material List</a></li>
            <li><a href="/fmsv1/MaterialReceipt">Material Receipt</a></li>
            <li><a href="/fmsv1/MaterialConsumption">Material Consumption</a></li>
          </ul>
        </li>
        

        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Maintenance <span class="caret"></span></a>
          <ul class="dropdown-menu">
          <li><a href="/fmsv1/ManagedEntityType">Managed Type</a></li>
          <li><a href="/fmsv1/ManagedEntitySubType">Managed SubType</a></li>
          <!--  <li><a href="/fmsv1/ChecklistSummary">Default CheckList</a></li> -->
          <li><a href="/fmsv1/ManagedEntityGroupPage">Managed Entity Groups</a></li>   
            <li><a href="/fmsv1/ManagedEntityPage">Managed Entity</a></li>
           
                   
            <li><a href="/fmsv1/CheckLists">Check Lists</a></li>
            
          </ul>
        </li>
        
        
        <li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Incident Register <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="/fmsv1/IncidentCategoryEntity">Incident
									Category</a></li>
							<li><a href="/fmsv1/EscalationMatrixEntity">Escalation
									Matrix</a></li>
							<li><a href="/fmsv1/IncidentRegisterEntity">Incident
									Register</a></li>
							<li><a href="/fmsv1/IncidentRegisterEntity">Incident
									Type</a></li>											
							<li><a href="/fmsv1/IncidentRegisterUpdateEntity">Incident
									Register Update</a></li>
									<li><a href="/fmsv1/IncidentRegisterUpdateEntity">Incident
									Summary</a></li>
						</ul>
		</li>
		
		<li class="dropdown">
		<a href="#" class="dropdown-toggle" data-toggle="dropdown">Asset Management<b class="caret"></b></a>
			<ul class="dropdown-menu">
			
			    <li><a href="/fmsv1/RelationForm">Relation</a></li>
				<li><a href="/fmsv1/AssetManagementSummary">Asset Summary</a></li>
				<li><a href="/fmsv1/AssetManagementPosition">Asset Position</a></li>
				<li><a href="/fmsv1/AssetManagementCategory">Asset Category</a></li>
				<li><a href="/fmsv1/AssetAttributeType">Asset Attribute Type</a></li>
				<li><a href="/fmsv1/AssetManagementCategoryAttribute">Asset Category Attribute</a></li>				
				<li><a href="/fmsv1/AssetManagementCondition">Asset Condition</a></li>
				<li><a href="/fmsv1/AssetManagementWarranty">Asset Warranty</a></li>
				<li><a href="/fmsv1/AssetManagementRelation">Asset Relation</a></li>
				<li><a href="/fmsv1/AssetManagementInstallation">Asset Installation</a></li>
				<li><a href="/fmsv1/AssetManagementTechnicalDetails">Technical Details</a></li>
				<li><a href="/fmsv1/AssetManagementAdministration">Administration</a></li>
				<li><a href="/fmsv1/AssetManagementNotes">Notes</a></li>
				<li><a href="/fmsv1/AssetManagementMaintainance">Asset Maintainance</a></li>						
			</ul>
		</li>
        
     
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Reports<b class="caret"></b></a>
          <ul class="dropdown-menu">
			<li><a class="trigger right-caret" href="#">Employee Attendance</a>
								<ul class="dropdown-menu sub-menu">									
									<li><a href="/fmsv1/employeeAttendanceReport">Employee Attendance - Organization</a></li>
									<li><a href="/fmsv1/employeeAttendanceReport">Employee Attendance - Branch</a></li>			
								</ul></li>                  
            <li><a href="/fmsv1/employeeGroomingReport">Employee Grooming</a></li>
            <li><a href="/fmsv1/checklistReport">CheckList</a></li>
            <li><a href="/fmsv1/materialIndentReport">Material Indent</a></li>
            <li><a href="/fmsv1/siteSnapShotReport">Site Snapshot</a></li>            
             <li><a href="/fmsv1/snagRegisterReport">Snag Register</a></li>
            
          </ul>
        </li>
     
     
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Schedule <b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li><a href="/fmsv1/Schedule">Maintain Shedule</a></li>
             <li><a href="/fmsv1/ManageSchedules">Manage Schedules</a></li>
          </ul>
        </li>
        
     
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin <b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li><a href="/fmsv1/ChangePassword">Change Password</a></li>
            <li><a href="#">Access Control</a></li>
            <li><a href="/fmsv1/ChangeBranch">Change Branch</a></li>
            <li><a href="/fmsv1/login">Sign Out</a></li>
          </ul>
        </li>
					
					
					
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
    <script>
	$(function() {
		////not required ...  use firefox's firebug instead;
		$(".dropdown-menu > li > a.trigger").on(
				"click",
				function(e) {
					////not required ...  use firefox's firebug instead;
					var current = $(this).next();
					var grandparent = $(this).parent().parent();
					if ($(this).hasClass('left-caret')
							|| $(this).hasClass('right-caret'))
						$(this).toggleClass('right-caret left-caret');
					grandparent.find('.left-caret').not(this).toggleClass(
							'right-caret left-caret');
					grandparent.find(".sub-menu:visible").not(current).hide();
					current.toggle();
					e.stopPropagation();
				});
		$(".dropdown-menu > li > a:not(.trigger)").on("click", function() {
			var root = $(this).closest('.dropdown');
			root.find('.left-caret').toggleClass('right-caret left-caret');
			root.find('.sub-menu:visible').hide();
		});
	});
</script>
<style>
.dropdown-menu>li {
	position: relative;
	-webkit-user-select: none; /* Chrome/Safari */
	-moz-user-select: none; /* Firefox */
	-ms-user-select: none; /* IE10+ */
	/* Rules below not implemented in browsers yet */
	-o-user-select: none;
	user-select: none;
	cursor: pointer;
}

.dropdown-menu .sub-menu {
	left: 100%;
	position: absolute;
	top: 0;
	display: none;
	margin-top: -1px;
	border-top-left-radius: 0;
	border-bottom-left-radius: 0;
	border-left-color: #fff;
	box-shadow: none;
}

.right-caret:after, .left-caret:after {
	content: "";
	border-bottom: 5px solid transparent;
	border-top: 5px solid transparent;
	display: inline-block;
	height: 0;
	vertical-align: middle;
	width: 0;
	margin-left: 5px;
}

.right-caret:after {
	border-left: 5px solid #ffaf46;
}

.left-caret:after {
	border-right: 5px solid #ffaf46;
}
</style>
    </body>
    </html>

