package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill_Management {

	//A common method to connect to the DB
	
			private Connection connect() 
			 { 
			 Connection con = null; 
			 try
			 { 
			 Class.forName("com.mysql.jdbc.Driver"); 
			 
			 //Provide the correct details: DBServer/DBName, username, password 
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrical_grid_system","root","1997"); 
			 } 
			 catch (Exception e) 
			 {e.printStackTrace();} 
			 return con; 
			 } 
			
			
			//insert method
			
			public String insertDetails(String Account_Number, String days, String units, String Month) 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for inserting."; } 
			 // create a prepared statement
			 String query = " insert into electrical_grid_system.user_bill(`Bill_id`,`User_Account_Number`,`No_of_days`,`No_of_units`,`Month`)" + " values (?, ?, ?, ?, ?)"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, Account_Number); 
			 preparedStmt.setString(3, days); 
			 preparedStmt.setDouble(4, Double.parseDouble(units)); 
			 preparedStmt.setString(5, Month); 
			 // execute the statement
			 
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Inserted successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while inserting the details."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 
			
			
			//Read method
			
			public String readDetails() 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for reading."; } 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Account Number</th><th>No of Days</th>" +
			 "<th>No of Units</th>" + 
			 "<th>Month</th>" +
			 "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from electrical_grid_system.user_bill"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
			 String BillID = Integer.toString(rs.getInt("Bill_id")); 
			 String AccountNumber = rs.getString("User_Account_Number"); 
			 String Days = rs.getString("No_of_days"); 
			 String Units = Double.toString(rs.getDouble("No_of_units")); 
			 String Month = rs.getString("Month"); 
			 // Add into the html table
			 output += "<tr><td>" + AccountNumber + "</td>"; 
			 output += "<td>" + Days + "</td>"; 
			 output += "<td>" + Units + "</td>"; 
			 output += "<td>" + Month + "</td>"; 
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='Bill.jsp'>"
			 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
			 + "<input name='BillID' type='hidden' value='" + BillID + "'>" + "</form></td></tr>"; 
			 } 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while reading the details."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 
			
			
			// update method 
			
			public String updateDetails(String BillID, String AccountNumber, String Days, String Units, String Month) 
			 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; }
			 
			  // create a prepared statement
			 
			 String query = "UPDATE electrical_grid_system.user_bill SET User_Account_Number=?,No_of_days=?,No_of_units=?,Month=? WHERE Bill_id=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			 // binding values
			 preparedStmt.setString(1, AccountNumber); 
			 preparedStmt.setString(2, Days); 
			 preparedStmt.setDouble(3, Double.parseDouble(Units)); 
			 preparedStmt.setString(4, Month); 
			 preparedStmt.setInt(5, Integer.parseInt(BillID)); 
			 
			
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Updated successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while updating the details."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 }
			
			
}
