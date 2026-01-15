package com.srijit.empmanagement.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Employee extends BaseModel {

    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String department;
    private Double salary;
}
