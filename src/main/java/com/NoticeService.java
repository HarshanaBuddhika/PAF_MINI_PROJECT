package com;
import model.Notice;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Notice")
public class NoticeService
{
 Notice noticeObj = new Notice();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readNotice()
 {
	return noticeObj.readNotice();
 }

@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertNotice(@FormParam("noticeName") String noticeName,
 @FormParam("noticeDate") String noticeDate,
 @FormParam("noticeTime") String noticeTime,
 @FormParam("noticeDesc") String noticeDesc)
{
 String output = noticeObj.insertNotice(noticeName, noticeDate, noticeTime, noticeDesc);
return output;
}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateNotice(String noticeData)
{
//Convert the input string to a JSON Object
 JsonObject noticeObject = new JsonParser().parse(noticeData).getAsJsonObject();
//Read the values from the JSON object
 String noticeID = noticeObject.get("noticeID").getAsString();
 String noticeName = noticeObject.get("noticeName").getAsString();
 String noticeDate = noticeObject.get("noticeDate").getAsString();
 String noticeTime = noticeObject.get("noticeTime").getAsString();
 String noticeDesc = noticeObject.get("noticeDesc").getAsString();
 String output = noticeObj.updateNotice(noticeID, noticeName, noticeDate, noticeTime, noticeDesc);
return output;
}

//Delete
@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteNotice(String noticeData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(noticeData, "", Parser.xmlParser());

//Read the value from the element <noticeID>
 String noticeID = doc.select("noticeID").text();
 String output = noticeObj.deleteNotice(noticeID);
return output;
}


}