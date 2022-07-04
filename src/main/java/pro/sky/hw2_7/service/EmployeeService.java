package pro.sky.hw2_7.service;


import org.springframework.stereotype.Service;
import pro.sky.hw2_7.exception.EmployeeAlreadyAddedException;
import pro.sky.hw2_7.exception.EmployeeNotFoundException;
import pro.sky.hw2_7.exception.EmployeeStoragelsFullException;
import pro.sky.hw2_7.model.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private static final int LIMIT = 10;

    private final List<Employee> employees = new ArrayList<>();

    public Employee add(String name, String surname) {
        Employee employee = new Employee(name, surname);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStoragelsFullException();
    }

    public Employee remove(String name, String surname) {
        Employee employee = new Employee(name, surname);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee);
        return employee;
    }

    public Employee find(String name, String surname){
        Employee employee = new Employee(name, surname);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }
}

