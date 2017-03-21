package es.sidelab.emailService;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmailRunnable implements Runnable{
	String email;
	String asunto;
	String cuerpo;
	
	public SendEmailRunnable(String email, String asunto, String cuerpo){
		this.email = email;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
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
