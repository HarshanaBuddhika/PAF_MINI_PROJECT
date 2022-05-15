<%@page import="com_cus.customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script type="text/javascript" src="Components/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="Components/main.js"> </script>
</head>
<body>
<div class="container">
<div class="row">
	
	<div class="col-20">
		

	
	<form id="formCustomer" name="formCustomer">
	
	Customer ID :
	<input id="idcustomer" name="idcustomer" type="text"
						class="form-control form-control-sm" readonly>
	
	Enter Your Name :
	<input id="cusname" name="cusname" type="text" 
							class="form-control form-control-sm">
	
	<br>Enter Your e-mail Address 
	<input id="cusemail" name="cusemail" type="text" 
							class="form-control form-control-sm">
	
	<br>Enter Your Number
	<input id="cusnumber" name="cusnumber" type="text" 
							class="form-control form-control-sm">
	
	<br>Enter Your EG Account Number
	<input id="cusacnumber" name="cusacnumber" type="text" 
							class="form-control form-control-sm">
	
	<br>Enter Your NIC Number 
	<input id="cusnicnumber" name="cusnicnumber" type="text"
							class="form-control form-control-sm">
	
	<br>Enter Your Password
	<input id="cuspassword" name="cuspassword" type="text"
							class="form-control form-control-sm">
							
	<br>		
	<input id="btnsave" name="btnsave" type="button" value="save"
							class="btn btn-primary">
							
	<input id="btnLogout" name="btnLogout" type="button" value="Logout"
							class="btn btn-danger">
							
	<input type="hidden" id="hidCusIDSave" 
 							name="hidCusIDSave" value="">
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	
	<br>
	<br>
	<div id="divCustomerGrid">
		<%
			customer cusObj=new customer();
			String uid=(String)session.getAttribute("userac");
			//out.print(cusObj.getAllCustomers());
			out.print(cusObj.getACustomerDetails(uid));
			
			
		%>
	
	</div>
</div>
</div>
</div>
	

</body>
</html>