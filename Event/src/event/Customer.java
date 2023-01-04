package event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import org.json.JSONObject;

public class Customer extends Account {
	JSONObject object=new JSONObject();
	Scanner cin=new Scanner(System.in);
	private String name;
	private String uname;
	private String email;
	private String password;
	private String phone;
	private String address;


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public void register() {
		System.out.println("++++++++++++++++++++++++");
		System.out.println("+    Register Page     +");
		System.out.println("++++++++++++++++++++++++");
		String uname,name,email,pwd,phone,address;
		System.out.println("Enter the name:");
		name=cin.next();
		this.name=name;
		System.out.println("Enter the username:");
		uname=cin.next();
		this.uname=uname;
		System.out.println("Enter the email:");
		email=cin.next();
		this.email=email;
		System.out.println("Enter the password:");
		pwd=cin.next();
		this.password=pwd;
		System.out.println("Enter the phone:");
		phone=cin.next();
		this.phone=phone;
		System.out.println("Enter the address:");
		address=cin.next();
		this.address=address;
		
		object.put("name", this.name);
		object.put("uname", this.uname);
		object.put("email", this.email);
		object.put("password", this.password);
		object.put("phone", this.phone);
		object.put("address", this.address);
		
		object.toString();
		
		
		try {
			Connection con=Main.connection.dbco();
			PreparedStatement stmt=con.prepareStatement("insert ignore into user (name,uname,email,password,phone,address,usertype) values(?,?,?,?,?,?,?)");
			stmt.setString(1, object.getString("name"));
			stmt.setString(2, object.getString("uname"));
			stmt.setString(3, object.getString("email"));
			stmt.setString(4, object.getString("password"));
			stmt.setString(5, object.getString("phone"));
			stmt.setString(6, object.getString("address"));
			stmt.setString(7, "customer");
			int n=stmt.executeUpdate();
			if(n==1) {
			System.out.println("User registered succesfully\n");
			stmt.close();
			con.close();
			}else {
				System.out.println("user already exist");
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	

	@Override
	public boolean login() {
		boolean status=false;
		String UT="customer";
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
	public void logout() {
		Main.id=-1;
		login();
	}
	
void bookedsts() {
	try {
		Connection con=Main.connection.dbco();
		PreparedStatement stmt=con.prepareStatement("select message,ename,status from events e,booking b where e.eid=b.eventid and userid=?");
		stmt.setInt(1, Main.id);
		ResultSet rs=stmt.executeQuery();
		System.out.println("Event-name\tStatus\tMessage");
		while(rs.next()) {
			System.out.print(rs.getString("ename"));
			System.out.print("\t");
			System.out.print(rs.getString("status"));
			System.out.print("\t");
			System.out.println(rs.getString("message"));
			System.out.println();
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
}

}
