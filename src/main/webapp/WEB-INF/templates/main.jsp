<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
 <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=`1024px, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="assets/ico/favicon.png">

    <title><tiles:getAsString name="title" /></title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/assets/css/bootstrap.css"/>" rel="stylesheet"/>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="assets/js/html5shiv.js"></script>
      <script src="assets/js/respond.min.js"></script>
    <![endif]-->

    <!-- Custom styles for this template -->
    <link href="<c:url value="/assets/css/carousel.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/assets/css/font-awesome.min.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/assets/css/footable.core.min.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/assets/css/footable.metro.min.css"/>" rel="stylesheet"/>
	
  </head>
<body>
<div id="header">
	<div id="headerTitle"><tiles:insertAttribute name="header" /></div>
</div>
<div id="menu">
	<tiles:insertAttribute name="menu" />
</div>
<div id="content">
	<td><tiles:insertAttribute name="body" />
</div>
<div id="footer">
	<tiles:insertAttribute name="footer" />
</div>
 <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    <script src="<c:url value="/assets/js/jquery.js"/>"></script>
    <script src="<c:url value="/assets/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/assets/js/holder.js"/>"></script>
    <script src="<c:url value="/assets/js/footable.js"/>"></script>
    <script src="<c:url value="/assets/js/footable.paginate.js"/>"></script>
	<script src="<c:url value="/assets/js/footable.sort.js"/>"></script>
    <script src="<c:url value="/assets/js/footable.filter.js"/>"></script>
    <script src="<c:url value="/assets/js/admin/admin.js"/>"></script>
    <script src="<c:url value="/assets/js/fms/component-util.js"/>"></script>
    <script src="<c:url value="/assets/js/angular.min.js"/>"></script>
    
	<script type="text/javascript">
	
	
		$(function() {
			$('.footable').footable();
		});
		
		
	</script>
</body>
</html>