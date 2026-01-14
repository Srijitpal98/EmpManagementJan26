package com.srijit.empmanagement.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping
    public String getEmployee() {
        return "Employee API accessed successfully";
    }

    @PostMapping
    public String createEmployee() {
        return "Employee created successfully";
    }
}
