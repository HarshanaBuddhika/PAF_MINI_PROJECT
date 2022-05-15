package com_cus;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;


@WebServlet("/customerServlet")
public class customerAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	customer cusObj=new customer();
    
    public customerAPI() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = cusObj.insertcustomers(request.getParameter("cusname"),
											request.getParameter("cusemail"),
											request.getParameter("cusnumber"),
											request.getParameter("cusacnumber"),
											request.getParameter("cusnicnumber"),
											request.getParameter("cuspassword"));
		
		response.getWriter().write(output);
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		Map<String, String> paras = getParasMap(request);
		
		String output= cusObj.updatecustomer(paras.get("hidCusIDSave").toString(),
											paras.get("cusname").toString(),
											paras.get("cusemail").toString(),
											paras.get("cusnumber").toString(),
											paras.get("cusnicnumber").toString(),
											paras.get("cuspassword").toString());
		response.getWriter().write(output);
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> paras = getParasMap(request);
		
		String output = cusObj.deletecustomer(paras.get("idcustomer").toString());
		
		response.getWriter().write(output);
	}
	
private static Map getParasMap(HttpServletRequest request) 
{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	
	 try
	 { 
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
		 String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : ""; 
		 scanner.close();
		 
		 String[] params = queryString.split("&"); 
		 for (String param : params) 
		 {
			 String[] p = param.split("="); 
			 map.put(p[0], p[1]); 
		} 
	} 
	catch (Exception e) 
	{ 
	} 
	return map; 
}

}


