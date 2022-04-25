package model;
import java.sql.*;
public class Notice
{ //A common method to connect to the Database
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName, username, password details 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/notice_db", "root", "");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }
public String insertNotice(String code, String name, String price, String desc)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 // create a prepared statement
 String query = " insert into notice(`noticeID`,`noticeName`,`noticeDate`,`noticeTime`,`noticeDesc`)"
 + " values (?, ?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, code);
 preparedStmt.setString(3, name);
 preparedStmt.setDouble(4, Double.parseDouble(price));
 preparedStmt.setString(5, desc);
 // execute the statement
 preparedStmt.execute();
 con.close();
 output = "Inserted successfully";
 }
 catch (Exception e)
 {
 output = "Error while inserting the notice.";
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
 {return "Error while connecting to the database for reading."; }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Notice Name</th><th>Notice Date</th>" +
 "<th>Notice Time</th>" +
 "<th>Notice Description</th>" +
 "<th>Update</th><th>Remove</th></tr>";

 String query = "select * from notice";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String noticeID = Integer.toString(rs.getInt("noticeID"));
 String noticeName = rs.getString("noticeName");
 String noticeDate = rs.getString("noticeDate");
 String noticeTime = Double.toString(rs.getDouble("noticeTime"));
 String noticeDesc = rs.getString("noticeDesc");
 // Add into the html table
 output += "<tr><td>" + noticeName + "</td>";
 output += "<td>" + noticeDate + "</td>";
 output += "<td>" + noticeTime + "</td>";
 output += "<td>" + noticeDesc + "</td>";
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
 + "<td><form method='post' action='notice.jsp'>"
 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='noticeID' type='hidden' value='" + noticeID
 + "'>" + "</form></td></tr>";
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
public String updateNotice(String ID, String name, String date, String time, String desc)

{
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE notice SET noticeName=?,noticeDate=?,noticeTime=?,noticeDesc=? WHERE noticeID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, name);
	 preparedStmt.setString(2, date);
	 preparedStmt.setString(3, time);
	 preparedStmt.setString(4, desc);
	 preparedStmt.setInt(5, Integer.parseInt(ID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the notice.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String deleteNotice(String noticeID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from notice where noticeID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(noticeID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the notice.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	} 