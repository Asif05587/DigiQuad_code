package com.example.fileprocessor.controller;

import com.example.fileprocessor.model.FileData;
import com.example.fileprocessor.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/processFile")
    public ResponseEntity<List<FileData>> processFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "startRow", defaultValue = "0") int startRow
    ) {
        try {
            List<FileData> fileDataList = fileService.processFile(file, startRow);
            return ResponseEntity.ok(fileDataList);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
