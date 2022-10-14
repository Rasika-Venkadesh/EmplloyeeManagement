package com.ideas2it.employee.service;

import com.ideas2it.employee.model.Trainee;

import java.util.List;


public interface TraineeService {
    public abstract List<Trainee> getTrainees();

    public abstract Trainee getTraineeById(int traineeId);

    public abstract void removeTraineeDetails(int traineeId);
    public abstract List<Integer> validateAndAddTraineeDetails(Trainee trainee);

}
