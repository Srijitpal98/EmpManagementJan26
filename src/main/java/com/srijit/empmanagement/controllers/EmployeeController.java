package com.srijit.empmanagement.controllers;

import com.srijit.empmanagement.dtos.EmployeeRequest;
import com.srijit.empmanagement.dtos.EmployeeResponse;
import com.srijit.empmanagement.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Page<EmployeeResponse> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        return employeeService.getAll(page, size, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployee(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @PostMapping
    public EmployeeResponse createEmployee(@Valid @RequestBody EmployeeRequest dto) {
        return employeeService.create(dto);
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequest dto) {
        return employeeService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
    }

    @GetMapping("/search")
    public Page<EmployeeResponse> searchEmployees(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return employeeService.search(department, name, page, size, sortBy, sortDir);
    }
}
