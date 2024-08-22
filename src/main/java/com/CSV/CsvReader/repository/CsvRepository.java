package com.CSV.CsvReader.repository;

import com.CSV.CsvReader.model.Csv;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CsvRepository extends MongoRepository<Csv, String> {
}
