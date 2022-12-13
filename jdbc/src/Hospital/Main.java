package Hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {


	public static void Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital","root","Immanuvel*27");
			Statement st=con.createStatement();
			st.executeUpdate("create table patient(id int AUTO_INCREMENT,name varchar(50),intime varchar(6),outtime varchar(6),total_time int,phone varchar(10),waitingtime varchar(40),doctortime varchar(30),PRIMARY KEY (id))");
			st.executeUpdate("create table statusmsg(id int AUTO_INCREMENT,message varchar(5),phone varchar(10),PRIMARY KEY (id))");
		} catch(Exception e)  {
			System.out.println(e);
		}
	}


	public static void show(int id) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital","root","Immanuvel*27");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from patient where id='"+id+"'");

		if(rs.next()) {
			System.out.println("Patient Id:        "+rs.getInt(1));
			System.out.println("Patient Name:      "+rs.getString(2));
			System.out.println("In-Time:           "+rs.getString(3));
			System.out.println("Out-Time:          "+rs.getString(4));
			System.out.println("Total time taken:  "+rs.getString(5));
			System.out.println("Phone Number:      "+rs.getString(6));
			//			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
		}else {
			System.out.println("Patient is not vailable");
		}
		ResultSet rr=st.executeQuery("select * from statusmsg where phone='"+rs.getString(6)+"' ");
		if(rr.next()) {
			System.out.println("Message:           "+rr.getString(2));
		}
		st.close();
		con.close();
	}



	public static void Register()throws Exception {
		Scanner cin=new Scanner(System.in);
		int id;
		String uname="",intime="",outtime="";
		int dt;
		System.out.println("Enter the id:");
		id=cin.nextInt();

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital","root","Immanuvel*27");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from patient where id='"+id+"'");
		if(rs.next()) {
			Statement st1=con.createStatement();
			Statement st2=con.createStatement();
			DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
			System.out.println("Patient already registered");
			System.out.println("Enter the In-time:");
			intime=cin.next();
			System.out.println("Enter the doctor-time:");
			dt=cin.nextInt();
			LocalTime lt = LocalTime.parse(intime);
			int timetaken=15+dt;
			outtime=df.format(lt.plusMinutes(timetaken));
			String sts="";
			if(timetaken-15>10) {
				sts="Sorry";
			}else if(timetaken-15==0) {
				sts="";
			}else {
				sts="";
			}
			int wt;
			if(dt-10<0) {
				wt=0;
			}else {
				wt=dt-10;
			}
			st1.executeUpdate("update patient set intime='"+intime+"',outtime='"+outtime+"',total_time='"+timetaken+"'"
					+ ",waitingtime='"+wt+"',doctortime='"+dt+"' where id='"+id+"' ");
			st2.executeUpdate("update statusmsg set message='"+sts+"'where phone='"+rs.getString(6)+"'");
			show(id);
		}else {
			DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");

			System.out.println("Enter the username:");
			uname=cin.next();
			System.out.println("Enter the phone number:");
			long ph=cin.nextLong();
			System.out.println("Enter the In-time:");
			intime=cin.next();
			System.out.println("Enter the doctor-time:");
			dt=cin.nextInt();
			LocalTime lt = LocalTime.parse(intime);
			int timetaken=15+dt;
			outtime=df.format(lt.plusMinutes(timetaken));
			String sts="";
			if(timetaken-15>10) {
				sts="Sorry";
			}else if(timetaken-15==0) {
				sts="";
			}else {
				sts="";
			}
			int wt;
			if(dt-10<0) {
				wt=0;
			}else {
				wt=dt-10;
			}
			st.executeUpdate("insert into patient (name,intime,outtime,total_time,phone,waitingtime,doctortime) values('"+uname+"','"+intime+"','"+outtime+"','"+timetaken+"','"+ph+"','"+wt+"','"+dt+"')");
			st.executeUpdate("insert into statusmsg(message,phone) values('"+sts+"','"+ph+"')");

		}

		st.close();
		con.close();
	}
	
	
	public static void showall()throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital","root","Immanuvel*27");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from patient");
		System.out.println("Patient table");
		System.out.println("-----------------------------------------------------------------------------------------------------------");
		System.out.println("|   id      name       in-time   out-time   total-time   doctor-time  waiting-time  phone-number          |");
		System.out.println("-----------------------------------------------------------------------------------------------------------");
		while(rs.next()) {
		System.out.println("    "+rs.getInt("id")+"      "+rs.getString("name")+"       "+rs.getString("intime")+""
				+ "    "+rs.getString("outtime")+"            "+rs.getString("total_time")+"    "
						+ "        "+rs.getString("doctortime")+"          "+rs.getString("waitingtime")+"  "
								+ "        "+rs.getString("phone"));	
		}
		System.out.println("------------------------------------------------------------------------------------------------------------");
	}



	public static void main(String[] args) throws Exception {
		Database();
		Scanner cin=new Scanner(System.in);
		String name = null,pass=null;
		int check;
		System.out.println("Welcome to zoho incubation");
		do {
			System.out.println("\n\n1:Register    2:Display    3:show_full_detail     4:Exit");
			System.out.println("Enter your choice");
			check=cin.nextInt();
			switch(check) {
			case 1:{

				Register();
			}
			break;
			case 2:{
				int id;
				System.out.println("Enter the patient id to display");
				id=cin.nextInt();

				show(id);
			}
			break;
			case 3:{
				showall();
			}
			break;

			case 4:{
				System.out.println("thankyou!!!");
			}
			break;
			default:{
				System.out.println("Choose the correct option");
			}
			}
		}while(check!=4);
	}

}
