package org.shulgin.spring.rest.controller;

import org.shulgin.spring.rest.entity.Employee;
import org.shulgin.spring.rest.exception.NoSuchEmployeeException;
import org.shulgin.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);
        if(employee == null) {
            throw new NoSuchEmployeeException("There is no employee with id = " + id);
        }
        employeeService.deleteEmployee(id);
        return "Employee was deleted";
    }
}
