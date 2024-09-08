package com.CSV.CsvReader.service;


import com.CSV.CsvReader.dto.Records;
import com.CSV.CsvReader.model.Csv;
import com.CSV.CsvReader.repository.CsvRepository;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CsvService {

    @Autowired
    CsvRepository csvRepository;

    private final XmlMapper xmlMapper = new XmlMapper();

    public List<Csv> postCsv(MultipartFile file) throws IOException {
        List<Csv> csvList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
             CSVReader csvReader = new CSVReader(reader)) {

            List<String[]> records = csvReader.readAll();
            for (int i = 0; i < records.size(); i++) {
                String[] record = records.get(i);
                if (i == 0) continue;

                String name = (record[0] != null && !record[0].trim().isEmpty()) ? record[0].trim() : "Unknown";
                String email = (record[1] != null && !record[1].trim().isEmpty()) ? record[1].trim() : "Unknown";
                String designation = (record[2] != null && !record[2].trim().isEmpty()) ? record[2].trim() : "Unknown";

                Csv csv = new Csv();
                csv.setName(name);
                csv.setEmail(email);
                csv.setDesignation(designation);
                csvList.add(csv);
            }
            return csvRepository.saveAll(csvList);

        } catch (CsvException e) {
            throw new RuntimeException("Error processing CSV file", e);
        }
    }



    public List<Csv> getAll(int page, int size, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Csv> resultPage = csvRepository.findAll(pageable);
        return resultPage.getContent();
    }

    public List<Csv> uploadXMl(MultipartFile file) {

        try{
            Records records = xmlMapper.readValue(file.getInputStream(), Records.class);
            List<Csv> csvList = records.getCsv();
            return csvRepository.saveAll(csvList);

//xslt
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public InputStream load(String id) {
        Optional<Csv> csv = csvRepository.findById(id);
        if(csv.isPresent()){
            Csv csv1  = csv.get();
            try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                 CSVPrinter printer = new CSVPrinter(new OutputStreamWriter(out), CSVFormat.DEFAULT.withHeader("Name", "Email", "Designation"))) {

                printer.printRecord(csv1.getName(), csv1.getEmail(), csv1.getDesignation());
                printer.flush();

                return new ByteArrayInputStream(out.toByteArray());

            } catch (IOException e) {
                throw new RuntimeException("Error generating CSV", e);
            }
        }


        return null;
    }
}
