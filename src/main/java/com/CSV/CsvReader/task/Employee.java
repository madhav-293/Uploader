package com.CSV.CsvReader.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    int id;
    String name;
    int age;
    String department;
    double salary;

}
