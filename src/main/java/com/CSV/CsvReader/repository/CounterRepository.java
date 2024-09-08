package com.CSV.CsvReader.repository;

import com.CSV.CsvReader.model.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends MongoRepository<Counter,String> {
}
