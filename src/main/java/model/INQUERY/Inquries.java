package model.INQUERY;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Inquries {
	
	//DataBase Connection
	public Connection connect()
	{
		Connection con = null;

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3310/inquery_management","root", "Nel432156#");

			//For testing 
			System.out.print("Successfully connected");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return con;
	}
	
	//Insert Inquiry Details In To The inquery_management DataBase
	public String insertInquery(String inqueryCode, String inqueryCaption, String inqueryPerson, String inqueryDescription)
	{
		String output = "";

		try
		{
			Connection con = connect();

			if (con == null)
			{
				return "Error while connecting to the database";
			}

			// create a prepared statement 
			String query = " insert into inquery_details_display(`inquery_id`,`inquery_code`,`inquery_caption`,`inquery_person`,`inquery_description`)"
		+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values 
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, inqueryCode); 
			preparedStmt.setString(3, inqueryCaption);
			preparedStmt.setString(4, inqueryPerson); 
			preparedStmt.setString(5, inqueryDescription);

			//execute the statement 
			preparedStmt.execute(); 
			con.close();
			
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting"; 
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	//Read Inquires from the inquery_managemnt DataBase
	public String readInqueries()
	{
		String output = "";

		try
		{
			Connection con = connect();

			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}

			// Prepare the HTML table to be displayed
			output = "<table border='1'><tr><th>Inquery Code</th>"
				  +"<th>Item Caption</th>"
				  + "<th>Inquery Person</th>"
				  + "<th>Inquery Description</th>"
				  + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from inquery_details_display"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next())
			{
				String inqueryId = Integer.toString(rs.getInt("inquery_id")); 
				String inqueryCode = rs.getString("inquery_code");
				String inqueryCaption = rs.getString("inquery_caption");
				String inqueryPerson = rs.getString("inquery_person"); 
				String inqueryDescription = rs.getString("inquery_description");

				// Add a row into the HTML table
				output += "<tr><td>" + inqueryId + "</td>"; 
				output += "<td>" + inqueryCode + "</td>"; 
				output += "<td>" + inqueryCaption + "</td>";
				output += "<td>" + inqueryPerson + "</td>";
				output += "<td>" + inqueryDescription + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' "
					   + " type='button' value='Update'></td>"
					   + "<td><form method='post' action='InqueryDetails.jsp'>"
					   + "<input name='btnRemove' "
					   + " type='submit' value='Remove'>"
					   + "<input name='inqueryId' type='hidden' "
					   + " value='" + inqueryId + "'>" + "</form></td></tr>";
			}

			con.close();

			// Complete the HTML table 
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items."; System.err.println(e.getMessage());
		}

		return output;
	}	
	
	//Delete the Inquires from the DataBase
	public String deleteInquries(String inqueryId)
	{
		String output = "";

		try
		{
			Connection con = connect();

			if (con == null)
			{
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from inquery_details_display where inquery_id=?"; 
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			
			preparedStmt.setInt(1, Integer.parseInt(inqueryId));

			// execute the statement 
			preparedStmt.execute(); 
			con.close();

			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the item."; 
			System.err.println(e.getMessage());
		}

		return output;
	}	
	
	//Update the existing Inquiries in the DataBase
	public String updateInqueries(String inqueryId, String inqueryCode, String inqueryCaption, String inqueryPerson, String inqueryDescription)	 
	{
		String output = "";
 
		try
		{
			Connection con = connect();

			if (con == null)
			{
				return "Error while connecting to the database for updating."; 
			}

			// create a prepared statement
			String query = "UPDATE inquery_details_display SET inquery_code=?,inquery_caption=?,inquery_person=?,inquery_description=?WHERE inquery_id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values 
			preparedStmt.setString(1, inqueryCode); 
			preparedStmt.setString(2, inqueryCaption);
			preparedStmt.setString(3, inqueryPerson);
			preparedStmt.setString(4, inqueryDescription); 
			preparedStmt.setInt(5, Integer.parseInt(inqueryId));

			// execute the statement 
			preparedStmt.execute(); 
			con.close();

			output = "Updated successfully";
	     }
		 catch (Exception e)
		 {
			 output = "Error while updating the item."; 
			 System.err.println(e.getMessage());
		 }

		 return output;
	}
		
}
