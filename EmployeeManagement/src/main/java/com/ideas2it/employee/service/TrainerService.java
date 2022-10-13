package com.ideas2it.employee.service;

import com.ideas2it.employee.model.Trainer;

import java.util.List;

public interface TrainerService {
    List<Trainer> getTrainers();
    Trainer getTrainerId(int trainerId);

    void removeTrainerDetails(int trainerId);

    List<Integer> validateAndAddTrainerDetails(Trainer trainer);

}
