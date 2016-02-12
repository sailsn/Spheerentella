import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
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

			String StrQry2 = "SELECT * FROM `login` WHERE Email = '" + Email
					+ "' AND Password = '" + Pswd + "'";
			Boolean rs = stmt.execute(StrQry2);
			System.out.println(rs);
			
			if(Password == Password)
			{
				RequestDispatcher rd = request.getRequestDispatcher("/ChangePassword.jsp");
				rd.forward(request, response);
			}
			else
			{
				System.out.println("<font color='red'><b>You have entered incorrect password</b></font>");
			}
			
			// F
			// while (rs.next()) {
			// userName = rs.getString("Email");
			// pwd = rs.getString("Password");
			// }
			// System.out.println(userName);
			// System.out.println(pwd);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
}
