<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="assets/css/sri.css">
<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>

</head>
<body ng-app="incidentsummaryApp">
	<div class="intro-header">
		<h2 style="text-align: center">Incident Summary</h2>
		<br> <br>

		<div class="well" style="margin:0 10% 0 10%;" width=auto; ng-controller="IncidentsummaryCtrl">
		<div class="form-group">
					<label for="orgnazation" class="col-sm-5 control-label">Organization</label>
					<div class="col-sm-3">
						<select class="form-control" ng-model="organization" id="organizationId" name="organizationId" ng-change="selectBranch()">
							<option value="">Please select Organization</option>
							<option ng-repeat="organization in organizations|orderBy:'orgName'" value="{{organization.orgId}}">{{organization.orgName}}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="branch " class="col-sm-5 control-label2">Branch</label>
					<div class="col-sm-6">
						<select class="form-control" ng-model="branch" ng-change="getEmployeeSummary()" name="branchId" id="branchId" name="branchId"style="width: auto;">
							<option value="">Please Select Branch</option>
							<option value="{{br.id}}"ng-repeat="br in branches | orderBy:branchName">{{br.branchName}}</option>
						</select>
					</div>
				</div>
		
			
</body>
</html>