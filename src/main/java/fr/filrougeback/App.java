package fr.filrougeback;

import fr.filrougeback.service.VideoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication(scanBasePackages = {
		"fr.filrougeback.controllers", 
		"fr.filrougeback.dto", 
		"fr.filrougeback.exceptions", 
		"fr.filrougeback.model",
		"fr.filrougeback.repository",
		"fr.filrougeback.security",
		"fr.filrougeback.service"})
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }

	@Bean
	public CommandLineRunner init(VideoService videoService) {
		return args -> {
			try {
				videoService.importStaticCsv();
			} catch (IOException e) {
				System.err.println("Error occurred while loading a CSV file: " + e.getMessage());
			}
		};
	}
}
