package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
}
