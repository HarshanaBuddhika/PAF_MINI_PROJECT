package com; 
import model.payment; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Payments") 
public class payment_management_service 
{ 
 payment itemObj = new payment(); 
@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readPayment() 
 { 
 return itemObj.readPayment(); 
}

@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertPayment(
 @FormParam("account_num") String account_num, 
 @FormParam("user_name") String user_name, 
 @FormParam("amount")String amount, 
 @FormParam("payment_desc") String payment_desc) 
{ 
 String output = itemObj.insertPayment(account_num, user_name, amount, payment_desc); 
return output; 
}


@PUT
@Path("/") 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN) 
public String updatePayment(String itemData) 
{ 
//Convert the input string to a JSON object 
 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
//Read the values from the JSON object
 String payment_ID = itemObject.get("payment_ID").getAsString(); 
 String account_num = itemObject.get("account_num").getAsString(); 
 String user_name = itemObject.get("user_name").getAsString(); 
 String amount = itemObject.get("amount").getAsString(); 
 String payment_desc = itemObject.get("payment_desc").getAsString(); 
 String output = itemObj.updatePayment(payment_ID, account_num, user_name, amount, payment_desc); 
return output; 
}

@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deletePayment(String itemData) 
{ 
//Convert the input string to an XML document
 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
 
//Read the value from the element <itemID>
 String payment_ID = doc.select("payment_ID").text(); 
 String output = itemObj.deletePayment(payment_ID); 
return output; 
}

}
