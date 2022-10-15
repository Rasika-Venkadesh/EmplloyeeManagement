package com.ideas2it.employee.service;

import com.ideas2it.employee.customException.TraineeNotFoundException;
import com.ideas2it.employee.dto.TraineeDto;
import com.ideas2it.employee.model.Trainee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



public interface TraineeService {
    List<TraineeDto> getTrainees() ;

    TraineeDto getTraineeById(int traineeId) throws TraineeNotFoundException ;

    public abstract void removeTraineeDetails(int traineeId);

    List<Integer> validateAndAddTraineeDetails(TraineeDto traineeDto);

}