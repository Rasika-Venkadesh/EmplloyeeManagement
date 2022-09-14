package com.ideas2it.employee.customException;
import java.lang.RuntimeException;


/**
 * <p>
 * TraineeNotFoundException class is to handle to exceptions, when TraineeId is invalid.
 * </p> 
 *
 * @author Rasika Venkadesh
 * @version 1.0
 **/
public class TraineeNotFoundException extends RuntimeException {
    public TraineeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}