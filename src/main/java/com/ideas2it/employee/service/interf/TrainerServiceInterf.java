package com.ideas2it.employee.service.interf;

import com.ideas2it.employee.customException.TrainerNotFoundException;
import com.ideas2it.employee.model.Trainer;

import java.util.List;
import java.util.Optional;

public abstract class TrainerServiceInterf {
    public abstract List<Trainer> getTrainers();
    public abstract Optional<Trainer> getTrainerId(int trainerId);

    public abstract void removeTrainerDetails(int trainerId);

    public abstract List<Integer> validateAndAddTrainerDetails(Trainer trainer);

}
