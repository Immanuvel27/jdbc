package Loginregister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	
	public static void Register(String name,String pass)throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","Immanuvel*27");
		Statement st=con.createStatement();
		int count=st.executeUpdate("insert into student values('"+name+"','"+pass+"')");
		if(count==1) {
			System.out.println("Registered successfully");
		}else if(count==0){
			System.out.println("Not Registered");
			
		}
		st.close();
		con.close();
		
	}
	
public static void Login(String name,String pass) throws Exception{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","Immanuvel*27");
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery("select * from student where name='"+name+"'&& pass='"+pass+"'");
//	rs.next();
	if(rs.next()) {
		System.out.println("Login sucessfull");
	}else {
		System.out.println("login unsuccesfull");
	}
	st.close();
	con.close();
	
}

	public static void main(String[] args) throws Exception   {
		Scanner cin=new Scanner(System.in);
		String name = null,pass=null;
		int check;
		System.out.println("Welcome to zoho incubation");
		do {
			System.out.println("1:Register     2:Login     3:Exit");
			System.out.println("Enter your choice");
		check=cin.nextInt();
			switch(check) {
			case 1:{
				String uname="",pwd="";
				System.out.println("Enter the username:");
				uname=cin.next();
				System.out.println("Enter the password:");
				pwd=cin.next();
				Register(uname, pwd);
			}
			break;
			case 2:{
				System.out.println("LOGIN");
				System.out.println("Enter the username");
				name=cin.next();
				System.out.println("Enter the password");
				pass=cin.next();
				Login(name, pass);
			}
			break;
			case 3:{
				System.out.println("thankyou!!!");
			}
			break;
			default:{
				System.out.println("Choose the correct option");
			}
			}
		}while(check!=3);
	}

}
