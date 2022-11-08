package jdbc;
import java.sql.*;
public class gettingAll {
public static void main(String[] args)throws Exception{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","Immanuvel*27");
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery("select * from stud where age>20");
	while(rs.next()) {
		System.out.println(rs.getString(1)+" "+rs.getInt(2));
	}
	st.close();
	con.close();
}
}
