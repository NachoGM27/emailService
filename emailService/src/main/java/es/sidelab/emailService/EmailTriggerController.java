package es.sidelab.emailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailTriggerController {
    
    @Autowired
    MailSender mailSender;

    @RequestMapping("/")
    public String triggerEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
                
        message.setSubject("Asunto");
        message.setText("Cuerpo");
        
        message.setTo("blocnow@outlook.com");
        message.setFrom("blocnow@outlook.com");
        
        try {
            mailSender.send(message);
            return "{\"OK\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"Error\"}";
        }
    }

}