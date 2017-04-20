package es.sidelab.emailService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class MensajeController {
	
	@PostMapping(value = "/{id}")
	@ResponseBody
	public Email nuevoMensjae(@PathVariable("id") String id, @RequestBody Email mensaje){
		Email correo = new Email();
		correo.setEmail(mensaje.getEmail() + "servidor");
		
		Runnable emailSender = new SendEmailRunnable(mensaje.getEmail(), mensaje.getAsunto(), mensaje.getCuerpo());  
        App.executor.execute(emailSender);
		
		return correo;
	}
	
}
