<%@ page import="model.Bill_Management"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 
<%  

 //Delete item----------------------------------
if (request.getParameter("Bill_id") != null) 
{ 
Bill_Management BillObj = new Bill_Management(); 
String stsMsg = BillObj.deleteDetails(request.getParameter("Bill_id")); 
session.setAttribute("statusMsg", stsMsg); 
}
%> 
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Bill Details</h1>
 <%
Bill_Management itemObj = new Bill_Management();
out.print(itemObj.readDetails());
%>  

</body>
</html>