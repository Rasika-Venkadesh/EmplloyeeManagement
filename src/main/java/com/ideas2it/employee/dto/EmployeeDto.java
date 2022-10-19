package com.ideas2it.employee.dto;

import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.model.Role;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private int employeeId;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfJoin;
    private String gender;
    private long phoneNumber;
    private String emailId;
    private double salary;
    private long aadharId;
    private String bloodGroup;
    private QualificationDto qualificationDto;
    private RoleDto roleDto;

}
