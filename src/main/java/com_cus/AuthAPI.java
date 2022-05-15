package com_cus;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AuthAPI")
public class AuthAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	customer cusObj=new customer();  
   
    public AuthAPI() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		String authStatus = cusObj.login(request.getParameter("userid"), request.getParameter("userpassword"));
		
		String output = "";
		
		if(authStatus.equals("success"))
		{
			output = "{\"status\":\"success\", \"data\": \"\"}";
			
			session.setAttribute("userac", request.getParameter("userid"));
		}
		else
		{
			output = "{\"status\":\"error\", \"data\": \"" + authStatus + "\" }";
		}
		
		response.getWriter().write(output);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
			 
		HttpSession session = request.getSession(); 
			
		session.invalidate(); 
			
		response.getWriter().write("success"); 
	}

}
