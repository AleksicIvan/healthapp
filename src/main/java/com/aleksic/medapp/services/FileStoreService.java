package com.aleksic.medapp.services;

import com.aleksic.medapp.filestore.FileStore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Data
public class FileStoreService {
    @Autowired
    private FileStore fileStore;


    public void uploadHealthCheckReport(Integer userId, MultipartFile file) {
        // 1. Check if image is not empty
        // 2. The user exists in our DB
        // 3. Grab metadata from file if any
        // 4. Store the image in S3 and update DB (user's healthcheck with S3 file link)
    }
}
