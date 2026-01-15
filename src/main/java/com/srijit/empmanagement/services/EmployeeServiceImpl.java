package com.srijit.empmanagement.services;

import com.srijit.empmanagement.dtos.EmployeeRequest;
import com.srijit.empmanagement.dtos.EmployeeResponse;
import com.srijit.empmanagement.exceptions.ResourceNotFoundException;
import com.srijit.empmanagement.models.Employee;
import com.srijit.empmanagement.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponse create(EmployeeRequest dto) {
        Employee employee = mapToEntity(dto);
        return mapToResponse(employeeRepository.save(employee));
    }

    @Override
    public List<EmployeeResponse> getAll() {
        return employeeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public EmployeeResponse getById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        return mapToResponse(employee);
    }

    @Override
    public EmployeeResponse update(Long id, EmployeeRequest dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());
        return mapToResponse(employeeRepository.save(employee));
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    private Employee mapToEntity(EmployeeRequest dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());
        return employee;
    }

    private EmployeeResponse mapToResponse(Employee employee) {
        EmployeeResponse dto = new EmployeeResponse();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());
        dto.setSalary(employee.getSalary());
        return dto;
    }
}
