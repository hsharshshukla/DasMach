/*import , load- oracle driver,create connection,create sql query, execute sqlquery,processresult
 * Close 
 */
import java.sql.*;
public class jdbcdemo {
	public static void main(String args[])throws Exception{
		String url ="jdbc:oracle:thin:@rhel7:1521:OTDB";
		String uname ="sdtest";
		String pass ="sdtest";
		String query = "select userid from persons where username='Harsh'";



		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,uname,pass);
		Statement st = con.createStatement();
		st.executeQuery(query);
		ResultSet rs = st.executeQuery(query);
	    rs.next();
 		String name = rs.getString("username");
		System.out.println(name);
		st.close();
		con.close();

	}

}
