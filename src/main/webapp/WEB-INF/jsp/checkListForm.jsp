<%@ page language="java" contentType="text/html; charset=ISO-8859-1"pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
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
	<h2 style="text-align:center ">Add Check List </h2>
	<br>
	<br>
	
	<div class="well" style="margin : 0 25% 0  25%;">

			<form class="form-horizontal" style="margin-top:3%;" action="/fmsv1/saveCheckListForm" method="POST">
				<div class="form-group">
				<input type="hidden" name="id" id="id"  value="${checkList.id}"/>
		      <input type="hidden" name="orgId" id="orgId"  value="${chk_orgId}"/>
				<input type="hidden" name="branchId" id="branchId"  value="${chk_branchId}"/>  
					
			 <label for="checkListForm" class="col-sm-5 control-label2" >Check List Name</label>
			 <input type="text" class="form-control" id="checkListName" name="checkListName" style="width: auto" value='<c:out value="${checkList.name}"/>'> 
				 	 
		
				</div> 
				<div class="form-group">
					 <label for="branch " class="col-sm-5 control-label2">Check List Type</label>
					  <div class="col-sm-6">
                          <select class="form-control form-page" name="checkListTypeId" id="checkListTypeId" >
							<option value="">Please select checkListType</option>
							<c:forEach items="${checkListType}" var="checkListType">
								<option value="${checkListType.id}"
									${param.id == checkListTypeId.id ? 'selected' : ''}>${checkListType.name}</option>
							</c:forEach>
						</select>
						</div>
				</div>
				<div class="form-group">
					 <label for="select " class="col-sm-5 control-label2">Default check</label>
					  <div class="col-sm-6">
                           <select class="form-control"  name="defaultCheck" id="defaultCheck">			
                          	 	<option value="0">Yes</option>
								<option value="1">No</option>				
						</select>
						</div>
				</div>
												
			 <button type="button" class="btn btn-success" id="cancel">Cancel</button>&nbsp;&nbsp;&nbsp;
			<button type="submit" class="btn btn-success btn-default" value="save">Save</button>
	</form>
 </div>
</div>
 
  <script type="text/javascript">
	 	$('#cancel').click(function(e) {
			debugger;
			var organizationId = $('#orgId').val();
			var branchId = $('#branchId').val();
		//	alert('OraganizationId='+organizationId+"And BranchId="+branchId);
			if(organizationId!=""||branchId!="")
				{
				window.location.href = "/fmsv1/Home?organizationId=" +organizationId.id+"&branchId="+branchId.id;
				}
			
		});
	</script>
 



</body>
</html>