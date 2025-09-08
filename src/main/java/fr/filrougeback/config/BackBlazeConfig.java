package fr.filrougeback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;

@Configuration
public class BackBlazeConfig {

    @Value("${backblaze.b2.application-key-id}")
    private String applicationKeyId;

    @Value("${backblaze.b2.application-key}")
    private String applicationKey;

    @Value("${backblaze.b2.region}")
    private String region;

    @Bean
    public S3Client s3Client() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(applicationKeyId, applicationKey);
        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);

        return S3Client.builder()
                .credentialsProvider(credentialsProvider)
                .region(Region.of(region))
                .endpointOverride(URI.create("https://s3." + region + ".backblazeb2.com"))
                .build();
    }
}