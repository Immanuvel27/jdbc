package event;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbconnection {
	public  Connection dbco() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/event","root","Immanuvel*27");
		  return con;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
