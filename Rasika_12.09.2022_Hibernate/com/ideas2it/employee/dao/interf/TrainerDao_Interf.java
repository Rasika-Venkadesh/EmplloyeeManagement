package com.ideas2it.employee.dao.interf;
import com.ideas2it.employee.model.Trainer;

import java.util.ArrayList;
import java.util.List;

public interface TrainerDao_Interf {
    
    List<Trainer> retrieveTrainer();
    void insertTrainer(Trainer trainer);
    boolean validateTrainerById(int trainerId);
    Trainer retrieveTrainerById(int trainerId);
    void updateTrainer(Trainer updateTrainer, int updateChoice);
    void deleteTrainer(int trainerId);
}

