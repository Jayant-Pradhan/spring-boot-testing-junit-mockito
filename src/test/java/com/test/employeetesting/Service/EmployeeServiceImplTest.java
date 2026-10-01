package com.test.employeetesting.Service;


import com.test.employeetesting.Entity.Employee;
import com.test.employeetesting.Exception.EmployeeNotFoundException;
import com.test.employeetesting.Repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private  EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl service;


    @Test
    public void createEmployee_ShouldCreateEmployee(){
        Employee employee = new Employee();
        when(repository.save(employee)).thenReturn(employee);

        String result = service.createEmployee(employee);

        assertEquals("employee created successfully" , result);

        verify(repository).save(employee);
    }

    @Test
    public void getEmployee_byEmployeeId(){
        Employee employee = new Employee();

        employee.setId(1L);
        employee.setDepartment("Engineering");
        employee.setEmail("rahul.sharma@gmail.com");
        employee.setName("Rahul Sharma");
        employee.setSalary(65000);

        when(repository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = service.getEmployeeById(1L);

        assertEquals(employee,result);
        verify(repository).findById(1L);
    }

    @Test
    public void getEmployeeById_WhenEmployeeNotFound_ShouldThrowException(){
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class , ()->service.getEmployeeById(99L));
    }

    @Test
    public void getAllEmployeeInTheDb(){
        Employee employee = new Employee();

        when(repository.findAll()).thenReturn(List.of(employee));

        List<Employee> result = service.getAllEmployees();

        assertEquals(List.of(employee), result);

        verify(repository).findAll();
    }

    @Test
    public void updateEmployeeInDb(){
        Employee employee = new Employee();

        employee.setId(2L);
        employee.setName("Jayant pradhan");
        employee.setSalary(20000);
        employee.setEmail("jayant2668@gmail.com");
        employee.setDepartment("IT");

        when(repository.findById(2L)).thenReturn(Optional.of(employee));
        when(repository.save(employee)).thenReturn(employee);

        Employee result = service.updateEmployee(2L,employee);

        assertEquals(employee,result);
        verify(repository).save(employee);

    }
    @Test
    public void updateEmployeeInDb_ExceptionHandling(){
        Employee employee = new Employee();
        when(repository.findById(88L)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class,()->service.updateEmployee(88L,employee));
        verify(repository,never()).save(employee);
    }

    @Test
    public void deleteEmployeeFromDb(){

        when(repository.existsById(2L)).thenReturn(true);

        service.deleteEmployee(2L);
        verify(repository).deleteById(2L);


    }

    @Test
    public void deleteEmployeeFromDb_ExceptionHandling(){
        when(repository.existsById(77L)).thenReturn(false);
        assertThrows(EmployeeNotFoundException.class,()->service.deleteEmployee(77L));
        verify(repository,never()).deleteById(77L);
    }

}
