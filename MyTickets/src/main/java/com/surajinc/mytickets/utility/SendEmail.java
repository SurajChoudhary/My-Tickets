/**
 * 
 */
package com.surajinc.mytickets.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.surajinc.mytickets.form.BookMovieForm;



public class SendEmail {
	public static void sendEmail(String recipient, String firstName, BookMovieForm form ) {

		final String username = "myticketsmailer@gmail.com";
		final String password = "mytickets@mailer123";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String date=dateFormat.format(form.getShowtime().getDate());
		
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("myticketsmailer@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recipient));
			message.setSubject("Your Booking Confirmation-MyTickets.com");
			message.setText("Dear "+firstName+","
					+ "\n\n You just made a boooking using MyTicket.com!"
					+ "\n Theater name :"+form.getMovieShowing().getCinema().getName()
					+ "\n Movie name :"+form.getMovieShowing().getMovie().getName()
					+ "\n Show date :"+date
					+ "\n Showtime :"+ form.getShowtime().getShowStartTime()
					+ "\n Number of tickets :"+ form.getNumberOfTickets()
					+ "\n Theater address :"+form.getMovieShowing().getCinema().getAddress()
					+ "\n\n Enjoy your movie,"
					);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
