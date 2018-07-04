$(document).ready(function(){

$('#signInBtn').click(function(){
	alert("clicked");
	login();
});

function login() {

	$.ajax({ 
	    url: "mobileLogin", 
	    type: 'POST', 
	    dataType: 'json', 
	    data: "{user:{'userId':'dicdoc', 'password':'dicdoc'}}",
	    contentType: 'application/json',
	    mimeType: 'application/json',
	    Accepts: "{'text/html'}",
	    
	    success: function(response) {
	    	//alert(response.user.status);
	    	window.location.href = 'doctor';
	    	
	    },
	    error:function(data,status,er) { 
	        alert("error: "+data+" status: "+status+" er:"+er);
	    }
	});
 }

});