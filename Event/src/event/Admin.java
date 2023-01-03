package event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Admin extends Account{
	Scanner cin=new Scanner(System.in);
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
		do {System.out.println("\n1:Event details\t2:Staff details");
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
		System.out.println("Enter the Event price:");
		double price=cin.nextDouble();
		System.out.println("Enter the rating:");
		int r=cin.nextInt();

		try {
			Connection con=Main.connection.dbco();
			PreparedStatement stmt=con.prepareStatement("insert into events (ename,price,Rating) values (?,?,?)");
			
			stmt.setString(1, ename);
			stmt.setDouble(2, price);
			stmt.setInt(3, r);
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

	public void deleteEvents() {
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

	public void updateEvent() {
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



}
