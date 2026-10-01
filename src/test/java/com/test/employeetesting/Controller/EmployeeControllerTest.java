package com.test.employeetesting.Controller;

import com.test.employeetesting.Entity.Employee;
import com.test.employeetesting.Service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import tools.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.*;

@WebMvcTest
public class EmployeeControllerTest {

    @MockitoBean
    private EmployeeService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createEmployee_ControllerTesting() throws Exception {
        Employee employee = new Employee(100L,"Saroj kumar ","saroj2668@gmail.com" ,"python" , 20000);
        ObjectMapper mapper = new ObjectMapper();
        String employeeJson = mapper.writeValueAsString(employee);
        when(service.createEmployee(any(Employee.class))).thenReturn("employee created successfully");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                                                                        .post("/employees")
                                                                        .contentType(MediaType.APPLICATION_JSON)
                                                                        .content(employeeJson);
        ResultActions perform = mockMvc.perform(requestBuilder);
        int status = perform.andReturn().getResponse().getStatus();
        String response = perform.andReturn().getResponse().getContentAsString();
        assertEquals("employee created successfully",response);
        assertEquals(201,status);
    }

    @Test
    public void getById_ControllerTesting() throws Exception{
        Employee employee = new Employee();
        employee.setId(2L);
        when(service.getEmployeeById(2L)).thenReturn(employee);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees/2");
        ResultActions perform = mockMvc.perform(requestBuilder);
        int status = perform.andReturn().getResponse().getStatus();
        assertEquals(200 ,status);
    }
    @Test
    public void getAllEmployee_ControllerTesting()throws Exception{
        Employee employee = new Employee();

        when(service.getAllEmployees()).thenReturn(List.of(employee));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees");
        ResultActions perform = mockMvc.perform(requestBuilder);
        int status = perform.andReturn().getResponse().getStatus();
        assertEquals(200,status);

    }

    @Test
    public void updateEmployee_ControllerTesting() throws Exception{
        Employee employee = new Employee(4L,"sneha reddy","snehareddy23@gmail.com","MBA",45000);
        ObjectMapper mapper = new ObjectMapper();

        String employeeJson = mapper.writeValueAsString(employee);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/employees/4")
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(employeeJson);

        ResultActions perform = mockMvc.perform(requestBuilder);
        int status = perform.andReturn().getResponse().getStatus();
        assertEquals(200,status);


    }

    @Test
    public void deleteEmployee_ControllerTesting() throws Exception{
       doNothing().when(service).deleteEmployee(2L);
       MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/employees/2");
       ResultActions  perform = mockMvc.perform(requestBuilder);
       int status = perform.andReturn().getResponse().getStatus();
       assertEquals(204,status);
    }

}
