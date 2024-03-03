package com.example.fileprocessor.serviceimpl;

import com.example.fileprocessor.model.FileData;
import com.example.fileprocessor.service.FileService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public List<FileData> processFile(MultipartFile file, int startRow) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            List<FileData> jsonData;

            if (file.getOriginalFilename().endsWith(".csv")) {
                jsonData = processCSV(inputStream, startRow);
            } else if (file.getOriginalFilename().endsWith(".xlsx") || file.getOriginalFilename().endsWith(".xls")) {
                jsonData = processExcel(inputStream, startRow);
            } else {
                throw new IllegalArgumentException("Invalid file type");
            }

            return jsonData;
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    private List<FileData> processCSV(InputStream inputStream, int startRow) throws IOException, CsvException {
        List<FileData> jsonData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> rows = csvReader.readAll();

            for (int i = startRow; i < rows.size(); i++) {
                String[] row = rows.get(i);
                jsonData.add(convertArrayToFileData(row));
            }
        }

        return jsonData;
    }

    private List<FileData> processExcel(InputStream inputStream, int startRow) throws IOException {
        List<FileData> jsonData = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i < startRow; i++) {
                sheet.removeRow(sheet.getRow(i));
            }

            for (Row row : sheet) {
                jsonData.add(convertRowToFileData(row));
            }
        }

        return jsonData;
    }

    private FileData convertArrayToFileData(String[] array) {
        FileData fileData = new FileData();
        Map<String, String> data = new HashMap<>();

        char customKey = 'A';
        for (String value : array) {
            data.put(String.valueOf(customKey), value);
            customKey++;
        }

        fileData.setData(data);
        return fileData;
    }


    private FileData convertRowToFileData(Row row) {
        List<String> rowData = new ArrayList<>();
        for (Cell cell : row) {
            rowData.add(cell.toString());
        }
        return convertArrayToFileData(rowData.toArray(new String[0]));
    }
}
