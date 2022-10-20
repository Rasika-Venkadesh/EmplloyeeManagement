package com.ideas2it.employee.dto;

import com.ideas2it.employee.model.Trainee;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDto extends EmployeeDto{
    private int experience;
    private List<Integer> traineesId;
    private Set<Trainee> trainees;
    private List<String> traineeNames;
}
