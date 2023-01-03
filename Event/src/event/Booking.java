package event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Booking{
	Scanner cin=new Scanner(System.in);
	public String venue;
	public String date;

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	public void booking(int cid,int eid){
		System.out.println("Enter the venue:");
		this.venue=cin.nextLine();
		System.out.println("Enter the date(DD:MM:YYYY):");
		this.date=cin.next();
		try {
			Connection con=Main.connection.dbco();
			PreparedStatement stmt=con.prepareStatement("insert into booking (venue,date,userid,eventid) values (?,?,?,?)");
			stmt.setString(1, this.venue);
			stmt.setString(2,this.date);
			stmt.setInt(3,cid);
			stmt.setInt(4,eid);
			int n=stmt.executeUpdate();
			
			if(n!=0) {
				System.out.println("Requested wait for response");
			}else {
				System.out.println("Declined");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
public void cancelEvents(int cid,int eid) {
	
	try {
		Connection con=Main.connection.dbco();
		PreparedStatement stmt=con.prepareStatement("delete from booking where userid=? and eventid=?");
		stmt.setInt(1,cid);
		stmt.setInt(2,eid);
		int n=stmt.executeUpdate();
		
		if(n!=0) {
			System.out.println("Canceled booking");
		}else {
			System.out.println("Declined");
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
		
	}
	
}
