<%@ page import="model.customer.customerDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%
	 if(request.getParameter("cusname")!=null)
		{
			customerDetails cus=new customerDetails();
			
			String stsmsg = cus.insertcustomers(request.getParameter("cusname"),
							request.getParameter("cusemail"), 
							request.getParameter("cusnumber"),
							request.getParameter("cusacnumber"),
							request.getParameter("cusnicnumber"),
							request.getParameter("cuspassword"));
			
			if(stsmsg =="Inserted Successfully")
			{
				response.sendRedirect("Login.jsp");
			}
			
		}
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
</head>
<body>
	
	<form action="" method="post">
	Enter Your Name  <input type="text" name="cusname"><br>
	Enter Your e-mail Address <input type="text" name="cusemail"><br>
	Enter Your Number <input type="text" name="cusnumber"><br>
	Enter Your EG Account Number <input type="text" name="cusacnumber"><br>
	Enter Your NIC Number <input type="text" name="cusnicnumber"><br>
	Enter Your Password <input type="text" name="cuspassword"><br>
		
	<input type="submit" value="submit">
	</form>

</body>
</html>