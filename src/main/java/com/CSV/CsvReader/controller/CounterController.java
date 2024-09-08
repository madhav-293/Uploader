package com.CSV.CsvReader.controller;


import com.CSV.CsvReader.model.Counter;
import com.CSV.CsvReader.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CounterController {

    @Autowired
    CounterService counterService;

    @PostMapping("/post")
    public Counter postCounter(@RequestBody Counter counter){
        return counterService.saveChanges(counter);
    }


}
