package com.CSV.CsvReader.scheduler;

import com.CSV.CsvReader.model.Counter;
import com.CSV.CsvReader.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class CounterScheduler {


    @Autowired
    CounterService counterService;


    @Scheduled(cron = "${cron.expression.value}")
    public void scheduler(){
        String id = "66d1986e13b98d1b2b6ce1be";
        Optional<Counter> optionalCounter = counterService.findById(id);
        if (optionalCounter.isPresent()) {
            Counter counter = optionalCounter.get();
            counter.setCounter(counter.getCounter() + 1);
            counter.setDateTime(LocalDateTime.now());
            counterService.saveChanges(counter);
            System.out.println("Updated counter: " + counter);
        } else {
            System.out.println("No such value present with:" + id);
        }
    }

}
