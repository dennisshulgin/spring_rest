package org.shulgin.spring.rest.controller;

import org.shulgin.spring.rest.entity.Employee;
import org.shulgin.spring.rest.exception.EmployeeIncorrectData;
import org.shulgin.spring.rest.exception.NoSuchEmployeeException;
import org.shulgin.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee showEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);
        if(employee == null)
            throw new NoSuchEmployeeException("There is not employee with id = " + id);
        return employeeService.getEmployeeById(id);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> exceptionHandler(
            NoSuchEmployeeException exception) {
        EmployeeIncorrectData employeeIncorrectData = new EmployeeIncorrectData();
        employeeIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(employeeIncorrectData, HttpStatus.NOT_FOUND);
    }
}
