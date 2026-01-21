package com.srijit.empmanagement.services;

import com.srijit.empmanagement.dtos.EmployeeRequest;
import com.srijit.empmanagement.dtos.EmployeeResponse;
import com.srijit.empmanagement.exceptions.DuplicateResourceException;
import com.srijit.empmanagement.exceptions.ResourceNotFoundException;
import com.srijit.empmanagement.models.Employee;
import com.srijit.empmanagement.repositories.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponse create(EmployeeRequest dto) {
        if(employeeRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException(
                    "Employee with Email " + dto.getEmail() + " already exists");
        }

        Employee employee = mapToEntity(dto);
        return mapToResponse(employeeRepository.save(employee));
    }

    @Override
    public Page<EmployeeResponse> getAll(int page, int size, String sortBy, String sortDir) {
        // Pagination and sorting logic to be implemented
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return employeeRepository.findAll(pageRequest)
                .map(this::mapToResponse);
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

        if(!employee.getEmail().equals(dto.getEmail())
                && employeeRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException(
                    "Employee with email " + dto.getEmail() + " already exists"
            );
        }

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

    @Override
    public Page<EmployeeResponse> search(String department, String name, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Employee> result;

        if (department != null && name != null) {
            result = employeeRepository.findByDepartmentIgnoreCaseAndNameContainingIgnoreCase(department, name, pageable);
        } else if (department != null) {
            result = employeeRepository.findByDepartmentIgnoreCase(department, pageable);
        } else if (name != null) {
            result = employeeRepository.findByNameContainingIgnoreCase(name, pageable);
        } else {
            result = employeeRepository.findAll(pageable);
        }

        return result.map(this::mapToResponse);
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
