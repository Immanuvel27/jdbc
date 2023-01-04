package event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Admin extends Account{
	Scanner cin=new Scanner(System.in);
	Eventspojo e=new Eventspojo();
	
	
	@Override
	boolean login() {
		boolean status=false;
		String UT="admin";
		String email,pwd;
		System.out.println("++++++++++++++++++++++++");
		System.out.println("+      Login Page      +");
		System.out.println("++++++++++++++++++++++++");
		System.out.println("Enter the email:");
		email=cin.next();
		System.out.println("Enter the password:");
		pwd=cin.next();

		try {
			Connection con=Main.connection.dbco();
			PreparedStatement stmt=con.prepareStatement("select * from user where email=? and password=?and usertype=?");
			stmt.setString(1, email);
			stmt.setString(2, pwd);
			stmt.setString(3, UT);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()) {
				Main.id=rs.getInt("id");
				status=true;
			}


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return(status);
	}

	@Override
	void logout() {
		// TODO Auto-generated method stub
		Main.id=0;
		login();

	}

	public void manage() {
		System.out.println("ACCOUNT: ADMIN");
		int ch;
		do {System.out.println("\n1:Event details\t2:Staff details\t3:Booking details");
		System.out.println("Enter your choice:");
		
		ch=cin.nextInt();
		switch (ch) {
		case 1:
		{
			System.out.println("\nEvent Details\n");
			
			int chh;

			do {System.out.println("\n1:Insert Events\t\t2:View Events\t3:Delete events\t4:update Event");
			System.out.println("Enter your choice:");
			chh=cin.nextInt();
			switch (chh) {
			case 1:
				createEvents();
				break;
			case 2:
				viewEvents();
				break;
			case 3:
				deleteEvents();
				break;
			case 4:
				updateEvent();
				break;

			default:
				break;
			}
			} while (chh!=0);
		}

		break;
		case 2:{
			int choice;
			do {
			System.out.println("Staff details");
			System.out.println("1:Insert-staff\t2:delete-staff\t3:view-staff\t0:Exit");
			System.out.println("Enter your choice:");
			choice=cin.nextInt();
			switch (choice) {
			case 1:
				addStaff();
				break;
			case 2:
				deleteStaff();
				break;
			case 3:
				viewStaff();
				break;

			default:
				break;
			}
			
		} while (choice!=0);

		}
		break;
		case 3:{
			System.out.println("Bookings\n");
			System.out.println("Approve bookings");
			approveBooking();
			
		}
			break;

		default:
			break;
		}
		} while (ch!=0);
		
	}
	//inserting new events
	  private void createEvents() {
		viewEvents();
		System.out.println("\nInsert new events");
		System.out.println("Enter the Event name:");
		String ename=cin.next();
		e.setEname(ename);
		System.out.println("Enter the Event price:");
		double price=cin.nextDouble();
		e.setPrice(price);
		System.out.println("Enter the rating:");
		int r=cin.nextInt();
		e.setRating(r);

		try {
			Connection con=Main.connection.dbco();
			PreparedStatement stmt=con.prepareStatement("insert into events (ename,price,Rating) values (?,?,?)");
			
			stmt.setString(1, e.getEname());
			stmt.setDouble(2, e.getPrice());
			stmt.setInt(3, e.getRating());
			int n=stmt.executeUpdate();
			if(n!=0) {
				System.out.println("Inserted Successfully");
			}else {
				System.out.println("Not inserted");
			}
			stmt.close();
			con.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	//View events
	 void viewEvents() {
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

	private void deleteEvents() {
		viewEvents();
		int id;
		System.out.println("Delete Events");
		System.out.println("Enter the EventName to delete:");
		id=cin.nextInt();

		try {
			Connection con=Main.connection.dbco();
			PreparedStatement stmt=con.prepareStatement("delete from events where eid=?");
			stmt.setInt(1, id);
			int n=stmt.executeUpdate();
			if(n!=0) {
				System.out.println("Deleted Successfully");
			}else {
				System.out.println("No records deleted");
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	
	//update events
	private void updateEvent() {
		viewEvents();
		int id;
		System.out.println("Update Event");
		System.out.println("Enter the id to update");
		id=cin.nextInt();
		String en;
		double p;
		int s;

		try {
			Connection con=Main.connection.dbco();
			System.out.println("Enter the event name:");
			en=cin.next();
			System.out.println("Enter the price");
			p=cin.nextDouble();
			System.out.println("Enter the rating");
			s=cin.nextInt();

			PreparedStatement stmt=con.prepareStatement("UPDATE events SET ename = ?, price = ?, Rating = ? WHERE (eid = ?);");
			stmt.setString(1, en);
			stmt.setDouble(2, p);
			stmt.setInt(3,s);
			stmt.setInt(4, id);
			int n=stmt.executeUpdate();
			if(n!=0) {
				System.out.println(n+" Records updated");
			}else {
				System.out.println("no records updated");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


	}
	
	void bookedevents(int id) {
		try {
			Connection con=Main.connection.dbco();
			PreparedStatement stmt=con.prepareStatement("select venue,date,userid,status from booking where userid=?");
			stmt.setInt(1,id);
			ResultSet rs=stmt.executeQuery();
			
			System.out.println("venue\t\tdate\t\tuserid\t\tstatus");
			while (rs.next()) {
//				System.out.println("'"+rs.getString("venue")+"'\t'"+rs.getString("date")+"'\t'"+rs.getInt(userid)+"'\t'"+rs.getString("status")+"'");
				System.out.print(rs.getString("venue"));
				System.out.print("\t");
				System.out.print(rs.getString("date"));
				System.out.print("\t");
				System.out.print(rs.getInt("userid"));
				System.out.print("\t");
				System.out.print(rs.getString("status"));
				System.out.println();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}
	
	void reqevents() {
		try {
			Connection con=Main.connection.dbco();
			PreparedStatement stmt=con.prepareStatement("select bid,venue,date,userid,status from booking where status=?");
			stmt.setString(1, "Requested");
			ResultSet rs=stmt.executeQuery();
			
			System.out.println("id\tvenue\t\tdate\t\tuserid\t\tstatus");
			while (rs.next()) {
				System.out.println("'"+rs.getInt("bid")+"'\t'"+rs.getString("venue")+"'\t'"+rs.getString("date")+"'\t'"+rs.getInt("userid")+"'\t'"+rs.getString("status")+"'");

			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}
	
	
	private void addStaff() {
		String name,phno;
		int eid;
		System.out.println("Enter the name:");
		name=cin.next();
		System.out.println("Enter the ph no:");
		phno=cin.next();
		System.out.println("Enter the event id:");
		eid=cin.nextInt();
		
		try {
			Connection con=Main.connection.dbco();
			PreparedStatement stmt=con.prepareStatement("insert into manager(name,eventid,phno) values(?,?,?)");
			stmt.setString(1, name);
			stmt.setInt(2, eid);
			stmt.setString(3, phno);
			int n=stmt.executeUpdate();
			if(n!=0) {
				System.out.println("Successfully inserted");
			}else {
				System.out.println("Not inserted");
			}
			
			stmt.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void viewStaff() {
		try {
			Connection con=Main.connection.dbco();
			PreparedStatement stmt=con.prepareStatement("select * from manager");
			ResultSet rs=stmt.executeQuery();
			System.out.println("Id\tName\tphoneno");
			while (rs.next()) {
				System.out.println(rs.getInt("mid")+"\t"+rs.getString("name")+"\t"+rs.getString("phno"));
				
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void deleteStaff() {
		viewStaff();
		int id;
		System.out.println("\nEnter the id to delete:");
		id=cin.nextInt();
		try {
			Connection con=Main.connection.dbco();
			PreparedStatement stmt=con.prepareStatement("delete from manager where mid=?");
			stmt.setInt(1,id);
			int n=stmt.executeUpdate();
			if(n!=0) {
				System.out.println("Deleted successfully");
			}else {
				System.out.println("Nothing Deleted");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void approveBooking() {
		reqevents();
		int id;
		System.out.println("enter the booking id to approve");
		id=cin.nextInt();
		
		try {
			Connection con=Main.connection.dbco();
			PreparedStatement stmt=con.prepareStatement("select eventid from booking where bid=?");
			stmt.setInt(1, id);
			ResultSet rs=stmt.executeQuery();
			rs.next();
			PreparedStatement s=con.prepareStatement("select * from manager where eventid=?");
			s.setInt(1, rs.getInt("eventid"));
			ResultSet rs1=s.executeQuery();
			rs1.next();
			PreparedStatement st=con.prepareStatement("UPDATE booking SET status = ?,message=? WHERE bid = ?");
			st.setString(1, "Approved");
			st.setString(2,rs1.getString("name")+" is assigned "+rs1.getString("phno"));
			st.setInt(3, id);
			int n=st.executeUpdate();
			if(n!=0) {
				System.out.println("Approved");
			}else {
				System.out.println("Declined");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}



}
