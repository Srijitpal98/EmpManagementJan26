package com.srijit.empmanagement.repositories;

import com.srijit.empmanagement.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findByDepartmentIgnoreCase(String department, Pageable pageable);

    Page<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Employee> findByDepartmentIgnoreCaseAndNameContainingIgnoreCase(
            String department, String name, Pageable pageable);
}
