package com;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Notice {
    //A common method to connect to the DB
	 private Connection connect() 
     { 
         Connection con = null; 
         try
     { 
         Class.forName("com.mysql.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName, username, password 
 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/noticedb", "root", ""); 
} 
    catch (Exception e) 
	        {e.printStackTrace();} 
    return con; 
    } 
	  
    public String addnotice(String noticID, String noticeName, String noticeDate,String noticeTime, String noticeDes) 
    { 
        String output = ""; 
            try
            { 
                Connection con = connect(); 
                if (con == null) 
                    {	
                        return "Error while connecting to the database for inserting.";
                    } 
                
                // create a prepared statement
                String query = "insert into  notice(`noticID`,`noticeName`,`noticeDate`,`noticeTime`,`noticeDes`)"
                + " values (?, ?, ?, ?, ?)"; 
                PreparedStatement preparedStmt = con.prepareStatement(query); 
                // binding values
                preparedStmt.setInt(1, 0); 
                preparedStmt.setString(2, noticeName); 
                preparedStmt.setString(3, noticeDate);
                preparedStmt.setString(4, noticeTime); 
                preparedStmt.setString(5, noticeDes); 
                
               // execute the statement
                preparedStmt.execute(); 
                con.close(); 
                //output = "New bill Create successfully"; 
                
                String newBills = readNotice();
                output = "{\"status\":\"success\", \"data\": \"" + 
           			 newBills + "\"}"; 
                
            } 
            catch (Exception e) 
            { 

            output = "{\"status\":\"error\", \"data\": \"Error while inserting the bill.\"}"; 
            System.err.println(e.getMessage()); 
            } 
        return output; 
    } 
   
    public String readNotice() 
    { 
    String output = ""; 
    try
    { 
        Connection con = connect(); 
        if (con == null) 
        {
        	return "Error while connecting to the database for reading."; } 
        // Prepare the html table to be displayed
        output = "<table border='1'><tr>"
        		+ "<th>Notice ID</th>"
        		+ "<th>Notice name</th>" +
                "<th>Notice Date</th>" + 
                "<th>Notice time</th>"+
                "<th>Description</th> </tr>"; 
        
        
        
        /**Use join query connect tree table */
        String query = "SELECT * FROM notice" ; 
        Statement stmt = con.createStatement(); 
        ResultSet rs = stmt.executeQuery(query); 
        // iterate through the rows in the result set
        while (rs.next()) 
        { 
            String noticID = rs.getString("noticID"); 
            String noticeName = rs.getString("noticeName"); 
            String noticeDate = rs.getString("noticeDate"); 
            String noticeTime  = rs.getString("noticeTime");
            String noticeDes = rs.getString("noticeDes"); 
         
           
            // Add into the html table
            output += "<tr><td>" + noticID + "</td>"; 
            output += "<td>" + noticeName + "</td>"; 
            output += "<td>" + noticeDate + "</td>";
            output += "<td>" + noticeTime + "</td>"; 
            output += "<td>" + noticeDes + "</td>"; 
          
 
            		 
            // buttons
        			output += "<td><input name='btnUpdate' "
        					+ "type='button' value='Update' "
        					+ " class='btnUpdate btn btn-secondary'></td>"
        					+ "<td><input name='btnRemove' "
        					+ "type='button' value='Remove' "
        					+ "class='btnRemove btn btn-danger' "
        					+ "data-id='"
        			 + noticID + "'>" + "</td></tr>";
        } 
        con.close(); 
        // Complete the html table
        output += "</table>"; 
    } 
	catch (Exception e) 
	{ 
		output = "Error while reading the notice."; 
		System.err.println(e.getMessage()); 
	} 
	return output; 
	} 
    
   
    
    
    
    public String updatenotice(String noticID, String noticeName, String noticeDate,
		    String noticeTime, String noticeDes )  
    
    { 
        String output = ""; 
    try
    { 
        Connection con = connect(); 
        if (con == null) 
    { 
	return "Error while connecting to the database for updating."; } 
    // create a prepared statement
    String query = "UPDATE notice SET noticeName=?,noticeDate=?"
    		+ ",noticeTime=?,noticeDes=? WHERE noticID=?"; 
    PreparedStatement preparedStmt = con.prepareStatement(query); 
        // binding values
    
    
    preparedStmt.setString(1, noticeName); 
    preparedStmt.setString(2, noticeDate);
    preparedStmt.setString(3, noticeTime); 
    preparedStmt.setString(4, noticeDes);  
    preparedStmt.setInt(5, Integer.parseInt(noticID));
    
       

    
    // execute the statement
    preparedStmt.execute(); 
    con.close(); 
    String newAppointment = readNotice();
  	output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}";
  }
  catch (Exception e)
  {
  	output = "{\"status\":\"error\", \"data\": \"Error while deleting the appointment details. \"}";
  	System.err.println(e.getMessage());
  }
        return output; 
    } 

    

    public String deletePayment(String noticID) 
    { 
	    String output = ""; 
	    try
	    { 
	    	Connection con = connect(); 
		    if (con == null) 
		    	{return "Error while connecting to the database for deleting."; } 
			    // create a prepared statement
			    String query = "delete from notice where noticID=?"; 
			    PreparedStatement preparedStmt = con.prepareStatement(query); 
			    // binding values
			    //preparedStmt.setString(1, paymentID);
			    preparedStmt.setInt(1, Integer.parseInt(noticID));
			    // execute the statement
			    preparedStmt.execute(); 
			    con.close(); 
			 
	   
    String newAppointment = readNotice();
	output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}";
}
catch (Exception e)
{
	output = "{\"status\":\"error\", \"data\": \"Error while deleting the appointment details. \"}";
	System.err.println(e.getMessage());
}

return output;
}

}

