package paperProject;
import java.sql.*;

import javax.swing.DefaultListModel;

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
			myConn = DriverManager.getConnection("jdbc:mysql://35.192.15.32/SENG300", "root", "password");
			myStmt = myConn.createStatement();
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
	
	//return [role, firstName, lastName]
	public String[] validateUser(String email, String password) {
		String[] ans = new String[3];
		try {
			PreparedStatement ps1 = myConn.prepareStatement("SELECT * FROM user WHERE email LIKE ? ");	
			ps1.setString(1, email);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()){
				if(password.equals(rs1.getString("pass"))){//password equals
					if(rs1.getString("role").equals("AUTHOR")){
						ans[0] = "AUTHOR";
						ans[1] = rs1.getString("firstName");
						ans[2] = rs1.getString("lastName");
						return ans;
					}else if(rs1.getString("role").equals("REVIEWER")) {
						ans[0] = "REVIEWER";
						ans[1] = rs1.getString("firstName");
						ans[2] = rs1.getString("lastName");
						return ans;
					}else if(rs1.getString("role").equals("ADMIN")) {
						ans[0] = "ADMIN";
						ans[1] = rs1.getString("firstName");
						ans[2] = rs1.getString("lastName");
						return ans;
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			ans[0] = "";
			return ans;
		}
		ans[0] = "";
		return ans;
	}
	
	public DefaultListModel<String> getReviewer(){
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		try{
			PreparedStatement ps1 = myConn.prepareStatement("SELECT * FROM user WHERE (role LIKE ?)");
			ps1.setString(1, "REVIEWER");
			ResultSet rs1 = ps1.executeQuery();
		
			while(rs1.next()){
				listModel.addElement(rs1.getString("lastName") + "," + rs1.getString("firstName"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return listModel;
	}
}
