package fr.filrougeback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LiquibaseConfig {

    @Bean
    public LiquibaseDelayRunner liquibaseDelay() {
        return new LiquibaseDelayRunner();
    }

    public static class LiquibaseDelayRunner {
        public LiquibaseDelayRunner() {
            try {
                System.out.println("Waiting for MySQL to be ready...");
                Thread.sleep(20000);
                System.out.println("MySQL should be ready now, starting Liquibase...");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Liquibase delay was interrupted");
            }
        }
    }
}