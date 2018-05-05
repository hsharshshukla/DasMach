import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

	Connection con;
	PreparedStatement ps;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:OTDB","system","system");
		ps = con.prepareStatement("insert into studentinfo values(?,?,?,?,?)");
		
		}catch(ClassNotFoundException e){
		 e.printStackTrace();
		 }
		 catch(SQLException e){
			e.printStackTrace();
			}
		
		
	}
		/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String gender = request.getParameter("gender");
		String course [] = request.getParameterValues("course");
		String city = request.getParameter("city");
		
		int num=0;
		try{
			ps.setString(1,fname);
			ps.setString(2,lname);
			ps.setString(3,gender);
			
			String temp = null;
			for(String str:course){
				temp+=str;
				}
				
			ps.setString(4,temp);
			ps.setString(5,city);
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("<title> This is my RegServlet");
		out.print("</title>");
		out.print("</head>");
		out.print("<body>");
		
		if(num>0){
			out.print("<h1>Data Inserted Successfully</h1>");
		}
		else{
			out.print("<h1>Data Not Inserted </h1>");
		}
		out.print("Your First Name is : "+fname+"<br>");
		out.print("Your Last Name is : "+lname+"<br>");
		out.print("Your Gender is : "+gender+"<br>");
		out.print("Selected is : ");
		
		for (String str:course){
			out.print(" "+str);
		}
		out.print("<br>Your City is :"+city);
		
		out.print("</body>");
		out.print("/html");
		
	}
	
	@Override
	public void destroy(){
		try{
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	doGet(request,response);
}	
}