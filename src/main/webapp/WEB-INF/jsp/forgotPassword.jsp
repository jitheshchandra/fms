<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>Forgot Password</title>
 
</head>

<body>
<div class="container" style="padding-bottom: 3%;">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<h3 class="text-center" style="margin-top: 10%;">
				Forgot Your Password?
			</h3>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column" style="margin-left: 20%; margin-top: 5%;">
			<form class="form-horizontal" role="form">
				<div class="form-group">
					 <label for="email" class="col-sm-2 control-label">Enter your email ID</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="email" style="width: 30%;">
					</div>
				</div>
					
			</form>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column">
			 <button type="button" class="btn btn-primary btn-default" style="margin-left: 50%;">Generate Password</button>
		</div>
	</div>
</div>
</body>