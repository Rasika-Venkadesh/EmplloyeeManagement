package com.ideas2it.employee.service.interf;
import com.ideas2it.employee.dao.impl.TrainerDaoImpl;
import com.ideas2it.employee.dao.interf.TrainerDao_Interf;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.utility.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;


/**
 * <p>
 * TrainerService_interf is an interface which holds all abstract methods of TrainerService class.
 * </p>
 * @author Rasika Venkadesh
 * @version 1.0
 **/
public interface TrainerService_Interf {
    List<Trainer> getTrainers();
    void addTrainerDetails(Trainer trainer);
    boolean validateTrainerId(int trainerId);
    Trainer getTrainerById(int trainerId);
    void modifyTrainerDetails(Trainer trainer);
    void removeTrainerDetails(int trainerId);
    List<Integer> validateAndAddTrainerDetails(Trainer tempTrainer);
    boolean validateTrainerPhoneNumber(long phoneNumber,Trainer trainer);
    boolean validateTrainerEmailId(String emailId, Trainer trainer);
    boolean validateTrainerAadhar(long aadhar,Trainer trainer);
    
}

