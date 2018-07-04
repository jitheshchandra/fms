<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Incident Register Update</title>
<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>

</head>
<script type="text/javascript">
	$(function() {
		var loggedInUser = 'Hari';
		var incidentUpdateApp = angular.module('incidentUpdateApp', []);
		incidentUpdateApp.controller('IncidentUpdateCtrl', function($scope,
				$http, $filter) {
			$scope.statuses = [ {
				id : 'O',
				value : 'Open'
			}, {
				id : 'C',
				value : 'Closed'
			} ];

			$scope.incidents = [];
			var openIncidents = "getOpenIncidents";
			$http.post(openIncidents).success(function(data) {
				$scope.incidents = data;
			});

			$scope.validateForm = function() {
				for (i = 0; i < $scope.incidents.length; i++) {
					if ($scope.incidents[i].closeRemarks != ''
							&& $scope.incidents[i].closeRemarks != undefined) {
						var closeRem = $scope.incidents[i].closeRemarks;
						var resolveRem = '';
						if ($scope.incidents[i].resolvedRemarks != '') {
							resolveRem += '<br>';
						}
						resolveRem += '[' + loggedInUser + ':'
								+ $filter('date')(new Date(), 'MM-dd-yyyy')
								+ ']:' + closeRem;
						$scope.incidents[i].resolvedRemarks += resolveRem;
					}
				}

			};
		});
	});
</script>

<body ng-app="incidentUpdateApp">
	<div class="intro-header">
		<h2 style="text-align: center">Incident Register Update</h2>
		<br> <br>

		<div class="well" style="margin: 0 9% 0 10%;"
			ng-controller="IncidentUpdateCtrl">
			<form:form class="form-horizontal" action="updateIncidents"
				method="POST" style="margin-top: 3%;"
				modelAttribute="updateIncidentForm" ng-submit="validateForm()">
				<div class="form-group">
					<div class="table-responsive">
						<table class="table table-bordered " align="center">
							<thead>
								<tr>
									<th class="tbh">SNo</th>
									<th class="tbh">Complaint</th>
									<th class="tbh">Reported Date - Time</th>
									<th class="tbh">Status</th>
									<th class="tbh">Remarks</th>

								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="incident in incidents"
									ng-form="incidentForm{{$index}}">
									<td>{{$index+1}}<input type="hidden"
										name="incidents[{{$index}}].id" value={{incident.id}}></td>
									<td>?? ... >>> {{incident.remarks}}</td>
									<td>{{incident.incidentDate| date:'MM/dd/yyyy'}} -
										{{incident.incidentTime}}</td>
									<td><select class="form-control"
										name="incidents[{{$index}}].status">
											<option ng-repeat="status in statuses" value="{{status.id}}"
												ng-selected="incidents[{{$index}}].status == status.id">{{status.value}}</option>
									</select></td>
									<td><input type="hidden"
										ng-model="incident.resolvedRemarks"
										name="incidents[{{$index}}].resolvedRemarks"
										value="{{incident.resolvedRemarks}}">
										{{incident.resolvedRemarks}} <textarea class="form-control"
											rows="2" ng-required="incidentStatus=='C'"
											ng-model="incident.closeRemarks"></textarea></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="row clearfix">
						<div class="col-md-12 column">
						<a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="submit" class="btn btn-primary"
								>Save</button>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>


</body>
</html>