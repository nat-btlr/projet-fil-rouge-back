package fr.filrougeback.service;

import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BackBlazeService {
    private final S3Template s3Template;
    private final S3Client s3Client;

    @Value("${backblaze.b2.bucket-name}")
    private String bucketName;

    // A method to upload a new file to BB, maybe not needed in the future
    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = "videos/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
        s3Template.upload(bucketName, fileName, file.getInputStream());
        return s3Client.utilities().getUrl(b -> b.bucket(bucketName).key(fileName)).toString();
    }

    // A method to retrieve the link of the video to save it in the database
    public String getFileUrl(String fileName) {
        return String.format("https://%s.s3.%s.backblazeb2.com/%s",
                bucketName,
                s3Client.serviceClientConfiguration().region().toString(),
                fileName);
    }
}