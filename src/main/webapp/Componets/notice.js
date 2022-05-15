$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
	 $("#alertSuccess").hide();
	 }
	 $("#alertError").hide();
});



 // SAVE ============================================
   
$(document).on("click", "#btnSave", function(event)
	{
		
		// Clear alerts---------------------
		
		 $("#alertSuccess").text("");
		 $("#alertSuccess").hide();
		 $("#alertError").text("");
		 $("#alertError").hide();
		 
		// Form validation-------------------
	
		var status = validateBillForm();
		if (status != true)
	  {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
	   }
	
		
	var type = ($("#hideNoticeIDSave").val().trim() == "") ? "POST" : "PUT";
	console.log(`\n\n\n>>>> FORM SUBMIT METHOD = ${type}\n\n\n`);
	$.ajax( 
			{ 
				 url : "PaymentsAPI", 
				 type : type, 
				 data : $("#formNotice").serialize(), 
				 dataType : "text",
				 complete : function(response, status)
				 { 
				 	console.log(`>>>> RES ${response}`);
					 onBillSaveComplete(response.responseText, status); 
				 } 
			}
		);
		
	});
	
	
	
	//Billsavecomplefunction
	


	function onBillSaveComplete(response, status) 
	{ 
	  if (status == "success") 
	   { 
				 var resultSet = JSON.parse(response); 
				 
				 if (resultSet.status.trim() == "success") 
				 
				 { 
					 $("#alertSuccess").text("Successfully saved."); 
					 $("#alertSuccess").show(); 
					 $("#divItemsGrid").html(resultSet.data); 
					 
				 } else if (resultSet.status.trim() == "error") 
				 
				 { 
					 $("#alertError").text(resultSet.data); 
					 $("#alertError").show();
					  
				 } 
		 
		 } else if (status == "error") 
		 
		 { 
			 $("#alertError").text("Error while saving."); 
			 $("#alertError").show(); 
			 
		 } else
		 
		 { 
			 $("#alertError").text("Unknown error while saving.."); 
			 $("#alertError").show(); 
			 
		 } 
		
	
		 $("#hideNoticeIDSave").val(""); 
		 $("#formNotice")[0].reset(); 
	}
	


	
	

	
	   
	
	$(document).on("click", ".btnUpdate", function(event) 
	{ 
		   $("#hideNoticeIDSave").val($(this).closest("tr").find('td:eq(0)').text());
		 $("#noticID").val($(this).closest("tr").find('td:eq(0)').text());
		 $("#noticeName").val($(this).closest("tr").find('td:eq(1)').text());
		 $("#noticeDate").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#noticeTime").val($(this).closest("tr").find('td:eq(3)').text()); 
		 $("#noticeDes").val($(this).closest("tr").find('td:eq(4)').text()); 
	
		
	
		
	});
	
	
	
//delete
	
$(document).on("click", ".btnRemove", function(event) 
	{ 
		 $.ajax( 
			 { 
					 url : "PaymentsAPI", 
					 type : "DELETE", 
					 data : "id=" + $(this).data("id"),
					 dataType : "text", 
					 complete : function(response, status) 
				 { 
			     onBillDeleteComplete(response.responseText, status); 
			     } 
		 }); 
	});
	


//deletecompletion

function onBillDeleteComplete(response, status) 
{ 
	  if (status == "success") 
	 { 
		 var resultSet = JSON.parse(response); 
		 
			 if (resultSet.status.trim() == "success") 
				 { 
					 $("#alertSuccess").text("Successfully deleted."); 
					 $("#alertSuccess").show(); 
					 
					 $("#divItemsGrid").html(resultSet.data); 
			 } else if (resultSet.status.trim() == "error") 
				 
			 { 
				 $("#alertError").text(resultSet.data); 
				 $("#alertError").show(); 
			 } 
			 
	} else if (status == "error") 
			 
	{ 
	     $("#alertError").text("Error while deleting."); 
		 $("#alertError").show(); 
	} else
			 
	 { 
	     $("#alertError").text("Unknown error while deleting.."); 
		 $("#alertError").show(); 
	 } 
		
		
	}	
		
	// CLIENT-MODEL================================================================
	//(String noticID, String noticeName, String noticeDate,String noticeTime, String noticeDes)
	function validateBillForm()
	{
	
	 
		
		
	 
		
		if ($("#noticeName").val().trim() == "")
		 {
		 	return "Insert joinDate.";
		 }
		 
		
		if ($("#noticeDate").val().trim() == "")
		 {
			 return "Insert meterReadingBeforeDate.";
		 }
		 
		 if ($("#noticeTime").val().trim() == "")
		 {
		 	return "Insert meterReadingBefore.";
		 }
		 
		 
		  if ($("#noticeDes").val().trim() == "")
		 {
		 	return "Insert meterReadingNow.";
		 }
		 
		 
		
		return true;
		


}
