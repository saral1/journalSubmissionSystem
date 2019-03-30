package journalSystem;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.DefaultListModel;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class dbConnection {
	/**
	* myConn the connection to database
	*/
	static Connection myConn;
	/**
	* mtStmt the statement
	*/
	static Statement myStmt;
	/**
	 * sets up connection to the database
	 */
	public dbConnection ()	{
		getConnection();
	}
	public void getConnection(){
		try{
			myConn = DriverManager.getConnection("jdbc:mysql://35.192.15.32/SENG300?autoReconnect=true&useSSL=false", "root", "password");
			myStmt = myConn.createStatement();
			System.out.println("Connected to google cloud SQL");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public boolean addReviewer(String email, String password, String first, String last) {
		try {
			String query = "INSERT INTO reviewerUnapproved (email, pass, firstName, lastName)"
					+ "VALUES(?, ?, ?, ?)";
			PreparedStatement insert = myConn.prepareStatement(query);
			insert.setString(1, email);
			insert.setString(2, password);
			insert.setString(3, first);
			insert.setString(4, last);
			insert.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean addAuthor(String email, String password, String first, String last) {
		try {
			String query = "INSERT INTO user (email, pass, role, firstName, lastName)"
					+ "VALUES(?, ?, ?, ?, ?)";
			PreparedStatement insert = myConn.prepareStatement(query);
			insert.setString(1, email);
			insert.setString(2, password);
			insert.setString(3, "AUTHOR");
			insert.setString(4, first);
			insert.setString(5, last);
			insert.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	public User validateUser(String email, String password) {
		User u = new User();
		try {
			PreparedStatement ps1 = myConn.prepareStatement("SELECT * FROM user WHERE email LIKE ? ");	
			ps1.setString(1, email);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()){
				if(password.equals(rs1.getString("pass"))){//password equals
					u.setEmail(rs1.getString("email"));
					u.setPass(rs1.getString("pass"));
					u.setRole(rs1.getString("role"));
					u.setFirstName(rs1.getString("firstName"));
					u.setLastName(rs1.getString("lastName"));
					return u;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	public DefaultListModel<String> getReviewer(){
//		DefaultListModel<String> listModel = new DefaultListModel<String>();
//		try{
//			PreparedStatement ps1 = myConn.prepareStatement("SELECT * FROM user WHERE (role LIKE ?)");
//			ps1.setString(1, "REVIEWER");
//			ResultSet rs1 = ps1.executeQuery();
//		
//			while(rs1.next()){
//				listModel.addElement(rs1.getString("lastName") + "," + rs1.getString("firstName"));
//			}
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
//		return listModel;
//	}
	
	//return set of reviewers
	public HashSet<User> getReviewer() {
		HashSet<User> reviewers = new HashSet<User>();
		try{
			PreparedStatement ps1 = myConn.prepareStatement("SELECT * FROM user WHERE (role LIKE ?)");
			ps1.setString(1, "REVIEWER");
			ResultSet rs1 = ps1.executeQuery();
		
			while(rs1.next()){
				User u = new User(rs1.getString("email"), rs1.getString("pass"), rs1.getString("role"), rs1.getString("firstName"), rs1.getString("lastName"));
				reviewers.add(u);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return reviewers;
	}
	
	public boolean submitPaper(User u, String title, File file) {
		try {
			//read pdf
			byte[] pdfData = new byte[(int) file.length()];
			DataInputStream dis = new DataInputStream(new FileInputStream(file));
			dis.readFully(pdfData);  // read from file into byte[] array
			dis.close();
			
			String query = "INSERT INTO submission (aEmail, title, submission, submitDate, paperStatus)"
					+ "VALUES(?, ?, ?, ?, ?)";
			PreparedStatement insert = myConn.prepareStatement(query);
			insert.setString(1, u.getEmail());
			insert.setString(2, title);
			insert.setBytes(3, pdfData);
			
			TodaysDate today = new TodaysDate();
			java.sql.Date sqlDate = new java.sql.Date(today.getDate().getTime()); 
			insert.setDate(4, sqlDate);
			insert.setString(5, "In-Process");
			insert.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean addAuthorReviewer(User u, HashSet<String> reviewers) {
		//delete all associated with this user first
		try {
			String query = "DELETE FROM authorReviewer WHERE aEmail = ?";
			PreparedStatement delete = myConn.prepareStatement(query);
			delete.setString(1, u.getEmail());
			delete.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		//add author reviewer conflicts
		for(String r: reviewers) {
			String[] r2 = r.split(":");
			//r2[1] is the reviewer's email
			try {
				String query = "INSERT INTO authorReviewer (aEmail, rEmail)"
						+ "VALUES(?, ?)";
				PreparedStatement insert = myConn.prepareStatement(query);
				insert.setString(1, u.getEmail());
				insert.setString(2, r2[1]);
				insert.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public boolean addAuthorConflict(User u, HashMap<String, String> reviewerReason) {
		return true;
	}
	
	public HashSet<Paper> getAuthorSubmission(User u){
		HashSet<Paper> set = new HashSet<Paper>();
		try{
			PreparedStatement ps1 = myConn.prepareStatement("SELECT * FROM submission WHERE (aEmail LIKE ?)");
			ps1.setString(1, u.getEmail());
			ResultSet rs1 = ps1.executeQuery();
		
			while(rs1.next()){
				Paper p = new Paper(rs1.getDate("submitDate"), rs1.getString("title"), rs1.getString("paperStatus"), rs1.getInt("sNum"));
				set.add(p);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return set;
	}
	
	public HashSet<User> getSuggestedReviewers(){
		HashSet<User> set = new HashSet<User>();
		return set;
	}
	
	public HashMap<User, String> getConflictReviewers(){
		HashMap<User, String> map = new HashMap<User, String>();
		//user is the key
		//reason is the value
		return map;
	}
	
	public HashSet<User> getUnapprovedReviewer(){
		HashSet<User> set = new HashSet<User>();
		try{
			PreparedStatement ps = myConn.prepareStatement("SELECT * from reviewerUnapproved");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("email"));
				User u = new User(rs.getString("email"), rs.getString("pass"), "REVIEWER", rs.getString("firstName"), rs.getString("lastName"));
				set.add(u);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println(set.size());
		return set;
	}
	
	public boolean approveReviewer(User u) {		
		try {
			//add to user database
			String query = "INSERT INTO user (email, pass, role, firstName, lastName)"
					+ "VALUES(?, ?, ?, ?, ?)";
			PreparedStatement insert = myConn.prepareStatement(query);
			insert.setString(1, u.getEmail());
			insert.setString(2, u.getPass());
			insert.setString(3, "REVIEWER");
			insert.setString(4, u.getfirstName());
			insert.setString(5, u.getLastName());
			insert.executeUpdate();
			
			//delete from reviewerUnapproved
			String query2 = "DELETE FROM reviewerUnapproved WHERE email = ?";
			PreparedStatement delete = myConn.prepareStatement(query2);
			delete.setString(1, u.getEmail());
			delete.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteSubmission(int sNum) {
		try {
			String query = "DELETE FROM submission WHERE sNum = ?";
			PreparedStatement delete = myConn.prepareStatement(query);
			delete.setInt(1, sNum);
			delete.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
