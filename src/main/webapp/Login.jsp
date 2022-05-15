<%@page import="com_cus.customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script type="text/javascript" src="Components/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="Components/auth.js"> </script>
</head>
<body>

<div class="container">
<div class="row">
	<div class="col-8">
		
		<h1 class="m-3">User Login</h1>
		
		<form id="formLogin" name="formLogin">
		
		<!-- NAME -->
		<div class="input-group input-group-sm mb-3">
 				<div class="input-group-prepend">
 				<span class="input-group-text" id="lblName">Account Number: </span>
 				</div>
 				<input type="text" name="userid" id="userid" required>
		 </div>
		
		<!-- Password -->
		<div class="input-group input-group-sm mb-3">
 				<div class="input-group-prepend">
 				<span class="input-group-text" id="lblName">Password: </span>
 				</div>
 				<input type="password" name="userpassword" id="userpassword">
		 </div>
		 
		 <div id="alertSuccess" class="alert alert-success"></div>
		 <div id="alertError" class="alert alert-danger"></div>
				 
		 <input id="btnLogin" name="btnLogin" type="button" value="Login" class="btn btn-primary">
			
		</form>
	
	</div>
</div>
</div>	

<br>
	
<div class="container">
	<div class="row">
		<div class="col-8">
			<a href="Registration.jsp"><button class="btn btn-primary">Create A Account</button></a> 
		</div>
	</div>
</div>
	

</body>
</html>