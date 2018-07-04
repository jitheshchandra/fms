<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

	<script type="text/javascript">
	$('#myTab a').click(function (e) {
		  e.preventDefault()
		  $(this).tab('show')
		})
	</script>
</head>
<body>
	<div class="container">
	<div class="row">
	<div class="hero-unit">
<ul class="nav nav-tabs" role="tablist">
  <li class="active"><a href="#Branch" role="tab" data-toggle="tab">Branch</a></li>
  <li><a href="#Contact" role="tab" data-toggle="tab">Contact</a></li>

</ul>

<!-- Tab panes -->
<div class="tab-content">
  <div class="tab-pane active" id="Branch">
  	<table class="table">
      <thead>
        <tr>
          
          <th>Name</th>
          <th>Designation</th>
          <th>Email</th>
          <th>Contact Number</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>jithesh</td>
          <td>programmer</td>
          <td>jithesh@softerveglobal.in</td>
          <td>9876543211</td>
        </tr>
        <tr>
          <td>Sridhar</td>
          <td>Programmer</td>
          <td>sridhar@softserveglobal.in</td>
          <td>9876543211</td>
        </tr>
       
      </tbody>
    </table>
  </div>
  <div class="tab-pane" id="Contact">

 <form class="form-horizontal" role="form" style="margin-top:5%; margin-left: 30%;"action="/fmsv1/addOrg" method="Post">
    <div class="form-group">
    <label class="col-sm-2 control-label" >Name</label>
    <input type="text" class="form-control" id="inputSuccess4" style="width: 30%;">
    
  	<br/>
      <label for="Organization" class="col-sm-2 control-label">Organization</label>
        <select class="form-control" style="width: 30%;">
       <c:forEach items="${States}" var="state">
				<option value='${state}'>${state} </option>
			
						
				</c:forEach>
      </select>
      
      <br/>
       <label class="col-sm-2 control-label" >Address Line 1</label>
    <input type="text" class="form-control" id="Addr1" style="width: 30%;">
      
      <br/>
      
       <label class="col-sm-2 control-label" >Address Line 2</label>
    <input type="text" class="form-control" id="Addr2" style="width: 30%;">
    
    <br/>
    
     <label class="col-sm-2 control-label" >Address Line 3</label>
    <input type="text" class="form-control" id="Addr3" style="width: 30%;">
    <br/>
    
     <label for="Organization" class="col-sm-2 control-label">State</label>
       <select class="form-control" style="width: 30%;">
       <c:forEach items="${Organizations}" var="orgs">
				<option value='${orgs}'>${orgs} </option>
			
						
				</c:forEach>
      </select>
    
    <br/>
    
    <label class="col-sm-2 control-label" >Pin Code</label>
    <input type="text" class="form-control" id="inputSuccess4" style="width: 30%;">
    
    <br/>
    
	    
	   <p id="b_viewedit" style="width: 30%;margin-left:20%">
<input type="reset" class="btn btn-primary btn-lg" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   	<input type="submit" class="btn btn-primary btn-lg" value="Save">
	   </p>
	

    </div>
   </form>
  </div>
 </div>
</div>
</div>
</div>


</body>
</html>