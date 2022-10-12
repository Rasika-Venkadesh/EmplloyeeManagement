package com.ideas2it.employee.service.interf;

import com.ideas2it.employee.customException.BadRequest;
import com.ideas2it.employee.model.Trainee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class TraineeServiceInterf {
    public abstract List<Trainee> getTrainees();

    public abstract Trainee getTraineeById(int traineeId);

    public abstract void removeTraineeDetails(int traineeId);
    public abstract List<Integer> validateAndAddTraineeDetails(Trainee trainee);

}
