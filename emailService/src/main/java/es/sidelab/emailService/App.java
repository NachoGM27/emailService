package es.sidelab.emailService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class App {

	public static void main(String[] args)
	{
		//SpringApplication.run(App.class, args)
		
		try {
			ServerSocket serverSocket = new ServerSocket(9999);
			while (true) {
				Socket socket = serverSocket.accept();
				try {
					System.out.println("Connected");
					BufferedReader brSocketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String email = brSocketIn.readLine();
					String asunto = brSocketIn.readLine();
					String cuerpo = brSocketIn.readLine();

					System.out.println("e: " + email + ", a: " + asunto + ", c: " + cuerpo);
					System.out.println("Ended");
					
					sendEmail(email, asunto, cuerpo);
					
					socket.close();
				} catch (Exception e) {
					socket.close();
					serverSocket.close();
				}
			}
		} catch (Exception e){}
	}
	
	public static void sendEmail(String email, String asunto, String cuerpo){
	    Properties props = System.getProperties();
	    props.put("mail.smtp.starttls.enable", true);
	    props.put("mail.smtp.host", "smtp.office365.com");
	    props.put("mail.smtp.user", "blocnow@outlook.com");
	    props.put("mail.smtp.password", "JorgeNacho_1234");
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", true);
	    
	    cuerpo+= " ";//+ "<a href=\"http://www.google.com\">Google</a>";

	    Session session = Session.getInstance(props,null);
	    MimeMessage message = new MimeMessage(session);

	    System.out.println("Port: "+session.getProperty("mail.smtp.port"));

	    // Create the email addresses involved
	    try {
	        InternetAddress from = new InternetAddress("blocnow@outlook.com");
	        message.setFrom(from);
	        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

	        // Create the html subject
	        message.setSubject(asunto);
	        
	        // Create a multi-part to combine the parts
	        Multipart multipart = new MimeMultipart("alternative");

	        // Create your text message part
	        /*BodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setText(cuerpo);

	        // Add the text part to the multipart
	        multipart.addBodyPart(messageBodyPart);*/

	        // Create the html part
	        BodyPart messageBodyPart = new MimeBodyPart();
	        String htmlMessage = cuerpo;
	        messageBodyPart.setContent(htmlMessage, "text/html");

	        // Add html part to multi part
	        multipart.addBodyPart(messageBodyPart);

	        // Associate multi-part with message
	        message.setContent(multipart);

	        // Send message
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.office365.com", "blocnow@outlook.com", "JorgeNacho_1234");
	        System.out.println("Transport: "+transport.toString());
	        transport.sendMessage(message, message.getAllRecipients());

	    } catch (Exception e) {}
	}
}
