<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>
label 
{
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>

<script type="text/javascript">
	$(function()
			{
		var escalationApp = angular.module('escalationApp', []);
		escalationApp.controller('EscalationCtrl', function($scope, $http)
				{
			$scope.escalations = [];
			$scope.escalationLevels = [];
			$scope.branches = [];
			$scope.branch = "";

			$scope.organizations = [];
			var getOrg = "getOrganizations";
			$http.post(getOrg).success(function(data) {
			$scope.organizations = data;}
			);

			$scope.getBranches = function() 
			{ debugger;
				var orgId = $scope.organization;
				$scope.escalations = [];
				$scope.branches = [];
				$scope.branch = "";

				var action = "getBranchByOrgId?";
				var param = 'orgId=' + orgId;
				$http.post(action + param).success(function(data) { $scope.branches = data;});
			};

			$scope.getEscalationMatrix = function()
			{
				debugger;
				
				$('#add-escalation').removeAttr('disabled');
				$scope.escalations = [];
				$scope.escalationLevels = [];
				if ($scope.branch == '' || $scope.category == undefined || $scope.category == '')
				{
					return;
				}

				var action = "getEscalationMatrix?";
				var param = 'branchId=' + $scope.branch + '&categoryTypeId='+ $scope.category;
				$http.post(action + param).success(function(data){
					$scope.escalations = data.escList;
					$scope.escalationLevels = data.escalationLevels;
				});
			};
			
			$scope.deleteEscalationLevel = function(index) 
			{
				var esc = $scope.escalations[index];
				if (esc.id != '') 
				{
					var action = 'deleteEscalationMatrix';
					param = 'escalationMatrixId=' + esc.id;
					doAjaxCall(action, param, function(response)
					{
						var obj = eval(response);
						if (obj.result) 
						{
							$scope.escalations.splice(index, 1);
						}
					}, function(respones) { //on error, dont do anything. 
						});
				} else {
					//just delete from the page 
					$scope.escalations.splice(index, 1);
				}

			};

			$scope.addEscalationTemplate = function() 
			{
				len = $scope.escalations.length;
				$scope.escalations.push({ id : '' });
			};

		});

	});

	function validate(form) {
		if ($('#escalation-level .level').length == 0) {
			alert('Nothing to save...');
			return false;
		}
	}
</script>

</head>
<body ng-app="escalationApp">
	<div class="intro-header">
		<h2 style="text-align: center">Escalation Matrix</h2>
		<br> <br>
		<!-- <select ng-model="Score"
			ng-change="getScoreData(Score)"
			ng-options="c.name for c in     Scores"></select> -->
		<div class="well" style="margin: 0 9% 0 10%;" ng-controller="EscalationCtrl">
			<form:form class="form-horizontal" action="saveEscalationMatrix" method="POST" style="margin-top: 3%;" onsubmit="return validate(this);" modelAttribute="escalationMatrixInfoForm">
				<div class="form-group">
					<label for="Organization" class="col-sm-5 control-label2">Organization</label>
					<div class="col-sm-6">
						<select class="form-control form-page" name="organizationId"
							ng-model="organization" id="organizationId" style="width: auto;"
							ng-change="getBranches()">
							<option value="">Please Select Organization</option>
							<option ng-selected="org.orgId==organization"
								value="{{org.orgId}}"
								ng-repeat="org in organizations | orderBy:orgName">{{org.orgName}}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="branch " class="col-sm-5 control-label2">Branch</label>
					<div class="col-sm-6">
						<select class="form-control" ng-model="branch"
							ng-change="getEscalationMatrix()" name="branchId"
							style="width: auto;">
							<option value="">Please Select Branch</option>
							<option ng-selected="br.id==branch" value="{{br.id}}"
								ng-repeat="br in branches | orderBy:branchName">{{br.branchName}}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="branch " class="col-sm-5 control-label2">Incident
						Type</label>
					<div class="col-sm-6">
						<select class="form-control" name="categoryId"
							ng-change="getEscalationMatrix()" ng-model="category"
							style="width: auto;">
							<option value="">Default</option>
							<c:forEach items="${categories}" var="category">
								<option value="${category.id}">${category.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="table-responsive">
					<table class="table table-bordered" id="escalation-level">
						<thead>
							<tr>
								<th class="tbh">Escalation Level</th>
								<th class="tbh">Contact Person</th>
								<th class="tbh">Email</th>
								<th class="tbh">Phone</th>
								<th class="tbh">
								  <a href="">
								    <button type="button" class="btn btn-info" ng-disabled="escalationLevels.length==0" id='add-escalation' ng-click="addEscalationTemplate()">
										<span class="glyphicon-plus">Add Escalation</span>
									</button>
								  </a>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="escalation in escalations">
								<td class="tbh level">
								    <select class="form-control" name="matrixLevel[{{$index}}].levelId" ng-model="level" required="required" style="width: auto;">
										<option value="">Choose Option</option>
										<option value="Low">Low</option>
										<option value="Medium">Medium</option>
										<option value="High">High</option>
										<option value="Critical">Critical</option>
										
										<option ng-selected="el.id=={{escalation.level.id}}" value="{{el.id}}" ng-repeat="el in escalationLevels | orderBy:name">{{el.name}}</option>
								    </select> <input name="matrixLevel[{{$index}}].id" type="hidden" value="{{escalation.id}}">
								</td>
								<td>
								    <input type="text" class="form-control" name="matrixLevel[{{$index}}].contactName"  value="{{escalation.contactName}}" required> 
							    </td>
								<td>
								    <input type="email" class="form-control" placeholder="john.doe@ex.org" name="matrixLevel[{{$index}}].contactEmail" required value="{{escalation.contactEmail}}">
								</td>
								<td>
								    <input class="form-control" type="tel" placeholder="888-888-8888" title="XXX-XXX-XXXX" required pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" maxlength="12"  name="matrixLevel[{{$index}}].contactPhone" required value="{{escalation.contactPhone}}" placeholder="00000000">
								</td>
								<td>
									<button type="button" class="btn btn-danger btn-xs" ng-click="deleteEscalationLevel($index)">
										<span class="fa fa-times"></span> Delete
									</button>
								</td>
							</tr>
						</tbody>
					</table>
					<div style="margin-left: 75%;">
				<a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="submit" class="btn btn-success" ng-disabled="escalations.length==0" id="save-escalation">Save</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>