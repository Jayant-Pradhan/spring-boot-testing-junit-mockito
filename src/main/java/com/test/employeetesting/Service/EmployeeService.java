package com.test.employeetesting.Service;

import com.test.employeetesting.Entity.Employee;

import java.util.List;

public interface EmployeeService {

    String createEmployee(Employee employee);

    Employee getEmployeeById(Long id);

    List<Employee> getAllEmployees();

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);

}
