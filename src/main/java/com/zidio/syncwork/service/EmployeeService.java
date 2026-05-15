package com.zidio.syncwork.service;

import com.zidio.syncwork.entity.Employee;
import com.zidio.syncwork.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Sabhi employees ki list lene ke liye
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Naya employee save karne ke liye
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // ID se employee dhoondhne ke liye
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // Employee delete karne ke liye
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}