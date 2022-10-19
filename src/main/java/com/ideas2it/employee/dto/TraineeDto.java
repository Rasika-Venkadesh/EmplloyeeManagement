package com.ideas2it.employee.dto;

import com.ideas2it.employee.model.Trainer;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TraineeDto extends EmployeeDto{
    private int trainingPeriod;
    private Set<Trainer> trainers;
    private List<Integer> trainersId;
    private List<String> trainerNames;

}
