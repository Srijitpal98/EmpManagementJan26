package com.srijit.empmanagement.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Employee extends BaseModel {

    private String name;
    private String email;
    private String department;
    private Double salary;
}
