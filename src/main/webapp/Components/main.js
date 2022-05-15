$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();

});

$(document).on("click", "#btnsave", function(event){ 
		// Clear alerts---------------------
		 $("#alertSuccess").text(""); 
		 $("#alertSuccess").hide(); 
		 $("#alertError").text(""); 
		 $("#alertError").hide(); 
		
		 // Form validation-------------------
		var status = validateForm(); 
		if (status != true){ 
		 	$("#alertError").text(status); 
		 	$("#alertError").show(); 
		 	return; 
		 }
		
		// If valid------------------------
		var type = ($("#hidCusIDSave").val() == "") ? "POST" : "PUT"; 
		
		$.ajax( 
				 { 
				 	url : "customerServlet", 
				 	type : type, 
				 	data : $("#formCustomer").serialize(), 
				 	dataType : "text", 
				 	complete : function(response, status) 
				 { 
				 	onItemSaveComplete(response.responseText, status); 
				 } 
				 });
});

$(document).on("click", "#btnUpdate", function(event)
{
	$("#hidCusIDSave").val($(this).data("cusid"));
	$("#idcustomer").val($(this).closest("tr").find('td:eq(0)').text());
 	$("#cusname").val($(this).closest("tr").find('td:eq(1)').text()); 
 	$("#cusemail").val($(this).closest("tr").find('td:eq(2)').text()); 
 	$("#cusnumber").val($(this).closest("tr").find('td:eq(3)').text()); 
 	$("#cusacnumber").val($(this).closest("tr").find('td:eq(4)').text());
	$("#cusnicnumber").val($(this).closest("tr").find('td:eq(5)').text());
	$("#cuspassword").val($(this).closest("tr").find('td:eq(6)').text()); 

});

$(document).on("click", "#btnRemove", function(events) 
{ 
 	$.ajax( 
 	{ 
 		url : "customerServlet", 
 		type : "DELETE", 
 		data : "idcustomer=" + $(this).data("cusid"),
 		dataType : "text", 
 		complete : function(response, status) 
 	{ 
 		onItemDeleteComplete(response.responseText, status); 
 	} 
 	}); 
});

$(document).on("click", "#btnLogout", function(event) 
{ 
 	$.ajax( 
 	{ 
 		url : "AuthAPI", 
 		type : "DELETE", 
 		data : "", 
 		dataType : "text", 
 		complete : function(response, status) 
 		{ 
 			onLogoutComplete(response.responseText, status); 
 		} 
 	}); 
}); 

function onLogoutComplete(response, status) 
{ 
	if (status == "success") 
 	{ 
 		if (response.trim() == "success") 
 		{ 
 			//Redirect to index------------------
 			document.location = "Login.jsp"; 
 		} 
 	} 
}


function validateForm()
{
	if($("#cusname").val().trim() == "")
	{
		return "Enter a Name";
	}
	
	if($("#cusemail").val().trim() == "")
	{
		return "Enter a Email";
	}
	
	if($("#cusnumber").val().trim() == "")
	{
		return "Enter a Number";
	}
	
	if($("#cusacnumber").val().trim() == "")
	{
		return "Enter a Account Number";
	}
	
	if($("#cusnicnumber").val().trim() == "")
	{
		return "Enter a NIC number";
	}
	
	if($("#cuspassword").val().trim() == "")
	{
		return "Enter a Password";
	}
	
		return true;
}

function onItemSaveComplete(response, status) 
{ 
	if (status == "success") 
 	{ 
 		var resultSet = JSON.parse(response); 
 		
 		if (resultSet.status.trim() == "success") 
 		{ 
 			$("#alertSuccess").text("Successfully saved."); 
 			$("#alertSuccess").show(); 
 			$("#divCustomerGrid").html(resultSet.data); 
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
	
	$("#hidCusIDSave").val(""); 
	$("#formCustomer")[0].reset(); 
}

function onItemDeleteComplete(response, status) 
{ 
	if (status == "success") 
 	{ 
 		var resultSet = JSON.parse(response); 
 		if (resultSet.status.trim() == "success") 
 		{ 
 				$("#alertSuccess").text("Successfully deleted."); 
 				$("#alertSuccess").show(); 
 				$("#divCustomerGrid").html(resultSet.data); 
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
