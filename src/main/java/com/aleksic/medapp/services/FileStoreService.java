package com.aleksic.medapp.services;

import com.aleksic.medapp.bucket.BucketName;
import com.aleksic.medapp.filestore.FileStore;
import com.aleksic.medapp.models.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
public class FileStoreService {
    @Autowired
    private FileStore fileStore;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private HealthCheckService healthCheckService;

    @Autowired
    private ReportService reportService;

    public Map<String, String> uploadHealthCheckReport(Integer userId, Integer healthcheckId, MultipartFile file) {
        // 1. Check if image is not empty
        isFileEmpty(file);

        // 2. The user exists in our DB
        User user = getUserOrThrow(userId);

        // 3. Grab metadata from file if any
        Map<String, String> metadata = extractMetaData(file);

        // 4. Store the image in S3 and update DB (user's healthcheck with S3 file link)
        String path = String.format("%s/%s/%s", BucketName.HEALTHCHECK_BUCKET.getBucketName(), user.getId(), healthcheckId);
        String fileName = String.format("%s-%s", UUID.randomUUID(), file.getOriginalFilename());
        // https://medapp-healthcheck-12042020.s3.eu-central-1.amazonaws.com/3/1/pregled1.png - example
        String newReportFileName = String.format("https://%s.s3.eu-central-1.amazonaws.com/%s/%s/%s", BucketName.HEALTHCHECK_BUCKET.getBucketName(), user.getId(), healthcheckId, fileName);
        Map<String, String> s3UploadResponseMap = new HashMap<>();
        s3UploadResponseMap.put("s3FileUrl", newReportFileName);
        try {
            fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
            return s3UploadResponseMap;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private Map<String, String> extractMetaData(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private User getUserOrThrow(Integer userId) {
        return customerUserDetailsService
                .getAllUsers()
                .stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User not found"));
    }

    private void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Can not upload empty file");
        }
    }
}
