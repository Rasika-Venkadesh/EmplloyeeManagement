package com.ideas2it.employee.customException;
import java.lang.RuntimeException;


/**
 * <p>
 * TrainerNotFoundException class is to handle to exceptions, when TrainerId is invalid.
 * </p> 
 *
 * @author Rasika Venkadesh
 * @version 1.0
 **/
public class TrainerNotFoundException extends RuntimeException {
    public TrainerNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}