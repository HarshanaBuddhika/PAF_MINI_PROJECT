package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Bill_Management;

public class Bill_Management_Service {
	@Path("/Bills")

	public class Bill_Management_Service{

		
		Bill_Management BillObj = new Bill_Management();
		
		
		@GET
		@Path("/") 
		@Produces(MediaType.TEXT_HTML) 
		public String readDetails() 
		 { 
		 return BillObj.readDetails(); 
		 } 
		
		
		
		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String insertDetails(@FormParam("User_Account_Number") String User_Account_Number, 
				@FormParam("No_of_days") String No_of_days, 
				@FormParam("No_of_units") String No_of_units, 
				@FormParam("Month") String Month,
				@FormParam("Amount") String Amount) 
		{ 
			String output = BillObj.insertDetails(User_Account_Number, No_of_days, No_of_units, Month, Amount); 
			return output; 
		}
		
		

		
		
		
		@PUT
		@Path("/") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String updateDetails(String BillData) 
		{ 
		//Convert the input string to a JSON object 
		 JsonObject Bill_Object = new JsonParser().parse(BillData).getAsJsonObject(); 
		 
		//Read the values from the JSON object
		 String BillID = Bill_Object.get("Bill_id").getAsString(); 
		 String AccountNumber = Bill_Object.get("User_Account_Number").getAsString(); 
		 String Days = Bill_Object.get("No_of_days").getAsString(); 
		 String Units = Bill_Object.get("No_of_units").getAsString(); 
		 String Month = Bill_Object.get("Month").getAsString(); 
		 String Amount = Bill_Object.get("Amount").getAsString();
		 
		 String output = BillObj.updateDetails(BillID, AccountNumber, Days,Units, Month, Amount); 
		 
		return output; 
		}
		
		

		@DELETE
		@Path("/") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String deleteDetails(String BillData) 
		{ 
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(BillData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		 String Bill_ID = doc.select("Bill_ID").text(); 
		 String output = BillObj.deleteDetails(Bill_ID); 
		return output; 
		}
		
}
