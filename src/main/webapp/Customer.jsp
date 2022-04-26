<%@ page import="model.customer.customerDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% 
	//Deletion of customer
	if(request.getParameter("cusid")!=null)
	{
		customerDetails cus= new customerDetails();
		
		String stsmsg = cus.deletecustomer(request.getParameter("cusid"));
		
		session.setAttribute("Statusmsg",stsmsg);
	}

	if(request.getParameter("cusid")!=null)
	{
		customerDetails cus=new customerDetails();
		
		boolean success = false;
		
		if(success==true)
		{
			response.sendRedirect("Customer.jsp");
		}
	}
%>    



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Home</title>
</head>
<body>
	
	<% 
		customerDetails cus= new customerDetails();
		String uid=(String)session.getAttribute("userid");
		out.print(cus.getACustomerDetails(uid));
	%>
	
		
</body>
</html>