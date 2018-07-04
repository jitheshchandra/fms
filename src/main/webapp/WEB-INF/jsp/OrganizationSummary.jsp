<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FMS-ORGSummary</title>
<script src="./assets/js/jquery-1.9.1.js"></script>

  
</head>
<body>
<div class="intro-header" >
	<h2 style="text-align:center ">Organizations</h2>
	<br>
	<br>
	
	<div class="well" style="margin : 0 9% 0  10%;">
	<div class="table-responsive">
			<table class="table table-striped ">
			 
		<tr>
		<td colspan="2" style="text-align:left;"> <a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<td colspan="6" style="text-align:right;"><a href="/fmsv1/addOrganization"><button type="button" class="btn btn-info"><span class="glyphicon-plus">Add Organization</span></button></a></td>
			
		</tr>
		
		<tr>
			<th>Name</th>
			<th>Type</th>
			<th>Address</th>
			<th>Contact Person</th>
			<th>Contact No.</th>	
			<th colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
		</tr>
		
			<c:forEach items="${orgSummary_detail}" var="org">
				<tr>
				<td><a href="/fmsv1/orgdetails?id=${org.orgId}">${org.orgName}</a></td>
				<td>${org.orgType}</td>
				<td>${org.orgAddress}</td>
				<td>${org.orgContactPerson}</td>
				<td>${org.orgContactNumber}</td>
				<td><button value="${org.orgId}" class='removeBtn btn btn-danger btn-xs' value="delete">
				<span class="fa fa-times"></span>Delete</button>
				</td>
				</tr>			
				</c:forEach>
		
		
	</table>
	</div>
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
$(document).ready(function(){
  $('button.removeBtn').click(function(e){
	 var $form = $(this).closest('form');
	    var btn_val =$(this).val(); 
	    e.preventDefault();
	    $('#confirm').modal({ backdrop: 'static', keyboard: true })
	        .one('click', '#delete', function (e) {
	    	
	        	window.location.href = "/fmsv1/delOrg?id="+btn_val;
        });
  });
});
 
  </script>
	</div>
</body>
	
	
</html>