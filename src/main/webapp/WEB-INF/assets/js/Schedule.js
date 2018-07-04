	var App = App || {}
	App = {

		SaveEvent : function(){  
			debugger;
			var isValid = false;
			if ($('#eventTitle').val() == '' || $('#eventDate').val() == '' || $('#eventTime').val() == '' || $('#eventDuration').val() == '')
			{
				alert('Please fill out all the fields');
				e.preventDefault();
				return false;
			}
			$('#popupEventForm').hide();
	//JsonData
			var dataRow = { 
			
				'Title': $('#eventTitle').val(),
				'StartDate': $('#datetimepickerforStartDate').val(),
				'Enddate': $('#datetimepickerforEndDate').val(),
				'Duration': $('#eventDuration').val(),
				'Recurrence_Pattern':$('#Recurrence_Pattern').val(),
				'Managed_Entity_Group' : $('#Managed_Entity_Group').val(),
				'Supervisor' : $('#Supervisor').val(),
				'CheckList' : $('#CheckList').val()
				 
			}
	
			App.ClearPopupFormValues();
			
			$.ajax({
				type: 'POST',
				url: "/Home/SaveEvent",
				data: dataRow,
				success: function (response) {
					if (response == 'True') {
						$('#calendar').fullCalendar('refetchEvents');
						alert('New event saved!');
					}
					else {
						alert('Error, could not save event!');
					}
				}
			});

		},
		CancelEvent : function()
		{
			App.ClearPopupFormValues();
			$('#popupEventForm').hide(1000);
		},
		ShowEventPopup: function(date)
		{	
			App.ClearPopupFormValues();
			$('#popupEventForm').show();
			
			$('#eventTitle').focus();
		},
		ClearPopupFormValues : function()
		{
			$('#eventID').val("");
			$('#eventTitle').val("");
			$('#eventDateTime').val("");
			$('#eventDuration').val("");
		},
		UpdateEvent : function(EventID, EventStart, EventEnd)
		{
			var dataRow = {
			
				'ID': EventID,
				'NewEventStart': EventStart,
				'NewEventEnd': EventEnd
			}

			$.ajax({
				type: 'POST',
				url: "/Home/UpdateEvent",
				dataType: "json",
				contentType: "application/json",
				data: JSON.stringify(dataRow)
			});
		}
		
	}
   