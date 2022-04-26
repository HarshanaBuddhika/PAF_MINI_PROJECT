package com.INQUERYMANAGEMENT;

import model.INQUERY.Inquries;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/InqueriesManagement")
public class InqueryManagement
{
	Inquries inqueryObj = new Inquries();

	//Read the Data from the Database
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readInqueries()
	{
			return inqueryObj.readInqueries();
	}
	
	//Insert Data in to the Database
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN)
	public String insertInquery(@FormParam("inquery_code") String inquery_code,
						@FormParam("inquery_caption") String inquery_caption, 
						@FormParam("inquery_person") String inquery_person, 
						@FormParam("inquery_description") String inquery_description)
	{
		String output = inqueryObj.insertInquery(inquery_code, inquery_caption, inquery_person, inquery_description);
		return output;
	}
    
	//Update existing data in the Database.
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN)
	public String updateInqueries(String inqueryData)
	{
	//Convert the input string to a JSON object
	JsonObject inqueryObject = new JsonParser().parse(inqueryData).getAsJsonObject();

	//Read the values from the JSON object
	String inqueryId = inqueryObject.get("inquery_id").getAsString(); 
	String inqueryCode = inqueryObject.get("inquery_code").getAsString(); 
	String inqueryCaption = inqueryObject.get("inquery_caption").getAsString(); 
	String inqueryPerson = inqueryObject.get("inquery_person").getAsString(); 
	String inqueryDescription = inqueryObject.get("inquery_description").getAsString();

	String output = inqueryObj.updateInqueries(inqueryId, inqueryCode, inqueryCaption, inqueryPerson, inqueryDescription);

	return output;
	}

	//Delete data from Database
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInquries(String inqueryData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(inqueryData, "", Parser.xmlParser());

		//Read the value from the element <itemID> 
		String inqueryId = doc.select("inquery_id").text();

		String output = inqueryObj.deleteInquries(inqueryId);

		return output;
	}
	
}
