import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		Connection conn = null;
		Statement stmt = null;
		try {
			String FirstName = request.getParameter("FirstName");
			System.out.println(FirstName);
			String LastName = request.getParameter("LastName");
			System.out.println(LastName);
			String Email = request.getParameter("Email");
			System.out.println(Email);
			String PhoneNumber = request.getParameter("PhoneNumber");
			int Number = Integer.parseInt(PhoneNumber);
			System.out.println(Number);

			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to a Database");

			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/accounts", "root", "");

			stmt = conn.createStatement();

			String strQry = "INSERT INTO `accounts`.`registerdata` (`FirstName`, `LastName`, `Email`, `PhoneNumber`) VALUES ('"
					+ FirstName
					+ "', '"
					+ LastName
					+ "', '"
					+ Email
					+ "', '"
					+ Number + "')";

			stmt.executeUpdate(strQry);

			SendMail s = new SendMail();
			RandomPassword rp = new RandomPassword();
			String password=rp.getPassword();
			s.Sendto(Email,password);
			
			String strQry1 = "INSERT INTO `accounts`.`login` (`Email`, `Password`) VALUES ( '"+ Email+ "','"+password+"')";
      
			stmt.executeUpdate(strQry1);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
