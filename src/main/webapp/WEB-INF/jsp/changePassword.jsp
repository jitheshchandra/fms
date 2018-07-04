<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html; charset=iso-8859-1" language="java"
	import="java.security.MessageDigest"%>

<html>
<head>
<meta charset="utf-8">
<title>Change Password</title>
<style>
label {
	color: black;
	font-family: "Lato", "Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align: right;
}
</style>
<script type="text/javascript">
	function checkForm(form) {
		//not required ...  use firefox's firebug instead;
		var passhash = CryptoJS.MD5(form.password.value).toString();
		var userId = $('#userId').val();
		//var newPassword = CryptoJS.MD5(form.newpassword.value).toString();

		debugger;
		if (passhash == form.oldPwd.value) {

			if (form.newpassword.value != ""
					&& form.newpassword.value == form.confirmpassword.value) {
				if (form.newpassword.value.length < 8) {
					alert("Error: Password must contain at least 8 characters!");

					form.newpassword.focus();
					return false;
				}
				re = /[0-9]/;
				if (!re.test(form.newpassword.value)) {
					alert("Error: password must contain at least one number (0-9)!");
					form.newpassword.focus();
					return false;
				}
				re = /[a-z]/;
				if (!re.test(form.newpassword.value)) {
					alert("Error: password must contain at least one lowercase letter (a-z)!");
					form.newpassword.focus();
					return false;
				}
				re = /[A-Z]/;
				if (!re.test(form.newpassword.value)) {
					alert("Error: password must contain at least one uppercase letter (A-Z)!");
					form.newpassword.focus();
					return false;
				}
			} else {
				alert("Error: Please check that you've entered and confirmed your password!");
				form.newpassword.focus();
				return false;
			}

			{

			}
			alert("You entered a valid password: ");
			return true;
		} else {
			alert('entered Old Password is incorrect!');
			return false;
		}
	};
</script>


</head>
<body>

	<script
		src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/md5.js"></script>
	<div class="intro-header">
		<h2 style="text-align: center">Change Password</h2>
		<br> <br>
		<div class="well" style="margin: 0% 30% 0% 30%; padding: 2% 2% 0% 0%;">
			<form class="form-horizontal" action="/fmsv1/updatePassword" role="form" method="get"
				onsubmit="return checkForm(this);">
				<div class="form-group">
					<label class="col-sm-6 myLabel">Old password</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" name="password"
							id="password" style="width: 100%;" /> <input type="hidden"
							id="oldPwd" name="oldPwd" value='${oldPwd}'> <input
							type="hidden" id="userId" name="userId" value='${userId}'>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-6 myLabel">New password</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" name="newpassword"
							id="newpassword" style="width: 100%;" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-6 myLabel">Confirm password</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" name="confirmpassword"
							id="confirmpassword" style="width: 100%;" />
					</div>
				</div>
				<div class="row clearfix">
					<div class="col-md-12 column">
					<a href="/fmsv1/Home"> <button type="button" class="btn btn-default">Back</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="submit" class="btn btn-primary btn-default"
							>Submit</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<br />
	<br />
</body>

</html>
