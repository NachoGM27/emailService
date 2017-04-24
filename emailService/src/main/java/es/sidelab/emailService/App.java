package es.sidelab.emailService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class App {
	
	public static ExecutorService executor;

	public static void main(String[] args)
	{		
		executor = Executors.newFixedThreadPool(5); 
		SpringApplication.run(App.class, args);
	}
		
}
