package com.example.fileprocessor.service;

import com.example.fileprocessor.model.FileData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<FileData> processFile(MultipartFile file, int startRow) throws IOException;
}
