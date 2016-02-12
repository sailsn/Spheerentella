import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangePassword extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection conn = null;
			Statement stmt = null;

			String Email = request.getParameter("Email");
			// System.out.println(Email);

			String Password = request.getParameter("Password");
			int Pswd = Integer.parseInt(Password);
			// System.out.println(Pswd);

			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to a Database");

			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/accounts", "root", "");
			stmt = conn.createStatement();

			String UpdateQuery = "UPDATE `login` SET `Password`= '" + Pswd
					+ "' WHERE `Password`= '" + Pswd + "' ";
			stmt.executeUpdate(UpdateQuery);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
