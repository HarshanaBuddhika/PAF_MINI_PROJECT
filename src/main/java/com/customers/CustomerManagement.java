package com.customers;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.customers.CustomerManagement;
//For JSON
import com.google.gson.*;

import model.customers.customerDetails;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/customerDetail")
public class CustomerManagement {
	
	customerDetails customerObj = new customerDetails();
	
	//Read the data from database
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomers()
	{
		return customerObj.getAllCustomers(); 
	}
	
	//Read single data from database
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String getaCustomer(String cstmid)
	{
		JsonObject customObj = new JsonParser().parse(cstmid).getAsJsonObject();
		
		String customerac = customObj.get("customerac").getAsString();
		
		String output=customerObj.getACustomerDetails(customerac);
		return output;
	}
	
	
	//Insert Data into Database
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String InsertCustomers(@FormParam("cusname") String cusname,
					@FormParam("cusemail") String cusemail,
					@FormParam("cusnumber") String cusnumber,
					@FormParam("cusacnumber") String cusacnumber,
					@FormParam("cusnicnumber") String cusnicnumber,
					@FormParam("cuspassword") String cuspassword)
	{
		String output = customerObj.insertcustomers(cusname, cusemail, cusnumber, cusacnumber, cusnicnumber, cuspassword);
		return output;
	}
	
	
	//Update customer from the database
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatecustomers(@FormParam("idcustomer") String idcustomer,
			@FormParam("cusname") String cusname,
			@FormParam("cusemail") String cusemail,
			@FormParam("cusnumber") String cusnumber,
			@FormParam("cusnicnumber") String cusnicnumber,
			@FormParam("cuspassword") String cuspassword)
	{
		String output=customerObj.updatecustomer(idcustomer, cusname, cusemail, cusnumber, cusnicnumber, cuspassword);
		return output;
	}
	
	
	
	//Delete data from database
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomers(@FormParam("cusid") String cusid)
	{
		String output=customerObj.deletecustomer(cusid);
		
		return output;
	}


}
