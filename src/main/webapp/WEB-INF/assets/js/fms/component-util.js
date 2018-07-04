function buildSelectOption(nameVal) {
	var options = '';
	for (i = 0; i < nameVal.length; i++) {
		options += '<option value="' + nameVal[i].value + '">'
				+ nameVal[i].display + '</option>';
	}
	return options;
}

function doAjaxCall(action, submitParam, onSuccess, onError) {
	$.ajax({
		type : "post",
		url : action,
		cache : false,
		data : submitParam,
		success : onSuccess,
		error : onError
	});
}