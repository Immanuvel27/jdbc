package jdbc;
import java.sql.*;
public class insertOne {
public static void main(String[] args)throws Exception {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","Immanuvel*27");
	Statement st=con.createStatement();
	int count=st.executeUpdate("insert into stud values('gayathiri',23)");
	System.out.println(count+" rows affected");
	st.close();
	con.close();
	
	
}
}
