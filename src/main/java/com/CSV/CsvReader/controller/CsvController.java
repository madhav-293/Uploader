package com.CSV.CsvReader.controller;


import com.CSV.CsvReader.dto.FileDetail;
import com.CSV.CsvReader.model.Csv;
import com.CSV.CsvReader.service.CsvService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/csv")
public class CsvController {

    private final XmlMapper xmlMapper = new XmlMapper();

    @Autowired
    CsvService csvService;

    @PostMapping("/post")
    public List<Csv> uploadCSV(@RequestParam("file") MultipartFile file) throws IOException {
        // Call the service method to process the file
        return csvService.postCsv(file);
    }

    @GetMapping("/getAll")
    public List<Csv> getCsv(){
        return csvService.getAll();
    }


    @PostMapping("/postXML")
    public List<Csv> uploadXML(@RequestParam("file") MultipartFile file) {

        return csvService.uploadXMl(file);
    }

    @GetMapping("/downloadCSVById")
    public ResponseEntity<Resource> getFile(@RequestParam("id") String id){
        String fileName = "user.csv";
        InputStreamResource file = new InputStreamResource(csvService.load(id));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

}
