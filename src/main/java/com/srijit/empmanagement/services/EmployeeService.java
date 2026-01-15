package com.srijit.empmanagement.services;

import com.srijit.empmanagement.dtos.EmployeeRequest;
import com.srijit.empmanagement.dtos.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse create(EmployeeRequest dto);
    List<EmployeeResponse> getAll();
    EmployeeResponse getById(Long id);
    EmployeeResponse update(Long id, EmployeeRequest dto);
    void delete(Long id);
}
