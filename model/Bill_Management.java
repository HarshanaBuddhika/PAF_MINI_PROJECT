package model;

import java.sql.Connection;
import java.sql.DriverManager;

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
}
