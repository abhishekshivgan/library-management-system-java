package Library;

import java.sql.*;

public class ConnectionClass {
	Connection con;
	Statement stn;
	
	ConnectionClass() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "Abhishek@1234");
			stn = con.createStatement();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ConnectionClass();
	}
}
