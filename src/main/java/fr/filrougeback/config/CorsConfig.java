package fr.filrougeback.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${app.url}")
    private String appUrl;

    @Value("${app.local.url:http://localhost:5173}")
    private String localUrl;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        List<String> allowedOrigins = Arrays.asList(appUrl.split(","));

        if (!allowedOrigins.contains(localUrl)) {
            allowedOrigins.add(localUrl);
        }

        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins.toArray(new String[0]))
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}