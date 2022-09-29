package com.ideas2it.employee.service.interf;
import com.ideas2it.employee.dao.impl.TraineeDaoImpl;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.utility.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;


/**
 * <p>
 * TraineeService_interf is an interface which holds all abstract methods of TraineeService class.
 * </p>
 * @author Rasika Venkadesh
 * @version 1.0
 **/
public interface TraineeService_Interf {
    List<Trainee> getTrainees();
    void addTraineeDetails(Trainee trainee);
    boolean validateTraineeId(int traineeId);
    Trainee getTraineeById(int traineeId);
    void modifyTraineeDetails(Trainee trainee);
    void removeTraineeDetails(int traineeId);
    List<Integer> validateAndAddTraineeDetails(Trainee tempTrainee);
    boolean validateTraineePhoneNumber(long phoneNumber,Trainee trainee);
    boolean validateTraineeEmailId(String emailId, Trainee trainee);
    boolean validateTraineeAadharId(long aadhar,Trainee trainee);
    
}

