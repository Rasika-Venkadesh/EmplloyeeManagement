package com.ideas2it.employee.dao.interf;

import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * TraineeDao_Interf interface holds abstract methods of TraineeDaoImpl class.
 * </p> 
 * @author Rasika Venkadesh
 * @version 1.0
 **/
public interface TraineeDao_Interf {   
    List<Trainee> retrieveTrainee();
    void insertTrainee(Trainee trainee);
    boolean validateTraineeById(int traineeId);
    Trainee retrieveTraineeById(int traineeId);
    void updateTrainee(Trainee trainee);
    boolean deleteTrainee(int traineeId);
}

