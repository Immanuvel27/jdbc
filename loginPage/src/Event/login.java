package Event;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.ha.backend.Sender;

import com.google.gson.Gson;


/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("application/json");
		PrintWriter out=response.getWriter();
		String a=request.getParameter("uname");
		String b=request.getParameter("pass");
//		System.out.println(a+" "+b);
	
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/event","root","Immanuvel*27");
			Statement st=con.createStatement();
			String result;
			ResultSet rs=st.executeQuery("select * from admin where email='"+a+"' and password='"+b+"' ");
			if(rs.next()) {
				/* System.out.println("true"); */
				/*
				 * user u=new user(); u.setUname(a); u.setPass(b); String uString=new
				 * Gson().toJson(u);
				 */
				/* out.write(uString); */
				result="Success";
	            
			}else {
				/* System.out.println("false"); */
				result="unsuccess";
			}
			
			 response.setContentType("text/plain");
	            out.print(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

}
