$( document ).ready(function(){
	

	$('#testButton').click(function(){
		
		$.ajax({
		    type : "GET",
		    beforeSend : function(req) {
		        req.setRequestHeader("Accept", "text/html;type=ajax");
		    },
		    url : "/dicdocv1/clinic/clinicAdmin",
		    data: "",
		    success : function(response) {
		        $("#testyourAjax").html($(response).find("#testClinicAdmin").text());
		    },
		    error : function(e) {
		        alert('Error : ' + e);
		    }
		});
		
	});
	
});
