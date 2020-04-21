package com.aleksic.medapp.controllers;

import com.aleksic.medapp.services.FileStoreService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Data
@RequestMapping("/api/s3upload")
public class FileStoreController {
    private FileStoreService fileStoreService;

    @Autowired
    public FileStoreController(FileStoreService fileStoreService) {
        this.fileStoreService = fileStoreService;
    }

    @PostMapping(
            path = "{userId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadHealthCheckReport(@PathVariable("userId")Integer userId,
                                        @RequestParam("file") MultipartFile file) {
        fileStoreService.uploadHealthCheckReport(userId, file);
    }
}
