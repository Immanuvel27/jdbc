package event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Event {
	
	private String eventname;
	private Double price;
	private String date;
	
	
	public String getEventname() {
		return eventname;
	}
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	public void viewEvents() {
		try {
			Connection con=Main.connection.dbco();
			PreparedStatement stmt=con.prepareStatement("select * from events");
			ResultSet rs=stmt.executeQuery();
			System.out.println("ID\tEvent name\tEvent price\trating");
			
			while (rs.next()) {
				System.out.println("'"+rs.getInt("eid")+"'\t'"+rs.getString("ename")+"'\t'"+rs.getDouble("price")+"'\t'"+rs.getInt("rating")+"'");

			}
			
			stmt.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	

}
