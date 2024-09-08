package com.CSV.CsvReader.task;

import ch.qos.logback.core.model.conditional.ElseModel;

import java.util.*;
import java.util.stream.Collectors;

public class DayTask {


    public static void main(String args[]){
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1, "Alice Smith", 30, "HR", 50000));
        employees.add(new Employee(2, "Bob Johnson", 40, "Engineering", 70000));
        employees.add(new Employee(3, "Charlie Brown", 25, "Marketing", 45000));
        employees.add(new Employee(4, "Diana Prince", 35, "Finance", 60000));
        employees.add(new Employee(5, "Evelyn Carter", 29, "Engineering", 72000));
        employees.add(new Employee(6, "Franklin Lee", 32, "Marketing", 48000));
        employees.add(new Employee(7, "Grace Miller", 45, "HR", 52000));
        employees.add(new Employee(8, "Henry Davis", 38, "Finance", 63000));
        employees.add(new Employee(9, "Iris Wilson", 27, "Engineering", 69000));
        employees.add(new Employee(10, "Jack Turner", 50, "Marketing", 47000));
        employees.add(new Employee(11, "Karen Adams", 33, "HR", 51000));
        employees.add(new Employee(12, "Liam Scott", 41, "Finance", 65000));
        employees.add(new Employee(13, "Mia Johnson", 26, "Engineering", 70000));
        employees.add(new Employee(14, "Nathan King", 37, "Marketing", 46000));
        employees.add(new Employee(15, "Olivia White", 31, "Finance", 62000));
        employees.add(new Employee(16, "Paul Robinson", 39, "HR", 53000));
        employees.add(new Employee(17, "Quinn Taylor", 28, "Engineering", 71000));
        employees.add(new Employee(18, "Rachel Lewis", 34, "Marketing", 48000));
        employees.add(new Employee(19, "Samuel Clark", 44, "Finance", 64000));
        employees.add(new Employee(20, "Tina Young", 30, "HR", 50000));

//        System.out.println(filterByAge(employees));
//        System.out.println(avgSalaryBasedOnDepartment(employees));
//        System.out.println(maxSalaryEmpInDep(employees));
        System.out.println(sortedEmpInDep(employees));
    }

    public static List<Employee> filterByAge(List<Employee> employees){
        return employees.stream().filter(employee -> employee.age>30).collect(Collectors.toCollection(ArrayList::new));
    }

    public static Map<String, Double> avgSalaryBasedOnDepartment(List<Employee> employees){
        List<Employee>  filteredEmployee = employees.stream().filter(employee -> employee.age<=30).collect(Collectors.toCollection(ArrayList::new));
        return filteredEmployee.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));

    }

    public static Map<String,Employee> maxSalaryEmpInDep(List<Employee> employees){

        return  employees.stream().collect(Collectors.groupingBy(employee -> employee.department,Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(e-> e.salary)), Optional::get)));
    }

    public static Map<String,List<Employee>> sortedEmpInDep(List<Employee> employees){
        Map<String, List<Employee>> groupedByDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        groupedByDept.forEach((dept, empList) -> empList.sort((e1, e2) -> e1.getName().compareTo(e2.getName())));
        return groupedByDept;
    }



}
