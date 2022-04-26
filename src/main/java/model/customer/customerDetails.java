package model.customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class customerDetails {

	//Database Connection
	public Connection connect()
	{
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/paf","root", "Ilovesql@97");
			
			System.out.print("Connected Successfully");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	//Login validation
	public boolean login(String cusid,String cuspassword)
	{
		boolean validate = false;
		
		try
		{
			Connection con = connect();
			
			if(con==null)
			{
				System.out.print("Error while connecting to the database");
			}
			
			Statement st = con.createStatement();
			ResultSet rs;
			rs = st.executeQuery("select * from customer where cusaccountNo='" + cusid + "' and cuspassword='" + cuspassword + "'");
			 if (rs.next()) 
			 {
			     validate=true;
			 } 
			 else 
			 {
			      validate=false;
			 }
					
			
		}
		catch(Exception e)
		{
			System.out.println("Enter valide credentials to login");
		}
		return validate;
	}
	
	//Customer Insert
	public String insertcustomers(String name, String email, String number,String accnumber,String nicnumber,String password)
	{
		String output="";
		
		try
		{
			
			Connection con = connect();
			
			if(con == null)
			{
				return "Error while connection to the database";
			
			}
			
			String query = "insert into customer(`idcustomer`,`cusname`,`cusemail`,`cusnumber`,`cusaccountNo`,`cusnicno`,`cuspassword`)"
			+ "values(?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedstmt = con.prepareStatement(query);
			
			preparedstmt.setInt(1, 0);
			preparedstmt.setString(2, name);
			preparedstmt.setString(3, email);
			preparedstmt.setString(4, number);
			preparedstmt.setString(5, accnumber);
			preparedstmt.setString(6, nicnumber);
			preparedstmt.setString(7, password);
			
			
			preparedstmt.execute();
			con.close();
			
			output = "Inserted Successfully";
			
		}
		catch(Exception e)
		{
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}
	
	//Read all the customers form the database
	public String getAllCustomers()
	{
		String output="";
		
		try
		{
			Connection con= connect();
			
			if(con == null)
			{
				return "Error while connecting to the database";
			}
			
		//HTML Table for the display
			output = "<table border='1'><tr>"
					+ "<th>Customer IDe</th>"
					+"<th>Customer Name</th>"
					+ "<th>Customer email</th>"
					+ "<th>Customer Number</th>"
					+ "<th>Customer Acc Number</th>"
					+ "<th>Customer NIC Number</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
		
			String query = "select * from customer";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
		
			while(rs.next())
			{
				String cusid = Integer.toString(rs.getInt("idcustomer"));
				String cusname = rs.getString("cusname");
				String cusemail = rs.getString("cusemail");
				String cusnumber = rs.getString("cusnumber");
				String cusaccnum = rs.getString("cusaccountNo");
				String cusnic = rs.getString("cusnicno");
				
				//Add a row into the HTML table
				output += "<tr><td>" +cusid+ "</td>";
				output += "<td>" +cusname+ "</td>";
				output += "<td>" +cusemail+ "</td>";
				output += "<td>" +cusnumber+ "</td>";
				output += "<td>" +cusaccnum+ "</td>";
				output += "<td>" +cusnic+ "</td>";
			
				//buttons
				output += "<td><input name='btnUpdate' "
						   + " type='button' value='Update'></td>"
						   + "<td><form method='post' action='Customer.jsp'>"
						   + "<input name='btnRemove' "
						   + " type='submit' value='Remove'>"
						   + "<input name='cusid' type='hidden' "
						   + " value='" + cusid + "'>" + "</form></td></tr>";
			
			
			}
			
			con.close();
			
			output += "</table>";
		

			
		}
		catch (Exception e)
		{
			output = "Error while reading the customers details"; System.err.println(e.getMessage());
		}
		
		return output;
		
	}
	
	//Read A single customer details
	public String getACustomerDetails(String id)
	{
		String customer = "";
		
		try
		{
			Connection con=connect();
			
			if(con==null)
			{
				return "Error while connecting to the database";
				
			}
			
			//HTML Table for the display
			customer = "<table border='1'><tr>"
					+ "<th>Customer ID</th>"
					+"<th>Customer Name</th>"
					+ "<th>Customer email</th>"
					+ "<th>Customer Number</th>"
					+"<th>Customer Acc Number</th>"
					+"<th>Customer NIC No</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
		
			String query = "select * from customer where cusaccountNo ='"+id+"'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
		
			while(rs.next())
			{
				String cusid = Integer.toString(rs.getInt("idcustomer"));
				String cusname = rs.getString("cusname");
				String cusemail = rs.getString("cusemail");
				String cusnumber = rs.getString("cusnumber");
				String cusaccnum = rs.getString("cusaccountNo");
				String cusnic = rs.getString("cusnicno");
				
				
				//Add a row into the HTML table
				customer += "<tr><td>" +cusid+ "</td>";
				customer += "<td>" +cusname+ "</td>";
				customer += "<td>" +cusemail+ "</td>";
				customer += "<td>" +cusnumber+ "</td>";
				customer += "<td>" +cusaccnum+ "</td>";
				customer += "<td>" +cusnic+ "</td>";
				
			
				//buttons
				customer += "<td><form method='post' action='Update.jsp'>"
							+"<a href='Update.jsp'><input name='btnUpdate' "
						   + " type='submit' value='Update'>"
						   +"<input name='cusid' type='hidden'"
						   +" value='" + cusid + "'>" +"</a></form></td>"
						   + "<td><form method='post' action='Customer.jsp'>"
						   + "<input name='btnRemove' "
						   + " type='submit' value='Remove'>"
						   + "<input name='cusid' type='hidden' "
						   + " value='" + cusid + "'>" + "</form></td></tr>";
			
			
			}
			
			con.close();
			
			customer += "</table>";
		}
		catch(Exception e)
		{
			customer = "Error while reading the customer details"; System.err.println(e.getMessage());
		}
		
		return customer;
	}
	
	public String updatecustomer(String id,String name, String email, String number,String nicnumber,String password )
	{
		String output="";
					
		try 
		{
			Connection con=connect();
			
			if(con==null)
			{
				return "Error while connecting to the database";
				
			}
			
			String query = "update customer set cusname=?, cusemail=?, cusnumber=?, cusnicno=?, cuspassword=? where idcustomer=?";
			
			PreparedStatement preparedstmt = con.prepareStatement(query);
			
			
			preparedstmt.setString(1, name);
			preparedstmt.setString(2, email);
			preparedstmt.setString(3, number);
			preparedstmt.setString(4, nicnumber);
			preparedstmt.setString(5, password);
			preparedstmt.setInt(6, Integer.parseInt(id));
			
			
			preparedstmt.execute();
			con.close();
			
			output= "Error while updating";
			
		} 
		catch (SQLException e) 
		{
			output = "Error while updating";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	//Delete Customer
	public String deletecustomer(String cusid)
	{
		String output=null;
		
		try
		{
			Connection con = connect();
			
			if(con==null)
			{
				return "Error while connecting to tha database";
			}
			
			String query = "delete from customer where idcustomer=? ";
			
			PreparedStatement preparedstmt = con.prepareStatement(query);
			
			preparedstmt.setInt(1, Integer.parseInt(cusid));
				
			preparedstmt.execute();
			
			con.close();
			
			output = "Successfully deleted";
					
											
		}
		catch(Exception e)
		{
			output = "Error while deleting";
			System.err.println(e.getMessage());
		}
		return output;
		
	}
}
