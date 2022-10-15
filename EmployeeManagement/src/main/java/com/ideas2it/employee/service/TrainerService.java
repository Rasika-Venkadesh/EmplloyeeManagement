package com.ideas2it.employee.service;

import com.ideas2it.employee.dto.TrainerDto;
import com.ideas2it.employee.model.Trainer;

import java.util.List;

public interface TrainerService {
    List<TrainerDto> getTrainers();
    TrainerDto getTrainerId(int trainerId);

    void removeTrainerDetails(int trainerId);

    List<Integer> validateAndAddTrainerDetails(TrainerDto trainerDto);

}
