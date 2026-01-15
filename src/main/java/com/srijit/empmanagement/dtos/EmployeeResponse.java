package com.srijit.empmanagement.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EmployeeResponse {
    private Long id;
    private String name;
    private String email;
    private String department;
    private Double salary;
}
