package es.sidelab.emailService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class App {
	
	public static ExecutorService executor;

	public static void main(String[] args)
	{
		
		executor = Executors.newFixedThreadPool(5); 
		SpringApplication.run(App.class, args);
		
		/*try {
			ServerSocket serverSocket = new ServerSocket(9999);
			
			while (true) {
				Socket socket = serverSocket.accept();
				try {
					BufferedReader brSocketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String email = brSocketIn.readLine();
					String asunto = brSocketIn.readLine();
					String cuerpo = brSocketIn.readLine();

		            Runnable emailSender = new SendEmailRunnable(email, asunto, cuerpo);  
		            executor.execute(emailSender);
					
					socket.close();
				} catch (Exception e) {
					socket.close();
					serverSocket.close();
			        executor.shutdown();
				}
			}
		} catch (Exception e){}
	*/
	}
		
	/*@Bean
	public RestTemplate resttemplate (RestTemplateBuilder builder){
		
	}*/
		
}
