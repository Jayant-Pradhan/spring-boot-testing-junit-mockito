package com.test.employeetesting.Service;

import com.test.employeetesting.Entity.Employee;
import com.test.employeetesting.Exception.EmployeeNotFoundException;
import com.test.employeetesting.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository repository;

    @Override
    public String createEmployee(Employee employee) {
        repository.save(employee);
        return "employee created successfully";
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return  repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = repository.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());

        return repository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        if(!repository.existsById(id)){
            throw new EmployeeNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
