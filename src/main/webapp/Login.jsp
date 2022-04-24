<%@ page import="model.customer.customerDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%
	//Customer Login
	String userid=request.getParameter("userid");
	customerDetails cus=new customerDetails();
	boolean valide = cus.login(request.getParameter("userid"),request.getParameter("userpassword"));
	
	if(valide==false)
	{
		String statusMsg="Enter valide credentials";
		session.setAttribute("Statusmsg",statusMsg);
		
	}
	else
	{
		
		session.setAttribute("userid",userid);
		response.sendRedirect("Customer.jsp");
	}
	
%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	
	<form action="" method="post">
	User ID  <input type="text" name="userid"><br>
	Password<input type="password" name="userpassword"><br>
	<input type="submit" value="login" name="login">
	</form>
	
	<%
		out.print(session.getAttribute("statusMsg"));
	%>
	
	
	<br>
	
	
	<a href="Registration.jsp"><button>Create A Account</button></a> 
	
	

</body>
</html>