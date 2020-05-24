package com.aleksic.medapp.controllers;

import com.aleksic.medapp.services.FileStoreService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@Data
@RequestMapping("/api/s3upload/{userId}/{healthcheckId}")
public class FileStoreController {
    private FileStoreService fileStoreService;

    @Autowired
    public FileStoreController(FileStoreService fileStoreService) {
        this.fileStoreService = fileStoreService;
    }

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> uploadHealthCheckReport(@PathVariable("userId")Integer userId,
                                                  @PathVariable("healthcheckId")Integer healthCheckId,
                                                  @RequestParam("file") MultipartFile file) {
       Map<String, String> storedS3File = fileStoreService.uploadHealthCheckReport(userId, healthCheckId, file);
       return new ResponseEntity(storedS3File, new HttpHeaders(), HttpStatus.OK);
    }
}
