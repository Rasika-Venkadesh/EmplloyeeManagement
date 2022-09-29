package com.ideas2it.employee.dao.interf;

import com.ideas2it.employee.model.Trainer;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * TrainerDao_Interf interface holds TrainerDaoImpl class
 * </p> 
 * @author Rasika Venkadesh
 * @version 1.0
 **/
public interface TrainerDao_Interf {    
    List<Trainer> retrieveTrainer();
    void insertTrainer(Trainer trainer);
    boolean validateTrainerById(int trainerId);
    Trainer retrieveTrainerById(int trainerId);
    void updateTrainer(Trainer trainer);
    boolean deleteTrainer(int trainerId);
}

