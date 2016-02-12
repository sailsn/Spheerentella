import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	public void Sendto(String to, String pwd) {

		String from = "testingtheis@gmail.com";

		String password = "lsnworks";

		// String to = "Email";

		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();// Getting the System
														// properties
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// properties.setProperty("mail.smtp.host", host);// Setup the mail
		// server.

		Session session = Session.getDefaultInstance(properties);// Get the
																	// Default
																	// session
																	// object

		try {
			MimeMessage message = new MimeMessage(session);// Create Default
															// MimeMessage Obj

			message.setFrom(new InternetAddress(from));// Set FROM:Header field
														// of the header.

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));// Set To:Header field of the header

			message.setSubject("Email Conformation");// setSubject:header
														// field

			message.setText("How is it");// setText:

			String Emailbody = "Hi,<br><br><p style='text-indent: 3em;'><a href='http://localhost:8080/ISSampleproject/Login.jsp'>Click this Link to activate your Account</a></p><br><br> UserName: "
					+ to
					+ "<br><br>Password:"
					+ pwd
					+ "<br><br>Thanks & Regards,<br><b>D.V.SAIKUMAR</b>";
			message.setContent(Emailbody, "text/html");

			Transport transport = session.getTransport("smtp");

			transport.connect(host, from, password);

			transport.sendMessage(message, message.getAllRecipients());

			transport.close();

			// transport.send(message);// sending the message

			System.out.println("Sent Message Successfully.....");
		} catch (MessagingException me) {
			me.printStackTrace();
		}

	}
}
