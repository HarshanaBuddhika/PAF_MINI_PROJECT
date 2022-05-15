<%@ page import="com.Notice"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Notice Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Componets/jquery-3.2.1.min.js"></script>
<script src="Componets/notice.js"></script>

</head>
<body class="container">
	<div class="row my-3">
	 	<div class="col-md-12">
	
		<h1>Notice Management</h1>
		   <form id="formNotice" name="formNotice" method="post" action="notice.jsp">
				
			 	notice ID:
				<input id="noticID" name="noticID" type="text"
				 class="form-control form-control-sm" readonly>
				<br>
				  name:
				<input id="noticeName" name="noticeName" type="text"
				 class="form-control form-control-sm">
				<br>
				 notice Date:
				<input id="noticeDate" name="noticeDate" type="date"
				 class="form-control form-control-sm">
				<br> 
				<br>notice time:
				<input id="noticeTime" name="noticeTime" type="number"
				 class="form-control form-control-sm">
				<br>
				 Description:
				<input id="noticeDes" name="noticeDes" type="text"
				 class="form-control form-control-sm">
				<br>
				
				
				
				<input id="btnSave" name="btnSave" type="button" value="Save"
				 class="btn btn-primary">
				<input type="hidden" id="hideNoticeIDSave" name="hideNoticeIDSave" value="">
				
			  </form>	
		
			<div id="alertSuccess" class="alert alert-success"></div>
		    <div id="alertError" class="alert alert-danger"></div>
	
		</div>
	 
	 </div>


<div class="row my-3">
 
		<div class="col-md-12">
				<div id="divItemsGrid">
				 <%
				 Notice itemObj = new Notice();
				 		 		 		 out.print(itemObj.readNotice());
				 %>
				 </div>
		</div>
 
</div>

	
		<br> 
	

</body>
</html>