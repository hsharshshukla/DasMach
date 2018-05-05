

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/run")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	response.setContentType("text/html");
	PrintWriter out =  response.getWriter();
	
	try{
		Class.forName("oracle.jdbc.drive.OracleDriver");
		Connection con=  DriverManager.getConnection("jdbc:oracle:thin;@localhost:1521:otdb","system","oracle");
				
		System.out.println("Connection established"+con);
		out.println("Connection established"+con);
		
	}catch(Exception e){
		System.out.println(e);
	}
		
	}

}
