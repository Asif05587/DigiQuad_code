package com.example.fileprocessor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@RestController
@RequestMapping("/api")
public class XmlToJsonController {

    @PostMapping("/convertXmlToJson")
    public ResponseEntity<String> convertXmlToJson(
            @RequestParam("file") MultipartFile file
    ) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            JsonNode jsonNode = xmlMapper.readTree(file.getInputStream());
            String jsonString = jsonNode.toPrettyString();

            return ResponseEntity.ok(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error converting XML to JSON");
        }
    }
}
