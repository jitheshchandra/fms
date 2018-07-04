<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FMS</title>
<style>
label 
{
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>

<SCRIPT type="text/javascript">
       $(function(){
        	$('#tr1').removeAttr('style');
        	var checkListdetailApp=angular.module('checkListdetailApp',[]);
        	checkListdetailApp.controller('checkListController',function($scope,$http){
        		$scope.rows=[];
        		$scope.addRow=function(){	
        			$scope.rows.push({id:''});
        		};
        		
        		$scope.deleteChecklistdetail = function(index) {
    				$scope.rows.splice(index, 1);
    		};
        	});
        });
       //get managed entity type and sub type here .
</SCRIPT>

</head>
<body ng-app="checkListdetailApp">
	<div class="intro-header">
		<h2 style="text-align: center">Check Lists Details</h2>
		<br> <br>
		<div class="well" style="margin: 0 10% 0 10%; width: auto;" ng-controller="checkListController">
			<form class="form-horizontal" action="/fmsv1/addCheckListDetail" method="POST">
				<div class="form-group">

					<div class="well" style="margin: 0 25% 0 25%; width: auto;">

						<div class="form-group">
							<label for="empcode" class="col-sm-5 control-label2">Organization</label>
							<input type="text" class="form-control" id="organizationName" name="organizationName" value="${checkList.organizationId.name}" style="width:auto;" readonly="readonly">
							<br>
							 
							<label for="firstname" class="col-sm-5 control-label2">Branch</label>
							<input type="text" class="form-control" id="branchName" name="branchName" value="${checkList.branchId.branchName}" style="width:auto;" readonly="readonly"/>
							<br>
							 
							<label for="lastname" class="col-sm-5 control-label2">Check List</label>
							<input type="text" class="form-control" id="checkList" name="checkList" value="${checkList.name}"style="width:auto;" readonly="readonly"/>
							<input type="hidden" name="checkListId" id="checkListId"  value="${checkList.id}"/>
						</div>
					</div>
					<br />
					<div class="container">
						<div class="row clearfix">
							<div class="col-md-12 column">
								<div class="well" style="margin: 0 10% 0 0%; width: auto;">
									<div class="table-responsive">
										<table class="table table-bordered table-condensed" align="center">
											<thead>
												<tr>
													<th class="tbh">SlNo</th>
													<th class="tbh">Name</th>
													<th class="tbh">Group</th>
													<th class="tbh">Seq No</th>
													<th class="tbh">Min</th>
													<th class="tbh">Max</th>
													<th class="tbh">Quality</th>
													<th class="tbh">UOfM</th>
													<th></th>
												</tr>
											</thead>
											<% int rowCount = 0; %>
											<tbody id="rowData">
											<c:forEach items="${checkListDetails}" var="checkListDetail">
												<tr>
													<td id="td1"><%rowCount++; %><%=rowCount %></td>
													<td>${checkListDetail.name}</td>
													<td>${checkListDetail.groupName }</td>
													<td>${checkListDetail.seqNo}</td>
													<td>${checkListDetail.minLimit}</td>
													<td>${checkListDetail.maxLimit}</td>
													<td>${checkListDetail.quantity}</td>
													<td>${checkListDetail.uomId}</td>
													<td>
													<button class="removeBtn btn btn-sm btn-danger" type="button" value="${checkListDetail.id}">
										            <span class="glyphicon glyphicon-trash"></span>
							                          </button>  
							                        </td>
												</tr>
												</c:forEach>
												<tr id="tr1" style="display: none;" ng-repeat="row in rows">
												  <td><input type="hidden" class="form-control">New</td>
												  <td><input type="text" max="50" class="form-control" name="checkListdetail[{{$index}}].name" id="checkListdetail[{{$index}}].name" required></td>
												  <td><input type="text" class="form-control" name="checkListdetail[{{$index}}].groupName" id="checkListdetail[{{$index}}].groupName" required></td>
												  <td><input type="number" class="form-control" name="checkListdetail[{{$index}}].seqNo" id="checkListdetail[{{$index}}].seqNo"required></td>
												  <td><input type="text" class="form-control" name="checkListdetail[{{$index}}].minLimits" id="checkListdetail[{{$index}}].minLimits"required></td>
												  <td><input type="text" class="form-control" name="checkListdetail[{{$index}}].maxLimits" id="checkListdetail[{{$index}}].maxLimits"required></td>
												  <td><input type="number" class="form-control" name="checkListdetail[{{$index}}].quantity" id="checkListdetail[{{$index}}].quantity"required></td>
												  <td>
												     <select class="form-control" name="checkListdetail[{{$index}}].UOMId" id="checkListdetail[{{$index}}].UOMId" style="width: auto;" required="required">
												        <option value="">Please select UOM</option>
						                                   <c:forEach items="${uom }" var="uom">
							                                <option value="${uom.id}">${uom.name}</option>
						                                   </c:forEach>
						                              </select>
												  <td> 
												    <button class="btn btn-sm btn-danger" type="button" ng-click="deleteChecklistdetail($index)">
										              <span class="glyphicon glyphicon-trash"></span>
							                        </button>
							                      </td>
							                      
												</tr>			
											</tbody>	
										</table>
									</div>
									<div class="row clearfix">
										<div class="col-md-12 column" style="margin-left: 5%;">
											<button type="button" class="btn btn-success" ng-click="addRow()">Add</button>
											&nbsp;&nbsp;&nbsp;
											<button type="submit" class="btn btn-success" value="save">Save</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<br />
	<div id="confirm" class="modal fade" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body" style="background-color:#70B8FF" >
				Are you sure?
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn btn-primary"
						id="delete">Delete</button>
					<button type="button" data-dismiss="modal" class="btn">Cancel</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
							$('button.removeBtn').click(
											function(e) {
												var $form = $(this).closest('form');
												var btn_val = $(this).val();
												var checkList=$('#checkListId').val();
												e.preventDefault();
												$('#confirm').modal(
																{
																	backdrop : 'static',
																	keyboard : true
																})
														.one('click','#delete',
																function(e)
																{
																	window.location.href = "/fmsv1/deleteCheckListDetail?id="+ btn_val+"&checkListId="+checkList;
																});
											});
						});
	</script>

</body>
</html>