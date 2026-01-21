package com.srijit.empmanagement.services;

import com.srijit.empmanagement.dtos.EmployeeRequest;
import com.srijit.empmanagement.dtos.EmployeeResponse;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    EmployeeResponse create(EmployeeRequest dto);
    Page<EmployeeResponse> getAll(int page, int size, String sortBy, String sortDir);
    EmployeeResponse getById(Long id);
    EmployeeResponse update(Long id, EmployeeRequest dto);
    void delete(Long id);
    Page<EmployeeResponse> search(String department, String name, int page, int size, String sortBy, String sortDir);
}
