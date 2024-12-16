package fr.filrougeback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
}
