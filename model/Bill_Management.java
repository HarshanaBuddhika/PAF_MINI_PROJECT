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
			
			public String insertDetails(String Account_Number, String days, String units, String Month, String Amount) 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for inserting."; } 
			 // create a prepared statement
			 String query = " insert into electrical_grid_system.user_bill(`Bill_id`,`User_Account_Number`,`No_of_days`,`No_of_units`,`Month`,`Amount`)" + " values (?, ?, ?, ?, ?, ?)"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, Account_Number); 
			 preparedStmt.setInt(3, Integer.parseInt(days)); 
			 preparedStmt.setDouble(4, Double.parseDouble(units)); 
			 preparedStmt.setString(5, Month); 
			 preparedStmt.setDouble(6, Double.parseDouble(Amount)); 
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
			 "<th>Month</th>" + "<th>Amount</th>" +
			 "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from electrical_grid_system.user_bill"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
			 String Bill_id = Integer.toString(rs.getInt("Bill_id")); 
			 String User_Account_Number = rs.getString("User_Account_Number"); 
			 String No_of_days = Integer.toString(rs.getInt("No_of_days")); 
			 String No_of_units = Double.toString(rs.getDouble("No_of_units")); 
			 String Month = rs.getString("Month"); 
			 String Amount = Double.toString(rs.getDouble("Amount")); 
			 // Add into the html table
			 output += "<tr><td>" + User_Account_Number + "</td>"; 
			 output += "<td>" + No_of_days + "</td>"; 
			 output += "<td>" + No_of_units + "</td>"; 
			 output += "<td>" + Month + "</td>"; 
			 output += "<td>" + Amount + "</td>";
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='Bill_Display.jsp'>"
			 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
			 + "<input name='Bill_id' type='hidden' value='" + Bill_id + "'>" + "</form></td></tr>"; 
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
			
			public String updateDetails(String BillID, String Account_Number, String days, String units, String Month, String Amount) 
			 
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
			 
			 String query = "UPDATE electrical_grid_system.user_bill SET User_Account_Number=?,No_of_days=?,No_of_units=?,Month=?,Amount=? WHERE Bill_id=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			 // binding values
			 preparedStmt.setString(1, Account_Number); 
			 preparedStmt.setInt(2, Integer.parseInt(days)); 
			 preparedStmt.setDouble(3, Double.parseDouble(units)); 
			 preparedStmt.setString(4, Month); 
			 preparedStmt.setInt(5, Integer.parseInt(BillID));
			 preparedStmt.setDouble(6, Double.parseDouble(Amount));
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Updated 45 successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while updating the details."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 }
			
			
			
			//delete method 
			
			public String deleteDetails(String Bill_id) 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for deleting."; } 
			 // create a prepared statement
			 String query = "delete from electrical_grid_system.user_bill where Bill_id=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(Bill_id)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Deleted successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while deleting the details."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 }
}	

