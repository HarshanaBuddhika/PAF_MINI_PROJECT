
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
var status = validateItemForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidInqueryIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "Inquries", 
 type : type, 
 data : $("#formInquery").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemSaveComplete(response.responseText, status); 
 } 
 }); 
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
$("#hidInqueryIDSave").val($(this).data("inqueryid")); 
 $("#inqueryCode").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#inqueryName").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#inqueryPrice").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#inqueryDescription").val($(this).closest("tr").find('td:eq(3)').text()); 
});

$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "Inquries", 
 type : "DELETE", 
 data : "inqueryID=" + $(this).data("inqueryID"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 
 }); 
});
// CLIENT-MODEL================================================================
function validateItemForm() 
{ 
// CODE
if ($("#inqueryCode").val().trim() == "") 
 { 
 return "Insert Inquery Code."; 
 } 
// NAME
if ($("#inqueryName").val().trim() == "") 
 { 
 return "Insert Inquery Name."; 
 } 
// PRICE-------------------------------
if ($("#inqueryPrice").val().trim() == "") 
 { 
 return "Insert Inquery Price."; 
 } 
// is numerical value
var tmpPrice = $("#inqueryPrice").val().trim(); 
if (!$.isNumeric(tmpPrice)) 
 { 
 return "Insert a String value for Item Caption."; 
 } 
// convert to decimal price
 $("#inqueryPrice").val(parseFloat(tmpPrice).toFixed(2)); 
// DESCRIPTION------------------------
if ($("#inqueryDescription").val().trim() == "") 
 { 
 return "Insert Inquery Description."; 
 } 
return true; 
}

function onInquerySaveComplete(response, status) 
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
 $("#hidItemIDSave").val(""); 
 $("#formItem")[0].reset(); 
}


function onInqueryDeleteComplete(response, status) 
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




