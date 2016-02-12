import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangePassword extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection conn = null;
			Statement stmt = null;

		    String Password = request.getParameter("Old Password");
		    
		    String CPassword = request.getParameter("New Password");

			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to a Database");

			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/accounts", "root", "");
			stmt = conn.createStatement();

			String UpdateQuery = "UPDATE `login` SET `Password`= '" + CPassword
					+ "' WHERE `Password`= '" + Password + "' ";
			stmt.executeUpdate(UpdateQuery);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
