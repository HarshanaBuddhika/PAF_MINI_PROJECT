<%@page import="model.INQUERY.Inquries"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if (request.getParameter("inqueryCode") != null) 
    { 
        Inquery inqueryObj = new Inquery(); 
        String stsMsg = ""; 
        //Insert--------------------------
        if (request.getParameter("hidInqueryIDSave") == "") 
        { 
            stsMsg = inqueryObj.insertInquery(request.getParameter("inqueryCode"), 
            request.getParameter("inqueryName"), 
            request.getParameter("inqueryCaption"), 
            request.getParameter("inqueryPerson")); 
            request.getParameter("inqueryDescription")); 
        } 

        else
        //Update----------------------
        { 
            stsMsg = inqueryObj.updateInquery(request.getParameter("hidInqueryIDSave"), 
            request.getParameter("inqueryCode"), 
            request.getParameter("inqueryName"), 
            request.getParameter("inqueryCaption"), 
            request.getParameter("inqueryPerson")); 
            request.getParameter("inqueryDescription")); 

        } 
        session.setAttribute("statusMsg", stsMsg); 
        } 

        //Delete-----------------------------
        if (request.getParameter("hidInqueryIDSave") != null) 
        { 
            Inquery inqueryObj = new Inquery(); 
            String stsMsg = 
            inqueryObj.deleteInquery(request.getParameter("hidInqueryIDSave")); 
            session.setAttribute("statusMsg", stsMsg); 
        }

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inquery Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Inquery_Management.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Inquery Management V10.1</h1>
<form id="formInquery" name="formInquery">
 Inquery code: 
 <input id="inqueryCode" name="inqueryCode" type="text" 
 class="form-control form-control-sm">
 <br> Inquery name: 
 <input id="inqueryName" name="inqueryName" type="text" 
 class="form-control form-control-sm">
 <br> Inquery price: 
 <input id="inqueryCaption" name="inqueryCaption" type="text" 
 class="form-control form-control-sm">
 <br> Inquery description: 
 <input id="inqueryDescription" name="inqueryDescription" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidInqueryIDSave" 
 name="hidInqueryIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Inquery inqueryObj = new Inquery(); 
 out.print(inqueryObj.readInquery()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
