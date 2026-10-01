# Spring Boot Testing - JUnit & Mockito

## Project Overview

This project is a **Spring Boot Employee Management application** created to practice and implement automated testing using **JUnit 5, Mockito, MockMvc, and JaCoCo**.

The main focus of this project is understanding how to write unit tests and controller tests for a Spring Boot REST API.

## Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- JUnit 5
- Mockito
- MockMvc
- JaCoCo
- Maven

## Application Features

The application provides basic employee management operations:

- Create Employee
- Get Employee by ID
- Get All Employees
- Update Employee
- Delete Employee

## Testing

### Service Layer Testing

The service layer is tested using **JUnit 5 and Mockito**.

Mockito is used to mock the `EmployeeRepository`, allowing the service logic to be tested independently without connecting to the actual database.

Test cases include:

- Creating an employee
- Getting an employee by ID
- Handling employee-not-found scenarios
- Getting all employees
- Updating an employee
- Handling update failures
- Deleting an employee
- Handling delete failures

### Controller Layer Testing

The REST controller is tested using **Spring Boot `@WebMvcTest` and MockMvc**.

MockMvc is used to simulate HTTP requests without starting the complete application.

Tested endpoints include:

- `POST /employees`
- `GET /employees/{id}`
- `GET /employees`
- `PUT /employees/{id}`
- `DELETE /employees/{id}`

The controller tests verify HTTP response status codes and response bodies.

## JaCoCo Code Coverage

**JaCoCo** is used to measure test code coverage.

The project currently achieves:

**100% test coverage**

The JaCoCo report can be generated using Maven and is available at:

```text
target/site/jacoco/index.html
