package com.CSV.CsvReader.service;

import com.CSV.CsvReader.model.Counter;
import com.CSV.CsvReader.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CounterService {

    @Autowired
    CounterRepository counterRepository;

    public Optional<Counter> findById(String id){
        return counterRepository.findById(id);
    }

    public Counter saveChanges(Counter counter){
        Counter counter1 = counterRepository.save(counter);
        System.out.println(counter1);
        return counter1;
    }


}
